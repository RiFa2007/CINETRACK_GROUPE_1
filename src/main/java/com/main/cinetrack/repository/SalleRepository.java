package com.main.cinetrack.repository;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.main.cinetrack.database.DataBaseConnexion;
import com.main.cinetrack.exception.DaoException;
import com.main.cinetrack.model.Salle;

public class SalleRepository implements ISalleRepository {

    private static final String COLONNES = "id, nom, capacite, equipment";

    private static final String SQL_INSERT =
            "INSERT INTO salles (nom, capacite, equipment) VALUES (?, ?, ?)";

    private static final String SQL_UPDATE =
            "UPDATE salles SET nom = ?, capacite = ?, equipment = ? WHERE id = ?";

    private static final String SQL_DELETE =
            "DELETE FROM salles WHERE id = ?";

    private static final String SQL_FIND_BY_ID =
            "SELECT " + COLONNES + " FROM salles WHERE id = ?";

    private static final String SQL_FIND_ALL =
            "SELECT " + COLONNES + " FROM salles ORDER BY LOWER(nom)";

    /** Violation de contrainte CHECK (capacité > 200, plus de 3 équipements). */
    private static final String CONTRAINTE_CHECK = "23514";
    /** Violation d'unicité (si vous ajoutez UNIQUE sur le nom). */
    private static final String UNICITE = "23505";

    /** Maximum d'équipements autorisé par le schéma. */
    private static final int MAX_EQUIPEMENTS = 3;

    private final DataBaseConnexion db;

    public SalleRepository(DataBaseConnexion db) {
        this.db = db;
    }

    @Override
    public void ajouter(Salle salle) {
        try (Connection cn = db.getConnection();
             PreparedStatement ps = cn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, salle.getNom());
            ps.setInt(2, salle.getCapacite());
            setEquipement(cn, ps, 3, salle.getEquipement());
            ps.executeUpdate();

            try (ResultSet cles = ps.getGeneratedKeys()) {
                if (cles.next()) {
                    salle.setIdSalle(cles.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw traduire(e, "Ajout de la salle impossible : " + salle.getNom());
        } catch (ClassNotFoundException e) {
            throw new DaoException("Driver PostgreSQL introuvable", e);
        }
    }

    @Override
    public void modifier(Salle salle) {
        try (Connection cn = db.getConnection();
             PreparedStatement ps = cn.prepareStatement(SQL_UPDATE)) {

            ps.setString(1, salle.getNom());
            ps.setInt(2, salle.getCapacite());
            setEquipement(cn, ps, 3, salle.getEquipement());
            ps.setInt(4, salle.getIdSalle());

            if (ps.executeUpdate() == 0) {
                throw new DaoException("Aucune salle à modifier pour l'id " + salle.getIdSalle());
            }

        } catch (SQLException e) {
            throw traduire(e, "Modification de la salle impossible : id=" + salle.getIdSalle());
        } catch (ClassNotFoundException e) {
            throw new DaoException("Driver PostgreSQL introuvable", e);
        }
    }

    @Override
    public void supprimer(int id) {
        try (Connection cn = db.getConnection();
             PreparedStatement ps = cn.prepareStatement(SQL_DELETE)) {

            ps.setInt(1, id);

            if (ps.executeUpdate() == 0) {
                throw new DaoException("Aucune salle à supprimer pour l'id " + id);
            }

        } catch (SQLException e) {
            throw traduire(e, "Suppression de la salle impossible : id=" + id);
        } catch (ClassNotFoundException e) {
            throw new DaoException("Driver PostgreSQL introuvable", e);
        }
    }

    @Override
    public Salle findById(int id) {
        try (Connection cn = db.getConnection();
             PreparedStatement ps = cn.prepareStatement(SQL_FIND_BY_ID)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapper(rs) : null;
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new DaoException("Lecture de la salle impossible : id=" + id, e);
        }
    }

    @Override
    public List<Salle> findAll() {
        List<Salle> salles = new ArrayList<>();

        try (Connection cn = db.getConnection();
             PreparedStatement ps = cn.prepareStatement(SQL_FIND_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                salles.add(mapper(rs));
            }
            return salles;

        } catch (SQLException | ClassNotFoundException e) {
            throw new DaoException("Lecture des salles impossible", e);
        }
    }
    private void setEquipement(Connection cn, PreparedStatement ps, int index, String equipement)
            throws SQLException {

        if (equipement == null || equipement.trim().isEmpty()) {
            // NULL non typé : PostgreSQL déduit le type de la colonne equipment.
            ps.setObject(index, null);
            return;
        }

        List<String> elements = new ArrayList<>();
        for (String morceau : equipement.split(",")) {
            String valeur = morceau.trim();
            if (!valeur.isEmpty()) {
                elements.add(valeur);
            }
        }

        if (elements.isEmpty()) {
            ps.setObject(index, null);
            return;
        }

        if (elements.size() > MAX_EQUIPEMENTS) {
            throw new DaoException("3 équipements maximum par salle (reçu : " + elements.size() + ")");
        }

        String[] valeurs = elements.toArray(new String[0]);
        Array tableau = cn.createArrayOf("varchar", valeurs);
        ps.setArray(index, tableau);
    }

    /** Convertit la ligne courante du ResultSet en objet Salle. */
    private Salle mapper(ResultSet rs) throws SQLException {
        return new Salle(
                rs.getInt("id"),
                rs.getString("nom"),
                rs.getInt("capacite"),
                lireEquipement(rs.getArray("equipment"))
        );
    }

    /** Transforme le varchar[] de PostgreSQL en « Projecteur, Son ». */
    private String lireEquipement(Array tableau) throws SQLException {
        if (tableau == null) {
            return null;
        }
        String[] valeurs = (String[]) tableau.getArray();
        return valeurs == null ? null : String.join(", ", Arrays.asList(valeurs));
    }

    /** Traduit une erreur SQL en message compréhensible par l'utilisateur. */
    private DaoException traduire(SQLException e, String action) {
        if (CONTRAINTE_CHECK.equals(e.getSQLState())) {
            return new DaoException(
                    action + " — valeurs refusées par la base (capacité ≤ 200, 3 équipements maximum)", e);
        }
        if (UNICITE.equals(e.getSQLState())) {
            return new DaoException(action + " — ce nom de salle est déjà utilisé", e);
        }
        return new DaoException(action, e);
    }
}
package com.main.cinetrack.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.main.cinetrack.database.DataBaseConnexion;
import com.main.cinetrack.exception.DaoException;
import com.main.cinetrack.model.Film;

public class FilmRepository implements IFilmRepository {

    private static final String COLONNES = "id, titre, genre, duree, note, synopsis";

    private static final String SQL_INSERT =
            "INSERT INTO films (titre, genre, duree, note, synopsis) VALUES (?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE =
            "UPDATE films SET titre = ?, genre = ?, duree = ?, note = ?, synopsis = ? WHERE id = ?";

    private static final String SQL_DELETE =
            "DELETE FROM films WHERE id = ?";

    private static final String SQL_FIND_BY_ID =
            "SELECT " + COLONNES + " FROM films WHERE id = ?";

    private static final String SQL_FIND_ALL =
            "SELECT " + COLONNES + " FROM films ORDER BY LOWER(titre)";

    private static final String SQL_SEARCH =
            "SELECT " + COLONNES + " FROM films "
          + "WHERE LOWER(titre) LIKE ? "
          + "  AND (? = '' OR LOWER(genre) = ?) "
          + "ORDER BY LOWER(titre)";

    /** Violation de contrainte CHECK (durée, note). */
    private static final String CONTRAINTE_CHECK = "23514";

    private final DataBaseConnexion db;

    public FilmRepository(DataBaseConnexion db) {
        this.db = db;
    }

    @Override
    public void ajouter(Film film) {
        try (Connection cn = db.getConnection();
             PreparedStatement ps = cn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {

            remplirParametres(ps, film);
            ps.executeUpdate();

            try (ResultSet cles = ps.getGeneratedKeys()) {
                if (cles.next()) {
                    film.setIdFilm(cles.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw traduire(e, "Ajout du film impossible : " + film.getTitre());
        } catch (ClassNotFoundException e) {
            throw new DaoException("Driver PostgreSQL introuvable", e);
        }
    }

    @Override
    public void modifier(Film film) {
        try (Connection cn = db.getConnection();
             PreparedStatement ps = cn.prepareStatement(SQL_UPDATE)) {

            remplirParametres(ps, film);
            ps.setInt(6, film.getIdFilm());

            if (ps.executeUpdate() == 0) {
                throw new DaoException("Aucun film à modifier pour l'id " + film.getIdFilm());
            }

        } catch (SQLException e) {
            throw traduire(e, "Modification du film impossible : id=" + film.getIdFilm());
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
                throw new DaoException("Aucun film à supprimer pour l'id " + id);
            }

        } catch (SQLException e) {
            throw traduire(e, "Suppression du film impossible : id=" + id);
        } catch (ClassNotFoundException e) {
            throw new DaoException("Driver PostgreSQL introuvable", e);
        }
    }

    @Override
    public Film findById(int id) {
        try (Connection cn = db.getConnection();
             PreparedStatement ps = cn.prepareStatement(SQL_FIND_BY_ID)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapper(rs) : null;
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new DaoException("Lecture du film impossible : id=" + id, e);
        }
    }

    @Override
    public List<Film> findAll() {
        List<Film> films = new ArrayList<>();

        try (Connection cn = db.getConnection();
             PreparedStatement ps = cn.prepareStatement(SQL_FIND_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                films.add(mapper(rs));
            }
            return films;

        } catch (SQLException | ClassNotFoundException e) {
            throw new DaoException("Lecture du catalogue impossible", e);
        }
    }

    public List<Film> rechercher(String texte, String genre) {
        String motif = "%" + (texte == null ? "" : texte.trim().toLowerCase()) + "%";
        String genreFiltre = (genre == null ? "" : genre.trim().toLowerCase());

        List<Film> films = new ArrayList<>();

        try (Connection cn = db.getConnection();
             PreparedStatement ps = cn.prepareStatement(SQL_SEARCH)) {

            ps.setString(1, motif);
            ps.setString(2, genreFiltre);
            ps.setString(3, genreFiltre);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    films.add(mapper(rs));
                }
            }
            return films;

        } catch (SQLException | ClassNotFoundException e) {
            throw new DaoException("Recherche de films impossible", e);
        }
    }

    // ------------------------------------------------------------------
    // Helpers privés
    // ------------------------------------------------------------------

    /** Renseigne les 5 paramètres communs à l'INSERT et à l'UPDATE. */
    private void remplirParametres(PreparedStatement ps, Film film) throws SQLException {
        ps.setString(1, film.getTitre());
        ps.setString(2, film.getGenre());
        ps.setInt(3, film.getDuree());
        ps.setDouble(4, film.getNote());
        ps.setString(5, film.getSynopsis());
    }

    /** Convertit la ligne courante du ResultSet en objet Film. */
    private Film mapper(ResultSet rs) throws SQLException {
        return new Film(
                rs.getInt("id"),
                rs.getString("titre"),
                rs.getString("genre"),
                rs.getInt("duree"),
                rs.getDouble("note"),
                rs.getString("synopsis"),
                null                                    // pas de colonne image dans le schéma
        );
    }

    /** Traduit une erreur SQL en message compréhensible par l'utilisateur. */
    private DaoException traduire(SQLException e, String action) {
        if (CONTRAINTE_CHECK.equals(e.getSQLState())) {
            return new DaoException(
                    action + " — valeurs refusées par la base (durée entre 0 et 200 min, note entre 0 et 10)", e);
        }
        return new DaoException(action, e);
    }
}
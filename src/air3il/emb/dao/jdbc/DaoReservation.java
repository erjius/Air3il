package air3il.emb.dao.jdbc;

import air3il.commun.dao.IDaoReservation;
import air3il.commun.dto.DtoPlace;
import air3il.commun.dto.DtoReservation;
import air3il.commun.dto.DtoVol;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ARSENE
 */
public class DaoReservation implements IDaoReservation {

    // Champs
    private final Connection connection;
    private int idReservation;

    // Constructeur
    public DaoReservation(ManagerDao managerDao) {
        connection = managerDao.getConnection();
    }

    /**
     *
     * @param reservation
     */
    @Override
    public void inserer(DtoReservation reservation) {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "";
            stmt = connection.prepareStatement(sql,
                    new String[]{"IReservation"});
            stmt.setString(1, reservation.getNom_pass());
            stmt.setString(2, reservation.getPrenom_pass());
            stmt.executeUpdate();

            rs = stmt.getGeneratedKeys();
            rs.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

//		Alert alert = new Alert( AlertType.WARNING);
//		alert.setHeaderText( "Id : "  + reservation.getId() );
//		alert.showAndWait();
    }

    @Override
    public void modifier(DtoReservation reservation) {

        PreparedStatement stmt = null;

        try {
            String sql = "";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, reservation.getNom_pass());
            stmt.setString(2, reservation.getPrenom_pass());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public void supprimer(DtoVol vol, DtoPlace place) {

        String sql;
        PreparedStatement stmt = null;

        // Supprime les enregistrements dans la table fille
//		try {
//			sql = "DELETE FROM telephone WHERE IdReservation= ? ";
//			stmt = connection.prepareStatement(sql);
//			stmt.setInt( 1, idReservation);
//			stmt.executeUpdate();
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		} finally {
//			try {
//				if (stmt != null) stmt.close();
//			} catch (SQLException e) {
//				throw new RuntimeException(e);
//			}
//		}
        //DtoReservation reservation = retrouver( idReservation);
        /*for ( DtoTelephone telephone : reservation.getTelephones() ) {
			daoTelephone.supprimer( telephone.getId() );
		}*/
        // Supprime l'enregistrement dans la table principale
        try {
            sql = " ";
            stmt = connection.prepareStatement(sql);

            stmt.setInt(1, idReservation);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public DtoReservation retrouver(DtoVol vol, DtoPlace place) {

        DtoReservation reservation = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            String sql = "";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idReservation);
            rs = stmt.executeQuery();

            if (rs.next()) {
                reservation = construirePersonne(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return reservation;
    }

    @Override
    public List<DtoReservation> listerTout() {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<DtoReservation> reservations = new ArrayList<>();

        try {

            String sql = "";
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                reservations.add(construirePersonne(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return reservations;
    }

    // Méthodes auxiliaires
    private DtoReservation construirePersonne(ResultSet rs) throws SQLException {
        DtoReservation reservation = new DtoReservation();
        reservation.setNom_pass(rs.getString("Nom"));
        reservation.setPrenom_pass(rs.getString("Prenom"));
        return reservation;
    }

}

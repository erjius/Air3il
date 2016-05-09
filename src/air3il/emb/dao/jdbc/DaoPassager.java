package air3il.emb.dao.jdbc;

import air3il.commun.dao.IDaoPassager;
import air3il.commun.dto.DtoPassager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoPassager implements IDaoPassager {

	
	// Champs

	private final Connection		connection;
	
	
	// Constructeur

    public DaoPassager( ManagerDao managerDao ) {
		connection = managerDao.getConnection();
	}

	
	// Actions

	@Override
	public void inserer(DtoPassager passager) {

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			String sql = "INSERT INTO passager ( Nom, Prenom ) VALUES ( ?, ? )";
			stmt = connection.prepareStatement(sql,
					new String[] { "IPassager" } );
			stmt.setString(	1, passager.getNom() );
			stmt.setString(	2, passager.getPrenom() );
			stmt.executeUpdate();

			rs = stmt.getGeneratedKeys();
			rs.next();
			int id = rs.getInt(1);
			passager.setId( id );

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		
//		Alert alert = new Alert( AlertType.WARNING);
//		alert.setHeaderText( "Id : "  + passager.getId() );
//		alert.showAndWait();
		

	}
 
	@Override
	public void modifier(DtoPassager passager) {

		PreparedStatement stmt = null;

		try {
			String sql = "UPDATE passager SET Nom = ?, Prenom = ? WHERE IdPassager =  ?";
			stmt = connection.prepareStatement( sql );
			stmt.setString(	1, passager.getNom() );
			stmt.setString(	2, passager.getPrenom() );
			stmt.setInt(	3, passager.getId() );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (stmt != null) stmt.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

		
		
	}

	@Override
	public void supprimer(int idPassager) {

		String sql;
		PreparedStatement stmt = null;

		// Supprime les enregistrements dans la table fille
//		try {
//			sql = "DELETE FROM telephone WHERE IdPassager = ? ";
//			stmt = connection.prepareStatement(sql);
//			stmt.setInt( 1, idPassager );
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
		
		
		// Supprime l'enregistrement dans la table principale
		try {
			sql = "DELETE FROM passager WHERE IdPassager = ? ";
			stmt = connection.prepareStatement(sql);
			stmt.setInt( 1, idPassager );
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (stmt != null) stmt.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public DtoPassager retrouver(int idPassager) {

		DtoPassager passager = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

        try {

            String sql = "SELECT * FROM passager WHERE IdPassager = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idPassager);
            rs = stmt.executeQuery();

            if ( rs.next() ) {
                passager = construirePassager(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return passager;
	}

	@Override
	public List<DtoPassager> listerTout() {

		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<DtoPassager> passagers = new ArrayList<>();

		try {

			String sql = "SELECT * FROM passager ORDER BY Nom, Prenom";
			stmt = connection.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				passagers.add( construirePassager(rs) );
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

		return passagers;
	}
	
	
	// Méthodes auxiliaires
	
	private DtoPassager construirePassager( ResultSet rs ) throws SQLException {
		DtoPassager passager = new DtoPassager();
		passager.setId(rs.getInt("IdPassager"));
		passager.setNom(rs.getString("Nom"));
		passager.setPrenom(rs.getString("Prenom"));
                passager.setTel(rs.getString("Tel"));
                passager.setEmail(rs.getString("Email"));
		return passager;
	}

}

    import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Regledaoimpl implements RegleIDAO {

	@Override
	public Regle find(int id) {
		Regle regle = null;
		Connection connection = Singleconnection.getConnection();
		String query = "SELECT * FROM Regles WHERE id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				int idAppareil = rs.getInt("idAppareil");
				String conditionString = rs.getString("conditionString");
				String action = rs.getString("action");
				int idUtilisateur = rs.getInt("idUtilisateur");
				regle = new Regle(id, idAppareil, conditionString, action, idUtilisateur);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return regle;
	}

	@Override
	public List<Regle> findAll() {
		List<Regle> regles = new ArrayList<>();
		
		String query = "SELECT * FROM Regles";
		try ( Connection connection = Singleconnection.getConnection();
				Statement statement = connection.createStatement(); ResultSet rs = statement.executeQuery(query)) {
			while (rs.next()) {
				int id = rs.getInt("id");
				int idAppareil = rs.getInt("idAppareil");
				String conditionString = rs.getString("conditionString");
				String action = rs.getString("action");
				int idUtilisateur = rs.getInt("idUtilisateur");
				Regle regle = new Regle(id, idAppareil, conditionString, action, idUtilisateur);
				regles.add(regle);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return regles;
	}

	@Override
	public void save(Regle regle) {
		Connection connection = Singleconnection.getConnection();
		String query = "INSERT INTO Regles (id, idAppareil,condition_column, action_column,, idUtilisateur) VALUES (?, ?, ?, ?, ?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, regle.getId());
			preparedStatement.setInt(2, regle.getIdAppareil());
			preparedStatement.setString(3, regle.getCondition_column());
			preparedStatement.setString(4, regle.getAction_column());
			preparedStatement.setInt(5, regle.getIdUtilisateur());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Regle regle) {
	    String query = "UPDATE Regles SET idAppareil = ?, conditionString = ?, action = ?, idUtilisateur = ? WHERE ID = ?";
	    
	    try (Connection connection = Singleconnection.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	        preparedStatement.setInt(1, regle.getIdAppareil());
	        preparedStatement.setString(2, regle.getCondition_column());
	        preparedStatement.setString(3, regle.getAction_column());
	        preparedStatement.setInt(4, regle.getIdUtilisateur());
	        preparedStatement.setInt(5, regle.getId());

	        int rowsAffected = preparedStatement.executeUpdate();
	        System.out.println("Rows affected: " + rowsAffected);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}



	@Override
	public void delete(Regle regle) {
		Connection connection = Singleconnection.getConnection();
		String query = "DELETE FROM Regles WHERE id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, regle.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

    

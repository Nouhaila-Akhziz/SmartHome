import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Regledaoimpl implements RegleIDAO {

	@Override
	public Regle find(int id) {
		Regle regle = null;
		Connection connection = Singleconnection.getConnection();
		String query = "SELECT * FROM Regles WHERE ID = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				int idAppareil = rs.getInt("IDAppareil");
				String condition = rs.getString("Condition");
				String action = rs.getString("Action");
				int idUtilisateur = rs.getInt("IDUtilisateur");
				regle = new Regle(id, idAppareil, condition, action, idUtilisateur);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return regle;
	}

	@Override
	public List<Regle> findAll() {
		List<Regle> regles = new ArrayList<>();
		Connection connection = Singleconnection.getConnection();
		String query = "SELECT * FROM Regles";
		try (Statement statement = connection.createStatement(); ResultSet rs = statement.executeQuery(query)) {
			while (rs.next()) {
				int id = rs.getInt("ID");
				int idAppareil = rs.getInt("IDAppareil");
				String condition = rs.getString("Condition");
				String action = rs.getString("Action");
				int idUtilisateur = rs.getInt("IDUtilisateur");
				Regle regle = new Regle(id, idAppareil, condition, action, idUtilisateur);
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
		String query = "INSERT INTO Regles (ID, IDAppareil, Condition, Action, IDUtilisateur) VALUES (?, ?, ?, ?, ?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, regle.getId());
			preparedStatement.setInt(2, regle.getIdAppareil());
			preparedStatement.setString(3, regle.getCondition());
			preparedStatement.setString(4, regle.getAction());
			preparedStatement.setInt(5, regle.getIdUtilisateur());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Regle regle) {
		Connection connection = Singleconnection.getConnection();
		String query = "UPDATE Regles SET IDAppareil = ?, Condition = ?, Action = ?, IDUtilisateur = ? WHERE ID = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, regle.getIdAppareil());
			preparedStatement.setString(2, regle.getCondition());
			preparedStatement.setString(3, regle.getAction());
			preparedStatement.setInt(4, regle.getIdUtilisateur());
			preparedStatement.setInt(5, regle.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Regle regle) {
		Connection connection = Singleconnection.getConnection();
		String query = "DELETE FROM Regles WHERE ID = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, regle.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

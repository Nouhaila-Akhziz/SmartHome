import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Accesdaoimpl implements AccesIDAO {

	@Override
	public Acces find(int id) {
		Acces acces = null;
		Connection connection = Singleconnection.getConnection();
		String query = "SELECT * FROM Accès WHERE ID = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				int idUtilisateur = rs.getInt("IDUtilisateur");
				int idAppareil = rs.getInt("IDAppareil");
				String niveauAcces = rs.getString("NiveauAccès");

				acces = new Acces(id, idUtilisateur, idAppareil, niveauAcces);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return acces;
	}

	@Override
	public List<Acces> findAll() {
		List<Acces> accesList = new ArrayList<>();
		Connection connection = Singleconnection.getConnection();
		String query = "SELECT * FROM Accès";

		try (Statement statement = connection.createStatement(); ResultSet rs = statement.executeQuery(query)) {

			while (rs.next()) {
				int id = rs.getInt("ID");
				int idUtilisateur = rs.getInt("IDUtilisateur");
				int idAppareil = rs.getInt("IDAppareil");
				String niveauAcces = rs.getString("NiveauAccès");

				Acces acces = new Acces(id, idUtilisateur, idAppareil, niveauAcces);
				accesList.add(acces);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accesList;
	}

	@Override
	public void save(Acces acces) {
		Connection connection = Singleconnection.getConnection();
		String query = "INSERT INTO Accès (ID, IDUtilisateur, IDAppareil, NiveauAccès) VALUES (?, ?, ?, ?)";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, acces.getId()); // Assuming there's a getId() method in acces class
			preparedStatement.setInt(2, acces.getIdUtilisateur());
			preparedStatement.setInt(3, acces.getIdAppareil());
			preparedStatement.setString(4, acces.getNiveauAcces());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Acces acces) {
		Connection connection = Singleconnection.getConnection();
		String query = "UPDATE Accès SET IDUtilisateur=?, IDAppareil=?, NiveauAccès=? WHERE ID=?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, acces.getIdUtilisateur());
			preparedStatement.setInt(2, acces.getIdAppareil());
			preparedStatement.setString(3, acces.getNiveauAcces());
			preparedStatement.setInt(4, acces.getId());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Acces acces) {
		Connection connection = Singleconnection.getConnection();
		String query = "DELETE FROM Accès WHERE ID=?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, acces.getId());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Implement the save, update, and delete methods similarly...
}

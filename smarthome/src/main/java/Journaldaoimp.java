import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Journaldaoimp implements JournalIDAO {

	@Override
	public Journal find(int id) {
		Journal journal = null;
		Connection connection = Singleconnection.getConnection();
		String query = "SELECT * FROM Journaux WHERE ID = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				int idAppareil = rs.getInt("IDAppareil");
				String typeEvenement = rs.getString("TypeEvenement");
				Timestamp timestamp = rs.getTimestamp("DateHeure");
				Date dateHeure = new Date(timestamp.getTime());
				journal = new Journal(id, idAppareil, typeEvenement, dateHeure);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return journal;
	}

	@Override
	public List<Journal> findAll() {
		List<Journal> journaux = new ArrayList<>();
		Connection connection = Singleconnection.getConnection();
		String query = "SELECT * FROM Journaux";
		try (Statement statement = connection.createStatement(); ResultSet rs = statement.executeQuery(query)) {
			while (rs.next()) {
				int id = rs.getInt("ID");
				int idAppareil = rs.getInt("IDAppareil");
				String typeEvenement = rs.getString("TypeEvenement");
				Timestamp timestamp = rs.getTimestamp("DateHeure");
				Date dateHeure = new Date(timestamp.getTime());
				Journal journal = new Journal(id, idAppareil, typeEvenement, dateHeure);
				journaux.add(journal);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return journaux;
	}

	@Override
	public void save(Journal journal) {
		Connection connection = Singleconnection.getConnection();
		String query = "INSERT INTO Journaux (ID, IDAppareil, TypeEvenement, DateHeure) VALUES (?, ?, ?, ?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, journal.getId());
			preparedStatement.setInt(2, journal.getIdAppareil());
			preparedStatement.setString(3, journal.getTypeEvenement());
			preparedStatement.setTimestamp(4, new Timestamp(journal.getDateHeure().getTime()));
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Journal journal) {
		Connection connection = Singleconnection.getConnection();
		String query = "UPDATE Journaux SET IDAppareil = ?, TypeEvenement = ?, DateHeure = ? WHERE ID = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, journal.getIdAppareil());
			preparedStatement.setString(2, journal.getTypeEvenement());
			preparedStatement.setTimestamp(3, new Timestamp(journal.getDateHeure().getTime()));
			preparedStatement.setInt(4, journal.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Journal journal) {
		Connection connection = Singleconnection.getConnection();
		String query = "DELETE FROM Journaux WHERE ID = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, journal.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

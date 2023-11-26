import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Appareilsdaoimp implements AppareilsIDAO {

	@Override
	public Appareils find(int id) {
		Appareils appareil = null;
		try (Connection connection = Singleconnection.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("SELECT * FROM Appareils WHERE ID = ?")) {

			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				String nomAppareil = rs.getString("NomAppareil");
				String typeAppareil = rs.getString("TypeAppareil");
				String étatAppareil = rs.getString("ÉtatAppareil");
				int idUtilisateur = rs.getInt("IDUtilisateur");
				appareil = new Appareils(id, nomAppareil, typeAppareil, étatAppareil, idUtilisateur);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return appareil;
	}

	@Override
	public List<Appareils> findAll() {
		List<Appareils> appareils = new ArrayList<>();
		try (Connection connection = Singleconnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Appareils");
				ResultSet rs = preparedStatement.executeQuery()) {

			while (rs.next()) {
				int id = rs.getInt("ID");
				String nomAppareil = rs.getString("NomAppareil");
				String typeAppareil = rs.getString("TypeAppareil");
				String étatAppareil = rs.getString("ÉtatAppareil");
				int idUtilisateur = rs.getInt("IDUtilisateur");
				Appareils appareil = new Appareils(id, nomAppareil, typeAppareil, étatAppareil, idUtilisateur);
				appareils.add(appareil);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return appareils;
	}

	@Override
	public void save(Appareils appareil) {
		try (Connection connection = Singleconnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(
						"INSERT INTO Appareils (NomAppareil, TypeAppareil, ÉtatAppareil, IDUtilisateur) VALUES (?, ?, ?, ?)")) {

			preparedStatement.setString(1, appareil.getNomAppareil());
			preparedStatement.setString(2, appareil.getTypeAppareil());
			preparedStatement.setString(3, appareil.getÉtatAppareil());
			preparedStatement.setInt(4, appareil.getIdUtilisateur());
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Appareils appareil) {
		try (Connection connection = Singleconnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(
						"UPDATE Appareils SET NomAppareil = ?, TypeAppareil = ?, ÉtatAppareil = ?, IDUtilisateur = ? WHERE ID = ?")) {

			preparedStatement.setString(1, appareil.getNomAppareil());
			preparedStatement.setString(2, appareil.getTypeAppareil());
			preparedStatement.setString(3, appareil.getÉtatAppareil());
			preparedStatement.setInt(4, appareil.getIdUtilisateur());
			preparedStatement.setInt(5, appareil.getId());
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Appareils appareil) {
		try (Connection connection = Singleconnection.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("DELETE FROM Appareils WHERE ID = ?")) {

			preparedStatement.setInt(1, appareil.getId());
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

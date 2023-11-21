import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Consommationenergiedaoimpl implements ConsommationenergieIDAO {

	@Override
	public Consommationenergie find(int id) {
		Consommationenergie consommationEnergie = null;
		Connection connection = Singleconnection.getConnection(); // Corrected method name
		String query = "SELECT * FROM Consommation_Energie WHERE ID = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				int idAppareil = rs.getInt("IDAppareil");
				double consommationWatts = rs.getDouble("ConsommationWatts");
				Timestamp timestamp = rs.getTimestamp("DateHeure");
				Date dateHeure = new Date(timestamp.getTime());
				consommationEnergie = new Consommationenergie(id, idAppareil, consommationWatts, dateHeure);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return consommationEnergie;
	}

	@Override
	public List<Consommationenergie> findAll() {
		List<Consommationenergie> consumptions = new ArrayList<>();
		Connection connection = Singleconnection.getConnection();
		String query = "SELECT * FROM Consommation_Energie";
		try (Statement statement = connection.createStatement(); ResultSet rs = statement.executeQuery(query)) {
			while (rs.next()) {
				int id = rs.getInt("ID");
				int idAppareil = rs.getInt("IDAppareil");
				double consommationWatts = rs.getDouble("ConsommationWatts");
				Timestamp timestamp = rs.getTimestamp("DateHeure");
				Date dateHeure = new Date(timestamp.getTime());
				Consommationenergie consommationEnergie = new Consommationenergie(id, idAppareil, consommationWatts,
						dateHeure);
				consumptions.add(consommationEnergie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return consumptions;
	}

	@Override
	public void save(Consommationenergie consommationEnergie) {
		Connection connection = Singleconnection.getConnection(); // Corrected method name
		String query = "INSERT INTO Consommation_Energie (ID, IDAppareil, ConsommationWatts, DateHeure) VALUES (?, ?, ?, ?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, consommationEnergie.getId());
			preparedStatement.setInt(2, consommationEnergie.getIdAppareil());
			preparedStatement.setDouble(3, consommationEnergie.getConsommationWatts());
			preparedStatement.setTimestamp(4, new Timestamp(consommationEnergie.getDateHeure().getTime()));
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Consommationenergie consommationEnergie) { // Corrected parameter type
		Connection connection = Singleconnection.getConnection(); // Corrected method name
		String query = "UPDATE Consommation_Energie SET IDAppareil = ?, ConsommationWatts = ?, DateHeure = ? WHERE ID = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, consommationEnergie.getIdAppareil());
			preparedStatement.setDouble(2, consommationEnergie.getConsommationWatts());
			preparedStatement.setTimestamp(3, new Timestamp(consommationEnergie.getDateHeure().getTime()));
			preparedStatement.setInt(4, consommationEnergie.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Consommationenergie consommationEnergie) {
		Connection connection = Singleconnection.getConnection(); // Corrected method name
		String query = "DELETE FROM Consommation_Energie WHERE ID = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, consommationEnergie.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

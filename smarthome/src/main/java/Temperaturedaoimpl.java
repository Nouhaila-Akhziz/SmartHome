import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Temperaturedaoimpl implements TemperatureIDAO {

	private Connection getConnection() {
		return Singleconnection.getConnection();
	}

	@Override
	public Temperature find(int id) {
		String query = "SELECT * FROM Temperature WHERE ID = ?";
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				return extractTemperatureFromResultSet(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Temperature> findAll() {
		List<Temperature> temperatures = new ArrayList<>();
		String query = "SELECT * FROM Temperature";

		try (Connection connection = getConnection();
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(query)) {
			while (rs.next()) {
				temperatures.add(extractTemperatureFromResultSet(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temperatures;
	}

	@Override
	public void save(Temperature temperature) {
		String query = "INSERT INTO Temperature (ID, IDCapteurTemperature, Temperature, DateHeure) VALUES (?, ?, ?, ?)";
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			setPreparedStatementForTemperature(preparedStatement, temperature);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Temperature temperature) {
		String query = "UPDATE Temperature SET IDCapteurTemperature = ?, Temperature = ?, DateHeure = ? WHERE ID = ?";
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			setPreparedStatementForTemperature(preparedStatement, temperature);
			preparedStatement.setInt(4, temperature.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Temperature temperature) {
		String query = "DELETE FROM Temperature WHERE ID = ?";
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, temperature.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Temperature extractTemperatureFromResultSet(ResultSet rs) throws SQLException {
		int id = rs.getInt("ID");
		int idCapteurTemperature = rs.getInt("IDCapteurTemperature");
		double temperatureValue = rs.getDouble("Temperature");
		Timestamp timestamp = rs.getTimestamp("DateHeure");
		Date dateHeure = new Date(timestamp.getTime());
		return new Temperature(id, idCapteurTemperature, temperatureValue, dateHeure);
	}

	private void setPreparedStatementForTemperature(PreparedStatement ps, Temperature temperature) throws SQLException {
		ps.setInt(1, temperature.getId());
		ps.setInt(2, temperature.getIdCapteurTemperature());
		ps.setDouble(3, temperature.getTemperatureValue());
		ps.setTimestamp(4, new Timestamp(temperature.getDateHeure().getTime()));
	}
}

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Temperaturedaoimpl implements TemperatureIDAO {

    // Use composition instead of inheritance for database connection

    // Default constructor
    Temperaturedaoimpl() {}

    @Override
    public Temperature find(int id) {
    	Temperature temperature = null;
        String query = "SELECT * FROM Temperature WHERE ID = ?";
        try (Connection connection = Singleconnection.getConnection();
        		PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
            	temperature = mapResultSetToTemperature(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception as needed
        }
        return temperature;
    }
    private Temperature mapResultSetToTemperature(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int idCapteurTemperature = rs.getInt("idCapteurTemperature");
        double temperatureValue = rs.getDouble("TemperatureValue");
        Timestamp dateHeure = rs.getTimestamp("dateHeure");

        return new Temperature(id, idCapteurTemperature, temperatureValue, dateHeure);
    }


    @Override
    public List<Temperature> findAll() {
        List<Temperature> temperatures = new ArrayList<>();
        String query = "SELECT * FROM Temperature";
        try (Connection connection = Singleconnection.getConnection();
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
        String query = "INSERT INTO Temperature (idCapteurTemperature, Temperature, dateHeure) VALUES (?, ?, ?)";
        try (Connection connection = Singleconnection.getConnection();
        		PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            setPreparedStatementForTemperature(preparedStatement, temperature);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Temperature temperature) {
        String query = "UPDATE Temperature SET idCapteurTemperature = ?, Temperature = ?, dateHeure = ? WHERE id = ?";
        try (Connection connection = Singleconnection.getConnection();
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
        String query = "DELETE FROM Temperature WHERE id = ?";
        try (Connection connection = Singleconnection.getConnection();
        		PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, temperature.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Temperature extractTemperatureFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int idCapteurTemperature = rs.getInt("idCapteurTemperature");
        double temperatureValue = rs.getDouble("Temperature");
        Timestamp timestamp = rs.getTimestamp("dateHeure");
        Date dateHeure = new Date(timestamp.getTime());
        return new Temperature(id, idCapteurTemperature, temperatureValue, dateHeure);
    }

    private void setPreparedStatementForTemperature(PreparedStatement ps, Temperature temperature) throws SQLException {
        ps.setInt(1, temperature.getIdCapteurTemperature());
        ps.setDouble(2, temperature.getTemperatureValue());
        ps.setTimestamp(3, new Timestamp(temperature.getDateHeure().getTime()));
    }

    // Rest of the code remains unchanged...


	
	public Temperature findLatestTemperature() {
	    int maxId = getMaxAppareilId(); // Assuming you have a method to get the maximum ID from Appareil

	    if (maxId > 0) {
	        return find(maxId);
	    } else {
	        return null; // or handle the case where no max ID is found
	    }
	}

	public int getMaxAppareilId() {
	    String query = "SELECT MAX(ID) FROM Appareils";
	    try (Connection connection = Singleconnection.getConnection();
	         Statement statement = connection.createStatement();
	         ResultSet rs = statement.executeQuery(query)) {
	        if (rs.next()) {
	            return (rs.getInt(1)-1); // Assuming the first column in the result set is the max ID
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return 0; // Default value if no max ID is found
	}
	public double findTemperatureValue(int id) {
		String query = "SELECT TemperatureValue FROM Temperature WHERE id = ?";

        
        try (Connection connection = Singleconnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs != null && rs.next()) {
                    return rs.getDouble("Temperature");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0; // Default value if no temperature is found or an error occurs
    }





}

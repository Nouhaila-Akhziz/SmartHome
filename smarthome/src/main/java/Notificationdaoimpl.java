import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Notificationdaoimpl implements NotificationIDAO {

	@Override
	public Notification find(int id) {
		Notification notification = null;
		Connection connection = Singleconnection.getConnection();
		String query = "SELECT * FROM Notifications WHERE ID = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				int idUtilisateur = rs.getInt("IDUtilisateur");
				String message = rs.getString("Message");
				Timestamp timestamp = rs.getTimestamp("DateHeure");
				Date dateHeure = new Date(timestamp.getTime());
				notification = new Notification(id, idUtilisateur, message, dateHeure);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return notification;
	}

	@Override
	public List<Notification> findAll() {
		List<Notification> notifications = new ArrayList<>();
		Connection connection = Singleconnection.getConnection();
		String query = "SELECT * FROM Notifications";
		try (Statement statement = connection.createStatement(); ResultSet rs = statement.executeQuery(query)) {
			while (rs.next()) {
				int id = rs.getInt("ID");
				int idUtilisateur = rs.getInt("IDUtilisateur");
				String message = rs.getString("Message");
				Timestamp timestamp = rs.getTimestamp("DateHeure");
				Date dateHeure = new Date(timestamp.getTime());
				Notification notification = new Notification(id, idUtilisateur, message, dateHeure);
				notifications.add(notification);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return notifications;
	}

	@Override
	public void save(Notification notification) {
		Connection connection = Singleconnection.getConnection();
		String query = "INSERT INTO Notifications (ID, IDUtilisateur, Message, DateHeure) VALUES (?, ?, ?, ?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, notification.getId());
			preparedStatement.setInt(2, notification.getIdUtilisateur());
			preparedStatement.setString(3, notification.getMessage());
			preparedStatement.setTimestamp(4, new Timestamp(notification.getDateHeure().getTime()));
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Notification notification) {
		Connection connection = Singleconnection.getConnection();
		String query = "UPDATE Notifications SET IDUtilisateur = ?, Message = ?, DateHeure = ? WHERE ID = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, notification.getIdUtilisateur());
			preparedStatement.setString(2, notification.getMessage());
			preparedStatement.setTimestamp(3, new Timestamp(notification.getDateHeure().getTime()));
			preparedStatement.setInt(4, notification.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Notification notification) {
		Connection connection = Singleconnection.getConnection();
		String query = "DELETE FROM Notifications WHERE ID = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, notification.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

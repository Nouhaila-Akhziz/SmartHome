import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Utilisateurdaoimpl implements UtilisateurIDAO {
	Connection connection; // Remove the explicit initialization

	public Utilisateurdaoimpl() {
	    this.connection = Singleconnection.getConnection();
	}


    

    @Override
    public Utilisateur find(int id) {
        Utilisateur utilisateur = null;
        String query = "SELECT * FROM Utilisateurs WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                utilisateur = mapResultSetToUtilisateur(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception as needed
        }
        return utilisateur;
    }

    @Override
    public List<Utilisateur> findAll() {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String query = "SELECT * FROM Utilisateurs";
        try (Statement statement = connection.createStatement(); ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {
                Utilisateur utilisateur = mapResultSetToUtilisateur(rs);
                utilisateurs.add(utilisateur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception as needed
        }
        return utilisateurs;
    }

    @Override
    public void save(Utilisateur utilisateur) {
        String query = "INSERT INTO Utilisateurs (nomUtilisateur, motDePasseHash, adresseEmail, niveauAcces) "
                + "VALUES (?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, utilisateur.getNomUtilisateur());
            preparedStatement.setString(2, utilisateur.getMotDePasseHash());
            preparedStatement.setString(3, utilisateur.getAdresseEmail());
            preparedStatement.setString(4, utilisateur.getNiveauAcces());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                utilisateur.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception as needed
        }
    }


    @Override
    public void update(Utilisateur utilisateur) {
        String query = "UPDATE Utilisateurs "
                + "SET nomUtilisateur = ?, motDePasseHash = ?, adresseEmail = ?, niveauAcces = ? " + "WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, utilisateur.getNomUtilisateur());
            preparedStatement.setString(2, utilisateur.getMotDePasseHash());
            preparedStatement.setString(3, utilisateur.getAdresseEmail());
            preparedStatement.setString(4, utilisateur.getNiveauAcces());
            preparedStatement.setInt(5, utilisateur.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception as needed
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM Utilisateurs WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception as needed
        }
    }

    private Utilisateur mapResultSetToUtilisateur(ResultSet rs) throws SQLException {
        int id = rs.getInt("ID");
        String nomUtilisateur = rs.getString("nomUtilisateur");
        String motDePasseHash = rs.getString("motDePasseHash");
        String adresseEmail = rs.getString("adresseEmail");
        String niveauAcces = rs.getString("niveauAcces");

        return new Utilisateur(id, nomUtilisateur, motDePasseHash, adresseEmail, niveauAcces);
    }
    public List<Utilisateur> findNonAdminUsers() {
        List<Utilisateur> nonAdminUsers = new ArrayList<>();
        String query = "SELECT * FROM Utilisateurs WHERE niveauAcces != 'admin'";
        try (Statement statement = connection.createStatement(); ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {
                Utilisateur utilisateur = mapResultSetToUtilisateur(rs);
                nonAdminUsers.add(utilisateur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception as needed
        }
        return nonAdminUsers;
    }

}

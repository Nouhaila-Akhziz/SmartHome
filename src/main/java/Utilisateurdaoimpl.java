import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;
import java.security.MessageDigest;


import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
public class Utilisateurdaoimpl implements UtilisateurIDAO {
	Connection connection; // Remove the explicit initialization

	public Utilisateurdaoimpl() {
	   
	}


    

    @Override
    public Utilisateur find(int id) {
        Utilisateur utilisateur = null;
        String query = "SELECT * FROM Utilisateurs WHERE ID = ?";
        try (Connection connection = Singleconnection.getConnection();
        		PreparedStatement preparedStatement = connection.prepareStatement(query)) {
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
   public Utilisateur findi(String nomUtilisateur) {
	    Utilisateur utilisateur = null;
	    String query = "SELECT * FROM Utilisateurs WHERE nomUtilisateur = ?";
	    
	    try (Connection connection = Singleconnection.getConnection();
	    		PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	        preparedStatement.setString(1, nomUtilisateur);
	        
	        try (ResultSet rs = preparedStatement.executeQuery()) {
	            if (rs.next()) {
	                utilisateur = mapResultSetToUtilisateur(rs);
	            }
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
        try (Connection connection = Singleconnection.getConnection();
        		PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, utilisateur.getNomUtilisateur());
            preparedStatement.setString(2, utilisateur.getMotDePasseHash2());
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
        try (Connection connection = Singleconnection.getConnection();
        		PreparedStatement preparedStatement = connection.prepareStatement(query)) {
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
    public String authenticateUser(String username, String hashedPassword) {
    	
    	
    	
        String query = "SELECT * FROM Utilisateurs WHERE nomUtilisateur = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();

            //Utilisateur utilisateur = null;

            if (rs.next()) {
                // User found, instantiate Utilisateur object
            	Utilisateur utilisateur = new Utilisateur(
                    rs.getInt("ID"),
                    rs.getString("nomUtilisateur"),
                    rs.getString("motDePasseHash"),
                    rs.getString("adresseEmail"),
                    rs.getString("niveauAcces")
                );
            

            if (utilisateur!= null && utilisateur.isPasswordCorrect(hashedPassword)) {
                // Password is correct, return the access level
                return utilisateur.getNiveauAcces();}}}
            
         catch (SQLException e) {
            e.printStackTrace();
            // Handle exception as needed
        }

        // User not found or incorrect password
        return null;
    }


    private static String getHashedPassword(String username) {
    	Connection connection = null ;

        String query = "SELECT motDePasseHash FROM Utilisateurs WHERE nomUtilisateur = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                return rs.getString("motDePasseHash");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception as needed
        }

        // Return null if the username is not found
        return null;
    }
    public String red2(String an, String b) throws Exception {
        String db = "Smarthome_bd";
        String user = "root";
        String pwd = "12345689";
        String url = "jdbc:mysql://localhost:3306/" + db;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pwd);
            System.out.println("Connection established!!");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Handle exception as needed
        }

        Utilisateurdaoimpl utilisateurdao = new Utilisateurdaoimpl();
        String query = "SELECT * FROM Utilisateurs WHERE nomUtilisateur = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, an);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(rs);

            if (rs.next()) {
                // User found, instantiate Utilisateur object
                Utilisateur utilisateur = new Utilisateur(
                        rs.getInt("id"),
                        rs.getString("nomUtilisateur"),
                        rs.getString("motDePasseHash"),
                        rs.getString("adresseEmail"),
                        rs.getString("niveauAcces")
                );

                String admin = "Admin";
                if (utilisateur != null && utilisateur.isPasswordCorrect(b)) {
                    if (admin.equals(utilisateur.getNiveauAcces())) {
                        return "Admin.xhtml";
                    } else if ("User".equals(utilisateur.getNiveauAcces())) {
                        return "User.xhtml";
                    } else {
                        System.out.println("Niveau d'accès non reconnu");
                        return "error.xhtml";
                    }
                } else {
                    System.out.println("Mot de passe incorrect");
                    return "error.xhtml";
                }
            } else {
                System.out.println("Aucun utilisateur trouvé");
                return "error.xhtml";
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gérer les erreurs de connexion à la base de données
            return "error.xhtml";
        }
    }




    public List<Utilisateur> findNonAdminUsers() {
        List<Utilisateur> nonAdminUsers = new ArrayList<>();
        String query = "SELECT * FROM Utilisateurs WHERE niveauAcces != 'admin'";
        try (Connection connection = Singleconnection.getConnection();
        		Statement statement = connection.createStatement(); ResultSet rs = statement.executeQuery(query)) {
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

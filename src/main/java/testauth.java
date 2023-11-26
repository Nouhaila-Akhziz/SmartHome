import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class testauth {

    public static void main(String[] args) {
    	Utilisateur utilisateur = null;
	    String query = "SELECT * FROM Utilisateurs WHERE nomUtilisateur = ?";
	    
	    try (Connection connection = Singleconnection.getConnection();
	    		PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	        String nomUtilisateur 	="Aya";
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
	    
	    System.out.println(utilisateur);
	    System.out.println(utilisateur.getNiveauAcces());
    }
    private static Utilisateur mapResultSetToUtilisateur(ResultSet rs) throws SQLException {
        int id = rs.getInt("ID");
        String nomUtilisateur = rs.getString("nomUtilisateur");
        String motDePasseHash = rs.getString("motDePasseHash");
        String adresseEmail = rs.getString("adresseEmail");
        String niveauAcces = rs.getString("niveauAcces");

        return new Utilisateur(id, nomUtilisateur, motDePasseHash, adresseEmail, niveauAcces);
    }
}

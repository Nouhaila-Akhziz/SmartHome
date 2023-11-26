import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import org.mindrot.jbcrypt.BCrypt;

@Named

@RequestScoped
public class SignupBean implements Serializable {
	@PostConstruct
    public void init() {
        System.out.println("SignupBean initialized");
    }

    private static final long serialVersionUID = 1L;
    private Utilisateurdaoimpl utilisateurdao = new Utilisateurdaoimpl();

    private Utilisateur utilisateur = new Utilisateur();
    

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    private Connection connection = Singleconnection.getConnection();

    public String register() {
        try {
            Utilisateurdaoimpl utilisateurdao = new Utilisateurdaoimpl();
            utilisateurdao.save(utilisateur);

            if ("User".equals(utilisateur.getNiveauAcces())) {
                return "User.xhtml?faces-redirect=true";
            } else {
                return "AdminPage.xhtml?faces-redirect=true";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "ErrorPage.xhtml";
        }
    }
    public String getNiveauAccesForNomUtilisateur(String nomUtilisateur) {
        Utilisateur user = utilisateurdao.findi(nomUtilisateur);
        if (user != null) {
            return user.getNiveauAcces();
        } else {
            return null; // Or handle the case where the user is not found
        }
    }
    public String log() {
    	String niveauAcces = getNiveauAccesForNomUtilisateur(utilisateur.getNomUtilisateur());

        if ("Admin".equals(niveauAcces)) {
        	return "User.xhtml?faces-redirect=true";
        } else {
                
              return "AdminPage.xhtml?faces-redirect=true";}
    	 
    }
    public String loginred() {
    
    	return"AdminPage.xhtml";
       /* try {
        	String userInput = utilisateur.getNomUtilisateur();

        	utilisateur.setNomUtilisateur(userInput);
            Utilisateurdaoimpl utilisateurdao = new Utilisateurdaoimpl();
            Utilisateur a = utilisateurdao.findi(utilisateur.getNomUtilisateur());

            if ("User".equals(a.getNiveauAcces())) {
                return "User.xhtml?faces-redirect=true";
            } else {
                return "AdminPage.xhtml?faces-redirect=true";
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Log the exception or add a FacesMessage to display an error message
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "An error occurred."));
            return "ErrorPage.xhtml";
        }*/
    }



    public Utilisateur authenticateUser(String username, String hashedPassword) {
        String query = "SELECT * FROM Utilisateur WHERE nomUtilisateur=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // User found, create an authenticated user object
                    Utilisateur authenticatedUser = new Utilisateur();
                    authenticatedUser.setId(resultSet.getInt("id"));
                    authenticatedUser.setNomUtilisateur(resultSet.getString("nomUtilisateur"));
                    authenticatedUser.setMotDePasseHash(resultSet.getString("motdepasseHash"));
                    authenticatedUser.setAdresseEmail(resultSet.getString("adresseEmail"));
                    authenticatedUser.setNiveauAcces(resultSet.getString("niveauAcces"));
                    return authenticatedUser;
                } else {
                    // User not found
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the SQL exception as needed
            return null;
        }
    }
    public List<Utilisateur> getNonAdminUsers() {
	    Utilisateurdaoimpl utilisateurdao = new Utilisateurdaoimpl();
	    return utilisateurdao.findNonAdminUsers();
	}


}

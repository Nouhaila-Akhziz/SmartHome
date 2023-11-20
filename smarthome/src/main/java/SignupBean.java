
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class SignupBean {
    
    private Utilisateur utilisateur = new Utilisateur();
   
    public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public String register() {
	    
	    
try {
	     
			Utilisateurdaoimpl utilisateurdao = new Utilisateurdaoimpl();
	        utilisateurdao.save(utilisateur);

	        
	        return "User.xhtml";
	       } 
	catch (Exception e) {
        throw e;
	    }
	}}

      

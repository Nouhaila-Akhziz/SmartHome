import java.security.MessageDigest;



import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;

import org.mindrot.jbcrypt.BCrypt;

public class Utilisateur {

	private int id;
	private String nomUtilisateur;
	private String motDePasseHash; 
	private String adresseEmail;
	private String niveauAcces;

	public Utilisateur(int id, String nomUtilisateur, String motDePasse, String adresseEmail,String niveauAcces) {
		this.id = id;
		this.nomUtilisateur = nomUtilisateur;
		this.motDePasseHash = motDePasse; 
		this.adresseEmail = adresseEmail;
		this.niveauAcces = niveauAcces;
	}
	public Utilisateur() {
	  
	}

  
	
	public String hashPassword(String password) {
		 return BCrypt.hashpw(password, BCrypt.gensalt());
	}

	public boolean isPasswordCorrect(String inputPassword) {
        return BCrypt.checkpw(inputPassword, getMotDePasseHash2());
	}

	

	// Mettre à jour le mot de passe de l'utilisateur
	public void changePassword(String newPassword) {
		this.motDePasseHash = newPassword;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomUtilisateur() {
		return nomUtilisateur;
	}
	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}
	public String getMotDePasseHash() {
		return motDePasseHash;
	}
	
	public String getMotDePasseHash2() {
		return hashPassword(motDePasseHash);
	}
	public void setMotDePasseHash(String motDePasseHash) {
		this.motDePasseHash = motDePasseHash;
	}
	public String getAdresseEmail() {
		return adresseEmail;
	}
	public void setAdresseEmail(String adresseEmail) {
		this.adresseEmail = adresseEmail;
	}
	public String getNiveauAcces() {
		return niveauAcces;
	}
	public void setNiveauAcces(String niveauAcces) {
		this.niveauAcces = niveauAcces;
	}
	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", nomUtilisateur=" + nomUtilisateur + ", motDePasseHash=" + motDePasseHash
				+ ", adresseEmail=" + adresseEmail + ", niveauAcces=" + niveauAcces + "]";
	}
	public class DateExample {

	    public static LocalDate getTodaysDate() {
	        return LocalDate.now();
	    }
	

	 }
	
	}

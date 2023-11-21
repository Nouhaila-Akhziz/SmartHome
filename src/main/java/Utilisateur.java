import java.security.MessageDigest;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;

public class Utilisateur {

	private int id;
	private String nomUtilisateur="Amina";
	private String motDePasseHash="123"; 
	private String adresseEmail="illisiaram@gmail.com";
	private String niveauAcces="Admin";

	public Utilisateur(int id, String nomUtilisateur, String motDePasse, String adresseEmail,String niveauAcces) {
		this.id = id;
		this.nomUtilisateur = nomUtilisateur;
		this.motDePasseHash = hashPassword(motDePasse); 
		this.adresseEmail = adresseEmail;
		this.niveauAcces = niveauAcces;
	}
	public Utilisateur() {
		
	}

	
	private String hashPassword(String password) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
			StringBuilder hexString = new StringBuilder();
			for (byte b : hash) {
				hexString.append(String.format("%02x", b));
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	// Vérifier si le mot de passe donné est correct
	public boolean isPasswordCorrect(String inputPassword) {
		return hashPassword(inputPassword).equals(motDePasseHash);
	}

	// Mettre à jour le mot de passe de l'utilisateur
	public void changePassword(String newPassword) {
		this.motDePasseHash = hashPassword(newPassword);
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
	

	 }}

import java.util.Date;

public class Notification {
	private int id;
	private int idUtilisateur;
	private String message;
	private Date dateHeure;

	// Constructor
	public Notification(int id, int idUtilisateur, String message, Date dateHeure) {
		this.id = id;
		this.idUtilisateur = idUtilisateur;
		this.message = message;
		this.dateHeure = dateHeure;
	}

	// Getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDateHeure() {
		return dateHeure;
	}

	public void setDateHeure(Date dateHeure) {
		this.dateHeure = dateHeure;
	}
}

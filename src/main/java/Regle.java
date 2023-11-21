public class Regle {
	private int id;
	private int idAppareil;
	private String condition;
	private String action;
	private int idUtilisateur;

	// Constructor
	public Regle(int id, int idAppareil, String condition, String action, int idUtilisateur) {
		this.id = id;
		this.idAppareil = idAppareil;
		this.condition = condition;
		this.action = action;
		this.idUtilisateur = idUtilisateur;
	}

	// Getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdAppareil() {
		return idAppareil;
	}

	public void setIdAppareil(int idAppareil) {
		this.idAppareil = idAppareil;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
}

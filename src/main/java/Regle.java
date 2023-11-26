    public class Regle {
	private int id;
	private int idAppareil;
	private String conditionString;
	private String action;
	private int idUtilisateur;

	// Constructor
	public Regle(int id, int idAppareil, String conditionString, String action, int idUtilisateur) {
		this.id = id;
		this.idAppareil = idAppareil;
		this.conditionString =conditionString;
		this.action = action;
		this.idUtilisateur = idUtilisateur;
	}
public Regle() {
}

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

	public String getCondition_column() {
		return conditionString;
	}

	public void setCondition_column(String condition_column) {
		this.conditionString = condition_column;
	}

	public String getAction_column() {
		return action;
	}

	public void setAction_column(String action) {
		this.action = action;
	}

	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	// Getters and setters
	
}

    

public class Acces {
	private int id;
	private int idUtilisateur;
	private int idAppareil;
	private String niveauAcces;

	// Constructeur
	public Acces(int id, int idUtilisateur, int idAppareil, String niveauAcces) {
		this.id = id;
		this.idUtilisateur = idUtilisateur;
		this.idAppareil = idAppareil;
		this.niveauAcces = niveauAcces;
	}

	// Getters
	public int getId() {
		return id;
	}

	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public int getIdAppareil() {
		return idAppareil;
	}

	public String getNiveauAcces() {
		return niveauAcces;
	}

	// Setters
	public void setId(int id) {
		this.id = id;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public void setIdAppareil(int idAppareil) {
		this.idAppareil = idAppareil;
	}

	public void setNiveauAcces(String niveauAcces) {
		this.niveauAcces = niveauAcces;
	}
}

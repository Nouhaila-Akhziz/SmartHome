import java.util.Date;

public class Journal {
	private int id;
	private int idAppareil;
	private String typeEvenement;
	private Date dateHeure;

	// Constructor
	public Journal(int id, int idAppareil, String typeEvenement, Date dateHeure) {
		this.id = id;
		this.idAppareil = idAppareil;
		this.typeEvenement = typeEvenement;
		this.dateHeure = dateHeure;
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

	public String getTypeEvenement() {
		return typeEvenement;
	}

	public void setTypeEvenement(String typeEvenement) {
		this.typeEvenement = typeEvenement;
	}

	public Date getDateHeure() {
		return dateHeure;
	}

	public void setDateHeure(Date dateHeure) {
		this.dateHeure = dateHeure;
	}
}

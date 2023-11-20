import java.util.Date;

public class Consommationenergie {
	private int id;
	private int idAppareil;
	private double consommationWatts; // Using 'double' for the REAL SQL type
	private Date dateHeure;

	// Constructor
	public Consommationenergie(int id, int idAppareil, double consommationWatts, Date dateHeure) {
		this.id = id;
		this.idAppareil = idAppareil;
		this.consommationWatts = consommationWatts;
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

	public double getConsommationWatts() {
		return consommationWatts;
	}

	public void setConsommationWatts(double consommationWatts) {
		this.consommationWatts = consommationWatts;
	}

	public Date getDateHeure() {
		return dateHeure;
	}

	public void setDateHeure(Date dateHeure) {
		this.dateHeure = dateHeure;
	}
}

import java.util.Date;

public class Temperature {
	private int id;
	private int idCapteurTemperature;
	private double temperatureValue; // Using 'double' for the REAL SQL type
	private Date dateHeure;

	// Constructor
	public Temperature(int id, int idCapteurTemperature, double temperatureValue, Date dateHeure) {
		this.id = id;
		this.idCapteurTemperature = idCapteurTemperature;
		this.temperatureValue = temperatureValue;
		this.dateHeure = dateHeure;
	}

	// Getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdCapteurTemperature() {
		return idCapteurTemperature;
	}

	public void setIdCapteurTemperature(int idCapteurTemperature) {
		this.idCapteurTemperature = idCapteurTemperature;
	}

	public double getTemperatureValue() {
		return temperatureValue;
	}

	public void setTemperatureValue(double temperatureValue) {
		this.temperatureValue = temperatureValue;
	}

	public Date getDateHeure() {
		return dateHeure;
	}

	public void setDateHeure(Date dateHeure) {
		this.dateHeure = dateHeure;
	}
}

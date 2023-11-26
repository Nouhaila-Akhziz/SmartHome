import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class Consommationenergie {
	private int id;
	private int idAppareil;
	private double consommationWatts; // Using 'double' for the REAL SQL type
	private Date dateHeure;
	private String nomAppareil;

	// Constructor
	public Consommationenergie(int id, int idAppareil, double consommationWatts, Date dateHeure) {
		this.id = id;
		this.idAppareil = idAppareil;
		this.consommationWatts = consommationWatts;
		this.dateHeure = dateHeure;
	}
	public Consommationenergie(int id, int idAppareil, double consommationWatts, Date dateHeure,String nomAppareil) {
		this.id = id;
		this.idAppareil = idAppareil;
		this.consommationWatts = consommationWatts;
		this.dateHeure = dateHeure;
		this.nomAppareil= nomAppareil;
	}
	public Consommationenergie() {}
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
	 public String getDeviceNameById(int idAppareil) throws SQLException {
	        Appareilsdaoimp appareilsDAO = new Appareilsdaoimp();
	        List<Appareils> appareilsList = appareilsDAO.findAll();

	        int i = 0;
	        while (i < appareilsList.size()) {
	            Appareils appareil = appareilsList.get(i);
	            if (appareil.getId() == idAppareil) {
	                System.out.println("nouhaila");
	                return appareil.getNomAppareil();
	            }
	            System.out.println("nouhaila1234");
	            i++;
	        }

	        return "";
	    }
	 
	 public String getNomAppareil() {
		return nomAppareil;
	}
	public void setNomAppareil(String nomAppareil) {
		this.nomAppareil = nomAppareil;
	}
	@Override
	    public String toString() {
	        return "Consommationenergie{" +
	                "id=" + id +
	                ", // other fields..." +
	                '}';
	    }
}

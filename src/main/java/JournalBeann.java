    import java.sql.SQLException;
import java.util.List;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class JournalBeann {
       public JournalBeann() {
    	   
       }
       
       public List<Journal> getHistory() {
    	   Journaldaoimp  Journaldao = new Journaldaoimp ();
           return Journaldao.findAll();
       }
       public String getDeviceNameById(int idAppareil) throws SQLException {
    	    Appareilsdaoimp appareilsDAO = new Appareilsdaoimp();
    	    List<Appareils> appareilsList = appareilsDAO.findAll(); 

    	    for (int i = 0; i < appareilsList.size(); i++) {
    	        Appareils appareil = appareilsList.get(i);
    	        if (appareil.getId() == idAppareil) {
    	            return appareil.getNomAppareil(); 
    	        }
    	    }

    	    // If no matching device is found, return null
           return "";
       
      }
}

    

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class ConsommationBean {

	public ConsommationBean() {
    }

    public List<Consommationenergie> getConsommation() {
        Consommationenergiedaoimpl consommationdao = new Consommationenergiedaoimpl();
        List<Consommationenergie> consommationList = consommationdao.findAll();

        Appareilsdaoimp appareilsDAO = new Appareilsdaoimp();
        List<Appareils> appareilsList = appareilsDAO.findAll();

        Map<Integer, String> appareilNamesMap = new HashMap<>();

        // Populate appareilNamesMap with the names of appareils
        for (Appareils appareil : appareilsList) {
            appareilNamesMap.put(appareil.getId(), appareil.getNomAppareil());
        }

        // Set the nomAppareil property in Consommationenergie objects
        for (Consommationenergie consommation : consommationList) {
            int idAppareil = consommation.getIdAppareil();

            // Ensure idAppareil is present in appareilNamesMap
            if (appareilNamesMap.containsKey(idAppareil)) {
                String nomAppareil = appareilNamesMap.get(idAppareil);
                consommation.setNomAppareil(nomAppareil);
            }
        }

        return consommationList;
    }
    public String getDeviceNameById() {
        List<Consommationenergie> consommationList = getConsommation();
        Appareilsdaoimp appareilsDAO = new Appareilsdaoimp();
        List<Appareils> appareilsList = appareilsDAO.findAll();

        int i = 0;
        while (i < appareilsList.size()) {
            Appareils appareil = appareilsList.get(i);
            if (appareil.getId() == consommationList.get(i).getIdAppareil()) {
                System.out.println("nouhaila");
                return appareil.getNomAppareil();
            }
            System.out.println("nouhaila1234");
            i++;
        }
		return null;

        
    }
    
    
    public void displayConsommationWithAppareilNames() {
        Consommationenergiedaoimpl consommationdao = new Consommationenergiedaoimpl();
        List<Consommationenergie> consommationList = consommationdao.findAll();

        Appareilsdaoimp appareilsDAO = new Appareilsdaoimp();
        List<Appareils> appareilsList = appareilsDAO.findAll();

        Map<Integer, String> appareilNamesMap = new HashMap<>();

        // Populate appareilNamesMap with the names of appareils
        for (Appareils appareil : appareilsList) {
            appareilNamesMap.put(appareil.getId(), appareil.getNomAppareil());
        }

        for (Consommationenergie consommation : consommationList) {
            int idAppareil = consommation.getIdAppareil();

            // Ensure idAppareil is present in appareilNamesMap
            if (appareilNamesMap.containsKey(idAppareil)) {
                String nomAppareil = appareilNamesMap.get(idAppareil);
                consommation.setNomAppareil(nomAppareil);
                System.out.println("ID: " + idAppareil + ", Nom Appareil: " + nomAppareil);
            } else {
                System.out.println("ID: " + idAppareil + ", Nom Appareil non trouvé");
            }
        }
    }

}
import java.util.ArrayList;
import java.util.List;

public class testauth {

    public static void main(String[] args) {
        // Instantiate your DAO class
        Consommationenergiedaoimpl consommationdao = new Consommationenergiedaoimpl();

        // Assuming you have already created the nomsAppareils list
        List<Consommationenergie> consommationList = consommationdao.findAll();

        // Instantiate the Appareils DAO class
        Appareilsdaoimp appareilsDAO = new Appareilsdaoimp();
        List<Appareils> appareilsList = appareilsDAO.findAll();

        System.out.println(appareilsList);

        List<String> nomsAppareils = new ArrayList<>();

        // Populate nomsAppareils with the names of appareils
        for (Appareils appareil : appareilsList) {
            nomsAppareils.add(appareil.getNomAppareil());
        }

        // Iterate through the consommationList and replace idAppareil with the corresponding name
        for (Consommationenergie consommation : consommationList) {
            int idAppareil = consommation.getIdAppareil();
            
            // Ensure idAppareil is within the bounds of nomsAppareils list
            if (idAppareil > 0 && idAppareil <= nomsAppareils.size()) {
                String nomAppareil = nomsAppareils.get(idAppareil - 1);
                consommation.setNomAppareil(nomAppareil);
                System.out.println(consommation.getNomAppareil());
            } else {
                System.out.println("Non");
            }
        }
    }
}

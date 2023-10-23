package MDP;

import java.util.List;

public interface AppareilsIDAO extends IDAO<Appareils>{
    Appareils find(int id);
    List<Appareils> findAll();
    void save(Appareils appareil);
    void update(Appareils appareil);
    void delete(Appareils appareil);
}

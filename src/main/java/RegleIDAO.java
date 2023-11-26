    
import java.util.List;

public interface RegleIDAO {
	Regle find(int id);

	List<Regle> findAll();

	void save(Regle regle);

	void update(Regle regle);

	void delete(Regle regle);
}

    

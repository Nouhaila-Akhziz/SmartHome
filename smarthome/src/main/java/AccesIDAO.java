import java.util.List;

public interface AccesIDAO {
	Acces find(int id);

	List<Acces> findAll();

	void save(Acces acces);

	void update(Acces acces);

	void delete(Acces acces);
}


import java.util.List;

public interface JournalIDAO {
	Journal find(int id);

	List<Journal> findAll();

	void save(Journal journal);

	void update(Journal journal);

	void delete(Journal journal);
}

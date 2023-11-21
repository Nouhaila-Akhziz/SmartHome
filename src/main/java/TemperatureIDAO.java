import java.util.List;

public interface TemperatureIDAO {
	Temperature find(int id);

	List<Temperature> findAll();

	void save(Temperature temperature);

	void update(Temperature temperature);

	void delete(Temperature temperature);
}
import java.util.List;

public interface ConsommationenergieIDAO {
	Consommationenergie find(int id);

	List<Consommationenergie> findAll();

	void save(Consommationenergie consommationEnergie);

	void update(Consommationenergie consommationEnergie);

	void delete(Consommationenergie consommationEnergie);
}

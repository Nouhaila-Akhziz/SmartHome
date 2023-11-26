import java.util.List;

public interface UtilisateurIDAO {
	Utilisateur find(int id);

	List<Utilisateur> findAll();

	void save(Utilisateur utilisateur);

	void update(Utilisateur utilisateur);

	void delete(int id);

	Utilisateur findi(String nomUtilisateur);
	
}

import java.util.List;

public interface NotificationIDAO {
	Notification find(int id);

	List<Notification> findAll();

	void save(Notification notification);

	void update(Notification notification);

	void delete(Notification notification);
}
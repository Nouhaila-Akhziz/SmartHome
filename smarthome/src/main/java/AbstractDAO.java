import java.sql.Connection;

public class AbstractDAO {
	protected Connection connection = Singleconnection.getConnection();
}

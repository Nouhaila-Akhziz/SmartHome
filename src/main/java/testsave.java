import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.protobuf.Timestamp;

public class testsave {
	

    public static void main(String[] args) {
	List<Regle> regles = new ArrayList<>();
		
		String query = "SELECT * FROM Regles";
		try ( Connection connection = Singleconnection.getConnection();
				Statement statement = connection.createStatement(); ResultSet rs = statement.executeQuery(query)) {
			while (rs.next()) {
				int id = rs.getInt("id");
				int idAppareil = rs.getInt("idAppareil");
				String conditionString = rs.getString("conditionString");
				String action = rs.getString("action");
				int idUtilisateur = rs.getInt("idUtilisateur");
				Regle regle = new Regle(id, idAppareil, conditionString, action, idUtilisateur);
				regles.add(regle);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(regles);
	}

    
}



import java.util.Comparator;
import java.util.List;
import java.time.LocalDate;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import org.mindrot.jbcrypt.BCrypt;

@Named

@RequestScoped

public class TemperatureBean {
    private Temperature lastTemperature;

    public TemperatureBean() {
       
    }

    public void setLastTemperature(Temperature lastTemperature) {
		this.lastTemperature = lastTemperature;
	}

	
	public static int getMaxAppareilId() {
	    String query = "SELECT MAX(id) FROM Temperature";
	    try (Connection connection = Singleconnection.getConnection();
	         Statement statement = connection.createStatement();
	         ResultSet rs = statement.executeQuery(query)) {
	        if (rs.next()) {
	            return (rs.getInt(1)); // Assuming the first column in the result set is the max ID
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return 0; // Default value if no max ID is found
	}
	public double getLastTemperature() {
        Temperaturedaoimpl temperaturedao = new Temperaturedaoimpl();
        int maxId= getMaxAppareilId();
        double temperatureValue = temperaturedao.find(maxId).getTemperatureValue();
        return temperatureValue;
    }
    

	
	public LocalDate getlocaldate() {LocalDate currentDate = LocalDate.now();
	return currentDate;}
    
    
}

    //import java.sql.SQLException;
import java.util.List;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class SettingsBean {
	  public SettingsBean()  {
   	   
      }
      public List<Regle> getSETTINGS() {
   	   Regledaoimpl  Regledao = new  Regledaoimpl();
          return Regledao.findAll();
      }

}

    

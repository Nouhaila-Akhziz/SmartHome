import java.util.List;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class SettingsBean {

    private List<Regle> settings;
    private Regledaoimpl regledao = new Regledaoimpl();

    public List<Regle> getSETTINGS() {
        return regledao.findAll();
    }

    public void updateSetting(Regle setting) {
        // Implement the logic to update the setting in the database
        regledao.update(setting);
    }
}

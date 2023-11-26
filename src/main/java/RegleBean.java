import java.util.Arrays;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named
@RequestScoped
public class RegleBean {

    private Regledaoimpl regledaoimpl = new Regledaoimpl();

    private int selectedId;
    private String regleAction;

    // Initialization method
    @PostConstruct
    public void init() {
        // Perform any initialization here
    }

    // Getter and setter for selectedId
    public int getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(int selectedId) {
        this.selectedId = selectedId;
    }

    // Getter and setter for regleAction
    public String getRegleAction() {
        return regleAction;
    }

    public void setRegleAction(String regleAction) {
        this.regleAction = regleAction;
    }

    // Getter for availableIds (to be used in the selectOneMenu)
    public List<Integer> getAvailableIds() {
        // Implement logic to retrieve available IDs from your data source
        // For example, you can fetch them from the database
        // This is just a placeholder, you need to replace it with actual logic
        return Arrays.asList(1, 2, 3, 4, 5);
    }

    // Method to update the regle
    
}

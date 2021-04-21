package simonova.rent.rentofpremises.dto;
import java.util.List;

public class UserDTO extends PersonDTO{
    private List<ApplicationDTO> applications;
    public List<ApplicationDTO> getApplications() {
        return applications;
    }
    public void setApplications(List<ApplicationDTO> applications) {
        this.applications = applications;
    }
}

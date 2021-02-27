package simonova.rent.rentofpremises.dto;

import java.util.Set;

public class UserDTO extends PersonDTO{

    private Set<ApplicationDTO> applications;


    public Set<ApplicationDTO> getApplications() {
        return applications;
    }

    public void setApplications(Set<ApplicationDTO> applications) {
        this.applications = applications;
    }
}

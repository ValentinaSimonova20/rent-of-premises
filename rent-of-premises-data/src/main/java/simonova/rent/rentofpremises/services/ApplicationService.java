package simonova.rent.rentofpremises.services;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import simonova.rent.rentofpremises.dto.ApplicationDTO;
import simonova.rent.rentofpremises.model.Application;
import java.util.List;

@Transactional
public interface ApplicationService extends CrudService<ApplicationDTO, Long>{
    List<ApplicationDTO> findByUserId(Long id);
    ApplicationDTO findByUserIdAndPremisesId(Long userId, Long premId);

    Page<Application> findPaginated(int pageNo, int pageSize);

    Page<Application> findByUserId(Long id, int pageNo, int pageSize);
}

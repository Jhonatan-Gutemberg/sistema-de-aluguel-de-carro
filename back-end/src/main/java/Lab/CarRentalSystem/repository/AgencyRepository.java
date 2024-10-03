package Lab.CarRentalSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Lab.CarRentalSystem.models.Agency;

@Repository
public interface AgencyRepository extends JpaRepository<Agency ,Long>{
    
}

package Lab.CarRentalSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Lab.CarRentalSystem.models.Employment;

@Repository
public interface EmployerRepository extends JpaRepository<Employment, Long> {

}

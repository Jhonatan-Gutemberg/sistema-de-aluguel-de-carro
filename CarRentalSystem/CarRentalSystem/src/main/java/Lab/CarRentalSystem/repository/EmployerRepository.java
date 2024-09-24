package Lab.CarRentalSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Lab.CarRentalSystem.models.Employer;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Long> {

}

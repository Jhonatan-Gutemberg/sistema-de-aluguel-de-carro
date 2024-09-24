package Lab.CarRentalSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Lab.CarRentalSystem.models.Automobile;

@Repository
public interface AutomobileRepository extends JpaRepository<Automobile, Long> {

}

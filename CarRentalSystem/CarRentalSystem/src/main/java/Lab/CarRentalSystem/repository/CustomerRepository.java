package Lab.CarRentalSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Lab.CarRentalSystem.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}

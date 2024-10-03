package Lab.CarRentalSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Lab.CarRentalSystem.models.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {

}

package Lab.CarRentalSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Lab.CarRentalSystem.models.CreditContract;

@Repository
public interface CreditContractRepository extends JpaRepository<CreditContract, Long> {
    List<CreditContract> findByIdIn(List<Long> ids);
}

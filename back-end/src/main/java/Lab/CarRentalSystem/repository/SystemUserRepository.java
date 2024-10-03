package Lab.CarRentalSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Lab.CarRentalSystem.models.SystemUser;

@Repository
public interface SystemUserRepository extends JpaRepository<SystemUser, Long> {
  SystemUser findByName(String name);
  
}

package Lab.CarRentalSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Lab.CarRentalSystem.models.Agent;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {

}

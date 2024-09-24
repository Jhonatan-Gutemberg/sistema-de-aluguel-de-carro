package Lab.CarRentalSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Lab.CarRentalSystem.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}

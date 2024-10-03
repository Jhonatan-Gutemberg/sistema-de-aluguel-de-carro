package Lab.CarRentalSystem.models;

import java.time.LocalDate;

import Lab.CarRentalSystem.enums.PlanType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "creationDate", nullable = false)
    private LocalDate creationDate;

    @Column(name = "deliveryDate", nullable = false)
    private LocalDate deliveryDate;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "automobile_id", nullable = false)
    private Long automobileId;

    private double value;

    @Enumerated(EnumType.STRING)
    private PlanType planType;

    @Column(name = "agency_id", nullable = false)
    private Long agencyId;

}

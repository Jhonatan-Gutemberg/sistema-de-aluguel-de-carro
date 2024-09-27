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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "tb_order")
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "creationDate", nullable = false, unique = false)
    private LocalDate creationDate;
    @Column(name = "deliveryDate", nullable = false, unique = false)
    private LocalDate deliveryDate;
    @Column(name = "status", nullable = false, unique = false)
    private boolean status;
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    @OneToOne
    @JoinColumn(name = "automobile_id", nullable = false)
    private Automobile automobile;
    private Double value;
    @Enumerated(EnumType.STRING)
    private PlanType planType;
    @OneToOne(mappedBy = "order")
    private CreditContract creditContract;
    @ManyToOne
    @JoinColumn(name = "agency_id", nullable = false)
    private Agency agency;

}

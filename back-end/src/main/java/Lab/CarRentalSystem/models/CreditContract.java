package Lab.CarRentalSystem.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_creditContract")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditContract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "amount", nullable = false, unique = false)
    private Double amount;

    @Column(name = "IDorder", nullable = false, unique = false)
    private Long orderId;

    @Column(name = "IDbank", nullable = false, unique = false)
    private Long bankId;

    @Column(name = "IDcustomer", nullable = true, unique = false)
    private Long customerId;

    @Column(name = "approved", nullable = true, unique = false)
    private boolean approved = false;

}

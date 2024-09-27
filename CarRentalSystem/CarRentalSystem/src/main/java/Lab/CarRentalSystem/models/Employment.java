package Lab.CarRentalSystem.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "tb_employer")
@AllArgsConstructor
@NoArgsConstructor
public class Employment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "profession", nullable = true, unique = false, columnDefinition = "VARCHAR(255)")
    private String profession;
    @Column(name = "salary", nullable = true, unique = false)
    private Double salary;
    @ManyToOne
    @JoinColumn(name = "customer_employer_id", nullable = false)
    private Customer customer;

}

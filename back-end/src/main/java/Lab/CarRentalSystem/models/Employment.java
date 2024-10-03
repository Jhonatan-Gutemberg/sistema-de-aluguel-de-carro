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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_employer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

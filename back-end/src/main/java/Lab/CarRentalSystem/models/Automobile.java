package Lab.CarRentalSystem.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_automobile")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Automobile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, unique = false, columnDefinition = "VARCHAR(255)")
    private String name;
    @Column(name = "valuePerDay", nullable = false, unique = false)
    private Double valuePerDay;
    @Column(name = "year", nullable = false, unique = false)
    private int year;
    @Column(name = "registrationNumber", nullable = true, unique = false, columnDefinition = "VARCHAR(255)")
    private String registrationNumber;
    @Column(name = "brand", nullable = false, unique = false, columnDefinition = "VARCHAR(255)")
    private String brand;
    @Column(name = "model", nullable = false, unique = false, columnDefinition = "VARCHAR(255)")
    private String model;
    @Column(name = "plate", nullable = false, unique = true, columnDefinition = "VARCHAR(255)")
    private String plate;
    private Long orderId;
}

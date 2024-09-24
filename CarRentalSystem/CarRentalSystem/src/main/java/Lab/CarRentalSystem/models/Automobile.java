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
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_automobile")
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    @OneToOne(mappedBy = "automobile")
    @PrimaryKeyJoinColumn
    private Order order;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValuePerDay() {
        return this.valuePerDay;
    }

    public void setValuePerDay(Double valuePerDay) {
        this.valuePerDay = valuePerDay;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getRegistrationNumber() {
        return this.registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlate() {
        return this.plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Order getOrder() {
        return this.order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

}

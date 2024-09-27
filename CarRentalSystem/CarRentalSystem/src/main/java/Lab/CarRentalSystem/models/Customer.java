package Lab.CarRentalSystem.models;

import java.util.List;

import Lab.CarRentalSystem.enums.SystemUserType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_customer")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer extends SystemUser {
    @Column(name = "registration", nullable = false, unique = false, columnDefinition = "VARCHAR(255)")
    private String registration;
    @Column(name = "cpf", nullable = false, unique = false, columnDefinition = "VARCHAR(255)")
    private String cpf;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;
    @OneToMany
    private List<Employment> employments;

    public Customer(Long id, String name, String address, SystemUserType type, boolean status, String password,
            String registration, String cpf, List<Order> orders, List<Employment> employments) {
        super(id, name, address, type, status, password);
        this.registration = registration;
        this.cpf = cpf;
        this.orders = orders;
        this.employments = employments;
    }

}

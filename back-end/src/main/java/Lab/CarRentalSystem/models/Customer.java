package Lab.CarRentalSystem.models;

import java.util.List;
import java.util.ArrayList;

import Lab.CarRentalSystem.enums.SystemUserType;
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

    @Column(name = "orders_id", nullable = true, unique = false)
    private List<Long> orders = new ArrayList<>();

    @OneToMany
    private List<Employment> employments;

    @Column(name = "creditCard_id", nullable = true, unique = false)
    private List<Long> creditCards = new ArrayList<>(); // Inicializa a lista aqui

    @Column(name = "value", nullable = true, unique = false)
    private double balance = 10000;

    // Construtor
    public Customer(String name, String address, SystemUserType type, boolean status, String password,
            String registration, String cpf, List<Long> orders, List<Employment> employments, double balance) {
        super(name, address, type, status, password);
        this.registration = registration;
        this.cpf = cpf;
        this.orders = orders;
        this.employments = employments;
        this.balance = 10000;
    }

}

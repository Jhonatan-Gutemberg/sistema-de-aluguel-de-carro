package Lab.CarRentalSystem.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_customer")
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends SystemUser {
    @Column(name = "registration", nullable = false, unique = false, columnDefinition = "VARCHAR(255)")
    private String registration;
    @Column(name = "cpf", nullable = false, unique = false, columnDefinition = "VARCHAR(255)")
    private String cpf;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;
    @OneToMany
    private List<Employer> employers;

    public String getRegistration() {
        return this.registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Employer> getEmployers() {
        return this.employers;
    }

    public void setEmployers(List<Employer> employers) {
        this.employers = employers;
    }

    public List<Order> getOrders() {
        return this.orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

}

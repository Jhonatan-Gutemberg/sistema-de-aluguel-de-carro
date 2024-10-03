package Lab.CarRentalSystem.models;

import java.util.List;
import java.util.ArrayList;

import Lab.CarRentalSystem.enums.SystemUserType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_agency")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agency extends SystemUser {

    @Column(name = "orders_id", nullable = true, unique = false)
    private List<Long> orders = new ArrayList<>();

    @Column(name = "value", nullable = true, unique = false)
    private double balance = 0;

    public Agency(String name, String address, SystemUserType type, boolean status, String password, double balance,
            List<Long> orders) {
        super(name, address, type, status, password);
        this.orders = orders;
        this.balance = balance;
    }

}

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
@Table(name = "tb_bank")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bank extends SystemUser {

    @Column(name = "limitValue", nullable = true, unique = false)
    private double limitValue;

    @Column(name = "IDcontracts", nullable = true, unique = false)
    private List<Long> contracts = new ArrayList<>();

    public Bank(String name, String address, SystemUserType type, boolean status, String password,
            double limitValue, List<Long> contracts) {
        super(name, address, type, status, password);
        this.limitValue = limitValue;
        this.contracts = contracts;
    }

}

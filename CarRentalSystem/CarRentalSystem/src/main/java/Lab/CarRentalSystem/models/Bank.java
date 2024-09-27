package Lab.CarRentalSystem.models;

import java.util.List;

import Lab.CarRentalSystem.enums.SystemUserType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_bank")

public class Bank extends SystemUser {

    @Column(name = "limitValue", nullable = false, unique = false)
    private double limitValue;

    @OneToMany(mappedBy = "bank")
    private List<CreditContract> creditContracts;

    public Bank(Long id, String name, String address, SystemUserType type, boolean status, String password,
            double limitValue, List<CreditContract> contracts) {
        super(id, name, address, type, status, password);
        this.limitValue = limitValue;
        this.creditContracts = contracts;
    }

}

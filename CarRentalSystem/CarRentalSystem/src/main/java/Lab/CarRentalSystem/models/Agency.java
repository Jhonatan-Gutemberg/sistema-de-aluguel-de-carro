package Lab.CarRentalSystem.models;

import java.util.List;

import Lab.CarRentalSystem.enums.SystemUserType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_agency")
public class Agency extends SystemUser {

    @OneToMany(mappedBy = "agency")
    private List<Order> orders;

    public Agency(Long id, String name, String address, SystemUserType type, boolean status, String password, List<Order> orders) {
        super(id, name, address, type, status, password);
        this.orders = orders;
    }

}

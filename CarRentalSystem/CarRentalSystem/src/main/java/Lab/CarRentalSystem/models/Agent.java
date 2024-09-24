package Lab.CarRentalSystem.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_agent")
@AllArgsConstructor
@NoArgsConstructor
public class Agent extends SystemUser {
    @Column(name = "limitValue", nullable = false, unique = false)
    private Double limitValue;

    public Double getLimitValue() {
        return this.limitValue;
    }

    public void setLimitValue(Double limitValue) {
        this.limitValue = limitValue;
    }

}

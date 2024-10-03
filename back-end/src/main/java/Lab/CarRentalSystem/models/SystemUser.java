package Lab.CarRentalSystem.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import Lab.CarRentalSystem.enums.SystemUserType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "tb_systemUser")
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class SystemUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, unique = true, columnDefinition = "VARCHAR(50)")
    protected String name;
    @Column(name = "address", nullable = false, unique = false, columnDefinition = "VARCHAR(255)")
    protected String address;
    @Enumerated(EnumType.STRING)
    protected SystemUserType type;
    @Column(name = "status", nullable = false, unique = false)
    protected boolean status;
    @Column(name = "password", nullable = false, unique = false, columnDefinition = "VARCHAR(255)")
    @JsonProperty(access = Access.WRITE_ONLY)
    protected String password;
    
    

    public SystemUser(String name, String address, SystemUserType type, boolean status, String password) {
        this.name = name;
        this.address = address;
        this.type = type;
        this.status = status;
        this.password = password;
    }

}

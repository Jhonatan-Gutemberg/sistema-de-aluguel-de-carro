package com.example.demo.Models;

import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends User {

    private String registration;
    private String cpf;
    private String profession;
    private List<Double> income;
    @OneToMany(mappedBy = "customer")
    private List<OrderAutomobile> orders;
}

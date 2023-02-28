package com.system.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "COMPANY")
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NAME", unique = true)
    private String name;
    @Column(name = "PHONE", unique = true, length = 30)
    private String phoneNumber;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "INN", unique = true)
    private String inn;
    @Column(name = "IBAN", unique = true)
    private String iban;
}

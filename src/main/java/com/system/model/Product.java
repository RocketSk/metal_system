package com.system.model;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Data
@Entity
@Table(name = "PRODUCTS")
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "COUNT")
    private Integer count;

    @Column(name = "ARTICLE", unique = true)
    private String article;

    @Column(name = "PRICE", nullable = false)
    private Double price;

    @ManyToOne(cascade = CascadeType.MERGE)
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "GROUP_ID")
    private ProductGroup productGroup;

    @Override
    public String toString() {
        return name + ", Количество : " + count + " \n";
    }
}

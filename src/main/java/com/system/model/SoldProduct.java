package com.system.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "SOLD_PRODUCT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SoldProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "PRODUCT_ID")
    private Integer productID;

    @Column(name = "ORDER_ID")
    private Integer orderID;

    @Column(name = "COUNT")
    private Integer count;

}

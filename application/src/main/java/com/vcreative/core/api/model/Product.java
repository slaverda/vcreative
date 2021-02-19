package com.vcreative.core.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Domain entity for the Product
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "product")
public class Product {
    @Id
    private String id;

    @Column
    private String sku;

    @Column
    private String name;

    @Column
    private String description;

    @Column(name = "unit_price")
    private double unitPrice;

    @Column(name = "image_url")
    private String imageUrl;

    @Column
    private byte active;

    @Column(name = "units_in_stock")
    private Integer unitsInStock;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @Column(name = "category_id")
    private String categoryId;
}

package com.example.auction_management.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", columnDefinition = "INT")
    private Long productId;

    @NotBlank(message = "Tên sản phẩm không được để trống")
    @Column(name = "name", columnDefinition = "VARCHAR(255) NOT NULL")
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id", columnDefinition = "INT")
    private Category category;

    @Lob
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @DecimalMin(value = "0.00", message = "Giá khởi điểm phải lớn hơn 0")
    @Column(name = "base_price", columnDefinition = "DECIMAL(10,2) NOT NULL")
    private BigDecimal basePrice;

    @Column(name = "image", columnDefinition = "VARCHAR(255)")
    private String image;

    @Column(name = "is_deleted", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isDeleted;
}

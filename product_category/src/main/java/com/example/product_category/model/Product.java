package com.example.product_category.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @NotNull(message = "Tên không được để trống")
    @Column(name="name", columnDefinition = "VARCHAR(255)")
    private String name;

    @NotNull(message = "Ngày nhập không được để trống")
    @Column(name="import_date")
    private Date importDate;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @NotNull(message = "Loại sản phẩm không được để trống")
    private Category category;
}

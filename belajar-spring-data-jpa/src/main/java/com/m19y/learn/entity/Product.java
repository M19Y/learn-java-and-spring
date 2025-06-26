package com.m19y.learn.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
@NamedQueries(value ={
  @NamedQuery(
    name = "Product.searchProductUsingName",
    query = "SELECT p FROM Product p WHERE p.name = :name"
  )
})
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private Long price;

  @ManyToOne
  @JoinColumn(name = "category_id",
          referencedColumnName = "id",
          foreignKey = @ForeignKey(foreignKeyDefinition = "fk_product_categories"))
  private Category category;
}

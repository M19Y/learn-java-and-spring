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
@Table(name = "addresses")
public class Address {

  @Id
  private String id;

  private String street;
  private String city;
  private String province;

  @Column(nullable = false)
  private String country;

  @Column(name = "postal_code")
  private String postalCode;

  // one contact can have many addresses
  @ManyToOne
  @JoinColumn(
          name = "contact_id",
          referencedColumnName = "id",
          foreignKey = @ForeignKey(name = "fk_contacts_addresses"))
  private Contact contact;


}

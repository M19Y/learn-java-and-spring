package com.m19y.learn.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contacts")
public class Contact {

  @Id
  private String id;

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  private String phone;

  private String email;

  // one user can have many contacts
  @ManyToOne
  @JoinColumn(
          name = "username",
          referencedColumnName = "username",
          foreignKey = @ForeignKey(name = "fk_users_contacts"))
  private User user;

  @OneToMany(mappedBy = "contact")
  private List<Address> addresses;
}

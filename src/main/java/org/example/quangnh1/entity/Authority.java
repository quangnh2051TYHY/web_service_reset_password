package org.example.quangnh1.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "authorities")
public class Authority {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false, length = 20)
  private String name;

  @ManyToMany(mappedBy = "authorities")
  private Collection<Role> roles;

  public Authority(String name) {
    this.name = name;
  }

  public Authority() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Collection<Role> getRoles() {
    return roles;
  }

  public void setRoles(Collection<Role> roles) {
    this.roles = roles;
  }
}
package org.example.quangnh1.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "roles")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    @ManyToMany(cascade = { CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name = "role_authorities",
    joinColumns=@JoinColumn(name="roles_id" , referencedColumnName = "id") ,
    inverseJoinColumns=@JoinColumn(name="authorities_id" , referencedColumnName = "id"))
    //từ thông tin bảng trung gian là role_authorities để lấy Collection<Authority>
    private Collection<Authority> authorities;

    public Collection<User> getUsers() {
        return users;
    }

    public Collection<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<Authority> authorities) {
        this.authorities = authorities;
    }

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

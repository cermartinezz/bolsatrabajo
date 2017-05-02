package com.bolsaTrabajo.model;


import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;

@Entity
@Table(name= "users")
public class User implements Serializable{

    private Long id;
    private String email;
    private String password;
    private String passwordConfirm;
    private String username;
    private String name;
    private String lastName;
    private int active;
    private Set<Role> roles;


    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Transient
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    //Metodos para control de contenido
    public boolean hasRole(String role){
        Iterator<Role> i = roles.iterator();            //Creando un iterador para los roles
        if (i.hasNext()){                               //Si el iterador contiene elementos itera y busca
            while (i.hasNext()){                        //Iterando cada elemento
                if (i.next().getName().equals(role))    //Buscando en cada elemento el role
                    return true;                        //Si encuentra el role retorna true
            }
        }
        return false;                                   //De estar los roles vacios o no encontrarse retorna false
    }

    public boolean hasPermission(String permission){
        Iterator<Role> i = roles.iterator();
        if (i.hasNext()){
            while (i.hasNext()){
                Iterator<Permission> ip = i.next().getPermissions().iterator();
                if (ip.hasNext()){
                    while (ip.hasNext()){
                        if (ip.next().getName().equals(permission))
                            return true;
                    }
                }
                else
                    return false;
            }
        }
        return false;
    }

}

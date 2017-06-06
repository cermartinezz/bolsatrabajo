package com.bolsaTrabajo.model.catalog;

import com.bolsaTrabajo.model.postulantInfo.PostulantLanguage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="languages")
public class Language implements Serializable{
    private int id;
    private String codigo;
    private String nombre;
    private Set<PostulantLanguage> postulantLanguages;

    public Language(){
        super();
    }

    public Language(int id) {
        this.id = id;
    }

    public Language(String codigo, String nombre){
        this.codigo = codigo;
        this.nombre = nombre;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "language_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "code",unique = true)
    @NotEmpty(message ="*Por favor ingrese un código")
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Column(name = "name")
    @NotEmpty(message ="*Por favor ingrese un nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @OneToMany(mappedBy = "primaryKey.language",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JsonIgnore
    public Set<PostulantLanguage> getPostulantLanguages() {
        return postulantLanguages;
    }

    public void setPostulantLanguages(Set<PostulantLanguage> postulantLanguages) {
        this.postulantLanguages = postulantLanguages;
    }
}

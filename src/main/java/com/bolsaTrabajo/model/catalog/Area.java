package com.bolsaTrabajo.model.catalog;

import javax.persistence.*;
import java.util.Set;

@Entity
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "CA_AREA",
                procedureName = "CA_AREA",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "A_NOMBRE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "A_ID", type = Long.class),
                })
})
public class Area {
    private long id;
    private String nombre;
    private Set<SubArea> subAreas;

    public Area(){super();}
    public Area(long id){this.id = id;}
    public Area(String nombre){this.nombre = nombre;}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(unique = true)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "area", cascade = CascadeType.REMOVE)
    public Set<SubArea> getSubAreas() {
        return subAreas;
    }

    public void setSubAreas(Set<SubArea> subAreas) {
        this.subAreas = subAreas;
    }

    @Override
    public String toString() {
        return "Area{" +
                "id=" + id +
                ", nombre='" + nombre +
                '}';
    }
}

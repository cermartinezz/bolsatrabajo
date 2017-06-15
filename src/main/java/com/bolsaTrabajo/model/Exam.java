package com.bolsaTrabajo.model;

import com.bolsaTrabajo.model.catalog.SubArea;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "CA_EXAM", //Usar el mismo nombre que el procedimiento almacenado
                procedureName = "CA_EXAM",           ////Usar el mismo nombre que el procedimiento almacenado
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "E_ID", type = Long.class),          //Estos parametros los
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "E_FECHA", type = Date.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "E_INSTRUCCIONES", type = String.class),//llamo igual que en el
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "E_PUBLICADO", type = int.class),    //procedimiento
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "E_SUBAREA_ID", type = long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "E_TITULO", type = String.class),
                })
})
public class Exam {
    private long id;
    private String titulo;
    private int publicado; //1 - Si, 0 - No
    private Date fecha;
    private String instrucciones; //instrucciones del examen
    private SubArea subArea;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getPublicado() {
        return publicado;
    }

    public void setPublicado(int publicado) {
        this.publicado = publicado;
    }

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }

    @ManyToOne
    @JsonIgnore
    public SubArea getSubArea() {
        return subArea;
    }

    public void setSubArea(SubArea subArea) {
        this.subArea = subArea;
    }

    public void publicar(){this.publicado = 1;}

    public void desPublicar(){this.publicado = 0;}
}

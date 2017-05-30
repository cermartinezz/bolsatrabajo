package com.bolsaTrabajo.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by mvip on 05-28-17.
 */
@Entity
@Table(name="postulants_publications")
public class PostulantPublication implements Serializable{

    private Integer id;
    private Postulant postulant;
    private Publication publication;
    private Date publicationDate;
    private String publicationCode;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "postulant_id")
    public Postulant getPostulant() {
        return postulant;
    }

    public void setPostulant(Postulant postulant) {
        this.postulant = postulant;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "publication_id")
    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "publication_date")
    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }


    public String getPublicationCode() {
        return publicationCode;
    }

    public void setPublicationCode(String publicationCode) {
        this.publicationCode = publicationCode;
    }
}

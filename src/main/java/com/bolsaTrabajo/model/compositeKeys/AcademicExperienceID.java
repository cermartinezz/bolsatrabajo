package com.bolsaTrabajo.model.compositeKeys;

import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.catalog.AcademicTitleCat;
import com.bolsaTrabajo.model.catalog.Institution;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by keepercito on 05-21-17.
 */

@Embeddable
public class AcademicExperienceID implements Serializable{

    private Postulant postulant;
    private Institution institution;
    private AcademicTitleCat title;

    @ManyToOne
    public AcademicTitleCat getTitle() {
        return title;
    }

    public void setTitle(AcademicTitleCat title) {
        this.title = title;
    }

    @ManyToOne
    public Postulant getPostulant() {
        return postulant;
    }

    public void setPostulant(Postulant postulant) {
        this.postulant = postulant;
    }

    @ManyToOne
    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }
}

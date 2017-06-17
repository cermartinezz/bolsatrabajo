package com.bolsaTrabajo.model.compositeKeys;

import com.bolsaTrabajo.model.catalog.Language;
import com.bolsaTrabajo.model.catalog.LanguageLevel;
import com.bolsaTrabajo.model.jobInfo.JobProfile;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class JobProfileLanguageId implements Serializable {


    private JobProfile jobProfile;
    private Language language;
    private LanguageLevel languageLevel;

    @ManyToOne
    @JsonIgnore
    public JobProfile getJobProfile() {
        return jobProfile;
    }

    public void setJobProfile(JobProfile jobProfile) {
        this.jobProfile = jobProfile;
    }

    @ManyToOne
    @JsonIgnore
    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    @ManyToOne
    @JsonIgnore
    public LanguageLevel getLanguageLevel() {
        return languageLevel;
    }

    public void setLanguageLevel(LanguageLevel languageLevel) {
        this.languageLevel = languageLevel;
    }
}
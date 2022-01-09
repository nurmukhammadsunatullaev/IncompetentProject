package org.undp.incompetent.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "case_declarant_table")
public class CaseDeclarantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "declarant_id")
    @JsonIgnore
    private Integer id;

    @Column(name = "declarant_name")
    private String name;

    @ManyToMany(mappedBy = "declarants")
    @JsonIgnore
    private List<CaseTypeEntity> caseTypes;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CaseTypeEntity> getCaseTypes() {
        return caseTypes;
    }

    public void setCaseTypes(List<CaseTypeEntity> caseTypes) {
        this.caseTypes = caseTypes;
    }
}

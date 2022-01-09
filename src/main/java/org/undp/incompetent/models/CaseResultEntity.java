package org.undp.incompetent.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "case_result_table")
public class CaseResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "result_id")
    @JsonIgnore
    private Integer id;

    @Column(name = "result_name")
    private String name;

    @ManyToMany(mappedBy = "results")
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

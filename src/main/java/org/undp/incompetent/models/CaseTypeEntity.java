package org.undp.incompetent.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "case_type_table")
public class CaseTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "type_id")
    @JsonIgnore
    private Integer id;

    @Column(name = "type_name")
    private String name;

    @ManyToMany(cascade = {CascadeType.REMOVE})
    @JoinTable(name = "type_result",
    joinColumns = @JoinColumn(name = "type_id"),
    inverseJoinColumns = @JoinColumn(name = "result_id"))
    @JsonIgnore
    private List<CaseResultEntity> results;


    @ManyToMany(cascade = {CascadeType.REMOVE})
    @JoinTable(name = "type_declarant",
            joinColumns = @JoinColumn(name = "type_id"),
            inverseJoinColumns = @JoinColumn(name = "declarant_id"))
    @JsonIgnore
    private List<CaseDeclarantEntity> declarants;


    @ManyToMany(cascade = {CascadeType.REMOVE})
    @JoinTable(name = "type_court",
            joinColumns = @JoinColumn(name = "type_id"),
            inverseJoinColumns = @JoinColumn(name = "court_id"))
    @JsonIgnore
    private List<CourtEntity> courts;

    public List<CourtEntity> getCourts() {
        return courts;
    }

    public void setCourts(List<CourtEntity> courts) {
        this.courts = courts;
    }

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

    public List<CaseResultEntity> getResults() {
        return results;
    }

    public void setResults(List<CaseResultEntity> results) {
        this.results = results;
    }

    public List<CaseDeclarantEntity> getDeclarants() {
        return declarants;
    }

    public void setDeclarants(List<CaseDeclarantEntity> declarants) {
        this.declarants = declarants;
    }
}

package org.undp.incompetent.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="court_table")
public class CourtEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="court_id")
    @JsonIgnore
    private Integer id;

    @Column(name="court_name")
    private String name;

    @Column(name = "court_address")
    @JsonIgnore
    private String address;

    @Column(name="court_parent_id")
    @JsonIgnore
    private Integer parentId;

    @Column(name="court_type")
    @JsonIgnore
    private Integer type;

    @ManyToOne
    @JoinColumn(name = "court_parent_id", insertable = false, updatable = false)
    @JsonIgnore
    private CourtEntity parent;

    @OneToMany(mappedBy = "parent")
    @JsonIgnore
    private List<CourtEntity> children;

    public List<UserEntity> getUserList() {
        return userList;
    }

    public void setUserList(List<UserEntity> userList) {
        this.userList = userList;
    }

    @ManyToMany(mappedBy = "courts")
    @JsonIgnore
    public List<CaseTypeEntity> caseTypes;

    @OneToMany(mappedBy = "courtEntity")
    @JsonIgnore
    private List<UserEntity> userList;

    public CourtEntity() {

    }

    public void setCaseTypes(List<CaseTypeEntity> caseTypes) {
        this.caseTypes = caseTypes;
    }

    public List<CaseTypeEntity> getCaseTypes() {
        return caseTypes;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer value) {
        this.type = value;
    }

    public CourtEntity getParent() {
        return parent;
    }

    public void setParent(CourtEntity parent) {
        this.parent = parent;
    }

    public List<CourtEntity> getChildren() {
        return children;
    }

    public void setChildren(List<CourtEntity> children) {
        this.children = children;
    }
}

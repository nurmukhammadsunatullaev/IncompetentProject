package org.undp.incompetent.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "country_table")
public class CountryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="area_id")
    @JsonIgnore
    private Integer id;

    @Column(name="area_name")
    private String name;

    @Column(name="area_parent_id")
    @JsonIgnore
    private Integer parentId;

    @Column(name="area_value")
    @JsonIgnore
    private Integer value;

    @Column(name = "area_order")
    @JsonIgnore
    private Integer order;

    @ManyToOne
    @JoinColumn(name = "area_parent_id", insertable = false, updatable = false)
    @JsonIgnore
    private CountryEntity parent;

    @OneToMany(mappedBy = "parent")
    @JsonIgnore
    private List<CountryEntity> children;


    @JsonProperty("region_name")
    public String getParentName(){
        return parent.name;
    }

    public CountryEntity() {
    }


    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public CountryEntity getParent() {
        return parent;
    }

    public void setParent(CountryEntity parent) {
        this.parent = parent;
    }

    public List<CountryEntity> getChildren() {
        return children;
    }

    public void setChildren(List<CountryEntity> children) {
        this.children = children;
    }
}

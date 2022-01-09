package org.undp.incompetent.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "incompetent_table")
public class IncompetentEntity implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "incompetent_id")
    @JsonIgnore
    private Long incompetentId;

    @NotBlank(message = "Майдонни тўлдиринг")
    @Column(name = "incompetent_surname")
    private String incompetentSurname;

    @NotBlank(message = "Майдонни тўлдиринг")
    @Column(name = "incompetent_firstname")
    private String incompetentFirstname;

    @NotBlank(message = "Майдонни тўлдиринг")
    @Column(name = "incompetent_patronymic")
    private String incompetentPatronymic;

    @NotBlank(message = "Майдонни тўлдиринг")
    @Column(name = "incompetent_passport")
    private String incompetentPassport;

    @Column(name = "incompetent_passport_type")
    @ColumnDefault("true")
    @JsonIgnore
    private Boolean incompetentPassportType;

    @NotNull(message = "Майдонни тўлдиринг")
    @Column(name = "incompetent_sex")
    private Boolean incompetentSex;

    @NotNull(message = "Майдонни тўлдиринг")
    @Column(name = "incompetent_birthday")
    private Date incompetentBirthday;

    @NotBlank(message = "Майдонни тўлдиринг")
    @Column(name = "incompetent_address")
    private String incompetentAddress;

    @NotNull(message = "Майдонни тўлдиринг")
    @Column(name = "incompetent_city_id")
    @JsonIgnore
    private Integer incompetentCityId;

    @ManyToOne
    @JoinColumn(name="incompetent_city_id", insertable = false,updatable = false)
    private CountryEntity incompetentCity;

    @OneToMany(mappedBy = "incompetent",cascade = CascadeType.REMOVE)
    private List<IncompetentCaseEntity> incompetentCaseList;

    @Column(name = "court_id")
    @JsonIgnore
    private Integer courtId;


    @ManyToOne
    @JoinColumn(name="court_id", insertable = false,updatable = false)
    private CourtEntity courtEntity;

    @Column(name = "date_entry", insertable = false, updatable = false)
    @JsonIgnore
    private Date dateOfDataEntry;

    @Column(name = "date_update", insertable = false)
    @JsonIgnore
    private Date dateOfDataUpdate;


    public IncompetentEntity() {

    }

    public String getIncompetentSurname() {
        return incompetentSurname;
    }

    public void setIncompetentSurname(String incompetentSurname) {
        this.incompetentSurname = incompetentSurname;
    }

    public String getIncompetentFirstname() {
        return incompetentFirstname;
    }

    public void setIncompetentFirstname(String incompetentFirstname) {
        this.incompetentFirstname = incompetentFirstname;
    }

    public String getIncompetentPatronymic() {
        return incompetentPatronymic;
    }

    public void setIncompetentPatronymic(String incompetentPatronymic) {
        this.incompetentPatronymic = incompetentPatronymic;
    }


    public Boolean getIncompetentPassportType() {
        return incompetentPassportType;
    }

    public void setIncompetentPassportType(Boolean incompetentPassportType) {
        this.incompetentPassportType = incompetentPassportType;
    }

    public CourtEntity getCourtEntity() {
        return courtEntity;
    }

    public void setCourtEntity(CourtEntity courtEntity) {
        this.courtEntity = courtEntity;
    }

    public Date getDateOfDataEntry() {
        return dateOfDataEntry;
    }

    public void setDateOfDataEntry(Date dateOfDataEntry) {
        this.dateOfDataEntry = dateOfDataEntry;
    }

    public Date getDateOfDataUpdate() {
        return dateOfDataUpdate;
    }

    public void setDateOfDataUpdate(Date dateOfDataUpdate) {
        this.dateOfDataUpdate = dateOfDataUpdate;
    }


    public CountryEntity getIncompetentCity() {
        return incompetentCity;
    }

    public void setIncompetentCity(CountryEntity incompetentCity) {
        this.incompetentCity = incompetentCity;
    }

    public Long getIncompetentId() {
        return incompetentId;
    }

    public void setIncompetentId(Long incompetentId) {
        this.incompetentId = incompetentId;
    }

    public String getIncompetentPassport() {
        return incompetentPassport;
    }

    public void setIncompetentPassport(String incompetentPassport) {
        this.incompetentPassport = incompetentPassport;
    }

    public Boolean getIncompetentSex() {
        return incompetentSex;
    }

    public void setIncompetentSex(Boolean incompetentSex) {
        this.incompetentSex = incompetentSex;
    }

    public Date getIncompetentBirthday() {
        return incompetentBirthday;
    }

    public void setIncompetentBirthday(Date incompetentBirthday) {
        this.incompetentBirthday = incompetentBirthday;
    }

    public String getIncompetentAddress() {
        return incompetentAddress;
    }

    public void setIncompetentAddress(String incompetentAddress) {
        this.incompetentAddress = incompetentAddress;
    }

    public Integer getIncompetentCityId() {
        return incompetentCityId;
    }

    public void setIncompetentCityId(Integer incompetentCityId) {
        this.incompetentCityId = incompetentCityId;
    }

    public List<IncompetentCaseEntity> getIncompetentCaseList() {
        return incompetentCaseList;
    }

    public void setIncompetentCaseList(List<IncompetentCaseEntity> incompetentCaseList) {
        this.incompetentCaseList = incompetentCaseList;
    }

    public Integer getCourtId() {
        return courtId;
    }

    public void setCourtId(Integer courtId) {
        this.courtId = courtId;
    }
}

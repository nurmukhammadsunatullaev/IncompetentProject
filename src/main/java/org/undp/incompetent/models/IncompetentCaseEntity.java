package org.undp.incompetent.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotBlank;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "incompetent_case_table")
public class IncompetentCaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "incompetent_case_id")
    @JsonIgnore
    private Long incompetentCaseId;

    @Column(name = "incompetent_case_type")
    @NotNull(message = "Майдонни тўлдиринг")
    @JsonIgnore
    private Integer incompetentCaseType;

    @ManyToOne
    @JoinColumn(name="incompetent_case_type", insertable = false,updatable = false)
    private CaseTypeEntity caseTypeEntity;

    @Column(name = "judgment_court_id")
    @NotNull(message = "Майдонни тўлдиринг")
    @JsonIgnore
    private Integer judgmentCourtId;

    @ManyToOne
    @JoinColumn(name="judgment_court_id", insertable = false,updatable = false)
    private CourtEntity judmentCourt;


    @Column(name = "judgment_date")
    @NotNull(message = "Майдонни тўлдиринг")
    private Date judgmentDate;

    @Column(name = "judgment_number")
    @NotBlank(message = "Майдонни тўлдиринг")
    private String judgmentNumber;


    @Column(name = "case_result")
    @JsonIgnore
    private Integer caseResult;

    @ManyToOne
    @JoinColumn(name="case_result", insertable = false,updatable = false)
    private CaseResultEntity caseResultEntity;

    @Column(name = "declarant_type")
    @NotNull(message = "Майдонни тўлдиринг")
    @JsonIgnore
    private Integer declarantType;

    @ManyToOne
    @JoinColumn(name="declarant_type", insertable = false,updatable = false)
    private CaseDeclarantEntity caseDeclarantEntity;


    @Column(name = "declarant_name")
    @NotBlank(message = "Майдонни тўлдиринг")
    private String declarantName;


    @Column(name="incompetent_id", nullable=false)
    @JsonIgnore
    private Long incompetentId;

    @ManyToOne
    @JoinColumn(name="incompetent_id", insertable = false,updatable = false)
    @JsonIgnore
    private IncompetentEntity incompetent;

    @Column(name = "case_date_entry")
    @JsonIgnore
    private Date dateOfDataEntry;


    @Column(name = "case_date_update")
    @JsonIgnore
    private Date dateOfDataUpdate;

    public IncompetentCaseEntity() {

    }

    public Long getIncompetentId() {
        return incompetentId;
    }

    public void setIncompetentId(Long incompetentId) {
        this.incompetentId = incompetentId;
    }

    public IncompetentCaseEntity(IncompetentEntity incompetentEntity,CaseTypeEntity caseTypeEntity) {
        setIncompetent(incompetentEntity);
        setCaseTypeEntity(caseTypeEntity);
    }

    public Date getDateOfDataUpdate() {
        return dateOfDataUpdate;
    }

    public void setDateOfDataUpdate(Date dateOfDataUpdate) {
        this.dateOfDataUpdate = dateOfDataUpdate;
    }

    public Date getDateOfDataEntry() {
        return dateOfDataEntry;
    }

    public void setDateOfDataEntry(Date dateOfDataEntry) {
        this.dateOfDataEntry = dateOfDataEntry;
    }

    public CourtEntity getJudmentCourt() {
        return judmentCourt;
    }

    public void setJudmentCourt(CourtEntity judmentCourt) {
        this.judmentCourt = judmentCourt;
        this.judgmentCourtId=judmentCourt.getId();
    }

    public CaseTypeEntity getCaseTypeEntity() {
        return caseTypeEntity;
    }

    public void setCaseTypeEntity(CaseTypeEntity caseTypeEntity) {
        this.caseTypeEntity = caseTypeEntity;
        this.incompetentCaseType=caseTypeEntity.getId();
    }

    public CaseResultEntity getCaseResultEntity() {
        return caseResultEntity;
    }

    public void setCaseResultEntity(CaseResultEntity caseResultEntity) {
        this.caseResultEntity = caseResultEntity;
    }

    public CaseDeclarantEntity getCaseDeclarantEntity() {
        return caseDeclarantEntity;
    }

    public void setCaseDeclarantEntity(CaseDeclarantEntity caseDeclarantEntity) {
        this.caseDeclarantEntity = caseDeclarantEntity;
    }

    public Long getIncompetentCaseId() {
        return incompetentCaseId;
    }

    public void setIncompetentCaseId(Long incompetentCaseId) {
        this.incompetentCaseId = incompetentCaseId;
    }

    public Integer getIncompetentCaseType() {
        return incompetentCaseType;
    }

    public void setIncompetentCaseType(Integer incompetentCaseType) {
        this.incompetentCaseType = incompetentCaseType;
    }

    public Integer getJudgmentCourtId() {
        return judgmentCourtId;
    }

    public void setJudgmentCourtId(Integer judgmentCourtId) {
        this.judgmentCourtId = judgmentCourtId;
    }

    public Date getJudgmentDate() {
        return judgmentDate;
    }

    public void setJudgmentDate(Date judgmentDate) {
        this.judgmentDate = judgmentDate;
    }

    public String getJudgmentNumber() {
        return judgmentNumber;
    }

    public void setJudgmentNumber(String judgmentNumber) {
        this.judgmentNumber = judgmentNumber;
    }

    public Integer getCaseResult() {
        return caseResult;
    }

    public void setCaseResult(Integer caseResult) {
        this.caseResult = caseResult;
    }

    public Integer getDeclarantType() {
        return declarantType;
    }

    public void setDeclarantType(Integer declarantType) {
        this.declarantType = declarantType;
    }

    public String getDeclarantName() {
        return declarantName;
    }

    public void setDeclarantName(String declarantName) {
        this.declarantName = declarantName;
    }

    public IncompetentEntity getIncompetent() {
        return incompetent;
    }

    public void setIncompetent(IncompetentEntity incompetent) {
        this.incompetent = incompetent;
        this.incompetentId=incompetent.getIncompetentId();

    }
}

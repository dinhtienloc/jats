package vn.locdt.jats.addon.entity.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by cuong on 10/30/2016.
 */
@Entity
@Table(name = "thesis")
public class Thesis {

    @JsonProperty("thesis_id")
    private int thesisId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("status")
    private ThesisStatus status;

    @JsonProperty("student")
    private Student student;

    @JsonProperty("first_supervisor")
    private Supervisor firstSupervisor;

    @JsonProperty("second_supervisor")
    private Supervisor secondSupervisor;

    @JsonProperty("first_supervisor_accepted")
    private int firstSupervisorAccepted = -1;

    @JsonProperty("second_supervisor_accepted")
    private int secondSupervisorAccepted = -1;

    @JsonProperty("english_type")
    private int englishType = -1;

    @JsonProperty("first_reviewer")
    private Supervisor firstReviewer;

    @JsonProperty("second_reviewer")
    private Supervisor secondReviewer;

    private ThesisRevision thesisRevision;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "first_reviewer_id")
    public Supervisor getFirstReviewer() {
        return firstReviewer;
    }

    public void setFirstReviewer(Supervisor firstReviewer) {
        this.firstReviewer = firstReviewer;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "second_reviewer_id")
    public Supervisor getSecondReviewer() {
        return secondReviewer;
    }

    public void setSecondReviewer(Supervisor secondReviewer) {
        this.secondReviewer = secondReviewer;
    }

    @OneToOne(mappedBy = "thesis", fetch = FetchType.EAGER)
    public ThesisRevision getThesisRevision() {
        return thesisRevision;
    }

    public void setThesisRevision(ThesisRevision thesisRevision) {
        this.thesisRevision = thesisRevision;
    }

    @Basic
    @Column(name = "second_supervisor_accepted")
    public int getSecondSupervisorAccepted() {
        return secondSupervisorAccepted;
    }

    public void setSecondSupervisorAccepted(int secondSupervisorAccepted) {
        this.secondSupervisorAccepted = secondSupervisorAccepted;
    }

    @Basic
    @Column(name = "first_supervisor_accepted")
    public int getFirstSupervisorAccepted() {
        return firstSupervisorAccepted;
    }

    public void setFirstSupervisorAccepted(int firstSupervisorAccepted) {
        this.firstSupervisorAccepted = firstSupervisorAccepted;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "second_supervisor_id")
    public Supervisor getSecondSupervisor() {
        return secondSupervisor;
    }

    public void setSecondSupervisor(Supervisor secondSupervisor) {
        this.secondSupervisor = secondSupervisor;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "first_supervisor_id")
    public Supervisor getFirstSupervisor() {
        return firstSupervisor;
    }

    public void setFirstSupervisor(Supervisor firstSupervisor) {
        this.firstSupervisor = firstSupervisor;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status")
    public ThesisStatus getStatus() {
        return status;
    }

    public void setStatus(ThesisStatus status) {
        this.status = status;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "thesis_id")
    public int getThesisId() {
        return thesisId;
    }

    public void setThesisId(int thesisId) {
        this.thesisId = thesisId;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "english_type")
    public int getEnglishType() { return englishType; }

    public void setEnglishType(int englishType) { this.englishType = englishType; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Thesis thesis = (Thesis) o;

        if (thesisId != thesis.thesisId) return false;
        if (status != thesis.status) return false;
        if (title != null ? !title.equals(thesis.title) : thesis.title != null) return false;
        if (description != null ? !description.equals(thesis.description) : thesis.description != null) return false;

        return true;
    }
}

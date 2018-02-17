package vn.locdt.jats.addon.entity.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Created by dinht_000 on 12/13/2016.
 */
@Entity
@Table(name = "council")
public class Council {
    @JsonProperty("council_id")
    private int councilId;

    @JsonProperty("council_name")
    private String councilName;

    @JsonProperty("president")
    private Supervisor president;

    @JsonProperty("secretary")
    private Supervisor secretary;

    @JsonProperty("reviewer")
    private Set<Supervisor> reviewers;

    @JsonProperty("location")
    private String location;

    @JsonProperty("thesis")
    private List<Thesis> theses;

    @Basic
    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Transient
    public List<Thesis> getTheses() {
        return theses;
    }

    public void setTheses(List<Thesis> theses) {
        this.theses = theses;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "council_id")
    public int getCouncilId() {
        return councilId;
    }

    public void setCouncilId(int councilId) {
        this.councilId = councilId;
    }

    @Basic
    @Column(name = "council_name")
    public String getCouncilName() {
        return councilName;
    }

    public void setCouncilName(String councilName) {
        this.councilName = councilName;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "president_id")
    public Supervisor getPresident() {
        return president;
    }

    public void setPresident(Supervisor president) {
        this.president = president;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "secretary_id")
    public Supervisor getSecretary() {
        return secretary;
    }

    public void setSecretary(Supervisor secretary) {
        this.secretary = secretary;
    }

    @OneToMany(mappedBy = "council", fetch = FetchType.EAGER)
    public Set<Supervisor> getReviewers() {
        return reviewers;
    }

    public void setReviewers(Set<Supervisor> reviewers) {
        this.reviewers = reviewers;
    }

    @Override
    public String toString() {
        return "Council{" +
                "councilId=" + councilId +
                ", councilName='" + councilName + '\'' +
                ", president=" + president +
                ", secretary=" + secretary +
                ", reviewer=" + reviewers +
                '}';
    }
}

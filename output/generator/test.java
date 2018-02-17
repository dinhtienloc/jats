package output.generator;

import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import output.generator.Thesis;
import javax.persistence.GenerationType;

@Entity
@Table(name="thesis_revision")
public class ThesisRevision {
	private Integer revisionId;
	private Thesis thesis;
	private String title;
	private String description;
	private Integer firstSupervisorId;
	private Integer secondSupervisorId;
	private Integer firstSupervisorAccepted;
	private Integer secondSupervisorAccepted;


    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    @Column(name = "revision_id")
	public Integer getInteger() {
		return revisionId;
	}

	public void setInteger(Integer revisionId){
		this.revisionId = revisionId;
	}

    @Column(name = "thesis_id")
	public Integer getInteger() {
		return thesisId;
	}

	public void setInteger(Integer thesisId){
		this.thesisId = thesisId;
	}

    @Column(name = "title")
	public String getString() {
		return title;
	}

	public void setString(String title){
		this.title = title;
	}

    @Column(name = "description")
	public String getString() {
		return description;
	}

	public void setString(String description){
		this.description = description;
	}

    @Column(name = "first_supervisor_id")
	public Integer getInteger() {
		return firstSupervisorId;
	}

	public void setInteger(Integer firstSupervisorId){
		this.firstSupervisorId = firstSupervisorId;
	}

    @Column(name = "second_supervisor_id")
	public Integer getInteger() {
		return secondSupervisorId;
	}

	public void setInteger(Integer secondSupervisorId){
		this.secondSupervisorId = secondSupervisorId;
	}

    @Column(name = "first_supervisor_accepted")
	public Integer getInteger() {
		return firstSupervisorAccepted;
	}

	public void setInteger(Integer firstSupervisorAccepted){
		this.firstSupervisorAccepted = firstSupervisorAccepted;
	}

    @Column(name = "second_supervisor_accepted")
	public Integer getInteger() {
		return secondSupervisorAccepted;
	}

	public void setInteger(Integer secondSupervisorAccepted){
		this.secondSupervisorAccepted = secondSupervisorAccepted;
	}
}



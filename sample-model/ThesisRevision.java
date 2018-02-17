package vn.locdt.jats.addon.entity.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

/**
 * sửa đổi khóa luận đã đăng ký
 * Created by dinht_000 on 12/7/2016.
 */
@Entity
@Table(name = "thesis_revision")
public class ThesisRevision {
    @JsonProperty("revision_id")
    private int revisionId;

    @JsonProperty("thesis")
    private Thesis thesis;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("first_supervisor")
    private Supervisor firstSupervisor;

    @JsonProperty("second_supervisor")
    private Supervisor secondSupervisor;

    @JsonProperty("first_supervisor_accepted")
    private int firstSupervisorAccepted = -1;

    @JsonProperty("second_supervisor_accepted")
    private int secondSupervisorAccepted = -1;

    /**
     * lấy tên giảng viên hướng dẫn thứ nhất
     * @return tên giảng viên hướng dẫn thứ nhất
     */
    @Basic
    @Column(name = "first_supervisor_accepted")
    public int getFirstSupervisorAccepted() {
        return firstSupervisorAccepted;
    }

    /**
     * thiết lập tên giảng viên hướng dẫn thứ nhất
     * @param firstSupervisorAccepted tên giảng viên hướng dẫn thứ nhất
     */
    public void setFirstSupervisorAccepted(int firstSupervisorAccepted) {
        this.firstSupervisorAccepted = firstSupervisorAccepted;
    }

    /**
     * lấy tên giảng viên hướng dẫn thứ hai
     * @return tên giảng viên hướng dẫn thứ hai
     */
    @Basic
    @Column(name = "second_supervisor_accepted")
    public int getSecondSupervisorAccepted() {
        return secondSupervisorAccepted;
    }

    /**
     * thiết lập tên giảng viên hướng dẫn thứ hai
     * @param secondSupervisorAccepted tên giảng viên hướng dẫn thứ hai
     */
    public void setSecondSupervisorAccepted(int secondSupervisorAccepted) {
        this.secondSupervisorAccepted = secondSupervisorAccepted;
    }

    /**
     * lấy id thiết lập lại
     * @return id thiết lập lại
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "revision_id")
    public int getRevisionId() {
        return revisionId;
    }

    /**
     * thiết lập id
     * @param revisionId id thiết lập lại
     */
    public void setRevisionId(int revisionId) {
        this.revisionId = revisionId;
    }

    /**
     * lấy thông tin khóa luận
     * @return thông tin khóa luận
     */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "thesis_id")
    public Thesis getThesis() {
        return thesis;
    }

    /**
     * thiết lập thông tin khóa luận
     * @param thesis thông tin khóa luận
     */
    public void setThesis(Thesis thesis) {
        this.thesis = thesis;
    }

    /**
     * lấy tiêu đề
     * @return tiêu đề khóa luận
     */
    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    /**
     * thiết lập tiêu đề khóa luận
     * @param title tiêu đề khóa luận
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * lấy mô tả khóa luận
     * @return mô tả khóa luận
     */
    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    /**
     * thiết lập mô tả khóa luận
     * @param description mô tả khóa luận
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * lấy giảng viên hướng dẫn thứ nhất
     * @return giảng viên hướng dẫn thứ nhất
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "first_supervisor_id")
    public Supervisor getFirstSupervisor() {
        return firstSupervisor;
    }

    /**
     * thiết lập giảng viên hướng dẫn thứ nhất
     * @param firstSupervisor giảng viên hướng dẫn thứ nhất
     */
    public void setFirstSupervisor(Supervisor firstSupervisor) {
        this.firstSupervisor = firstSupervisor;
    }

    /**
     * lấy giảng viên hướng dẫn thứ hai
     * @return giảng viên hướng dẫn thứ hai
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "second_supervisor_id")
    public Supervisor getSecondSupervisor() {
        return secondSupervisor;
    }

    /**
     * thiết lập giảng viên hướng dẫn thứ hai
     * @param secondSupervisor giảng viên hướng dẫn thứ hai
     */
    public void setSecondSupervisor(Supervisor secondSupervisor) {
        this.secondSupervisor = secondSupervisor;
    }

    @Override
    public String toString() {
        return "ThesisRevision{" +
                "revisionId=" + revisionId +
                ", thesis=" + thesis +
                ", firstSup=" + firstSupervisor +
                ", secondSup=" + secondSupervisor +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}


package vn.locdt.jats.addon.entity.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.org.apache.regexp.internal.RE;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by cuong on 10/30/2016.
 */
@Entity
@Table(name = "supervisor")
public class Supervisor {

    @JsonProperty("supervisor_id")
    private int supervisorId; // id của supervisor

    @JsonProperty("user")
    private User user; // user của supervisor

    @JsonProperty("supervisor_code")
    private String supervisorCode; // mã supervisor

    @JsonProperty("name")
    private String name; // tên supervisor

    @JsonProperty("degree")
    private String degree; // Học hàm, học vị

    @JsonProperty("research_intro")
    private String researchIntro; // Lĩnh vực nghiên cứu

    @JsonProperty("office")
    private String office;

    @JsonProperty("department")
    private Department department; // Khoa

    @JsonProperty("theses")
    private Set<Thesis> theses; // Khóa luận

    @JsonProperty("co_theses")
    private Set<Thesis> coTheses;

    @JsonProperty("r_theses")
    private Set<Thesis> rTheses; // Khóa luận

    @JsonProperty("r_co_theses")
    private Set<Thesis> rCoTheses;

    @JsonProperty("researches")
    private Set<Research> researches;

    @JsonProperty("council")
    private Council council;

    @JsonProperty("thresold")
    private int thresold;

    @OneToMany(mappedBy = "secondReviewer", fetch = FetchType.EAGER)
    public Set<Thesis> getrCoTheses() {
        return rCoTheses;
    }

    public void setrCoTheses(Set<Thesis> rCoTheses) {
        this.rCoTheses = rCoTheses;
    }

    @OneToMany(mappedBy = "firstReviewer", fetch = FetchType.EAGER)
    public Set<Thesis> getrTheses() {
        return rTheses;
    }

    public void setrTheses(Set<Thesis> rTheses) {
        this.rTheses = rTheses;
    }

    public int countAvailableThesis() {
        int available = thresold;
        if (theses != null) {
            for (Thesis thesis :theses)
                if (thesis.getFirstSupervisorAccepted() == 1)
                    available--;
        }

        if (coTheses != null) {
            for (Thesis thesis : coTheses)
                if (thesis.getSecondSupervisorAccepted() == 1)
                    available--;
        }
        return available;
    }

    @Basic
    @Column(name = "thresold")
    public int getThresold() {
        return thresold = 5;
    }

    public void setThresold(int thresold) {
        this.thresold = thresold;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "council_id")
    public Council getCouncil() {
        return council;
    }

    public void setCouncil(Council council) {
        this.council = council;
    }

    @Basic
    @Column(name = "office")
    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    /**
     * Lấy research
     * @return research
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "supervisor_research",
            joinColumns = @JoinColumn(name = "supervisor_id"),
            inverseJoinColumns = @JoinColumn(name = "research_id")
    )
    public Set<Research> getResearches() {
        return researches;
    }

    /**
     * Đặt research
     * @param researches
     */
    public void setResearches(Set<Research> researches) {
        this.researches = researches;
    }

    /**
     * Lấy học hàm, học vị
     * @return học hàm, học vị
     */
    @Basic
    @Column(name = "degree")
    public String getDegree() {
        return degree;
    }

    /**
     * Đặt học hàm, học vị
     * @param degree
     */
    public void setDegree(String degree) {
        this.degree = degree;
    }

    /**
     * Lấy đồng khóa luận
     * @return đồng khóa luận
     */
    @OneToMany(mappedBy = "secondSupervisor", fetch = FetchType.EAGER)
    public Set<Thesis> getCoTheses() {
        return coTheses;
    }

    /**
     * Đặt đồng khóa luận
     * @param coTheses
     */
    public void setCoTheses(Set<Thesis> coTheses) {
        this.coTheses = coTheses;
    }

    /**
     * Lấy khóa luận
     * @return theses
     */
    @OneToMany(mappedBy = "firstSupervisor", fetch = FetchType.EAGER)
    public Set<Thesis> getTheses() {
        return theses;
    }

    /**
     * Đặt khóa luận
     * @param theses
     */
    public void setTheses(Set<Thesis> theses) {
        this.theses = theses;
    }

    /**
     * Lấy hướng nghiên cứu
     * @return hướng nghiên cứu
     */
    @Basic
    @Column(name = "research_intro")
    public String getResearchIntro() {
        return researchIntro;
    }

    /**
     * Đặt hướng nghiên cứu
     * @param researchIntro
     */
    public void setResearchIntro(String researchIntro) {
        this.researchIntro = researchIntro;
    }

    /**
     * Lấy khoa
     * @return khoa
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    public Department getDepartment() {
        return department;
    }

    /**
     * Đặt khoa
     * @param department
     */
    public void setDepartment(Department department) {
        this.department = department;
    }

    /**
     * Lấy user của supervisor
     * @return user của supervisor
     */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    /**
     * Đặt user cho supervisor
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Lấy id của supervisor
     * @return id của supervisor
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supervisor_id")
    public int getSupervisorId() {
        return supervisorId;
    }

    /**
     * Đặt id cho supervisor
     * @param supervisorId
     */
    public void setSupervisorId(int supervisorId) {
        this.supervisorId = supervisorId;
    }

    /**
     * Lấy mã supervisor
     * @return mã supervisor
     */
    @Column(name = "supervisor_code")
    public String getSupervisorCode() {
        return supervisorCode;
    }

    /**
     * Đặt mã supervisor
     * @param supervisorCode
     */
    public void setSupervisorCode(String supervisorCode) {
        this.supervisorCode = supervisorCode;
    }

    /**
     * Lấy tên của supervisor
     * @return tên supervisor
     */
    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    /**
     * Đặt tên cho supervisor
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Kiểm tra xem hai supervisor có giống nhau không
     * @param o
     * @return true/false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Supervisor that = (Supervisor) o;

        if (supervisorCode != null ? !supervisorCode.equals(that.supervisorCode) : that.supervisorCode != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    /**
     * Lấy mã băm của supervisor
     * @return mã băm của supervisor
     */
    @Override
    public int hashCode() {
        int result = supervisorCode != null ? supervisorCode.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public String researchesAsString() {
        StringBuilder res = new StringBuilder();
        for (Research research : researches)
            res.append(research.getName()).append(", ");
        return res.toString();
    }

    @Override
    public String toString() {
        return "Supervisor{" +
                "supervisorId=" + supervisorId +
                ", user=" + user +
                ", supervisorCode='" + supervisorCode + '\'' +
                ", name='" + name + '\'' +
                ", degree='" + degree + '\'' +
                ", researchIntro='" + researchIntro + '\'' +
                ", office='" + office + '\'' +
                ", department=" + department +
                ", theses=" + theses +
                ", coTheses=" + coTheses +
                ", rTheses=" + rTheses +
                ", rCoTheses=" + rCoTheses +
                ", researches=" + researches +
                ", council=" + council +
                ", thresold=" + thresold +
                '}';
    }
}

package vn.locdt.jats.addon.entity.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by dinht_000 on 10/29/2016.
 */
@Entity
@Table(name = "specialization")
public class Specialization {

    @JsonProperty("spec_id")
    private int specId; // id của specialization

    @JsonProperty("name")
    private String name; // tên của specialization

    @JsonProperty("faculty")
    private Faculty faculty; // faculty của specialization

    @JsonProperty("students")
    private Set<Student> students;

    @OneToMany(mappedBy = "specialization", fetch = FetchType.EAGER)
    
    /**
     * Lấy sinh viên của chuyên ngành
     * @return sinh viên của chuyên ngành
    */
    public Set<Student> getStudents() {
        return students;
    }

    /**
     * Đặt sinh viên cho chuyên ngành
     * @param students sinh viên đặt cho chuyên ngành
    */
    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    /**
     * Lấy khoa của chuyên ngành
     * @return khoa của chuyên ngành
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "faculty_id")
    public Faculty getFaculty() {
        return faculty;
    }
    
    /**
     * Đặt khoa cho chuyên ngành
     * @param faculty khoa
    */
    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    /**
     * Lấy id của chuyên ngành
     * @return id của chuyên ngành
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "spec_id")
    public int getSpecId() {
        return specId;
    }

    /**
     * Đặt id cho chuyên ngành
     * @param specId id của chuyên ngành
     */
    public void setSpecId(int specId) {
        this.specId = specId;
    }

    /**
     * Lấy tên của chuyên ngành
     * @return tên của chuyên ngành
     */
    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }
    
    /**
     * Đặt tên cho chuyên ngành
     * @param name tên chuyên ngành
    */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Specialization{" +
                "specId=" + specId +
                ", name='" + name + '\'' +
                ", faculty=" + faculty.getFacultyId() +
                '}';
    }
}

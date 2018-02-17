package vn.locdt.jats.addon.entity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.lang.Class;
import java.util.Set;

/**
 * Created by cuong on 10/24/2016.
 */
@Entity
@Table(name = "faculty")
public class Faculty {

    @JsonProperty("id")
    private int facultyId;
    @JsonProperty("name")
    private String name;

    @JsonIgnore
    private Set<Department> departments; 

    @JsonIgnore
    private Set<Specialization> specializations;

    @JsonProperty
    private Set<java.lang.Class> classes;

    /**
     * Lấy danh sách lớp của khoa
     * @return danh sách lớp
     */
    public void setClasses(Set<java.lang.Class> classes) {
        this.classes = classes;
    }

    @OneToMany(mappedBy = "faculty", fetch = FetchType.EAGER)
    public Set<Class> getClasses() {
        return classes;
    }

    /**
     * Lấy specialization của faculty
     * @return specialization
     */
    @OneToMany(mappedBy = "faculty", fetch = FetchType.EAGER)
    public Set<Specialization> getSpecializations() {
        return specializations;
    }

    /**
     * Đặt chuyên ngành cho giảng viên
     * @param specializations chuyên ngành
     */
    public void setSpecializations(Set<Specialization> specializations) {
        this.specializations = specializations;
    }

    /**
     * Lấy id của faculty
     * @return id của faculty
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "faculty_id")
    public int getFacultyId() {
        return facultyId;
    }
    
    /**
     * Đặt id cho faculty
     * @param facultyId
    */
    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    /**
     * Lấy tên của faculty
     * @return tên của faculty
     */
    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }
    
    /**
     * Đặt tên cho faculty
     * @param name
    */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Lấy department của faculty
     * @return department
     */
    @OneToMany(mappedBy = "faculty", fetch = FetchType.EAGER)
    public Set<Department> getDepartments() {
        return departments;
    }
    
    /**
     * Đặt department cho faculty
     * @param departments
    */
    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

    /**
     * Kiểm tra hai faculty có giống nhau không
     * @return true/false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Faculty faculty = (Faculty) o;

        if (facultyId != faculty.facultyId) return false;
        if (name != null ? !name.equals(faculty.name) : faculty.name != null) return false;

        return true;
    }

    /**
     * Lấy mã băm của tên faculty
     * @return mã băm của tên faculty
     */
    @Override
    public int hashCode() {
        int result = facultyId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}

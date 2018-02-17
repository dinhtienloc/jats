package vn.locdt.jats.addon.entity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

/**
 * Created by cuong on 10/24/2016.
 */
@Entity
@Table(name = "department")
public class Department {

    @JsonProperty("id")
    private int departmentId; // id của department

    @JsonProperty("name")
    private String name; // tên của department

    @JsonProperty("faculty")
    private Faculty faculty; // faculty của department

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    
    /**
     * Lấy id của khoa
     * @return departmentId id của khoa
    */
    public int getDepartmentId() {
        return departmentId;
    }
    
    /**
     * Đặt Id cho khoa
     * @param departmentId id cho khoa
    */
    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    @Basic
    @Column(name = "name")
    
    /**
     * Lấy tên của khoa
     * @return name tên khoa
    */
    public String getName() {
        return name;
    }
    
    /**
     * Đặt tên cho khoa
     * @param name tên khoa
    */
    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "faculty_id")
    
    /**
     * Lấy giảng viên của khoa
     * @return giảng viên
    */
    public Faculty getFaculty() {
        return faculty;
    }
    
    /**
     * Đặt giảng viên cho khoa
     * @param faculty giảng viên
    */
    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    /**
     * Kiểm tra hai đối tượng khoa có giống nhau hay không
     * @param o
     * @return true/false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Department that = (Department) o;

        if (departmentId != that.departmentId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    /**
     * Lấy mã băm của id khoa
     * @return mã băm của id khoa
     */
    @Override
    public int hashCode() {
        int result = departmentId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentId=" + departmentId +
                ", name='" + name + '\'' +
                ", faculty=" + (faculty == null ? "null" : faculty.getFacultyId()) +
                '}';
    }
}

package vn.locdt.jats.addon.entity.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

/**
 * Created by dinht_000 on 10/29/2016.
 */
@Entity
@Table(name = "class")
public class Class {

    @JsonProperty("class_id")
    private int classId; // id của class

    @JsonProperty("name")
    private String name; // tên của class

    @JsonProperty("faculty")
    private Faculty faculty;

    /**
     * Lấy khoa quản lý
     * @return khoa
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "faculty_id")
    public Faculty getFaculty() {
        return faculty;
    }

    /**
     * Đặt khoa quản lý
     * @param faculty đối tượng khoa
     */
    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    /**
     * Lấy id của lớp
     * @return id của lớp
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    public int getClassId() {
        return classId;
    }
    
    /**
     * Đặt id cho class
     * @param classId id của class
    */
    public void setClassId(int classId) {
        this.classId = classId;
    }

    /**
     * Lấy tên của class
     * @return tên class
     */
    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }
    
    /**
     * Đặt tên cho class
     * @param name tên của class
    */
    public void setName(String name) {
        this.name = name;
    }
}

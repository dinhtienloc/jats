package vn.locdt.jats.addon.entity.model;

import javax.persistence.*;

/**
 * Created by cuong on 10/30/2016.
 */
@Entity
@Table(name = "faculty_staff")
public class FacultyStaff {

    private int staffId; // id của staff
    private User user; // user của staff
    private String name; // tên staff
    private Faculty faculty; // faculty của staff

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "faculty_id")
    
    /**
     * Lấy faculty của staff
     * @return faculty
    */
    public Faculty getFaculty() {
        return faculty;
    }
    
    /**
     * Đặt faculty cho staff
     * @param faculty
    */
    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    
    /**
     * Lấy id của staff
     * @return id của staff
    */
    public int getStaffId() {
        return staffId;
    }

    /**
     * Đặt id cho staff
     * @param staffId
    */
    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    
    /**
     * Lấy user của staff
     * @return user của staff
    */
    public User getUser() {
        return user;
    }
    
    /**
     * Đặt user cho staff
     * @param user 
    */
    public void setUser(User user) {
        this.user = user;
    }

    @Basic
    @Column(name = "name")
    
    /**
     * Lấy tên của staff
     * @return tên của staff
    */
    public String getName() {
        return name;
    }

    /**
     * Đặt tên cho staff
     * @param name
    */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Kiểm tra xem hai FacultyStaff có giống nhau hay không
     * @param o
     * @return true/false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FacultyStaff that = (FacultyStaff) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    /**
     * Lấy mã băm của tên staff
     * @return mã băm của staff
     */
    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}

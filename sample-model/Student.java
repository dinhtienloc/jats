package vn.locdt.jats.addon.entity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.lang.Class;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

/**
 * Created by cuong on 10/30/2016.
 */
@Entity
@Table(name = "student")
public class Student {

    @JsonProperty("student_id")
    private int studentId; // id của sinh viên

    @JsonProperty("user")
    private User user; // user của sinh viên

    @JsonProperty("code")
    private String studentCode; // mã sinh viên

    @JsonProperty("name")
    private String name; // tên sinh viên

    @JsonProperty("dob")
    private Date dob; // ngày sinh

    @JsonProperty("clazz")
    private java.lang.Class clazz; // lớp

    @JsonProperty("spec")
    private Specialization specialization; // chuyên ngành của sinh viên

    @JsonProperty("regisable")
    private Byte thesisRegistrable = -1; // xác nhận đăng ký khóa luận

    @JsonIgnore
    private Set<Thesis> theses; // đề tài khóa luận

    @Transient
    @JsonProperty("dob_as_string")
    private String dobAsString;

    @Transient
    public String getDobAsString() {
        return dobAsString;
    }

    public void setDobAsString(String dobAsString) {
        this.dobAsString = dobAsString;
    }

    /**
     * Lấy tên đề tài
     * @return tên đề tài
     */
    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    public Set<Thesis> getTheses() {
        return theses;
    }
    
    /**
     * Đặt tên đề tài
     * @param theses tên đề tài
    */
    public void setTheses(Set<Thesis> theses) {
        this.theses = theses;
    }

    /**
     * Lấy tên lớp của sinh viên
     * @return tên lớp của sinh viên
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "class_id")
    public java.lang.Class getClazz() {
        return clazz;
    }
    
    /**
     * Đặt tên lớp cho sinh viên
     * @param clazz tên lớp cho sinh viên
    */
    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    /**
     * Lấy chuyên ngành của sinh viên
     * @return chuyên ngành của sinh viên
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "specialization_id")
    public Specialization getSpecialization() {
        return specialization;
    }
    
    /**
     * Đặt chuyên ngành cho sinh viên
     * @param specialization chuyên ngành
    */
    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    /**
     * Lấy user cho sinh viên
     * @return user của sinh viên
     */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    /**
     * Đặt user cho sinh viên
     * @param user cho sinh viên
    */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Lấy id của sinh viên
     * @return id của sinh viên
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    public int getStudentId() {
        return studentId;
    }
    
    /**
     * Đặt id cho sinh viên
     * @param studentId id cho sinh viên
    */
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    /**
     * Lấy mã sinh viên
     * @return mã sinh viên
     */
    @Column(name = "student_code")
    public String getStudentCode() {
        return studentCode;
    }
    
    /**
     * Đặt mã sinh viên
     * @param studentCode mã sinh viên
    */
    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    /**
     * Lấy tên sinh viên
     * @return tên sinh viên
     */
    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }
    
    /**
     * Đặt tên sinh viên
     * @param name tên sinh viên
    */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * lấy ngày sinh của sinh viên
     * @return ngày sinh
     */
    @Basic
    @Column(name = "dob")
    public Date getDob() {
        return dob;
    }
    
    /**
     * Đặt ngày sinh cho sinh viên
     * @param dob ngày sinh
    */
    public void setDob(Date dob) {
        this.dob = dob;
        if (dob != null) {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            dobAsString = formatter.format(dob);
        }
    }

    /**
     * Lấy trạng thái đăng ký khóa luận
     * @return trạng thái đăng ký khóa luận
     */
    @Basic
    @Column(name = "thesis_registrable")
    public Byte getThesisRegistrable() {
        return thesisRegistrable;
    }

    /**
     * Đặt trạng thái đăng ký khóa luận
     * @param thesisRegistrable trạng thái đăng ký khóa luận
    */
    public void setThesisRegistrable(Byte thesisRegistrable) {
        this.thesisRegistrable = thesisRegistrable;
    }

    /**
     * Kiểm tra xem hai đối tượng sinh viên có giống nhau hay không
     * @param o
     * @return true/false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (studentCode != null ? !studentCode.equals(student.studentCode) : student.studentCode != null) return false;
        if (name != null ? !name.equals(student.name) : student.name != null) return false;
        if (dob != null ? !dob.equals(student.dob) : student.dob != null) return false;
        if (thesisRegistrable != null ? !thesisRegistrable.equals(student.thesisRegistrable) : student.thesisRegistrable != null)
            return false;

        return true;
    }

    /**
     * Lấy mã băm của sinh viên
     * @return mã băm của sinh viên
     */
    @Override
    public int hashCode() {
        int result = studentCode != null ? studentCode.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (dob != null ? dob.hashCode() : 0);
        result = 31 * result + (thesisRegistrable != null ? thesisRegistrable.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "user=" + (user == null ? "null" : user.getUserId()) +
                ", studentCode='" + studentCode + '\'' +
                ", name='" + name + '\'' +
                ", dob='" + dob + '\'' +
                ", clazz=" + (clazz == null ? "null" : clazz.getClassId()) +
                ", specialization=" + (specialization == null ? "null" : specialization.getSpecId()) +
                ", thesisRegistrable=" + thesisRegistrable +
                '}';
    }
}

package vn.locdt.jats.addon.entity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

/**
 * quản lý thông tin về người dùng
 * Created by cuong on 10/22/2016.
 */
@Entity
@Table(name = "user")
public class User {

    @JsonProperty("user_id")
    private int userId;

    @JsonProperty("username")
    private String username;

    @JsonIgnore
    private String password;

    @JsonProperty("email")
    private String email;

    @JsonProperty("enabled")
    private byte enabled = -1;

    @JsonProperty("role")
    private String role;

    /**
     * Constructor
     */
    public User() {

    }

    /**
     * Constructor
     * @param username tên tài khoản
     * @param password mật khẩu
     * @param email email
     * @param enabled trạng thái đã được kích hoạt hay chưa
     * @param role quyền của tài khoản (admin, giảng viên, sinh viên, ...)
     */
    public User(String username, String password, String email, byte enabled, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
        this.role = role;
    }

    /**
     * lấy id của user
     * @return id của người dùng
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    /**
     * đặt id cho user
     * @param userId id đặt cho user
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * lấy username của người dùng
     * @return username của người dùng
     */
    @Basic
    @Column(name = "username")
    public String getUsername() {

        return username;
    }

    /**
     * thiết lập username cho người dùng
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * lấy mật khẩu người dùng
     * @return mật khẩu người dùng
     */
    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    /**
     * thiết lập mật khẩu người dùng
     * @param password mật khẩu thiết lập cho người dùng
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * lấy email người dùng
     * @return email người dùng
     */
    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    /**
     * thiết lập email người dùng
     * @param email email thiết lập cho người dùng
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * lấy trạng thái người dùng
     * @return trạng thái của người dùng (đã kích hoạt/chưa kích hoạt)
     */
    @Basic
    @Column(name = "enabled")
    public byte getEnabled() {
        return enabled;
    }

    /**
     * thiết lập trạng thái người dùng
     * @param enabled trạng thái thiết lập cho người dùng (đã kích hoạt/chưa kích hoạt)
     */
    public void setEnabled(byte enabled) {
        this.enabled = enabled;
    }

    /**
     * lấy quyền của người dùng (admin/giảng viên/sinh viên/ ...)
     * @return quyền của người dùng (admin/giảng viên/sinh viên/ ...)
     */
    @Basic
    @Column(name = "role")
    public String getRole() {
        return role;
    }

    /**
     * thiết lập quyền người dùng
     * @param role quyền người dùng (admin/giảng viên/sinh viên/ ...)
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * So sánh hai đối tượng người dùng
     * @param o tham số người dùng thứ hai cần so sánh
     * @return true nếu hai người dùng là một/false nếu hai người dùng khác nhau
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != user.userId) return false;
        if (enabled != user.enabled) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (role != null ? !role.equals(user.role) : user.role != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (int) enabled;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", enabled=" + enabled +
                ", role='" + role + '\'' +
                '}';
    }
}
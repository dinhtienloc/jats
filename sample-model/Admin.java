package vn.locdt.jats.addon.entity.model;

import javax.persistence.*;

/**
 * Created by dinht_000 on 10/29/2016.
 * Created by cuong on 10/30/2016.
 */
@Entity
@Table(name = "admin")
public class Admin {

    private int adminId;
    private User user;
    private String name;

    /**
     * Lấy id của admin
     * @return id id của admin
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    public int getAdminId() {
        return adminId;
    }
    
    /**
     * Đặt id cho admin
     * @param adminId id truyền vào
     */
    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    /**
     * Lấy user của admin
     * @return đối tượng user mà admin sở hữu
     */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }
    
    /**
     * Đặt user cho admin
     * @param user đối trượng user truyền vào
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Lấy tên của admin
     * @return tên admin
     */
    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }
    
    /**
     * Đặt tên cho admin
     * @param name tên admin
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Kiểm tra hai admin có giống nhau hay không
     * @param o
     * @return true/false
     */
    @Override
    public boolean equals(Object o) {
        
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Admin admin = (Admin) o;
        
        if (name != null ? !name.equals(admin.name) : admin.name != null) return false;

        return true;
    }

    /**
     * Lấy mã băm của tên admin
     * @return mã băm của tên admin
     */
    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}

package vn.locdt.jats.addon.entity.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cuong on 10/24/2016.
 */
@Entity
@Table(name = "research")
public class Research {

    @JsonProperty("research_id")
    private int researchId; // id của research 

    @JsonProperty("name")
    private String name; // tên của research 

    @JsonProperty("parent")
    private Research parent; // parent của research 

    private Set<Research> children;
    private Set<Research> allChildren = new HashSet<>();

    private Set<Supervisor> supervisors;

    /**
     * lấy các giảng viên có cùng hướng nghiên cứu
     * @return tập hợp các giảng viên
     */
    @ManyToMany(mappedBy = "researches", fetch = FetchType.EAGER)
    public Set<Supervisor> getSupervisors() {
        return supervisors;
    }

    /**
     * thiết lập các giảng viên có cùng hướng nghiên cứu
     * @param supervisors các giảng viên cùng hướng nghiên cứu
    */
    public void setSupervisors(Set<Supervisor> supervisors) {
        this.supervisors = supervisors;
    }

    /**
     * Lấy tất cả children của hướng nghiên cứu
     * @return tất cả children của hướng nghiên cứu
     */
    @Transient
    public Set<Research> getAllChildren() {
        doGetAllChildren(this);
        return allChildren;
    }
    
    /**
     * Lấy tất cả children của parent
     * @param parent 
    */
    private void doGetAllChildren(Research parent) {
        if (parent.children == null)
            return;
        for (Research child : parent.children) {
            allChildren.add(child);
            doGetAllChildren(child);
        }
    }

    /**
     * Đặt các children cho parent 
     * @param allChildren
    */ 
    public void setAllChildren(Set<Research> allChildren) {
        this.allChildren = allChildren;
    }

    /**
     * Lấy children của hướng nghiên cứu
     * @return children của hướng nghiên cứu
     */
    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
    public Set<Research> getChildren() {
        return children;
    }
    
    /**
     * Đặt children cho hướng nghiên cứu
     * @param children 
    */
    public void setChildren(Set<Research> children) {
        this.children = children;
    }

    /**
     * Lấy parent của hướng nghiên cứu
     * @return parent của hướng nghiên cứu
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    public Research getParent() {
        return parent;
    }

    /**
     * Đặt parent cho hướng nghiên cứu
     * @param parent
    */
    public void setParent(Research parent) {
        this.parent = parent;
    }

    /**
     * Lấy id của hướng ngiên cứu
     * @return id của hướng nghiên cứu
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "research_id")
    public int getResearchId() {
        return researchId;
    }
    
    /**
     * Đặt id cho research
     * @param researchId
    */
    public void setResearchId(int researchId) {
        this.researchId = researchId;
    }

    /**
     * Lấy tên của research
     * @return tên của research
     */
    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }
    
    /**
     * Đặt tên cho research
     * @param name
    */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Kiểm tra xem hai research có giống nhau không
     * @return true/false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Research research = (Research) o;

        if (researchId != research.researchId) return false;
        if (name != null ? !name.equals(research.name) : research.name != null) return false;

        return true;
    }

    /**
     * Lấy mã băm của tên research
     * @return mã băm của tên research
     */
    @Override
    public int hashCode() {
        int result = researchId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}

package vn.locdt.jats.addon.entity.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

/**
 * trạng thái khóa luận
 * Created by cuong on 11/12/16.
 */
@Entity
@Table(name = "thesis_status")
public class ThesisStatus {

    public static final int WAIT_FOR_SUPERVISOR = 101;
    public static final int AGREED = 201;
    public static final int WAIT_TO_PROTECT = 202;
    public static final int FINISHED = 203;
    public static final int REQUEST_MODIFYING = 302;
    public static final int REQUEST_CANCELING = 303;
    public static final int SUPERVISOR_REFUSED = 401;
    public static final int CANCELED = 402;

    @JsonProperty("status_id")
    private int statusId;

    @JsonProperty("name")
    private String name;

    /**
     * constructor
     */
    public ThesisStatus() {}

    /**
     * constructor
     * @param status id trạng thái khóa luận
     */
    public ThesisStatus(int status) {
        this.statusId = status;
    }

    /**
     * lấy id trạng thái khóa luận
     * @return id trạng thái khóa luận
     */
    @Id
    @Column(name = "status_id")
    public int getStatusId() {
        return statusId;
    }

    /**
     * thiết lập id trạng thái khóa luận
     * @param statusId id trạng thái khóa luận
     */
    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    /**
     * lấy tên trạng thái
     * @return tên trạng thái
     */
    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    /**
     * thiết lập tên trạng thái
     * @param name tên trạng thái
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ThesisStatus that = (ThesisStatus) o;

        if (statusId != that.statusId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = statusId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ThesisStatus{" +
                "statusId=" + statusId +
                ", name='" + name + '\'' +
                '}';
    }
}

package cherry.sqlapp.db.gen.query;

import javax.annotation.Generated;

/**
 * BDayoffMaster is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class BDayoffMaster {

    private org.joda.time.LocalDateTime createdAt;

    private Integer deletedFlg;

    private org.joda.time.LocalDate dt;

    private Integer lockVersion;

    private String name;

    private String type;

    private org.joda.time.LocalDateTime updatedAt;

    public org.joda.time.LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(org.joda.time.LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getDeletedFlg() {
        return deletedFlg;
    }

    public void setDeletedFlg(Integer deletedFlg) {
        this.deletedFlg = deletedFlg;
    }

    public org.joda.time.LocalDate getDt() {
        return dt;
    }

    public void setDt(org.joda.time.LocalDate dt) {
        this.dt = dt;
    }

    public Integer getLockVersion() {
        return lockVersion;
    }

    public void setLockVersion(Integer lockVersion) {
        this.lockVersion = lockVersion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public org.joda.time.LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(org.joda.time.LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String toString() {
         return "createdAt = " + createdAt + ", deletedFlg = " + deletedFlg + ", dt = " + dt + ", lockVersion = " + lockVersion + ", name = " + name + ", type = " + type + ", updatedAt = " + updatedAt;
    }

}


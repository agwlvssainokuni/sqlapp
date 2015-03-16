package cherry.sqlapp.db.gen.query;

import javax.annotation.Generated;

/**
 * BDigit is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class BDigit {

    private org.joda.time.LocalDateTime createdAt;

    private Integer d;

    private Integer deletedFlg;

    private Integer lockVersion;

    private org.joda.time.LocalDateTime updatedAt;

    public org.joda.time.LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(org.joda.time.LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getD() {
        return d;
    }

    public void setD(Integer d) {
        this.d = d;
    }

    public Integer getDeletedFlg() {
        return deletedFlg;
    }

    public void setDeletedFlg(Integer deletedFlg) {
        this.deletedFlg = deletedFlg;
    }

    public Integer getLockVersion() {
        return lockVersion;
    }

    public void setLockVersion(Integer lockVersion) {
        this.lockVersion = lockVersion;
    }

    public org.joda.time.LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(org.joda.time.LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String toString() {
         return "createdAt = " + createdAt + ", d = " + d + ", deletedFlg = " + deletedFlg + ", lockVersion = " + lockVersion + ", updatedAt = " + updatedAt;
    }

}


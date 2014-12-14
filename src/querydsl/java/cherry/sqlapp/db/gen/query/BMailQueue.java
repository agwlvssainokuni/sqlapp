package cherry.sqlapp.db.gen.query;

import javax.annotation.Generated;

/**
 * BMailQueue is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class BMailQueue {

    private org.joda.time.LocalDateTime createdAt;

    private Integer deletedFlg;

    private Long id;

    private Integer lockVersion;

    private Long mailId;

    private org.joda.time.LocalDateTime scheduledAt;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLockVersion() {
        return lockVersion;
    }

    public void setLockVersion(Integer lockVersion) {
        this.lockVersion = lockVersion;
    }

    public Long getMailId() {
        return mailId;
    }

    public void setMailId(Long mailId) {
        this.mailId = mailId;
    }

    public org.joda.time.LocalDateTime getScheduledAt() {
        return scheduledAt;
    }

    public void setScheduledAt(org.joda.time.LocalDateTime scheduledAt) {
        this.scheduledAt = scheduledAt;
    }

    public org.joda.time.LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(org.joda.time.LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String toString() {
         return "createdAt = " + createdAt + ", deletedFlg = " + deletedFlg + ", id = " + id + ", lockVersion = " + lockVersion + ", mailId = " + mailId + ", scheduledAt = " + scheduledAt + ", updatedAt = " + updatedAt;
    }

}


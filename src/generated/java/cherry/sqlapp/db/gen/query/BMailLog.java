package cherry.sqlapp.db.gen.query;

import javax.annotation.Generated;

/**
 * BMailLog is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class BMailLog {

    private String body;

    private org.joda.time.LocalDateTime createdAt;

    private Integer deletedFlg;

    private String fromAddr;

    private Long id;

    private org.joda.time.LocalDateTime launchedAt;

    private String launchedBy;

    private Integer lockVersion;

    private Integer mailStatus;

    private String messageName;

    private org.joda.time.LocalDateTime scheduledAt;

    private org.joda.time.LocalDateTime sentAt;

    private String subject;

    private org.joda.time.LocalDateTime updatedAt;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

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

    public String getFromAddr() {
        return fromAddr;
    }

    public void setFromAddr(String fromAddr) {
        this.fromAddr = fromAddr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public org.joda.time.LocalDateTime getLaunchedAt() {
        return launchedAt;
    }

    public void setLaunchedAt(org.joda.time.LocalDateTime launchedAt) {
        this.launchedAt = launchedAt;
    }

    public String getLaunchedBy() {
        return launchedBy;
    }

    public void setLaunchedBy(String launchedBy) {
        this.launchedBy = launchedBy;
    }

    public Integer getLockVersion() {
        return lockVersion;
    }

    public void setLockVersion(Integer lockVersion) {
        this.lockVersion = lockVersion;
    }

    public Integer getMailStatus() {
        return mailStatus;
    }

    public void setMailStatus(Integer mailStatus) {
        this.mailStatus = mailStatus;
    }

    public String getMessageName() {
        return messageName;
    }

    public void setMessageName(String messageName) {
        this.messageName = messageName;
    }

    public org.joda.time.LocalDateTime getScheduledAt() {
        return scheduledAt;
    }

    public void setScheduledAt(org.joda.time.LocalDateTime scheduledAt) {
        this.scheduledAt = scheduledAt;
    }

    public org.joda.time.LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(org.joda.time.LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public org.joda.time.LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(org.joda.time.LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String toString() {
         return "body = " + body + ", createdAt = " + createdAt + ", deletedFlg = " + deletedFlg + ", fromAddr = " + fromAddr + ", id = " + id + ", launchedAt = " + launchedAt + ", launchedBy = " + launchedBy + ", lockVersion = " + lockVersion + ", mailStatus = " + mailStatus + ", messageName = " + messageName + ", scheduledAt = " + scheduledAt + ", sentAt = " + sentAt + ", subject = " + subject + ", updatedAt = " + updatedAt;
    }

}


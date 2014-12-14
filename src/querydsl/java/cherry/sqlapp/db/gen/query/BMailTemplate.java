package cherry.sqlapp.db.gen.query;

import javax.annotation.Generated;

/**
 * BMailTemplate is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class BMailTemplate {

    private String body;

    private org.joda.time.LocalDateTime createdAt;

    private Integer deletedFlg;

    private String fromAddr;

    private Long id;

    private Integer lockVersion;

    private String subject;

    private String templateName;

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

    public Integer getLockVersion() {
        return lockVersion;
    }

    public void setLockVersion(Integer lockVersion) {
        this.lockVersion = lockVersion;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public org.joda.time.LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(org.joda.time.LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String toString() {
         return "body = " + body + ", createdAt = " + createdAt + ", deletedFlg = " + deletedFlg + ", fromAddr = " + fromAddr + ", id = " + id + ", lockVersion = " + lockVersion + ", subject = " + subject + ", templateName = " + templateName + ", updatedAt = " + updatedAt;
    }

}


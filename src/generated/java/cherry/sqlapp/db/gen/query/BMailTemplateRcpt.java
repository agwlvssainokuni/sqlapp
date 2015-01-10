package cherry.sqlapp.db.gen.query;

import javax.annotation.Generated;

/**
 * BMailTemplateRcpt is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class BMailTemplateRcpt {

    private org.joda.time.LocalDateTime createdAt;

    private Integer deletedFlg;

    private Long id;

    private Integer lockVersion;

    private String rcptAddr;

    private String rcptType;

    private Long templateId;

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

    public String getRcptAddr() {
        return rcptAddr;
    }

    public void setRcptAddr(String rcptAddr) {
        this.rcptAddr = rcptAddr;
    }

    public String getRcptType() {
        return rcptType;
    }

    public void setRcptType(String rcptType) {
        this.rcptType = rcptType;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public org.joda.time.LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(org.joda.time.LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String toString() {
         return "createdAt = " + createdAt + ", deletedFlg = " + deletedFlg + ", id = " + id + ", lockVersion = " + lockVersion + ", rcptAddr = " + rcptAddr + ", rcptType = " + rcptType + ", templateId = " + templateId + ", updatedAt = " + updatedAt;
    }

}


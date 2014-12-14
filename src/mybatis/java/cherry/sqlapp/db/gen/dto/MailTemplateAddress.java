package cherry.sqlapp.db.gen.dto;

import cherry.foundation.type.DeletedFlag;
import java.io.Serializable;
import org.joda.time.LocalDateTime;

public class MailTemplateAddress implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MAIL_TEMPLATE_ADDRESS.ID
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MAIL_TEMPLATE_ADDRESS.TEMPLATE_ID
     *
     * @mbggenerated
     */
    private Long templateId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MAIL_TEMPLATE_ADDRESS.RCPT_TYPE
     *
     * @mbggenerated
     */
    private String rcptType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MAIL_TEMPLATE_ADDRESS.RCPT_ADDR
     *
     * @mbggenerated
     */
    private String rcptAddr;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MAIL_TEMPLATE_ADDRESS.UPDATED_AT
     *
     * @mbggenerated
     */
    private LocalDateTime updatedAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MAIL_TEMPLATE_ADDRESS.CREATED_AT
     *
     * @mbggenerated
     */
    private LocalDateTime createdAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MAIL_TEMPLATE_ADDRESS.LOCK_VERSION
     *
     * @mbggenerated
     */
    private Integer lockVersion;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MAIL_TEMPLATE_ADDRESS.DELETED_FLG
     *
     * @mbggenerated
     */
    private DeletedFlag deletedFlg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table MAIL_TEMPLATE_ADDRESS
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MAIL_TEMPLATE_ADDRESS.ID
     *
     * @return the value of MAIL_TEMPLATE_ADDRESS.ID
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MAIL_TEMPLATE_ADDRESS.ID
     *
     * @param id the value for MAIL_TEMPLATE_ADDRESS.ID
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MAIL_TEMPLATE_ADDRESS.TEMPLATE_ID
     *
     * @return the value of MAIL_TEMPLATE_ADDRESS.TEMPLATE_ID
     *
     * @mbggenerated
     */
    public Long getTemplateId() {
        return templateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MAIL_TEMPLATE_ADDRESS.TEMPLATE_ID
     *
     * @param templateId the value for MAIL_TEMPLATE_ADDRESS.TEMPLATE_ID
     *
     * @mbggenerated
     */
    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MAIL_TEMPLATE_ADDRESS.RCPT_TYPE
     *
     * @return the value of MAIL_TEMPLATE_ADDRESS.RCPT_TYPE
     *
     * @mbggenerated
     */
    public String getRcptType() {
        return rcptType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MAIL_TEMPLATE_ADDRESS.RCPT_TYPE
     *
     * @param rcptType the value for MAIL_TEMPLATE_ADDRESS.RCPT_TYPE
     *
     * @mbggenerated
     */
    public void setRcptType(String rcptType) {
        this.rcptType = rcptType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MAIL_TEMPLATE_ADDRESS.RCPT_ADDR
     *
     * @return the value of MAIL_TEMPLATE_ADDRESS.RCPT_ADDR
     *
     * @mbggenerated
     */
    public String getRcptAddr() {
        return rcptAddr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MAIL_TEMPLATE_ADDRESS.RCPT_ADDR
     *
     * @param rcptAddr the value for MAIL_TEMPLATE_ADDRESS.RCPT_ADDR
     *
     * @mbggenerated
     */
    public void setRcptAddr(String rcptAddr) {
        this.rcptAddr = rcptAddr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MAIL_TEMPLATE_ADDRESS.UPDATED_AT
     *
     * @return the value of MAIL_TEMPLATE_ADDRESS.UPDATED_AT
     *
     * @mbggenerated
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MAIL_TEMPLATE_ADDRESS.UPDATED_AT
     *
     * @param updatedAt the value for MAIL_TEMPLATE_ADDRESS.UPDATED_AT
     *
     * @mbggenerated
     */
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MAIL_TEMPLATE_ADDRESS.CREATED_AT
     *
     * @return the value of MAIL_TEMPLATE_ADDRESS.CREATED_AT
     *
     * @mbggenerated
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MAIL_TEMPLATE_ADDRESS.CREATED_AT
     *
     * @param createdAt the value for MAIL_TEMPLATE_ADDRESS.CREATED_AT
     *
     * @mbggenerated
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MAIL_TEMPLATE_ADDRESS.LOCK_VERSION
     *
     * @return the value of MAIL_TEMPLATE_ADDRESS.LOCK_VERSION
     *
     * @mbggenerated
     */
    public Integer getLockVersion() {
        return lockVersion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MAIL_TEMPLATE_ADDRESS.LOCK_VERSION
     *
     * @param lockVersion the value for MAIL_TEMPLATE_ADDRESS.LOCK_VERSION
     *
     * @mbggenerated
     */
    public void setLockVersion(Integer lockVersion) {
        this.lockVersion = lockVersion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MAIL_TEMPLATE_ADDRESS.DELETED_FLG
     *
     * @return the value of MAIL_TEMPLATE_ADDRESS.DELETED_FLG
     *
     * @mbggenerated
     */
    public DeletedFlag getDeletedFlg() {
        return deletedFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MAIL_TEMPLATE_ADDRESS.DELETED_FLG
     *
     * @param deletedFlg the value for MAIL_TEMPLATE_ADDRESS.DELETED_FLG
     *
     * @mbggenerated
     */
    public void setDeletedFlg(DeletedFlag deletedFlg) {
        this.deletedFlg = deletedFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE_ADDRESS
     *
     * @mbggenerated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        MailTemplateAddress other = (MailTemplateAddress) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTemplateId() == null ? other.getTemplateId() == null : this.getTemplateId().equals(other.getTemplateId()))
            && (this.getRcptType() == null ? other.getRcptType() == null : this.getRcptType().equals(other.getRcptType()))
            && (this.getRcptAddr() == null ? other.getRcptAddr() == null : this.getRcptAddr().equals(other.getRcptAddr()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getLockVersion() == null ? other.getLockVersion() == null : this.getLockVersion().equals(other.getLockVersion()))
            && (this.getDeletedFlg() == null ? other.getDeletedFlg() == null : this.getDeletedFlg().equals(other.getDeletedFlg()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE_ADDRESS
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTemplateId() == null) ? 0 : getTemplateId().hashCode());
        result = prime * result + ((getRcptType() == null) ? 0 : getRcptType().hashCode());
        result = prime * result + ((getRcptAddr() == null) ? 0 : getRcptAddr().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getLockVersion() == null) ? 0 : getLockVersion().hashCode());
        result = prime * result + ((getDeletedFlg() == null) ? 0 : getDeletedFlg().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE_ADDRESS
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", templateId=").append(templateId);
        sb.append(", rcptType=").append(rcptType);
        sb.append(", rcptAddr=").append(rcptAddr);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", lockVersion=").append(lockVersion);
        sb.append(", deletedFlg=").append(deletedFlg);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
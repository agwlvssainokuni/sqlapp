package cherry.sqlapp.db.gen.query;

import javax.annotation.Generated;

/**
 * BAsyncProcessFile is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class BAsyncProcessFile {

    private Long asyncId;

    private String contentType;

    private org.joda.time.LocalDateTime createdAt;

    private Integer deletedFlg;

    private Long fileSize;

    private String handlerName;

    private Long id;

    private Integer lockVersion;

    private String originalFilename;

    private String paramName;

    private org.joda.time.LocalDateTime updatedAt;

    public Long getAsyncId() {
        return asyncId;
    }

    public void setAsyncId(Long asyncId) {
        this.asyncId = asyncId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
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

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getHandlerName() {
        return handlerName;
    }

    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
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

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public org.joda.time.LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(org.joda.time.LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String toString() {
         return "asyncId = " + asyncId + ", contentType = " + contentType + ", createdAt = " + createdAt + ", deletedFlg = " + deletedFlg + ", fileSize = " + fileSize + ", handlerName = " + handlerName + ", id = " + id + ", lockVersion = " + lockVersion + ", originalFilename = " + originalFilename + ", paramName = " + paramName + ", updatedAt = " + updatedAt;
    }

}


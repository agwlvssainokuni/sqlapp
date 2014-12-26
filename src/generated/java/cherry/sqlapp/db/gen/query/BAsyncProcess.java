package cherry.sqlapp.db.gen.query;

import javax.annotation.Generated;

/**
 * BAsyncProcess is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class BAsyncProcess {

    private String asyncStatus;

    private String asyncType;

    private org.joda.time.LocalDateTime createdAt;

    private Integer deletedFlg;

    private String description;

    private org.joda.time.LocalDateTime finishedAt;

    private Long id;

    private org.joda.time.LocalDateTime launchedAt;

    private String launchedBy;

    private Integer lockVersion;

    private org.joda.time.LocalDateTime registeredAt;

    private org.joda.time.LocalDateTime startedAt;

    private org.joda.time.LocalDateTime updatedAt;

    public String getAsyncStatus() {
        return asyncStatus;
    }

    public void setAsyncStatus(String asyncStatus) {
        this.asyncStatus = asyncStatus;
    }

    public String getAsyncType() {
        return asyncType;
    }

    public void setAsyncType(String asyncType) {
        this.asyncType = asyncType;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public org.joda.time.LocalDateTime getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(org.joda.time.LocalDateTime finishedAt) {
        this.finishedAt = finishedAt;
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

    public org.joda.time.LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(org.joda.time.LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    public org.joda.time.LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(org.joda.time.LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public org.joda.time.LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(org.joda.time.LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String toString() {
         return "asyncStatus = " + asyncStatus + ", asyncType = " + asyncType + ", createdAt = " + createdAt + ", deletedFlg = " + deletedFlg + ", description = " + description + ", finishedAt = " + finishedAt + ", id = " + id + ", launchedAt = " + launchedAt + ", launchedBy = " + launchedBy + ", lockVersion = " + lockVersion + ", registeredAt = " + registeredAt + ", startedAt = " + startedAt + ", updatedAt = " + updatedAt;
    }

}


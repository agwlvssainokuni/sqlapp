package cherry.sqlapp.db.gen.query;

import javax.annotation.Generated;

/**
 * BAsyncProc is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class BAsyncProc {

    private org.joda.time.LocalDateTime createdAt;

    private Integer deletedFlg;

    private org.joda.time.LocalDateTime finishedAt;

    private Integer id;

    private org.joda.time.LocalDateTime invokedAt;

    private String launcherId;

    private Integer lockVersion;

    private String name;

    private org.joda.time.LocalDateTime registeredAt;

    private String result;

    private org.joda.time.LocalDateTime startedAt;

    private String status;

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

    public org.joda.time.LocalDateTime getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(org.joda.time.LocalDateTime finishedAt) {
        this.finishedAt = finishedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public org.joda.time.LocalDateTime getInvokedAt() {
        return invokedAt;
    }

    public void setInvokedAt(org.joda.time.LocalDateTime invokedAt) {
        this.invokedAt = invokedAt;
    }

    public String getLauncherId() {
        return launcherId;
    }

    public void setLauncherId(String launcherId) {
        this.launcherId = launcherId;
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

    public org.joda.time.LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(org.joda.time.LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public org.joda.time.LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(org.joda.time.LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public org.joda.time.LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(org.joda.time.LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String toString() {
         return "createdAt = " + createdAt + ", deletedFlg = " + deletedFlg + ", finishedAt = " + finishedAt + ", id = " + id + ", invokedAt = " + invokedAt + ", launcherId = " + launcherId + ", lockVersion = " + lockVersion + ", name = " + name + ", registeredAt = " + registeredAt + ", result = " + result + ", startedAt = " + startedAt + ", status = " + status + ", updatedAt = " + updatedAt;
    }

}


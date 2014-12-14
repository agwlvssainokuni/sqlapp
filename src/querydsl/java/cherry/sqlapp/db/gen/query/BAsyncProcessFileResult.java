package cherry.sqlapp.db.gen.query;

import javax.annotation.Generated;

/**
 * BAsyncProcessFileResult is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class BAsyncProcessFileResult {

    private Long asyncId;

    private org.joda.time.LocalDateTime createdAt;

    private Integer deletedFlg;

    private Long id;

    private Integer lockVersion;

    private Long ngCount;

    private Long okCount;

    private Long totalCount;

    private org.joda.time.LocalDateTime updatedAt;

    public Long getAsyncId() {
        return asyncId;
    }

    public void setAsyncId(Long asyncId) {
        this.asyncId = asyncId;
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

    public Long getNgCount() {
        return ngCount;
    }

    public void setNgCount(Long ngCount) {
        this.ngCount = ngCount;
    }

    public Long getOkCount() {
        return okCount;
    }

    public void setOkCount(Long okCount) {
        this.okCount = okCount;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public org.joda.time.LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(org.joda.time.LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String toString() {
         return "asyncId = " + asyncId + ", createdAt = " + createdAt + ", deletedFlg = " + deletedFlg + ", id = " + id + ", lockVersion = " + lockVersion + ", ngCount = " + ngCount + ", okCount = " + okCount + ", totalCount = " + totalCount + ", updatedAt = " + updatedAt;
    }

}


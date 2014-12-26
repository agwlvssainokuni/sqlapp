package cherry.sqlapp.db.gen.query;

import javax.annotation.Generated;

/**
 * BSqltoolMetadata is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class BSqltoolMetadata {

    private org.joda.time.LocalDateTime createdAt;

    private Integer deletedFlg;

    private String description;

    private Integer id;

    private Integer lockVersion;

    private String name;

    private String ownedBy;

    private Integer publishedFlg;

    private org.joda.time.LocalDateTime registeredAt;

    private String sqlType;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getOwnedBy() {
        return ownedBy;
    }

    public void setOwnedBy(String ownedBy) {
        this.ownedBy = ownedBy;
    }

    public Integer getPublishedFlg() {
        return publishedFlg;
    }

    public void setPublishedFlg(Integer publishedFlg) {
        this.publishedFlg = publishedFlg;
    }

    public org.joda.time.LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(org.joda.time.LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    public String getSqlType() {
        return sqlType;
    }

    public void setSqlType(String sqlType) {
        this.sqlType = sqlType;
    }

    public org.joda.time.LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(org.joda.time.LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String toString() {
         return "createdAt = " + createdAt + ", deletedFlg = " + deletedFlg + ", description = " + description + ", id = " + id + ", lockVersion = " + lockVersion + ", name = " + name + ", ownedBy = " + ownedBy + ", publishedFlg = " + publishedFlg + ", registeredAt = " + registeredAt + ", sqlType = " + sqlType + ", updatedAt = " + updatedAt;
    }

}


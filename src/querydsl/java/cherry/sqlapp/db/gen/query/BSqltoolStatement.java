package cherry.sqlapp.db.gen.query;

import javax.annotation.Generated;

/**
 * BSqltoolStatement is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class BSqltoolStatement {

    private org.joda.time.LocalDateTime createdAt;

    private String databaseName;

    private Integer deletedFlg;

    private Integer id;

    private Integer lockVersion;

    private String paramMap;

    private String query;

    private org.joda.time.LocalDateTime updatedAt;

    public org.joda.time.LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(org.joda.time.LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public Integer getDeletedFlg() {
        return deletedFlg;
    }

    public void setDeletedFlg(Integer deletedFlg) {
        this.deletedFlg = deletedFlg;
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

    public String getParamMap() {
        return paramMap;
    }

    public void setParamMap(String paramMap) {
        this.paramMap = paramMap;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public org.joda.time.LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(org.joda.time.LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String toString() {
         return "createdAt = " + createdAt + ", databaseName = " + databaseName + ", deletedFlg = " + deletedFlg + ", id = " + id + ", lockVersion = " + lockVersion + ", paramMap = " + paramMap + ", query = " + query + ", updatedAt = " + updatedAt;
    }

}


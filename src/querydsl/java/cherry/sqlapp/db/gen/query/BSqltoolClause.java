package cherry.sqlapp.db.gen.query;

import javax.annotation.Generated;

/**
 * BSqltoolClause is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class BSqltoolClause {

    private org.joda.time.LocalDateTime createdAt;

    private String databaseName;

    private Integer deletedFlg;

    private String fromClause;

    private String groupByClause;

    private String havingClause;

    private Integer id;

    private Integer lockVersion;

    private String orderByClause;

    private String paramMap;

    private String selectClause;

    private org.joda.time.LocalDateTime updatedAt;

    private String whereClause;

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

    public String getFromClause() {
        return fromClause;
    }

    public void setFromClause(String fromClause) {
        this.fromClause = fromClause;
    }

    public String getGroupByClause() {
        return groupByClause;
    }

    public void setGroupByClause(String groupByClause) {
        this.groupByClause = groupByClause;
    }

    public String getHavingClause() {
        return havingClause;
    }

    public void setHavingClause(String havingClause) {
        this.havingClause = havingClause;
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

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getParamMap() {
        return paramMap;
    }

    public void setParamMap(String paramMap) {
        this.paramMap = paramMap;
    }

    public String getSelectClause() {
        return selectClause;
    }

    public void setSelectClause(String selectClause) {
        this.selectClause = selectClause;
    }

    public org.joda.time.LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(org.joda.time.LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getWhereClause() {
        return whereClause;
    }

    public void setWhereClause(String whereClause) {
        this.whereClause = whereClause;
    }

    public String toString() {
         return "createdAt = " + createdAt + ", databaseName = " + databaseName + ", deletedFlg = " + deletedFlg + ", fromClause = " + fromClause + ", groupByClause = " + groupByClause + ", havingClause = " + havingClause + ", id = " + id + ", lockVersion = " + lockVersion + ", orderByClause = " + orderByClause + ", paramMap = " + paramMap + ", selectClause = " + selectClause + ", updatedAt = " + updatedAt + ", whereClause = " + whereClause;
    }

}


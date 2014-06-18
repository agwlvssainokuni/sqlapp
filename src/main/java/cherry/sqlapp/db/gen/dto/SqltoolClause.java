package cherry.sqlapp.db.gen.dto;

import cherry.sqlapp.db.BaseDto;
import java.io.Serializable;
import org.joda.time.LocalDateTime;

public class SqltoolClause extends BaseDto implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SQLTOOL_CLAUSE.ID
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SQLTOOL_CLAUSE.DATABASE_NAME
     *
     * @mbggenerated
     */
    private String databaseName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SQLTOOL_CLAUSE.SELECT_CLAUSE
     *
     * @mbggenerated
     */
    private String selectClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SQLTOOL_CLAUSE.FROM_CLAUSE
     *
     * @mbggenerated
     */
    private String fromClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SQLTOOL_CLAUSE.WHERE_CLAUSE
     *
     * @mbggenerated
     */
    private String whereClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SQLTOOL_CLAUSE.GROUP_BY_CLAUSE
     *
     * @mbggenerated
     */
    private String groupByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SQLTOOL_CLAUSE.HAVING_CLAUSE
     *
     * @mbggenerated
     */
    private String havingClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SQLTOOL_CLAUSE.ORDER_BY_CLAUSE
     *
     * @mbggenerated
     */
    private String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SQLTOOL_CLAUSE.PARAM_MAP
     *
     * @mbggenerated
     */
    private String paramMap;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SQLTOOL_CLAUSE.UPDATED_AT
     *
     * @mbggenerated
     */
    private LocalDateTime updatedAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SQLTOOL_CLAUSE.CREATED_AT
     *
     * @mbggenerated
     */
    private LocalDateTime createdAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SQLTOOL_CLAUSE.DELETED_FLG
     *
     * @mbggenerated
     */
    private Integer deletedFlg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table SQLTOOL_CLAUSE
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SQLTOOL_CLAUSE.ID
     *
     * @return the value of SQLTOOL_CLAUSE.ID
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SQLTOOL_CLAUSE.ID
     *
     * @param id the value for SQLTOOL_CLAUSE.ID
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SQLTOOL_CLAUSE.DATABASE_NAME
     *
     * @return the value of SQLTOOL_CLAUSE.DATABASE_NAME
     *
     * @mbggenerated
     */
    public String getDatabaseName() {
        return databaseName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SQLTOOL_CLAUSE.DATABASE_NAME
     *
     * @param databaseName the value for SQLTOOL_CLAUSE.DATABASE_NAME
     *
     * @mbggenerated
     */
    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SQLTOOL_CLAUSE.SELECT_CLAUSE
     *
     * @return the value of SQLTOOL_CLAUSE.SELECT_CLAUSE
     *
     * @mbggenerated
     */
    public String getSelectClause() {
        return selectClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SQLTOOL_CLAUSE.SELECT_CLAUSE
     *
     * @param selectClause the value for SQLTOOL_CLAUSE.SELECT_CLAUSE
     *
     * @mbggenerated
     */
    public void setSelectClause(String selectClause) {
        this.selectClause = selectClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SQLTOOL_CLAUSE.FROM_CLAUSE
     *
     * @return the value of SQLTOOL_CLAUSE.FROM_CLAUSE
     *
     * @mbggenerated
     */
    public String getFromClause() {
        return fromClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SQLTOOL_CLAUSE.FROM_CLAUSE
     *
     * @param fromClause the value for SQLTOOL_CLAUSE.FROM_CLAUSE
     *
     * @mbggenerated
     */
    public void setFromClause(String fromClause) {
        this.fromClause = fromClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SQLTOOL_CLAUSE.WHERE_CLAUSE
     *
     * @return the value of SQLTOOL_CLAUSE.WHERE_CLAUSE
     *
     * @mbggenerated
     */
    public String getWhereClause() {
        return whereClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SQLTOOL_CLAUSE.WHERE_CLAUSE
     *
     * @param whereClause the value for SQLTOOL_CLAUSE.WHERE_CLAUSE
     *
     * @mbggenerated
     */
    public void setWhereClause(String whereClause) {
        this.whereClause = whereClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SQLTOOL_CLAUSE.GROUP_BY_CLAUSE
     *
     * @return the value of SQLTOOL_CLAUSE.GROUP_BY_CLAUSE
     *
     * @mbggenerated
     */
    public String getGroupByClause() {
        return groupByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SQLTOOL_CLAUSE.GROUP_BY_CLAUSE
     *
     * @param groupByClause the value for SQLTOOL_CLAUSE.GROUP_BY_CLAUSE
     *
     * @mbggenerated
     */
    public void setGroupByClause(String groupByClause) {
        this.groupByClause = groupByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SQLTOOL_CLAUSE.HAVING_CLAUSE
     *
     * @return the value of SQLTOOL_CLAUSE.HAVING_CLAUSE
     *
     * @mbggenerated
     */
    public String getHavingClause() {
        return havingClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SQLTOOL_CLAUSE.HAVING_CLAUSE
     *
     * @param havingClause the value for SQLTOOL_CLAUSE.HAVING_CLAUSE
     *
     * @mbggenerated
     */
    public void setHavingClause(String havingClause) {
        this.havingClause = havingClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SQLTOOL_CLAUSE.ORDER_BY_CLAUSE
     *
     * @return the value of SQLTOOL_CLAUSE.ORDER_BY_CLAUSE
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SQLTOOL_CLAUSE.ORDER_BY_CLAUSE
     *
     * @param orderByClause the value for SQLTOOL_CLAUSE.ORDER_BY_CLAUSE
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SQLTOOL_CLAUSE.PARAM_MAP
     *
     * @return the value of SQLTOOL_CLAUSE.PARAM_MAP
     *
     * @mbggenerated
     */
    public String getParamMap() {
        return paramMap;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SQLTOOL_CLAUSE.PARAM_MAP
     *
     * @param paramMap the value for SQLTOOL_CLAUSE.PARAM_MAP
     *
     * @mbggenerated
     */
    public void setParamMap(String paramMap) {
        this.paramMap = paramMap;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SQLTOOL_CLAUSE.UPDATED_AT
     *
     * @return the value of SQLTOOL_CLAUSE.UPDATED_AT
     *
     * @mbggenerated
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SQLTOOL_CLAUSE.UPDATED_AT
     *
     * @param updatedAt the value for SQLTOOL_CLAUSE.UPDATED_AT
     *
     * @mbggenerated
     */
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SQLTOOL_CLAUSE.CREATED_AT
     *
     * @return the value of SQLTOOL_CLAUSE.CREATED_AT
     *
     * @mbggenerated
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SQLTOOL_CLAUSE.CREATED_AT
     *
     * @param createdAt the value for SQLTOOL_CLAUSE.CREATED_AT
     *
     * @mbggenerated
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SQLTOOL_CLAUSE.DELETED_FLG
     *
     * @return the value of SQLTOOL_CLAUSE.DELETED_FLG
     *
     * @mbggenerated
     */
    public Integer getDeletedFlg() {
        return deletedFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SQLTOOL_CLAUSE.DELETED_FLG
     *
     * @param deletedFlg the value for SQLTOOL_CLAUSE.DELETED_FLG
     *
     * @mbggenerated
     */
    public void setDeletedFlg(Integer deletedFlg) {
        this.deletedFlg = deletedFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SQLTOOL_CLAUSE
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
        SqltoolClause other = (SqltoolClause) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getDatabaseName() == null ? other.getDatabaseName() == null : this.getDatabaseName().equals(other.getDatabaseName()))
            && (this.getSelectClause() == null ? other.getSelectClause() == null : this.getSelectClause().equals(other.getSelectClause()))
            && (this.getFromClause() == null ? other.getFromClause() == null : this.getFromClause().equals(other.getFromClause()))
            && (this.getWhereClause() == null ? other.getWhereClause() == null : this.getWhereClause().equals(other.getWhereClause()))
            && (this.getGroupByClause() == null ? other.getGroupByClause() == null : this.getGroupByClause().equals(other.getGroupByClause()))
            && (this.getHavingClause() == null ? other.getHavingClause() == null : this.getHavingClause().equals(other.getHavingClause()))
            && (this.getOrderByClause() == null ? other.getOrderByClause() == null : this.getOrderByClause().equals(other.getOrderByClause()))
            && (this.getParamMap() == null ? other.getParamMap() == null : this.getParamMap().equals(other.getParamMap()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getDeletedFlg() == null ? other.getDeletedFlg() == null : this.getDeletedFlg().equals(other.getDeletedFlg()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SQLTOOL_CLAUSE
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getDatabaseName() == null) ? 0 : getDatabaseName().hashCode());
        result = prime * result + ((getSelectClause() == null) ? 0 : getSelectClause().hashCode());
        result = prime * result + ((getFromClause() == null) ? 0 : getFromClause().hashCode());
        result = prime * result + ((getWhereClause() == null) ? 0 : getWhereClause().hashCode());
        result = prime * result + ((getGroupByClause() == null) ? 0 : getGroupByClause().hashCode());
        result = prime * result + ((getHavingClause() == null) ? 0 : getHavingClause().hashCode());
        result = prime * result + ((getOrderByClause() == null) ? 0 : getOrderByClause().hashCode());
        result = prime * result + ((getParamMap() == null) ? 0 : getParamMap().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getDeletedFlg() == null) ? 0 : getDeletedFlg().hashCode());
        return result;
    }
}
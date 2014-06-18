package cherry.sqlapp.db.gen.dto;

import cherry.sqlapp.db.BaseDto;
import java.io.Serializable;
import org.joda.time.LocalDateTime;

public class SqltoolLoad extends BaseDto implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SQLTOOL_LOAD.ID
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SQLTOOL_LOAD.DATABASE_NAME
     *
     * @mbggenerated
     */
    private String databaseName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SQLTOOL_LOAD.QUERY
     *
     * @mbggenerated
     */
    private String query;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SQLTOOL_LOAD.UPDATED_AT
     *
     * @mbggenerated
     */
    private LocalDateTime updatedAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SQLTOOL_LOAD.CREATED_AT
     *
     * @mbggenerated
     */
    private LocalDateTime createdAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SQLTOOL_LOAD.DELETED_FLG
     *
     * @mbggenerated
     */
    private Integer deletedFlg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table SQLTOOL_LOAD
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SQLTOOL_LOAD.ID
     *
     * @return the value of SQLTOOL_LOAD.ID
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SQLTOOL_LOAD.ID
     *
     * @param id the value for SQLTOOL_LOAD.ID
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SQLTOOL_LOAD.DATABASE_NAME
     *
     * @return the value of SQLTOOL_LOAD.DATABASE_NAME
     *
     * @mbggenerated
     */
    public String getDatabaseName() {
        return databaseName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SQLTOOL_LOAD.DATABASE_NAME
     *
     * @param databaseName the value for SQLTOOL_LOAD.DATABASE_NAME
     *
     * @mbggenerated
     */
    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SQLTOOL_LOAD.QUERY
     *
     * @return the value of SQLTOOL_LOAD.QUERY
     *
     * @mbggenerated
     */
    public String getQuery() {
        return query;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SQLTOOL_LOAD.QUERY
     *
     * @param query the value for SQLTOOL_LOAD.QUERY
     *
     * @mbggenerated
     */
    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SQLTOOL_LOAD.UPDATED_AT
     *
     * @return the value of SQLTOOL_LOAD.UPDATED_AT
     *
     * @mbggenerated
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SQLTOOL_LOAD.UPDATED_AT
     *
     * @param updatedAt the value for SQLTOOL_LOAD.UPDATED_AT
     *
     * @mbggenerated
     */
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SQLTOOL_LOAD.CREATED_AT
     *
     * @return the value of SQLTOOL_LOAD.CREATED_AT
     *
     * @mbggenerated
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SQLTOOL_LOAD.CREATED_AT
     *
     * @param createdAt the value for SQLTOOL_LOAD.CREATED_AT
     *
     * @mbggenerated
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SQLTOOL_LOAD.DELETED_FLG
     *
     * @return the value of SQLTOOL_LOAD.DELETED_FLG
     *
     * @mbggenerated
     */
    public Integer getDeletedFlg() {
        return deletedFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SQLTOOL_LOAD.DELETED_FLG
     *
     * @param deletedFlg the value for SQLTOOL_LOAD.DELETED_FLG
     *
     * @mbggenerated
     */
    public void setDeletedFlg(Integer deletedFlg) {
        this.deletedFlg = deletedFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SQLTOOL_LOAD
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
        SqltoolLoad other = (SqltoolLoad) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getDatabaseName() == null ? other.getDatabaseName() == null : this.getDatabaseName().equals(other.getDatabaseName()))
            && (this.getQuery() == null ? other.getQuery() == null : this.getQuery().equals(other.getQuery()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getDeletedFlg() == null ? other.getDeletedFlg() == null : this.getDeletedFlg().equals(other.getDeletedFlg()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SQLTOOL_LOAD
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getDatabaseName() == null) ? 0 : getDatabaseName().hashCode());
        result = prime * result + ((getQuery() == null) ? 0 : getQuery().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getDeletedFlg() == null) ? 0 : getDeletedFlg().hashCode());
        return result;
    }
}
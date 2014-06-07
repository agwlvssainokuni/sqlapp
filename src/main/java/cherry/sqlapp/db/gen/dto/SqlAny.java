package cherry.sqlapp.db.gen.dto;

import cherry.sqlapp.db.BaseDto;
import java.io.Serializable;
import java.util.Date;

public class SqlAny extends BaseDto implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SQL_ANY.ID
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SQL_ANY.QUERY
     *
     * @mbggenerated
     */
    private String query;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SQL_ANY.PARAM_MAP
     *
     * @mbggenerated
     */
    private String paramMap;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SQL_ANY.UPDATED_AT
     *
     * @mbggenerated
     */
    private Date updatedAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SQL_ANY.CREATED_AT
     *
     * @mbggenerated
     */
    private Date createdAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SQL_ANY.DELETED_FLG
     *
     * @mbggenerated
     */
    private Integer deletedFlg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table SQL_ANY
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SQL_ANY.ID
     *
     * @return the value of SQL_ANY.ID
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SQL_ANY.ID
     *
     * @param id the value for SQL_ANY.ID
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SQL_ANY.QUERY
     *
     * @return the value of SQL_ANY.QUERY
     *
     * @mbggenerated
     */
    public String getQuery() {
        return query;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SQL_ANY.QUERY
     *
     * @param query the value for SQL_ANY.QUERY
     *
     * @mbggenerated
     */
    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SQL_ANY.PARAM_MAP
     *
     * @return the value of SQL_ANY.PARAM_MAP
     *
     * @mbggenerated
     */
    public String getParamMap() {
        return paramMap;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SQL_ANY.PARAM_MAP
     *
     * @param paramMap the value for SQL_ANY.PARAM_MAP
     *
     * @mbggenerated
     */
    public void setParamMap(String paramMap) {
        this.paramMap = paramMap;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SQL_ANY.UPDATED_AT
     *
     * @return the value of SQL_ANY.UPDATED_AT
     *
     * @mbggenerated
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SQL_ANY.UPDATED_AT
     *
     * @param updatedAt the value for SQL_ANY.UPDATED_AT
     *
     * @mbggenerated
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SQL_ANY.CREATED_AT
     *
     * @return the value of SQL_ANY.CREATED_AT
     *
     * @mbggenerated
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SQL_ANY.CREATED_AT
     *
     * @param createdAt the value for SQL_ANY.CREATED_AT
     *
     * @mbggenerated
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SQL_ANY.DELETED_FLG
     *
     * @return the value of SQL_ANY.DELETED_FLG
     *
     * @mbggenerated
     */
    public Integer getDeletedFlg() {
        return deletedFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SQL_ANY.DELETED_FLG
     *
     * @param deletedFlg the value for SQL_ANY.DELETED_FLG
     *
     * @mbggenerated
     */
    public void setDeletedFlg(Integer deletedFlg) {
        this.deletedFlg = deletedFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SQL_ANY
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
        SqlAny other = (SqlAny) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getQuery() == null ? other.getQuery() == null : this.getQuery().equals(other.getQuery()))
            && (this.getParamMap() == null ? other.getParamMap() == null : this.getParamMap().equals(other.getParamMap()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getDeletedFlg() == null ? other.getDeletedFlg() == null : this.getDeletedFlg().equals(other.getDeletedFlg()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SQL_ANY
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getQuery() == null) ? 0 : getQuery().hashCode());
        result = prime * result + ((getParamMap() == null) ? 0 : getParamMap().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getDeletedFlg() == null) ? 0 : getDeletedFlg().hashCode());
        return result;
    }
}
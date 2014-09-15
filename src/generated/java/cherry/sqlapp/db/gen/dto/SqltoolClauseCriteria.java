package cherry.sqlapp.db.gen.dto;

import cherry.spring.common.custom.DeletedFlag;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.LocalDateTime;

public class SqltoolClauseCriteria {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table SQLTOOL_CLAUSE
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table SQLTOOL_CLAUSE
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table SQLTOOL_CLAUSE
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SQLTOOL_CLAUSE
     *
     * @mbggenerated
     */
    public SqltoolClauseCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SQLTOOL_CLAUSE
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SQLTOOL_CLAUSE
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SQLTOOL_CLAUSE
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SQLTOOL_CLAUSE
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SQLTOOL_CLAUSE
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SQLTOOL_CLAUSE
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SQLTOOL_CLAUSE
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SQLTOOL_CLAUSE
     *
     * @mbggenerated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SQLTOOL_CLAUSE
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SQLTOOL_CLAUSE
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table SQLTOOL_CLAUSE
     *
     * @mbggenerated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameIsNull() {
            addCriterion("DATABASE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameIsNotNull() {
            addCriterion("DATABASE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameEqualTo(String value) {
            addCriterion("DATABASE_NAME =", value, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameNotEqualTo(String value) {
            addCriterion("DATABASE_NAME <>", value, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameGreaterThan(String value) {
            addCriterion("DATABASE_NAME >", value, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameGreaterThanOrEqualTo(String value) {
            addCriterion("DATABASE_NAME >=", value, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameLessThan(String value) {
            addCriterion("DATABASE_NAME <", value, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameLessThanOrEqualTo(String value) {
            addCriterion("DATABASE_NAME <=", value, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameLike(String value) {
            addCriterion("DATABASE_NAME like", value, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameNotLike(String value) {
            addCriterion("DATABASE_NAME not like", value, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameIn(List<String> values) {
            addCriterion("DATABASE_NAME in", values, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameNotIn(List<String> values) {
            addCriterion("DATABASE_NAME not in", values, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameBetween(String value1, String value2) {
            addCriterion("DATABASE_NAME between", value1, value2, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameNotBetween(String value1, String value2) {
            addCriterion("DATABASE_NAME not between", value1, value2, "databaseName");
            return (Criteria) this;
        }

        public Criteria andSelectClauseIsNull() {
            addCriterion("SELECT_CLAUSE is null");
            return (Criteria) this;
        }

        public Criteria andSelectClauseIsNotNull() {
            addCriterion("SELECT_CLAUSE is not null");
            return (Criteria) this;
        }

        public Criteria andSelectClauseEqualTo(String value) {
            addCriterion("SELECT_CLAUSE =", value, "selectClause");
            return (Criteria) this;
        }

        public Criteria andSelectClauseNotEqualTo(String value) {
            addCriterion("SELECT_CLAUSE <>", value, "selectClause");
            return (Criteria) this;
        }

        public Criteria andSelectClauseGreaterThan(String value) {
            addCriterion("SELECT_CLAUSE >", value, "selectClause");
            return (Criteria) this;
        }

        public Criteria andSelectClauseGreaterThanOrEqualTo(String value) {
            addCriterion("SELECT_CLAUSE >=", value, "selectClause");
            return (Criteria) this;
        }

        public Criteria andSelectClauseLessThan(String value) {
            addCriterion("SELECT_CLAUSE <", value, "selectClause");
            return (Criteria) this;
        }

        public Criteria andSelectClauseLessThanOrEqualTo(String value) {
            addCriterion("SELECT_CLAUSE <=", value, "selectClause");
            return (Criteria) this;
        }

        public Criteria andSelectClauseLike(String value) {
            addCriterion("SELECT_CLAUSE like", value, "selectClause");
            return (Criteria) this;
        }

        public Criteria andSelectClauseNotLike(String value) {
            addCriterion("SELECT_CLAUSE not like", value, "selectClause");
            return (Criteria) this;
        }

        public Criteria andSelectClauseIn(List<String> values) {
            addCriterion("SELECT_CLAUSE in", values, "selectClause");
            return (Criteria) this;
        }

        public Criteria andSelectClauseNotIn(List<String> values) {
            addCriterion("SELECT_CLAUSE not in", values, "selectClause");
            return (Criteria) this;
        }

        public Criteria andSelectClauseBetween(String value1, String value2) {
            addCriterion("SELECT_CLAUSE between", value1, value2, "selectClause");
            return (Criteria) this;
        }

        public Criteria andSelectClauseNotBetween(String value1, String value2) {
            addCriterion("SELECT_CLAUSE not between", value1, value2, "selectClause");
            return (Criteria) this;
        }

        public Criteria andFromClauseIsNull() {
            addCriterion("FROM_CLAUSE is null");
            return (Criteria) this;
        }

        public Criteria andFromClauseIsNotNull() {
            addCriterion("FROM_CLAUSE is not null");
            return (Criteria) this;
        }

        public Criteria andFromClauseEqualTo(String value) {
            addCriterion("FROM_CLAUSE =", value, "fromClause");
            return (Criteria) this;
        }

        public Criteria andFromClauseNotEqualTo(String value) {
            addCriterion("FROM_CLAUSE <>", value, "fromClause");
            return (Criteria) this;
        }

        public Criteria andFromClauseGreaterThan(String value) {
            addCriterion("FROM_CLAUSE >", value, "fromClause");
            return (Criteria) this;
        }

        public Criteria andFromClauseGreaterThanOrEqualTo(String value) {
            addCriterion("FROM_CLAUSE >=", value, "fromClause");
            return (Criteria) this;
        }

        public Criteria andFromClauseLessThan(String value) {
            addCriterion("FROM_CLAUSE <", value, "fromClause");
            return (Criteria) this;
        }

        public Criteria andFromClauseLessThanOrEqualTo(String value) {
            addCriterion("FROM_CLAUSE <=", value, "fromClause");
            return (Criteria) this;
        }

        public Criteria andFromClauseLike(String value) {
            addCriterion("FROM_CLAUSE like", value, "fromClause");
            return (Criteria) this;
        }

        public Criteria andFromClauseNotLike(String value) {
            addCriterion("FROM_CLAUSE not like", value, "fromClause");
            return (Criteria) this;
        }

        public Criteria andFromClauseIn(List<String> values) {
            addCriterion("FROM_CLAUSE in", values, "fromClause");
            return (Criteria) this;
        }

        public Criteria andFromClauseNotIn(List<String> values) {
            addCriterion("FROM_CLAUSE not in", values, "fromClause");
            return (Criteria) this;
        }

        public Criteria andFromClauseBetween(String value1, String value2) {
            addCriterion("FROM_CLAUSE between", value1, value2, "fromClause");
            return (Criteria) this;
        }

        public Criteria andFromClauseNotBetween(String value1, String value2) {
            addCriterion("FROM_CLAUSE not between", value1, value2, "fromClause");
            return (Criteria) this;
        }

        public Criteria andWhereClauseIsNull() {
            addCriterion("WHERE_CLAUSE is null");
            return (Criteria) this;
        }

        public Criteria andWhereClauseIsNotNull() {
            addCriterion("WHERE_CLAUSE is not null");
            return (Criteria) this;
        }

        public Criteria andWhereClauseEqualTo(String value) {
            addCriterion("WHERE_CLAUSE =", value, "whereClause");
            return (Criteria) this;
        }

        public Criteria andWhereClauseNotEqualTo(String value) {
            addCriterion("WHERE_CLAUSE <>", value, "whereClause");
            return (Criteria) this;
        }

        public Criteria andWhereClauseGreaterThan(String value) {
            addCriterion("WHERE_CLAUSE >", value, "whereClause");
            return (Criteria) this;
        }

        public Criteria andWhereClauseGreaterThanOrEqualTo(String value) {
            addCriterion("WHERE_CLAUSE >=", value, "whereClause");
            return (Criteria) this;
        }

        public Criteria andWhereClauseLessThan(String value) {
            addCriterion("WHERE_CLAUSE <", value, "whereClause");
            return (Criteria) this;
        }

        public Criteria andWhereClauseLessThanOrEqualTo(String value) {
            addCriterion("WHERE_CLAUSE <=", value, "whereClause");
            return (Criteria) this;
        }

        public Criteria andWhereClauseLike(String value) {
            addCriterion("WHERE_CLAUSE like", value, "whereClause");
            return (Criteria) this;
        }

        public Criteria andWhereClauseNotLike(String value) {
            addCriterion("WHERE_CLAUSE not like", value, "whereClause");
            return (Criteria) this;
        }

        public Criteria andWhereClauseIn(List<String> values) {
            addCriterion("WHERE_CLAUSE in", values, "whereClause");
            return (Criteria) this;
        }

        public Criteria andWhereClauseNotIn(List<String> values) {
            addCriterion("WHERE_CLAUSE not in", values, "whereClause");
            return (Criteria) this;
        }

        public Criteria andWhereClauseBetween(String value1, String value2) {
            addCriterion("WHERE_CLAUSE between", value1, value2, "whereClause");
            return (Criteria) this;
        }

        public Criteria andWhereClauseNotBetween(String value1, String value2) {
            addCriterion("WHERE_CLAUSE not between", value1, value2, "whereClause");
            return (Criteria) this;
        }

        public Criteria andGroupByClauseIsNull() {
            addCriterion("GROUP_BY_CLAUSE is null");
            return (Criteria) this;
        }

        public Criteria andGroupByClauseIsNotNull() {
            addCriterion("GROUP_BY_CLAUSE is not null");
            return (Criteria) this;
        }

        public Criteria andGroupByClauseEqualTo(String value) {
            addCriterion("GROUP_BY_CLAUSE =", value, "groupByClause");
            return (Criteria) this;
        }

        public Criteria andGroupByClauseNotEqualTo(String value) {
            addCriterion("GROUP_BY_CLAUSE <>", value, "groupByClause");
            return (Criteria) this;
        }

        public Criteria andGroupByClauseGreaterThan(String value) {
            addCriterion("GROUP_BY_CLAUSE >", value, "groupByClause");
            return (Criteria) this;
        }

        public Criteria andGroupByClauseGreaterThanOrEqualTo(String value) {
            addCriterion("GROUP_BY_CLAUSE >=", value, "groupByClause");
            return (Criteria) this;
        }

        public Criteria andGroupByClauseLessThan(String value) {
            addCriterion("GROUP_BY_CLAUSE <", value, "groupByClause");
            return (Criteria) this;
        }

        public Criteria andGroupByClauseLessThanOrEqualTo(String value) {
            addCriterion("GROUP_BY_CLAUSE <=", value, "groupByClause");
            return (Criteria) this;
        }

        public Criteria andGroupByClauseLike(String value) {
            addCriterion("GROUP_BY_CLAUSE like", value, "groupByClause");
            return (Criteria) this;
        }

        public Criteria andGroupByClauseNotLike(String value) {
            addCriterion("GROUP_BY_CLAUSE not like", value, "groupByClause");
            return (Criteria) this;
        }

        public Criteria andGroupByClauseIn(List<String> values) {
            addCriterion("GROUP_BY_CLAUSE in", values, "groupByClause");
            return (Criteria) this;
        }

        public Criteria andGroupByClauseNotIn(List<String> values) {
            addCriterion("GROUP_BY_CLAUSE not in", values, "groupByClause");
            return (Criteria) this;
        }

        public Criteria andGroupByClauseBetween(String value1, String value2) {
            addCriterion("GROUP_BY_CLAUSE between", value1, value2, "groupByClause");
            return (Criteria) this;
        }

        public Criteria andGroupByClauseNotBetween(String value1, String value2) {
            addCriterion("GROUP_BY_CLAUSE not between", value1, value2, "groupByClause");
            return (Criteria) this;
        }

        public Criteria andHavingClauseIsNull() {
            addCriterion("HAVING_CLAUSE is null");
            return (Criteria) this;
        }

        public Criteria andHavingClauseIsNotNull() {
            addCriterion("HAVING_CLAUSE is not null");
            return (Criteria) this;
        }

        public Criteria andHavingClauseEqualTo(String value) {
            addCriterion("HAVING_CLAUSE =", value, "havingClause");
            return (Criteria) this;
        }

        public Criteria andHavingClauseNotEqualTo(String value) {
            addCriterion("HAVING_CLAUSE <>", value, "havingClause");
            return (Criteria) this;
        }

        public Criteria andHavingClauseGreaterThan(String value) {
            addCriterion("HAVING_CLAUSE >", value, "havingClause");
            return (Criteria) this;
        }

        public Criteria andHavingClauseGreaterThanOrEqualTo(String value) {
            addCriterion("HAVING_CLAUSE >=", value, "havingClause");
            return (Criteria) this;
        }

        public Criteria andHavingClauseLessThan(String value) {
            addCriterion("HAVING_CLAUSE <", value, "havingClause");
            return (Criteria) this;
        }

        public Criteria andHavingClauseLessThanOrEqualTo(String value) {
            addCriterion("HAVING_CLAUSE <=", value, "havingClause");
            return (Criteria) this;
        }

        public Criteria andHavingClauseLike(String value) {
            addCriterion("HAVING_CLAUSE like", value, "havingClause");
            return (Criteria) this;
        }

        public Criteria andHavingClauseNotLike(String value) {
            addCriterion("HAVING_CLAUSE not like", value, "havingClause");
            return (Criteria) this;
        }

        public Criteria andHavingClauseIn(List<String> values) {
            addCriterion("HAVING_CLAUSE in", values, "havingClause");
            return (Criteria) this;
        }

        public Criteria andHavingClauseNotIn(List<String> values) {
            addCriterion("HAVING_CLAUSE not in", values, "havingClause");
            return (Criteria) this;
        }

        public Criteria andHavingClauseBetween(String value1, String value2) {
            addCriterion("HAVING_CLAUSE between", value1, value2, "havingClause");
            return (Criteria) this;
        }

        public Criteria andHavingClauseNotBetween(String value1, String value2) {
            addCriterion("HAVING_CLAUSE not between", value1, value2, "havingClause");
            return (Criteria) this;
        }

        public Criteria andOrderByClauseIsNull() {
            addCriterion("ORDER_BY_CLAUSE is null");
            return (Criteria) this;
        }

        public Criteria andOrderByClauseIsNotNull() {
            addCriterion("ORDER_BY_CLAUSE is not null");
            return (Criteria) this;
        }

        public Criteria andOrderByClauseEqualTo(String value) {
            addCriterion("ORDER_BY_CLAUSE =", value, "orderByClause");
            return (Criteria) this;
        }

        public Criteria andOrderByClauseNotEqualTo(String value) {
            addCriterion("ORDER_BY_CLAUSE <>", value, "orderByClause");
            return (Criteria) this;
        }

        public Criteria andOrderByClauseGreaterThan(String value) {
            addCriterion("ORDER_BY_CLAUSE >", value, "orderByClause");
            return (Criteria) this;
        }

        public Criteria andOrderByClauseGreaterThanOrEqualTo(String value) {
            addCriterion("ORDER_BY_CLAUSE >=", value, "orderByClause");
            return (Criteria) this;
        }

        public Criteria andOrderByClauseLessThan(String value) {
            addCriterion("ORDER_BY_CLAUSE <", value, "orderByClause");
            return (Criteria) this;
        }

        public Criteria andOrderByClauseLessThanOrEqualTo(String value) {
            addCriterion("ORDER_BY_CLAUSE <=", value, "orderByClause");
            return (Criteria) this;
        }

        public Criteria andOrderByClauseLike(String value) {
            addCriterion("ORDER_BY_CLAUSE like", value, "orderByClause");
            return (Criteria) this;
        }

        public Criteria andOrderByClauseNotLike(String value) {
            addCriterion("ORDER_BY_CLAUSE not like", value, "orderByClause");
            return (Criteria) this;
        }

        public Criteria andOrderByClauseIn(List<String> values) {
            addCriterion("ORDER_BY_CLAUSE in", values, "orderByClause");
            return (Criteria) this;
        }

        public Criteria andOrderByClauseNotIn(List<String> values) {
            addCriterion("ORDER_BY_CLAUSE not in", values, "orderByClause");
            return (Criteria) this;
        }

        public Criteria andOrderByClauseBetween(String value1, String value2) {
            addCriterion("ORDER_BY_CLAUSE between", value1, value2, "orderByClause");
            return (Criteria) this;
        }

        public Criteria andOrderByClauseNotBetween(String value1, String value2) {
            addCriterion("ORDER_BY_CLAUSE not between", value1, value2, "orderByClause");
            return (Criteria) this;
        }

        public Criteria andParamMapIsNull() {
            addCriterion("PARAM_MAP is null");
            return (Criteria) this;
        }

        public Criteria andParamMapIsNotNull() {
            addCriterion("PARAM_MAP is not null");
            return (Criteria) this;
        }

        public Criteria andParamMapEqualTo(String value) {
            addCriterion("PARAM_MAP =", value, "paramMap");
            return (Criteria) this;
        }

        public Criteria andParamMapNotEqualTo(String value) {
            addCriterion("PARAM_MAP <>", value, "paramMap");
            return (Criteria) this;
        }

        public Criteria andParamMapGreaterThan(String value) {
            addCriterion("PARAM_MAP >", value, "paramMap");
            return (Criteria) this;
        }

        public Criteria andParamMapGreaterThanOrEqualTo(String value) {
            addCriterion("PARAM_MAP >=", value, "paramMap");
            return (Criteria) this;
        }

        public Criteria andParamMapLessThan(String value) {
            addCriterion("PARAM_MAP <", value, "paramMap");
            return (Criteria) this;
        }

        public Criteria andParamMapLessThanOrEqualTo(String value) {
            addCriterion("PARAM_MAP <=", value, "paramMap");
            return (Criteria) this;
        }

        public Criteria andParamMapLike(String value) {
            addCriterion("PARAM_MAP like", value, "paramMap");
            return (Criteria) this;
        }

        public Criteria andParamMapNotLike(String value) {
            addCriterion("PARAM_MAP not like", value, "paramMap");
            return (Criteria) this;
        }

        public Criteria andParamMapIn(List<String> values) {
            addCriterion("PARAM_MAP in", values, "paramMap");
            return (Criteria) this;
        }

        public Criteria andParamMapNotIn(List<String> values) {
            addCriterion("PARAM_MAP not in", values, "paramMap");
            return (Criteria) this;
        }

        public Criteria andParamMapBetween(String value1, String value2) {
            addCriterion("PARAM_MAP between", value1, value2, "paramMap");
            return (Criteria) this;
        }

        public Criteria andParamMapNotBetween(String value1, String value2) {
            addCriterion("PARAM_MAP not between", value1, value2, "paramMap");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIsNull() {
            addCriterion("UPDATED_AT is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIsNotNull() {
            addCriterion("UPDATED_AT is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtEqualTo(LocalDateTime value) {
            addCriterion("UPDATED_AT =", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotEqualTo(LocalDateTime value) {
            addCriterion("UPDATED_AT <>", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThan(LocalDateTime value) {
            addCriterion("UPDATED_AT >", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("UPDATED_AT >=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThan(LocalDateTime value) {
            addCriterion("UPDATED_AT <", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("UPDATED_AT <=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIn(List<LocalDateTime> values) {
            addCriterion("UPDATED_AT in", values, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotIn(List<LocalDateTime> values) {
            addCriterion("UPDATED_AT not in", values, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("UPDATED_AT between", value1, value2, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("UPDATED_AT not between", value1, value2, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNull() {
            addCriterion("CREATED_AT is null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNotNull() {
            addCriterion("CREATED_AT is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtEqualTo(LocalDateTime value) {
            addCriterion("CREATED_AT =", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualTo(LocalDateTime value) {
            addCriterion("CREATED_AT <>", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThan(LocalDateTime value) {
            addCriterion("CREATED_AT >", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("CREATED_AT >=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThan(LocalDateTime value) {
            addCriterion("CREATED_AT <", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("CREATED_AT <=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIn(List<LocalDateTime> values) {
            addCriterion("CREATED_AT in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotIn(List<LocalDateTime> values) {
            addCriterion("CREATED_AT not in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("CREATED_AT between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("CREATED_AT not between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        public Criteria andLockVersionIsNull() {
            addCriterion("LOCK_VERSION is null");
            return (Criteria) this;
        }

        public Criteria andLockVersionIsNotNull() {
            addCriterion("LOCK_VERSION is not null");
            return (Criteria) this;
        }

        public Criteria andLockVersionEqualTo(Integer value) {
            addCriterion("LOCK_VERSION =", value, "lockVersion");
            return (Criteria) this;
        }

        public Criteria andLockVersionNotEqualTo(Integer value) {
            addCriterion("LOCK_VERSION <>", value, "lockVersion");
            return (Criteria) this;
        }

        public Criteria andLockVersionGreaterThan(Integer value) {
            addCriterion("LOCK_VERSION >", value, "lockVersion");
            return (Criteria) this;
        }

        public Criteria andLockVersionGreaterThanOrEqualTo(Integer value) {
            addCriterion("LOCK_VERSION >=", value, "lockVersion");
            return (Criteria) this;
        }

        public Criteria andLockVersionLessThan(Integer value) {
            addCriterion("LOCK_VERSION <", value, "lockVersion");
            return (Criteria) this;
        }

        public Criteria andLockVersionLessThanOrEqualTo(Integer value) {
            addCriterion("LOCK_VERSION <=", value, "lockVersion");
            return (Criteria) this;
        }

        public Criteria andLockVersionIn(List<Integer> values) {
            addCriterion("LOCK_VERSION in", values, "lockVersion");
            return (Criteria) this;
        }

        public Criteria andLockVersionNotIn(List<Integer> values) {
            addCriterion("LOCK_VERSION not in", values, "lockVersion");
            return (Criteria) this;
        }

        public Criteria andLockVersionBetween(Integer value1, Integer value2) {
            addCriterion("LOCK_VERSION between", value1, value2, "lockVersion");
            return (Criteria) this;
        }

        public Criteria andLockVersionNotBetween(Integer value1, Integer value2) {
            addCriterion("LOCK_VERSION not between", value1, value2, "lockVersion");
            return (Criteria) this;
        }

        public Criteria andDeletedFlgIsNull() {
            addCriterion("DELETED_FLG is null");
            return (Criteria) this;
        }

        public Criteria andDeletedFlgIsNotNull() {
            addCriterion("DELETED_FLG is not null");
            return (Criteria) this;
        }

        public Criteria andDeletedFlgEqualTo(DeletedFlag value) {
            addCriterion("DELETED_FLG =", value, "deletedFlg");
            return (Criteria) this;
        }

        public Criteria andDeletedFlgNotEqualTo(DeletedFlag value) {
            addCriterion("DELETED_FLG <>", value, "deletedFlg");
            return (Criteria) this;
        }

        public Criteria andDeletedFlgGreaterThan(DeletedFlag value) {
            addCriterion("DELETED_FLG >", value, "deletedFlg");
            return (Criteria) this;
        }

        public Criteria andDeletedFlgGreaterThanOrEqualTo(DeletedFlag value) {
            addCriterion("DELETED_FLG >=", value, "deletedFlg");
            return (Criteria) this;
        }

        public Criteria andDeletedFlgLessThan(DeletedFlag value) {
            addCriterion("DELETED_FLG <", value, "deletedFlg");
            return (Criteria) this;
        }

        public Criteria andDeletedFlgLessThanOrEqualTo(DeletedFlag value) {
            addCriterion("DELETED_FLG <=", value, "deletedFlg");
            return (Criteria) this;
        }

        public Criteria andDeletedFlgIn(List<DeletedFlag> values) {
            addCriterion("DELETED_FLG in", values, "deletedFlg");
            return (Criteria) this;
        }

        public Criteria andDeletedFlgNotIn(List<DeletedFlag> values) {
            addCriterion("DELETED_FLG not in", values, "deletedFlg");
            return (Criteria) this;
        }

        public Criteria andDeletedFlgBetween(DeletedFlag value1, DeletedFlag value2) {
            addCriterion("DELETED_FLG between", value1, value2, "deletedFlg");
            return (Criteria) this;
        }

        public Criteria andDeletedFlgNotBetween(DeletedFlag value1, DeletedFlag value2) {
            addCriterion("DELETED_FLG not between", value1, value2, "deletedFlg");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table SQLTOOL_CLAUSE
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table SQLTOOL_CLAUSE
     *
     * @mbggenerated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
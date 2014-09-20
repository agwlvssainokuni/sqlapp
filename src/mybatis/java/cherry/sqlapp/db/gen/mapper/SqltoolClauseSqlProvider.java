package cherry.sqlapp.db.gen.mapper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.DELETE_FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.ORDER_BY;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT_DISTINCT;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import cherry.sqlapp.db.gen.dto.SqltoolClause;
import cherry.sqlapp.db.gen.dto.SqltoolClauseCriteria.Criteria;
import cherry.sqlapp.db.gen.dto.SqltoolClauseCriteria.Criterion;
import cherry.sqlapp.db.gen.dto.SqltoolClauseCriteria;
import java.util.List;
import java.util.Map;

public class SqltoolClauseSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SQLTOOL_CLAUSE
     *
     * @mbggenerated
     */
    public String countByExample(SqltoolClauseCriteria example) {
        BEGIN();
        SELECT("count(*)");
        FROM("SQLTOOL_CLAUSE");
        applyWhere(example, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SQLTOOL_CLAUSE
     *
     * @mbggenerated
     */
    public String deleteByExample(SqltoolClauseCriteria example) {
        BEGIN();
        DELETE_FROM("SQLTOOL_CLAUSE");
        applyWhere(example, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SQLTOOL_CLAUSE
     *
     * @mbggenerated
     */
    public String insertSelective(SqltoolClause record) {
        BEGIN();
        INSERT_INTO("SQLTOOL_CLAUSE");
        
        if (record.getDatabaseName() != null) {
            VALUES("DATABASE_NAME", "#{databaseName,jdbcType=VARCHAR}");
        }
        
        if (record.getSelectClause() != null) {
            VALUES("SELECT_CLAUSE", "#{selectClause,jdbcType=VARCHAR}");
        }
        
        if (record.getFromClause() != null) {
            VALUES("FROM_CLAUSE", "#{fromClause,jdbcType=VARCHAR}");
        }
        
        if (record.getWhereClause() != null) {
            VALUES("WHERE_CLAUSE", "#{whereClause,jdbcType=VARCHAR}");
        }
        
        if (record.getGroupByClause() != null) {
            VALUES("GROUP_BY_CLAUSE", "#{groupByClause,jdbcType=VARCHAR}");
        }
        
        if (record.getHavingClause() != null) {
            VALUES("HAVING_CLAUSE", "#{havingClause,jdbcType=VARCHAR}");
        }
        
        if (record.getOrderByClause() != null) {
            VALUES("ORDER_BY_CLAUSE", "#{orderByClause,jdbcType=VARCHAR}");
        }
        
        if (record.getParamMap() != null) {
            VALUES("PARAM_MAP", "#{paramMap,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdatedAt() != null) {
            VALUES("UPDATED_AT", "#{updatedAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreatedAt() != null) {
            VALUES("CREATED_AT", "#{createdAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getLockVersion() != null) {
            VALUES("LOCK_VERSION", "#{lockVersion,jdbcType=INTEGER}");
        }
        
        if (record.getDeletedFlg() != null) {
            VALUES("DELETED_FLG", "#{deletedFlg,jdbcType=INTEGER}");
        }
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SQLTOOL_CLAUSE
     *
     * @mbggenerated
     */
    public String selectByExample(SqltoolClauseCriteria example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("ID");
        } else {
            SELECT("ID");
        }
        SELECT("DATABASE_NAME");
        SELECT("SELECT_CLAUSE");
        SELECT("FROM_CLAUSE");
        SELECT("WHERE_CLAUSE");
        SELECT("GROUP_BY_CLAUSE");
        SELECT("HAVING_CLAUSE");
        SELECT("ORDER_BY_CLAUSE");
        SELECT("PARAM_MAP");
        SELECT("UPDATED_AT");
        SELECT("CREATED_AT");
        SELECT("LOCK_VERSION");
        SELECT("DELETED_FLG");
        FROM("SQLTOOL_CLAUSE");
        applyWhere(example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SQLTOOL_CLAUSE
     *
     * @mbggenerated
     */
    public String updateByExampleSelective(Map<String, Object> parameter) {
        SqltoolClause record = (SqltoolClause) parameter.get("record");
        SqltoolClauseCriteria example = (SqltoolClauseCriteria) parameter.get("example");
        
        BEGIN();
        UPDATE("SQLTOOL_CLAUSE");
        
        if (record.getId() != null) {
            SET("ID = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getDatabaseName() != null) {
            SET("DATABASE_NAME = #{record.databaseName,jdbcType=VARCHAR}");
        }
        
        if (record.getSelectClause() != null) {
            SET("SELECT_CLAUSE = #{record.selectClause,jdbcType=VARCHAR}");
        }
        
        if (record.getFromClause() != null) {
            SET("FROM_CLAUSE = #{record.fromClause,jdbcType=VARCHAR}");
        }
        
        if (record.getWhereClause() != null) {
            SET("WHERE_CLAUSE = #{record.whereClause,jdbcType=VARCHAR}");
        }
        
        if (record.getGroupByClause() != null) {
            SET("GROUP_BY_CLAUSE = #{record.groupByClause,jdbcType=VARCHAR}");
        }
        
        if (record.getHavingClause() != null) {
            SET("HAVING_CLAUSE = #{record.havingClause,jdbcType=VARCHAR}");
        }
        
        if (record.getOrderByClause() != null) {
            SET("ORDER_BY_CLAUSE = #{record.orderByClause,jdbcType=VARCHAR}");
        }
        
        if (record.getParamMap() != null) {
            SET("PARAM_MAP = #{record.paramMap,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdatedAt() != null) {
            SET("UPDATED_AT = #{record.updatedAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreatedAt() != null) {
            SET("CREATED_AT = #{record.createdAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getLockVersion() != null) {
            SET("LOCK_VERSION = #{record.lockVersion,jdbcType=INTEGER}");
        }
        
        if (record.getDeletedFlg() != null) {
            SET("DELETED_FLG = #{record.deletedFlg,jdbcType=INTEGER}");
        }
        
        applyWhere(example, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SQLTOOL_CLAUSE
     *
     * @mbggenerated
     */
    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("SQLTOOL_CLAUSE");
        
        SET("ID = #{record.id,jdbcType=INTEGER}");
        SET("DATABASE_NAME = #{record.databaseName,jdbcType=VARCHAR}");
        SET("SELECT_CLAUSE = #{record.selectClause,jdbcType=VARCHAR}");
        SET("FROM_CLAUSE = #{record.fromClause,jdbcType=VARCHAR}");
        SET("WHERE_CLAUSE = #{record.whereClause,jdbcType=VARCHAR}");
        SET("GROUP_BY_CLAUSE = #{record.groupByClause,jdbcType=VARCHAR}");
        SET("HAVING_CLAUSE = #{record.havingClause,jdbcType=VARCHAR}");
        SET("ORDER_BY_CLAUSE = #{record.orderByClause,jdbcType=VARCHAR}");
        SET("PARAM_MAP = #{record.paramMap,jdbcType=VARCHAR}");
        SET("UPDATED_AT = #{record.updatedAt,jdbcType=TIMESTAMP}");
        SET("CREATED_AT = #{record.createdAt,jdbcType=TIMESTAMP}");
        SET("LOCK_VERSION = #{record.lockVersion,jdbcType=INTEGER}");
        SET("DELETED_FLG = #{record.deletedFlg,jdbcType=INTEGER}");
        
        SqltoolClauseCriteria example = (SqltoolClauseCriteria) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SQLTOOL_CLAUSE
     *
     * @mbggenerated
     */
    public String updateByPrimaryKeySelective(SqltoolClause record) {
        BEGIN();
        UPDATE("SQLTOOL_CLAUSE");
        
        if (record.getDatabaseName() != null) {
            SET("DATABASE_NAME = #{databaseName,jdbcType=VARCHAR}");
        }
        
        if (record.getSelectClause() != null) {
            SET("SELECT_CLAUSE = #{selectClause,jdbcType=VARCHAR}");
        }
        
        if (record.getFromClause() != null) {
            SET("FROM_CLAUSE = #{fromClause,jdbcType=VARCHAR}");
        }
        
        if (record.getWhereClause() != null) {
            SET("WHERE_CLAUSE = #{whereClause,jdbcType=VARCHAR}");
        }
        
        if (record.getGroupByClause() != null) {
            SET("GROUP_BY_CLAUSE = #{groupByClause,jdbcType=VARCHAR}");
        }
        
        if (record.getHavingClause() != null) {
            SET("HAVING_CLAUSE = #{havingClause,jdbcType=VARCHAR}");
        }
        
        if (record.getOrderByClause() != null) {
            SET("ORDER_BY_CLAUSE = #{orderByClause,jdbcType=VARCHAR}");
        }
        
        if (record.getParamMap() != null) {
            SET("PARAM_MAP = #{paramMap,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdatedAt() != null) {
            SET("UPDATED_AT = #{updatedAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreatedAt() != null) {
            SET("CREATED_AT = #{createdAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getLockVersion() != null) {
            SET("LOCK_VERSION = #{lockVersion,jdbcType=INTEGER}");
        }
        
        if (record.getDeletedFlg() != null) {
            SET("DELETED_FLG = #{deletedFlg,jdbcType=INTEGER}");
        }
        
        WHERE("ID = #{id,jdbcType=INTEGER}");
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SQLTOOL_CLAUSE
     *
     * @mbggenerated
     */
    protected void applyWhere(SqltoolClauseCriteria example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            WHERE(sb.toString());
        }
    }
}
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

import cherry.sqlapp.db.gen.dto.AsyncProcessCommandResult;
import cherry.sqlapp.db.gen.dto.AsyncProcessCommandResultCriteria.Criteria;
import cherry.sqlapp.db.gen.dto.AsyncProcessCommandResultCriteria.Criterion;
import cherry.sqlapp.db.gen.dto.AsyncProcessCommandResultCriteria;
import java.util.List;
import java.util.Map;

public class AsyncProcessCommandResultSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS_COMMAND_RESULT
     *
     * @mbggenerated
     */
    public String countByExample(AsyncProcessCommandResultCriteria example) {
        BEGIN();
        SELECT("count(*)");
        FROM("ASYNC_PROCESS_COMMAND_RESULT");
        applyWhere(example, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS_COMMAND_RESULT
     *
     * @mbggenerated
     */
    public String deleteByExample(AsyncProcessCommandResultCriteria example) {
        BEGIN();
        DELETE_FROM("ASYNC_PROCESS_COMMAND_RESULT");
        applyWhere(example, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS_COMMAND_RESULT
     *
     * @mbggenerated
     */
    public String insertSelective(AsyncProcessCommandResult record) {
        BEGIN();
        INSERT_INTO("ASYNC_PROCESS_COMMAND_RESULT");
        
        if (record.getAsyncId() != null) {
            VALUES("ASYNC_ID", "#{asyncId,jdbcType=BIGINT}");
        }
        
        if (record.getExitValue() != null) {
            VALUES("EXIT_VALUE", "#{exitValue,jdbcType=INTEGER}");
        }
        
        if (record.getStdout() != null) {
            VALUES("STDOUT", "#{stdout,jdbcType=VARCHAR}");
        }
        
        if (record.getStderr() != null) {
            VALUES("STDERR", "#{stderr,jdbcType=VARCHAR}");
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
     * This method corresponds to the database table ASYNC_PROCESS_COMMAND_RESULT
     *
     * @mbggenerated
     */
    public String selectByExample(AsyncProcessCommandResultCriteria example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("ID");
        } else {
            SELECT("ID");
        }
        SELECT("ASYNC_ID");
        SELECT("EXIT_VALUE");
        SELECT("STDOUT");
        SELECT("STDERR");
        SELECT("UPDATED_AT");
        SELECT("CREATED_AT");
        SELECT("LOCK_VERSION");
        SELECT("DELETED_FLG");
        FROM("ASYNC_PROCESS_COMMAND_RESULT");
        applyWhere(example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS_COMMAND_RESULT
     *
     * @mbggenerated
     */
    public String updateByExampleSelective(Map<String, Object> parameter) {
        AsyncProcessCommandResult record = (AsyncProcessCommandResult) parameter.get("record");
        AsyncProcessCommandResultCriteria example = (AsyncProcessCommandResultCriteria) parameter.get("example");
        
        BEGIN();
        UPDATE("ASYNC_PROCESS_COMMAND_RESULT");
        
        if (record.getId() != null) {
            SET("ID = #{record.id,jdbcType=BIGINT}");
        }
        
        if (record.getAsyncId() != null) {
            SET("ASYNC_ID = #{record.asyncId,jdbcType=BIGINT}");
        }
        
        if (record.getExitValue() != null) {
            SET("EXIT_VALUE = #{record.exitValue,jdbcType=INTEGER}");
        }
        
        if (record.getStdout() != null) {
            SET("STDOUT = #{record.stdout,jdbcType=VARCHAR}");
        }
        
        if (record.getStderr() != null) {
            SET("STDERR = #{record.stderr,jdbcType=VARCHAR}");
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
     * This method corresponds to the database table ASYNC_PROCESS_COMMAND_RESULT
     *
     * @mbggenerated
     */
    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("ASYNC_PROCESS_COMMAND_RESULT");
        
        SET("ID = #{record.id,jdbcType=BIGINT}");
        SET("ASYNC_ID = #{record.asyncId,jdbcType=BIGINT}");
        SET("EXIT_VALUE = #{record.exitValue,jdbcType=INTEGER}");
        SET("STDOUT = #{record.stdout,jdbcType=VARCHAR}");
        SET("STDERR = #{record.stderr,jdbcType=VARCHAR}");
        SET("UPDATED_AT = #{record.updatedAt,jdbcType=TIMESTAMP}");
        SET("CREATED_AT = #{record.createdAt,jdbcType=TIMESTAMP}");
        SET("LOCK_VERSION = #{record.lockVersion,jdbcType=INTEGER}");
        SET("DELETED_FLG = #{record.deletedFlg,jdbcType=INTEGER}");
        
        AsyncProcessCommandResultCriteria example = (AsyncProcessCommandResultCriteria) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS_COMMAND_RESULT
     *
     * @mbggenerated
     */
    public String updateByPrimaryKeySelective(AsyncProcessCommandResult record) {
        BEGIN();
        UPDATE("ASYNC_PROCESS_COMMAND_RESULT");
        
        if (record.getAsyncId() != null) {
            SET("ASYNC_ID = #{asyncId,jdbcType=BIGINT}");
        }
        
        if (record.getExitValue() != null) {
            SET("EXIT_VALUE = #{exitValue,jdbcType=INTEGER}");
        }
        
        if (record.getStdout() != null) {
            SET("STDOUT = #{stdout,jdbcType=VARCHAR}");
        }
        
        if (record.getStderr() != null) {
            SET("STDERR = #{stderr,jdbcType=VARCHAR}");
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
        
        WHERE("ID = #{id,jdbcType=BIGINT}");
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS_COMMAND_RESULT
     *
     * @mbggenerated
     */
    protected void applyWhere(AsyncProcessCommandResultCriteria example, boolean includeExamplePhrase) {
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
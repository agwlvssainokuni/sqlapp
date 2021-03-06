package cherry.sqlapp.db.gen.mapper;

import cherry.sqlapp.db.gen.dto.AsyncProcessException;
import cherry.sqlapp.db.gen.dto.AsyncProcessExceptionCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.JdbcType;

public interface AsyncProcessExceptionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS_EXCEPTION
     *
     * @mbggenerated
     */
    @SelectProvider(type=AsyncProcessExceptionSqlProvider.class, method="countByExample")
    int countByExample(AsyncProcessExceptionCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS_EXCEPTION
     *
     * @mbggenerated
     */
    @DeleteProvider(type=AsyncProcessExceptionSqlProvider.class, method="deleteByExample")
    int deleteByExample(AsyncProcessExceptionCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS_EXCEPTION
     *
     * @mbggenerated
     */
    @Delete({
        "delete from ASYNC_PROCESS_EXCEPTION",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS_EXCEPTION
     *
     * @mbggenerated
     */
    @Insert({
        "insert into ASYNC_PROCESS_EXCEPTION (ASYNC_ID, UPDATED_AT, ",
        "CREATED_AT, LOCK_VERSION, ",
        "DELETED_FLG, EXCEPTION)",
        "values (#{asyncId,jdbcType=BIGINT}, #{updatedAt,jdbcType=TIMESTAMP}, ",
        "#{createdAt,jdbcType=TIMESTAMP}, #{lockVersion,jdbcType=INTEGER}, ",
        "#{deletedFlg,jdbcType=INTEGER}, #{exception,jdbcType=CLOB})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(AsyncProcessException record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS_EXCEPTION
     *
     * @mbggenerated
     */
    @InsertProvider(type=AsyncProcessExceptionSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(AsyncProcessException record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS_EXCEPTION
     *
     * @mbggenerated
     */
    @SelectProvider(type=AsyncProcessExceptionSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="ASYNC_ID", property="asyncId", jdbcType=JdbcType.BIGINT),
        @Result(column="UPDATED_AT", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATED_AT", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LOCK_VERSION", property="lockVersion", jdbcType=JdbcType.INTEGER),
        @Result(column="DELETED_FLG", property="deletedFlg", jdbcType=JdbcType.INTEGER),
        @Result(column="EXCEPTION", property="exception", jdbcType=JdbcType.CLOB)
    })
    List<AsyncProcessException> selectByExampleWithBLOBsWithRowbounds(AsyncProcessExceptionCriteria example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS_EXCEPTION
     *
     * @mbggenerated
     */
    @SelectProvider(type=AsyncProcessExceptionSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="ASYNC_ID", property="asyncId", jdbcType=JdbcType.BIGINT),
        @Result(column="UPDATED_AT", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATED_AT", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LOCK_VERSION", property="lockVersion", jdbcType=JdbcType.INTEGER),
        @Result(column="DELETED_FLG", property="deletedFlg", jdbcType=JdbcType.INTEGER),
        @Result(column="EXCEPTION", property="exception", jdbcType=JdbcType.CLOB)
    })
    List<AsyncProcessException> selectByExampleWithBLOBs(AsyncProcessExceptionCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS_EXCEPTION
     *
     * @mbggenerated
     */
    @SelectProvider(type=AsyncProcessExceptionSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="ASYNC_ID", property="asyncId", jdbcType=JdbcType.BIGINT),
        @Result(column="UPDATED_AT", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATED_AT", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LOCK_VERSION", property="lockVersion", jdbcType=JdbcType.INTEGER),
        @Result(column="DELETED_FLG", property="deletedFlg", jdbcType=JdbcType.INTEGER)
    })
    List<AsyncProcessException> selectByExampleWithRowbounds(AsyncProcessExceptionCriteria example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS_EXCEPTION
     *
     * @mbggenerated
     */
    @SelectProvider(type=AsyncProcessExceptionSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="ASYNC_ID", property="asyncId", jdbcType=JdbcType.BIGINT),
        @Result(column="UPDATED_AT", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATED_AT", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LOCK_VERSION", property="lockVersion", jdbcType=JdbcType.INTEGER),
        @Result(column="DELETED_FLG", property="deletedFlg", jdbcType=JdbcType.INTEGER)
    })
    List<AsyncProcessException> selectByExample(AsyncProcessExceptionCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS_EXCEPTION
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "ID, ASYNC_ID, UPDATED_AT, CREATED_AT, LOCK_VERSION, DELETED_FLG, EXCEPTION",
        "from ASYNC_PROCESS_EXCEPTION",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="ASYNC_ID", property="asyncId", jdbcType=JdbcType.BIGINT),
        @Result(column="UPDATED_AT", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATED_AT", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LOCK_VERSION", property="lockVersion", jdbcType=JdbcType.INTEGER),
        @Result(column="DELETED_FLG", property="deletedFlg", jdbcType=JdbcType.INTEGER),
        @Result(column="EXCEPTION", property="exception", jdbcType=JdbcType.CLOB)
    })
    AsyncProcessException selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS_EXCEPTION
     *
     * @mbggenerated
     */
    @UpdateProvider(type=AsyncProcessExceptionSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") AsyncProcessException record, @Param("example") AsyncProcessExceptionCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS_EXCEPTION
     *
     * @mbggenerated
     */
    @UpdateProvider(type=AsyncProcessExceptionSqlProvider.class, method="updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") AsyncProcessException record, @Param("example") AsyncProcessExceptionCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS_EXCEPTION
     *
     * @mbggenerated
     */
    @UpdateProvider(type=AsyncProcessExceptionSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") AsyncProcessException record, @Param("example") AsyncProcessExceptionCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS_EXCEPTION
     *
     * @mbggenerated
     */
    @UpdateProvider(type=AsyncProcessExceptionSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(AsyncProcessException record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS_EXCEPTION
     *
     * @mbggenerated
     */
    @Update({
        "update ASYNC_PROCESS_EXCEPTION",
        "set ASYNC_ID = #{asyncId,jdbcType=BIGINT},",
          "UPDATED_AT = #{updatedAt,jdbcType=TIMESTAMP},",
          "CREATED_AT = #{createdAt,jdbcType=TIMESTAMP},",
          "LOCK_VERSION = #{lockVersion,jdbcType=INTEGER},",
          "DELETED_FLG = #{deletedFlg,jdbcType=INTEGER},",
          "EXCEPTION = #{exception,jdbcType=CLOB}",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKeyWithBLOBs(AsyncProcessException record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS_EXCEPTION
     *
     * @mbggenerated
     */
    @Update({
        "update ASYNC_PROCESS_EXCEPTION",
        "set ASYNC_ID = #{asyncId,jdbcType=BIGINT},",
          "UPDATED_AT = #{updatedAt,jdbcType=TIMESTAMP},",
          "CREATED_AT = #{createdAt,jdbcType=TIMESTAMP},",
          "LOCK_VERSION = #{lockVersion,jdbcType=INTEGER},",
          "DELETED_FLG = #{deletedFlg,jdbcType=INTEGER}",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(AsyncProcessException record);
}
package cherry.sqlapp.db.gen.mapper;

import cherry.sqlapp.db.gen.dto.AsyncProcessFileArg;
import cherry.sqlapp.db.gen.dto.AsyncProcessFileArgCriteria;
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

public interface AsyncProcessFileArgMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS_FILE_ARG
     *
     * @mbggenerated
     */
    @SelectProvider(type=AsyncProcessFileArgSqlProvider.class, method="countByExample")
    int countByExample(AsyncProcessFileArgCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS_FILE_ARG
     *
     * @mbggenerated
     */
    @DeleteProvider(type=AsyncProcessFileArgSqlProvider.class, method="deleteByExample")
    int deleteByExample(AsyncProcessFileArgCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS_FILE_ARG
     *
     * @mbggenerated
     */
    @Delete({
        "delete from ASYNC_PROCESS_FILE_ARG",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS_FILE_ARG
     *
     * @mbggenerated
     */
    @Insert({
        "insert into ASYNC_PROCESS_FILE_ARG (ASYNC_ID, ARGUMENT, ",
        "UPDATED_AT, CREATED_AT, ",
        "LOCK_VERSION, DELETED_FLG)",
        "values (#{asyncId,jdbcType=BIGINT}, #{argument,jdbcType=VARCHAR}, ",
        "#{updatedAt,jdbcType=TIMESTAMP}, #{createdAt,jdbcType=TIMESTAMP}, ",
        "#{lockVersion,jdbcType=INTEGER}, #{deletedFlg,jdbcType=INTEGER})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(AsyncProcessFileArg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS_FILE_ARG
     *
     * @mbggenerated
     */
    @InsertProvider(type=AsyncProcessFileArgSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(AsyncProcessFileArg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS_FILE_ARG
     *
     * @mbggenerated
     */
    @SelectProvider(type=AsyncProcessFileArgSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="ASYNC_ID", property="asyncId", jdbcType=JdbcType.BIGINT),
        @Result(column="ARGUMENT", property="argument", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATED_AT", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATED_AT", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LOCK_VERSION", property="lockVersion", jdbcType=JdbcType.INTEGER),
        @Result(column="DELETED_FLG", property="deletedFlg", jdbcType=JdbcType.INTEGER)
    })
    List<AsyncProcessFileArg> selectByExampleWithRowbounds(AsyncProcessFileArgCriteria example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS_FILE_ARG
     *
     * @mbggenerated
     */
    @SelectProvider(type=AsyncProcessFileArgSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="ASYNC_ID", property="asyncId", jdbcType=JdbcType.BIGINT),
        @Result(column="ARGUMENT", property="argument", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATED_AT", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATED_AT", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LOCK_VERSION", property="lockVersion", jdbcType=JdbcType.INTEGER),
        @Result(column="DELETED_FLG", property="deletedFlg", jdbcType=JdbcType.INTEGER)
    })
    List<AsyncProcessFileArg> selectByExample(AsyncProcessFileArgCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS_FILE_ARG
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "ID, ASYNC_ID, ARGUMENT, UPDATED_AT, CREATED_AT, LOCK_VERSION, DELETED_FLG",
        "from ASYNC_PROCESS_FILE_ARG",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="ASYNC_ID", property="asyncId", jdbcType=JdbcType.BIGINT),
        @Result(column="ARGUMENT", property="argument", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATED_AT", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATED_AT", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LOCK_VERSION", property="lockVersion", jdbcType=JdbcType.INTEGER),
        @Result(column="DELETED_FLG", property="deletedFlg", jdbcType=JdbcType.INTEGER)
    })
    AsyncProcessFileArg selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS_FILE_ARG
     *
     * @mbggenerated
     */
    @UpdateProvider(type=AsyncProcessFileArgSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") AsyncProcessFileArg record, @Param("example") AsyncProcessFileArgCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS_FILE_ARG
     *
     * @mbggenerated
     */
    @UpdateProvider(type=AsyncProcessFileArgSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") AsyncProcessFileArg record, @Param("example") AsyncProcessFileArgCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS_FILE_ARG
     *
     * @mbggenerated
     */
    @UpdateProvider(type=AsyncProcessFileArgSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(AsyncProcessFileArg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS_FILE_ARG
     *
     * @mbggenerated
     */
    @Update({
        "update ASYNC_PROCESS_FILE_ARG",
        "set ASYNC_ID = #{asyncId,jdbcType=BIGINT},",
          "ARGUMENT = #{argument,jdbcType=VARCHAR},",
          "UPDATED_AT = #{updatedAt,jdbcType=TIMESTAMP},",
          "CREATED_AT = #{createdAt,jdbcType=TIMESTAMP},",
          "LOCK_VERSION = #{lockVersion,jdbcType=INTEGER},",
          "DELETED_FLG = #{deletedFlg,jdbcType=INTEGER}",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(AsyncProcessFileArg record);
}
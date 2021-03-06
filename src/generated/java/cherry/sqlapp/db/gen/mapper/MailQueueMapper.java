package cherry.sqlapp.db.gen.mapper;

import cherry.sqlapp.db.gen.dto.MailQueue;
import cherry.sqlapp.db.gen.dto.MailQueueCriteria;
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

public interface MailQueueMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_QUEUE
     *
     * @mbggenerated
     */
    @SelectProvider(type=MailQueueSqlProvider.class, method="countByExample")
    int countByExample(MailQueueCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_QUEUE
     *
     * @mbggenerated
     */
    @DeleteProvider(type=MailQueueSqlProvider.class, method="deleteByExample")
    int deleteByExample(MailQueueCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_QUEUE
     *
     * @mbggenerated
     */
    @Delete({
        "delete from MAIL_QUEUE",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_QUEUE
     *
     * @mbggenerated
     */
    @Insert({
        "insert into MAIL_QUEUE (MAIL_ID, SCHEDULED_AT, ",
        "UPDATED_AT, CREATED_AT, ",
        "LOCK_VERSION, DELETED_FLG)",
        "values (#{mailId,jdbcType=BIGINT}, #{scheduledAt,jdbcType=TIMESTAMP}, ",
        "#{updatedAt,jdbcType=TIMESTAMP}, #{createdAt,jdbcType=TIMESTAMP}, ",
        "#{lockVersion,jdbcType=INTEGER}, #{deletedFlg,jdbcType=INTEGER})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(MailQueue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_QUEUE
     *
     * @mbggenerated
     */
    @InsertProvider(type=MailQueueSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(MailQueue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_QUEUE
     *
     * @mbggenerated
     */
    @SelectProvider(type=MailQueueSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="MAIL_ID", property="mailId", jdbcType=JdbcType.BIGINT),
        @Result(column="SCHEDULED_AT", property="scheduledAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATED_AT", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATED_AT", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LOCK_VERSION", property="lockVersion", jdbcType=JdbcType.INTEGER),
        @Result(column="DELETED_FLG", property="deletedFlg", jdbcType=JdbcType.INTEGER)
    })
    List<MailQueue> selectByExampleWithRowbounds(MailQueueCriteria example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_QUEUE
     *
     * @mbggenerated
     */
    @SelectProvider(type=MailQueueSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="MAIL_ID", property="mailId", jdbcType=JdbcType.BIGINT),
        @Result(column="SCHEDULED_AT", property="scheduledAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATED_AT", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATED_AT", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LOCK_VERSION", property="lockVersion", jdbcType=JdbcType.INTEGER),
        @Result(column="DELETED_FLG", property="deletedFlg", jdbcType=JdbcType.INTEGER)
    })
    List<MailQueue> selectByExample(MailQueueCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_QUEUE
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "ID, MAIL_ID, SCHEDULED_AT, UPDATED_AT, CREATED_AT, LOCK_VERSION, DELETED_FLG",
        "from MAIL_QUEUE",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="MAIL_ID", property="mailId", jdbcType=JdbcType.BIGINT),
        @Result(column="SCHEDULED_AT", property="scheduledAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATED_AT", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATED_AT", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LOCK_VERSION", property="lockVersion", jdbcType=JdbcType.INTEGER),
        @Result(column="DELETED_FLG", property="deletedFlg", jdbcType=JdbcType.INTEGER)
    })
    MailQueue selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_QUEUE
     *
     * @mbggenerated
     */
    @UpdateProvider(type=MailQueueSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") MailQueue record, @Param("example") MailQueueCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_QUEUE
     *
     * @mbggenerated
     */
    @UpdateProvider(type=MailQueueSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") MailQueue record, @Param("example") MailQueueCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_QUEUE
     *
     * @mbggenerated
     */
    @UpdateProvider(type=MailQueueSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(MailQueue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_QUEUE
     *
     * @mbggenerated
     */
    @Update({
        "update MAIL_QUEUE",
        "set MAIL_ID = #{mailId,jdbcType=BIGINT},",
          "SCHEDULED_AT = #{scheduledAt,jdbcType=TIMESTAMP},",
          "UPDATED_AT = #{updatedAt,jdbcType=TIMESTAMP},",
          "CREATED_AT = #{createdAt,jdbcType=TIMESTAMP},",
          "LOCK_VERSION = #{lockVersion,jdbcType=INTEGER},",
          "DELETED_FLG = #{deletedFlg,jdbcType=INTEGER}",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(MailQueue record);
}
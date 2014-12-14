package cherry.sqlapp.db.gen.mapper;

import cherry.sqlapp.db.gen.dto.MailTemplateAddress;
import cherry.sqlapp.db.gen.dto.MailTemplateAddressCriteria;
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

public interface MailTemplateAddressMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE_ADDRESS
     *
     * @mbggenerated
     */
    @SelectProvider(type=MailTemplateAddressSqlProvider.class, method="countByExample")
    int countByExample(MailTemplateAddressCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE_ADDRESS
     *
     * @mbggenerated
     */
    @DeleteProvider(type=MailTemplateAddressSqlProvider.class, method="deleteByExample")
    int deleteByExample(MailTemplateAddressCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE_ADDRESS
     *
     * @mbggenerated
     */
    @Delete({
        "delete from MAIL_TEMPLATE_ADDRESS",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE_ADDRESS
     *
     * @mbggenerated
     */
    @Insert({
        "insert into MAIL_TEMPLATE_ADDRESS (TEMPLATE_ID, RCPT_TYPE, ",
        "RCPT_ADDR, UPDATED_AT, ",
        "CREATED_AT, LOCK_VERSION, ",
        "DELETED_FLG)",
        "values (#{templateId,jdbcType=BIGINT}, #{rcptType,jdbcType=VARCHAR}, ",
        "#{rcptAddr,jdbcType=VARCHAR}, #{updatedAt,jdbcType=TIMESTAMP}, ",
        "#{createdAt,jdbcType=TIMESTAMP}, #{lockVersion,jdbcType=INTEGER}, ",
        "#{deletedFlg,jdbcType=INTEGER})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(MailTemplateAddress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE_ADDRESS
     *
     * @mbggenerated
     */
    @InsertProvider(type=MailTemplateAddressSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(MailTemplateAddress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE_ADDRESS
     *
     * @mbggenerated
     */
    @SelectProvider(type=MailTemplateAddressSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="TEMPLATE_ID", property="templateId", jdbcType=JdbcType.BIGINT),
        @Result(column="RCPT_TYPE", property="rcptType", jdbcType=JdbcType.VARCHAR),
        @Result(column="RCPT_ADDR", property="rcptAddr", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATED_AT", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATED_AT", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LOCK_VERSION", property="lockVersion", jdbcType=JdbcType.INTEGER),
        @Result(column="DELETED_FLG", property="deletedFlg", jdbcType=JdbcType.INTEGER)
    })
    List<MailTemplateAddress> selectByExampleWithRowbounds(MailTemplateAddressCriteria example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE_ADDRESS
     *
     * @mbggenerated
     */
    @SelectProvider(type=MailTemplateAddressSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="TEMPLATE_ID", property="templateId", jdbcType=JdbcType.BIGINT),
        @Result(column="RCPT_TYPE", property="rcptType", jdbcType=JdbcType.VARCHAR),
        @Result(column="RCPT_ADDR", property="rcptAddr", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATED_AT", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATED_AT", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LOCK_VERSION", property="lockVersion", jdbcType=JdbcType.INTEGER),
        @Result(column="DELETED_FLG", property="deletedFlg", jdbcType=JdbcType.INTEGER)
    })
    List<MailTemplateAddress> selectByExample(MailTemplateAddressCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE_ADDRESS
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "ID, TEMPLATE_ID, RCPT_TYPE, RCPT_ADDR, UPDATED_AT, CREATED_AT, LOCK_VERSION, ",
        "DELETED_FLG",
        "from MAIL_TEMPLATE_ADDRESS",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="TEMPLATE_ID", property="templateId", jdbcType=JdbcType.BIGINT),
        @Result(column="RCPT_TYPE", property="rcptType", jdbcType=JdbcType.VARCHAR),
        @Result(column="RCPT_ADDR", property="rcptAddr", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATED_AT", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATED_AT", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LOCK_VERSION", property="lockVersion", jdbcType=JdbcType.INTEGER),
        @Result(column="DELETED_FLG", property="deletedFlg", jdbcType=JdbcType.INTEGER)
    })
    MailTemplateAddress selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE_ADDRESS
     *
     * @mbggenerated
     */
    @UpdateProvider(type=MailTemplateAddressSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") MailTemplateAddress record, @Param("example") MailTemplateAddressCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE_ADDRESS
     *
     * @mbggenerated
     */
    @UpdateProvider(type=MailTemplateAddressSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") MailTemplateAddress record, @Param("example") MailTemplateAddressCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE_ADDRESS
     *
     * @mbggenerated
     */
    @UpdateProvider(type=MailTemplateAddressSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(MailTemplateAddress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE_ADDRESS
     *
     * @mbggenerated
     */
    @Update({
        "update MAIL_TEMPLATE_ADDRESS",
        "set TEMPLATE_ID = #{templateId,jdbcType=BIGINT},",
          "RCPT_TYPE = #{rcptType,jdbcType=VARCHAR},",
          "RCPT_ADDR = #{rcptAddr,jdbcType=VARCHAR},",
          "UPDATED_AT = #{updatedAt,jdbcType=TIMESTAMP},",
          "CREATED_AT = #{createdAt,jdbcType=TIMESTAMP},",
          "LOCK_VERSION = #{lockVersion,jdbcType=INTEGER},",
          "DELETED_FLG = #{deletedFlg,jdbcType=INTEGER}",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(MailTemplateAddress record);
}
package cherry.sqlapp.db.gen.mapper;

import cherry.sqlapp.db.gen.dto.SqlSelect;
import java.util.List;

public interface SqlSelectMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SQL_SELECT
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SQL_SELECT
     *
     * @mbggenerated
     */
    int insert(SqlSelect record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SQL_SELECT
     *
     * @mbggenerated
     */
    SqlSelect selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SQL_SELECT
     *
     * @mbggenerated
     */
    List<SqlSelect> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SQL_SELECT
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SqlSelect record);
}
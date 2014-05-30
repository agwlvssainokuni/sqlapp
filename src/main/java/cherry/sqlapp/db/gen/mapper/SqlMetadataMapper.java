package cherry.sqlapp.db.gen.mapper;

import cherry.sqlapp.db.gen.dto.SqlMetadata;
import java.util.List;

public interface SqlMetadataMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SQL_METADATA
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SQL_METADATA
     *
     * @mbggenerated
     */
    int insert(SqlMetadata record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SQL_METADATA
     *
     * @mbggenerated
     */
    SqlMetadata selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SQL_METADATA
     *
     * @mbggenerated
     */
    List<SqlMetadata> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SQL_METADATA
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SqlMetadata record);
}
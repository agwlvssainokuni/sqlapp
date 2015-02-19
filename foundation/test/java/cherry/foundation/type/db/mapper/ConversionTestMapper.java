/*
 * Copyright 2014,2015 agwlvssainokuni
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cherry.foundation.type.db.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import cherry.foundation.type.db.dto.ConversionTest;

public interface ConversionTestMapper {

	@Select({
			"SELECT",
			"id, joda_date, joda_time, joda_datetime, sec_str, sec_int, sec_long, sec_bigint, sec_bigdec, flag_code, deleted_flg",
			"FROM conversion_test" })
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
			@Result(column = "joda_date", property = "jodaDate", jdbcType = JdbcType.DATE),
			@Result(column = "joda_time", property = "jodaTime", jdbcType = JdbcType.TIME),
			@Result(column = "joda_datetime", property = "jodaDatetime", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "sec_str", property = "secStr", jdbcType = JdbcType.VARCHAR),
			@Result(column = "sec_int", property = "secInt", jdbcType = JdbcType.VARCHAR),
			@Result(column = "sec_long", property = "secLong", jdbcType = JdbcType.VARCHAR),
			@Result(column = "sec_bigint", property = "secBigint", jdbcType = JdbcType.VARCHAR),
			@Result(column = "sec_bigdec", property = "secBigdec", jdbcType = JdbcType.VARCHAR),
			@Result(column = "flag_code", property = "flagCode", jdbcType = JdbcType.INTEGER),
			@Result(column = "deleted_flg", property = "deletedFlg", jdbcType = JdbcType.INTEGER) })
	List<ConversionTest> selectAll();

	@Insert({
			"INSERT INTO conversion_test (",
			"joda_date, joda_time, joda_datetime, sec_str, sec_int, sec_long, sec_bigint, sec_bigdec, flag_code, deleted_flg",
			") VALUES (", //
			"#{jodaDate,jdbcType=DATE},", //
			"#{jodaTime,jdbcType=TIME},", //
			"#{jodaDatetime,jdbcType=TIMESTAMP},", //
			"#{secStr,jdbcType=VARCHAR},", //
			"#{secInt,jdbcType=VARCHAR},", //
			"#{secLong,jdbcType=VARCHAR},", //
			"#{secBigint,jdbcType=VARCHAR},", //
			"#{secBigdec,jdbcType=VARCHAR},", //
			"#{flagCode,jdbcType=INTEGER},", //
			"#{deletedFlg,jdbcType=INTEGER}", //
			")" })
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(ConversionTest record);

}

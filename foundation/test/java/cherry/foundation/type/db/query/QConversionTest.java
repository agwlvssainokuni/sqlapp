package cherry.foundation.type.db.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QConversionTest is a Querydsl query type for BConversionTest
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QConversionTest extends com.mysema.query.sql.RelationalPathBase<BConversionTest> {

    private static final long serialVersionUID = 480845314;

    public static final QConversionTest conversionTest = new QConversionTest("CONVERSION_TEST");

    public final NumberPath<Integer> deletedFlg = createNumber("deletedFlg", Integer.class);

    public final NumberPath<Integer> flagCode = createNumber("flagCode", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DatePath<org.joda.time.LocalDate> jodaDate = createDate("jodaDate", org.joda.time.LocalDate.class);

    public final DateTimePath<org.joda.time.LocalDateTime> jodaDatetime = createDateTime("jodaDatetime", org.joda.time.LocalDateTime.class);

    public final TimePath<org.joda.time.LocalTime> jodaTime = createTime("jodaTime", org.joda.time.LocalTime.class);

    public final StringPath secBigdec = createString("secBigdec");

    public final StringPath secBigint = createString("secBigint");

    public final StringPath secInt = createString("secInt");

    public final StringPath secLong = createString("secLong");

    public final StringPath secStr = createString("secStr");

    public QConversionTest(String variable) {
        super(BConversionTest.class, forVariable(variable), "PUBLIC", "CONVERSION_TEST");
        addMetadata();
    }

    public QConversionTest(String variable, String schema, String table) {
        super(BConversionTest.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QConversionTest(Path<? extends BConversionTest> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "CONVERSION_TEST");
        addMetadata();
    }

    public QConversionTest(PathMetadata<?> metadata) {
        super(BConversionTest.class, metadata, "PUBLIC", "CONVERSION_TEST");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(deletedFlg, ColumnMetadata.named("DELETED_FLG").withIndex(11).ofType(Types.INTEGER).withSize(10));
        addMetadata(flagCode, ColumnMetadata.named("FLAG_CODE").withIndex(10).ofType(Types.INTEGER).withSize(10));
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(jodaDate, ColumnMetadata.named("JODA_DATE").withIndex(2).ofType(Types.DATE).withSize(8));
        addMetadata(jodaDatetime, ColumnMetadata.named("JODA_DATETIME").withIndex(4).ofType(Types.TIMESTAMP).withSize(23).withDigits(10));
        addMetadata(jodaTime, ColumnMetadata.named("JODA_TIME").withIndex(3).ofType(Types.TIME).withSize(6));
        addMetadata(secBigdec, ColumnMetadata.named("SEC_BIGDEC").withIndex(9).ofType(Types.VARCHAR).withSize(104));
        addMetadata(secBigint, ColumnMetadata.named("SEC_BIGINT").withIndex(8).ofType(Types.VARCHAR).withSize(104));
        addMetadata(secInt, ColumnMetadata.named("SEC_INT").withIndex(6).ofType(Types.VARCHAR).withSize(40));
        addMetadata(secLong, ColumnMetadata.named("SEC_LONG").withIndex(7).ofType(Types.VARCHAR).withSize(40));
        addMetadata(secStr, ColumnMetadata.named("SEC_STR").withIndex(5).ofType(Types.VARCHAR).withSize(104));
    }

}


package cherry.sqlapp.db.gen.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QBizdatetimeMaster is a Querydsl query type for BBizdatetimeMaster
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QBizdatetimeMaster extends com.mysema.query.sql.RelationalPathBase<BBizdatetimeMaster> {

    private static final long serialVersionUID = 1180484156;

    public static final QBizdatetimeMaster bizdatetimeMaster = new QBizdatetimeMaster("BIZDATETIME_MASTER");

    public final DatePath<org.joda.time.LocalDate> bizdate = createDate("bizdate", org.joda.time.LocalDate.class);

    public final DateTimePath<org.joda.time.LocalDateTime> createdAt = createDateTime("createdAt", org.joda.time.LocalDateTime.class);

    public final NumberPath<Integer> deletedFlg = createNumber("deletedFlg", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> lockVersion = createNumber("lockVersion", Integer.class);

    public final NumberPath<Integer> offsetDay = createNumber("offsetDay", Integer.class);

    public final NumberPath<Integer> offsetHour = createNumber("offsetHour", Integer.class);

    public final NumberPath<Integer> offsetMinute = createNumber("offsetMinute", Integer.class);

    public final NumberPath<Integer> offsetSecond = createNumber("offsetSecond", Integer.class);

    public final DateTimePath<org.joda.time.LocalDateTime> updatedAt = createDateTime("updatedAt", org.joda.time.LocalDateTime.class);

    public final com.mysema.query.sql.PrimaryKey<BBizdatetimeMaster> bizdatetimeMasterPkc = createPrimaryKey(id);

    public QBizdatetimeMaster(String variable) {
        super(BBizdatetimeMaster.class, forVariable(variable), "PUBLIC", "BIZDATETIME_MASTER");
        addMetadata();
    }

    public QBizdatetimeMaster(String variable, String schema, String table) {
        super(BBizdatetimeMaster.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QBizdatetimeMaster(Path<? extends BBizdatetimeMaster> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "BIZDATETIME_MASTER");
        addMetadata();
    }

    public QBizdatetimeMaster(PathMetadata<?> metadata) {
        super(BBizdatetimeMaster.class, metadata, "PUBLIC", "BIZDATETIME_MASTER");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(bizdate, ColumnMetadata.named("BIZDATE").withIndex(2).ofType(Types.DATE).withSize(8).notNull());
        addMetadata(createdAt, ColumnMetadata.named("CREATED_AT").withIndex(8).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(deletedFlg, ColumnMetadata.named("DELETED_FLG").withIndex(10).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(lockVersion, ColumnMetadata.named("LOCK_VERSION").withIndex(9).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(offsetDay, ColumnMetadata.named("OFFSET_DAY").withIndex(3).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(offsetHour, ColumnMetadata.named("OFFSET_HOUR").withIndex(4).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(offsetMinute, ColumnMetadata.named("OFFSET_MINUTE").withIndex(5).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(offsetSecond, ColumnMetadata.named("OFFSET_SECOND").withIndex(6).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(updatedAt, ColumnMetadata.named("UPDATED_AT").withIndex(7).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
    }

}


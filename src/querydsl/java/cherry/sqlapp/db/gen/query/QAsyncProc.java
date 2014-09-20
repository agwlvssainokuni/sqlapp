package cherry.sqlapp.db.gen.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QAsyncProc is a Querydsl query type for BAsyncProc
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QAsyncProc extends com.mysema.query.sql.RelationalPathBase<BAsyncProc> {

    private static final long serialVersionUID = -1179490;

    public static final QAsyncProc asyncProc = new QAsyncProc("ASYNC_PROC");

    public final DateTimePath<org.joda.time.LocalDateTime> createdAt = createDateTime("createdAt", org.joda.time.LocalDateTime.class);

    public final NumberPath<Integer> deletedFlg = createNumber("deletedFlg", Integer.class);

    public final DateTimePath<org.joda.time.LocalDateTime> finishedAt = createDateTime("finishedAt", org.joda.time.LocalDateTime.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<org.joda.time.LocalDateTime> invokedAt = createDateTime("invokedAt", org.joda.time.LocalDateTime.class);

    public final StringPath launcherId = createString("launcherId");

    public final NumberPath<Integer> lockVersion = createNumber("lockVersion", Integer.class);

    public final StringPath name = createString("name");

    public final DateTimePath<org.joda.time.LocalDateTime> registeredAt = createDateTime("registeredAt", org.joda.time.LocalDateTime.class);

    public final StringPath result = createString("result");

    public final DateTimePath<org.joda.time.LocalDateTime> startedAt = createDateTime("startedAt", org.joda.time.LocalDateTime.class);

    public final StringPath status = createString("status");

    public final DateTimePath<org.joda.time.LocalDateTime> updatedAt = createDateTime("updatedAt", org.joda.time.LocalDateTime.class);

    public final com.mysema.query.sql.PrimaryKey<BAsyncProc> asyncProcPkc = createPrimaryKey(id);

    public QAsyncProc(String variable) {
        super(BAsyncProc.class, forVariable(variable), "PUBLIC", "ASYNC_PROC");
        addMetadata();
    }

    public QAsyncProc(String variable, String schema, String table) {
        super(BAsyncProc.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QAsyncProc(Path<? extends BAsyncProc> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "ASYNC_PROC");
        addMetadata();
    }

    public QAsyncProc(PathMetadata<?> metadata) {
        super(BAsyncProc.class, metadata, "PUBLIC", "ASYNC_PROC");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(createdAt, ColumnMetadata.named("CREATED_AT").withIndex(11).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(deletedFlg, ColumnMetadata.named("DELETED_FLG").withIndex(13).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(finishedAt, ColumnMetadata.named("FINISHED_AT").withIndex(8).ofType(Types.TIMESTAMP).withSize(23).withDigits(10));
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(invokedAt, ColumnMetadata.named("INVOKED_AT").withIndex(6).ofType(Types.TIMESTAMP).withSize(23).withDigits(10));
        addMetadata(launcherId, ColumnMetadata.named("LAUNCHER_ID").withIndex(2).ofType(Types.VARCHAR).withSize(512).notNull());
        addMetadata(lockVersion, ColumnMetadata.named("LOCK_VERSION").withIndex(12).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(name, ColumnMetadata.named("NAME").withIndex(3).ofType(Types.VARCHAR).withSize(32).notNull());
        addMetadata(registeredAt, ColumnMetadata.named("REGISTERED_AT").withIndex(5).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(result, ColumnMetadata.named("RESULT").withIndex(9).ofType(Types.VARCHAR).withSize(4096));
        addMetadata(startedAt, ColumnMetadata.named("STARTED_AT").withIndex(7).ofType(Types.TIMESTAMP).withSize(23).withDigits(10));
        addMetadata(status, ColumnMetadata.named("STATUS").withIndex(4).ofType(Types.VARCHAR).withSize(32).notNull());
        addMetadata(updatedAt, ColumnMetadata.named("UPDATED_AT").withIndex(10).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
    }

}


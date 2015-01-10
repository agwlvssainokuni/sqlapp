package cherry.sqlapp.db.gen.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QMailLog is a Querydsl query type for BMailLog
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QMailLog extends com.mysema.query.sql.RelationalPathBase<BMailLog> {

    private static final long serialVersionUID = 984675769;

    public static final QMailLog mailLog = new QMailLog("MAIL_LOG");

    public final StringPath body = createString("body");

    public final DateTimePath<org.joda.time.LocalDateTime> createdAt = createDateTime("createdAt", org.joda.time.LocalDateTime.class);

    public final NumberPath<Integer> deletedFlg = createNumber("deletedFlg", Integer.class);

    public final StringPath fromAddr = createString("fromAddr");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<org.joda.time.LocalDateTime> launchedAt = createDateTime("launchedAt", org.joda.time.LocalDateTime.class);

    public final StringPath launchedBy = createString("launchedBy");

    public final NumberPath<Integer> lockVersion = createNumber("lockVersion", Integer.class);

    public final NumberPath<Integer> mailStatus = createNumber("mailStatus", Integer.class);

    public final StringPath messageName = createString("messageName");

    public final DateTimePath<org.joda.time.LocalDateTime> scheduledAt = createDateTime("scheduledAt", org.joda.time.LocalDateTime.class);

    public final DateTimePath<org.joda.time.LocalDateTime> sentAt = createDateTime("sentAt", org.joda.time.LocalDateTime.class);

    public final StringPath subject = createString("subject");

    public final DateTimePath<org.joda.time.LocalDateTime> updatedAt = createDateTime("updatedAt", org.joda.time.LocalDateTime.class);

    public final com.mysema.query.sql.PrimaryKey<BMailLog> mailLogPkc = createPrimaryKey(id);

    public QMailLog(String variable) {
        super(BMailLog.class, forVariable(variable), "PUBLIC", "MAIL_LOG");
        addMetadata();
    }

    public QMailLog(String variable, String schema, String table) {
        super(BMailLog.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QMailLog(Path<? extends BMailLog> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "MAIL_LOG");
        addMetadata();
    }

    public QMailLog(PathMetadata<?> metadata) {
        super(BMailLog.class, metadata, "PUBLIC", "MAIL_LOG");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(body, ColumnMetadata.named("BODY").withIndex(10).ofType(Types.VARCHAR).withSize(5000).notNull());
        addMetadata(createdAt, ColumnMetadata.named("CREATED_AT").withIndex(12).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(deletedFlg, ColumnMetadata.named("DELETED_FLG").withIndex(14).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(fromAddr, ColumnMetadata.named("FROM_ADDR").withIndex(8).ofType(Types.VARCHAR).withSize(300).notNull());
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(launchedAt, ColumnMetadata.named("LAUNCHED_AT").withIndex(3).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(launchedBy, ColumnMetadata.named("LAUNCHED_BY").withIndex(2).ofType(Types.VARCHAR).withSize(100).notNull());
        addMetadata(lockVersion, ColumnMetadata.named("LOCK_VERSION").withIndex(13).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(mailStatus, ColumnMetadata.named("MAIL_STATUS").withIndex(4).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(messageName, ColumnMetadata.named("MESSAGE_NAME").withIndex(5).ofType(Types.VARCHAR).withSize(30).notNull());
        addMetadata(scheduledAt, ColumnMetadata.named("SCHEDULED_AT").withIndex(6).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(sentAt, ColumnMetadata.named("SENT_AT").withIndex(7).ofType(Types.TIMESTAMP).withSize(23).withDigits(10));
        addMetadata(subject, ColumnMetadata.named("SUBJECT").withIndex(9).ofType(Types.VARCHAR).withSize(1000).notNull());
        addMetadata(updatedAt, ColumnMetadata.named("UPDATED_AT").withIndex(11).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
    }

}


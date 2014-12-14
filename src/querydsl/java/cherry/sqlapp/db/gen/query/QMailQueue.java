package cherry.sqlapp.db.gen.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QMailQueue is a Querydsl query type for BMailQueue
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QMailQueue extends com.mysema.query.sql.RelationalPathBase<BMailQueue> {

    private static final long serialVersionUID = 1385407046;

    public static final QMailQueue mailQueue = new QMailQueue("MAIL_QUEUE");

    public final DateTimePath<org.joda.time.LocalDateTime> createdAt = createDateTime("createdAt", org.joda.time.LocalDateTime.class);

    public final NumberPath<Integer> deletedFlg = createNumber("deletedFlg", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> lockVersion = createNumber("lockVersion", Integer.class);

    public final NumberPath<Long> mailId = createNumber("mailId", Long.class);

    public final DateTimePath<org.joda.time.LocalDateTime> scheduledAt = createDateTime("scheduledAt", org.joda.time.LocalDateTime.class);

    public final DateTimePath<org.joda.time.LocalDateTime> updatedAt = createDateTime("updatedAt", org.joda.time.LocalDateTime.class);

    public final com.mysema.query.sql.PrimaryKey<BMailQueue> mailQueuePkc = createPrimaryKey(id);

    public QMailQueue(String variable) {
        super(BMailQueue.class, forVariable(variable), "PUBLIC", "MAIL_QUEUE");
        addMetadata();
    }

    public QMailQueue(String variable, String schema, String table) {
        super(BMailQueue.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QMailQueue(Path<? extends BMailQueue> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "MAIL_QUEUE");
        addMetadata();
    }

    public QMailQueue(PathMetadata<?> metadata) {
        super(BMailQueue.class, metadata, "PUBLIC", "MAIL_QUEUE");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(createdAt, ColumnMetadata.named("CREATED_AT").withIndex(5).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(deletedFlg, ColumnMetadata.named("DELETED_FLG").withIndex(7).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(lockVersion, ColumnMetadata.named("LOCK_VERSION").withIndex(6).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(mailId, ColumnMetadata.named("MAIL_ID").withIndex(2).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(scheduledAt, ColumnMetadata.named("SCHEDULED_AT").withIndex(3).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(updatedAt, ColumnMetadata.named("UPDATED_AT").withIndex(4).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
    }

}


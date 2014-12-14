package cherry.sqlapp.db.gen.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QMailRcpt is a Querydsl query type for BMailRcpt
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QMailRcpt extends com.mysema.query.sql.RelationalPathBase<BMailRcpt> {

    private static final long serialVersionUID = 460345376;

    public static final QMailRcpt mailRcpt = new QMailRcpt("MAIL_RCPT");

    public final DateTimePath<org.joda.time.LocalDateTime> createdAt = createDateTime("createdAt", org.joda.time.LocalDateTime.class);

    public final NumberPath<Integer> deletedFlg = createNumber("deletedFlg", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> lockVersion = createNumber("lockVersion", Integer.class);

    public final NumberPath<Long> mailId = createNumber("mailId", Long.class);

    public final StringPath rcptAddr = createString("rcptAddr");

    public final StringPath rcptType = createString("rcptType");

    public final DateTimePath<org.joda.time.LocalDateTime> updatedAt = createDateTime("updatedAt", org.joda.time.LocalDateTime.class);

    public final com.mysema.query.sql.PrimaryKey<BMailRcpt> mailRcptPkc = createPrimaryKey(id);

    public QMailRcpt(String variable) {
        super(BMailRcpt.class, forVariable(variable), "PUBLIC", "MAIL_RCPT");
        addMetadata();
    }

    public QMailRcpt(String variable, String schema, String table) {
        super(BMailRcpt.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QMailRcpt(Path<? extends BMailRcpt> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "MAIL_RCPT");
        addMetadata();
    }

    public QMailRcpt(PathMetadata<?> metadata) {
        super(BMailRcpt.class, metadata, "PUBLIC", "MAIL_RCPT");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(createdAt, ColumnMetadata.named("CREATED_AT").withIndex(6).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(deletedFlg, ColumnMetadata.named("DELETED_FLG").withIndex(8).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(lockVersion, ColumnMetadata.named("LOCK_VERSION").withIndex(7).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(mailId, ColumnMetadata.named("MAIL_ID").withIndex(2).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(rcptAddr, ColumnMetadata.named("RCPT_ADDR").withIndex(4).ofType(Types.VARCHAR).withSize(512).notNull());
        addMetadata(rcptType, ColumnMetadata.named("RCPT_TYPE").withIndex(3).ofType(Types.VARCHAR).withSize(3).notNull());
        addMetadata(updatedAt, ColumnMetadata.named("UPDATED_AT").withIndex(5).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
    }

}


package cherry.sqlapp.db.gen.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QAsyncProcessFileResultDetail is a Querydsl query type for BAsyncProcessFileResultDetail
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QAsyncProcessFileResultDetail extends com.mysema.query.sql.RelationalPathBase<BAsyncProcessFileResultDetail> {

    private static final long serialVersionUID = 2012144817;

    public static final QAsyncProcessFileResultDetail asyncProcessFileResultDetail = new QAsyncProcessFileResultDetail("ASYNC_PROCESS_FILE_RESULT_DETAIL");

    public final NumberPath<Long> asyncId = createNumber("asyncId", Long.class);

    public final DateTimePath<org.joda.time.LocalDateTime> createdAt = createDateTime("createdAt", org.joda.time.LocalDateTime.class);

    public final NumberPath<Integer> deletedFlg = createNumber("deletedFlg", Integer.class);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> lockVersion = createNumber("lockVersion", Integer.class);

    public final NumberPath<Long> recordNumber = createNumber("recordNumber", Long.class);

    public final DateTimePath<org.joda.time.LocalDateTime> updatedAt = createDateTime("updatedAt", org.joda.time.LocalDateTime.class);

    public final com.mysema.query.sql.PrimaryKey<BAsyncProcessFileResultDetail> asyncProcessFileResultDetailPkc = createPrimaryKey(id);

    public QAsyncProcessFileResultDetail(String variable) {
        super(BAsyncProcessFileResultDetail.class, forVariable(variable), "PUBLIC", "ASYNC_PROCESS_FILE_RESULT_DETAIL");
        addMetadata();
    }

    public QAsyncProcessFileResultDetail(String variable, String schema, String table) {
        super(BAsyncProcessFileResultDetail.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QAsyncProcessFileResultDetail(Path<? extends BAsyncProcessFileResultDetail> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "ASYNC_PROCESS_FILE_RESULT_DETAIL");
        addMetadata();
    }

    public QAsyncProcessFileResultDetail(PathMetadata<?> metadata) {
        super(BAsyncProcessFileResultDetail.class, metadata, "PUBLIC", "ASYNC_PROCESS_FILE_RESULT_DETAIL");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(asyncId, ColumnMetadata.named("ASYNC_ID").withIndex(2).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(createdAt, ColumnMetadata.named("CREATED_AT").withIndex(6).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(deletedFlg, ColumnMetadata.named("DELETED_FLG").withIndex(8).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(description, ColumnMetadata.named("DESCRIPTION").withIndex(4).ofType(Types.VARCHAR).withSize(50));
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(lockVersion, ColumnMetadata.named("LOCK_VERSION").withIndex(7).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(recordNumber, ColumnMetadata.named("RECORD_NUMBER").withIndex(3).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(updatedAt, ColumnMetadata.named("UPDATED_AT").withIndex(5).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
    }

}


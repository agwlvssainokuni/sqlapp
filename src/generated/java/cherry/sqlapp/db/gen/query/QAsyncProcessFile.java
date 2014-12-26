package cherry.sqlapp.db.gen.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QAsyncProcessFile is a Querydsl query type for BAsyncProcessFile
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QAsyncProcessFile extends com.mysema.query.sql.RelationalPathBase<BAsyncProcessFile> {

    private static final long serialVersionUID = -1630250429;

    public static final QAsyncProcessFile asyncProcessFile = new QAsyncProcessFile("ASYNC_PROCESS_FILE");

    public final NumberPath<Long> asyncId = createNumber("asyncId", Long.class);

    public final StringPath contentType = createString("contentType");

    public final DateTimePath<org.joda.time.LocalDateTime> createdAt = createDateTime("createdAt", org.joda.time.LocalDateTime.class);

    public final NumberPath<Integer> deletedFlg = createNumber("deletedFlg", Integer.class);

    public final NumberPath<Long> fileSize = createNumber("fileSize", Long.class);

    public final StringPath handlerName = createString("handlerName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> lockVersion = createNumber("lockVersion", Integer.class);

    public final StringPath originalFilename = createString("originalFilename");

    public final StringPath paramName = createString("paramName");

    public final DateTimePath<org.joda.time.LocalDateTime> updatedAt = createDateTime("updatedAt", org.joda.time.LocalDateTime.class);

    public final com.mysema.query.sql.PrimaryKey<BAsyncProcessFile> asyncProcessFilePkc = createPrimaryKey(id);

    public QAsyncProcessFile(String variable) {
        super(BAsyncProcessFile.class, forVariable(variable), "PUBLIC", "ASYNC_PROCESS_FILE");
        addMetadata();
    }

    public QAsyncProcessFile(String variable, String schema, String table) {
        super(BAsyncProcessFile.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QAsyncProcessFile(Path<? extends BAsyncProcessFile> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "ASYNC_PROCESS_FILE");
        addMetadata();
    }

    public QAsyncProcessFile(PathMetadata<?> metadata) {
        super(BAsyncProcessFile.class, metadata, "PUBLIC", "ASYNC_PROCESS_FILE");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(asyncId, ColumnMetadata.named("ASYNC_ID").withIndex(2).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(contentType, ColumnMetadata.named("CONTENT_TYPE").withIndex(6).ofType(Types.VARCHAR).withSize(50).notNull());
        addMetadata(createdAt, ColumnMetadata.named("CREATED_AT").withIndex(9).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(deletedFlg, ColumnMetadata.named("DELETED_FLG").withIndex(11).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(fileSize, ColumnMetadata.named("FILE_SIZE").withIndex(7).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(handlerName, ColumnMetadata.named("HANDLER_NAME").withIndex(3).ofType(Types.VARCHAR).withSize(50).notNull());
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(lockVersion, ColumnMetadata.named("LOCK_VERSION").withIndex(10).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(originalFilename, ColumnMetadata.named("ORIGINAL_FILENAME").withIndex(5).ofType(Types.VARCHAR).withSize(50).notNull());
        addMetadata(paramName, ColumnMetadata.named("PARAM_NAME").withIndex(4).ofType(Types.VARCHAR).withSize(50).notNull());
        addMetadata(updatedAt, ColumnMetadata.named("UPDATED_AT").withIndex(8).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
    }

}


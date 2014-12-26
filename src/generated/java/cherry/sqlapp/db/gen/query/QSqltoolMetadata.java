package cherry.sqlapp.db.gen.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QSqltoolMetadata is a Querydsl query type for BSqltoolMetadata
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSqltoolMetadata extends com.mysema.query.sql.RelationalPathBase<BSqltoolMetadata> {

    private static final long serialVersionUID = -1709572767;

    public static final QSqltoolMetadata sqltoolMetadata = new QSqltoolMetadata("SQLTOOL_METADATA");

    public final DateTimePath<org.joda.time.LocalDateTime> createdAt = createDateTime("createdAt", org.joda.time.LocalDateTime.class);

    public final NumberPath<Integer> deletedFlg = createNumber("deletedFlg", Integer.class);

    public final StringPath description = createString("description");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> lockVersion = createNumber("lockVersion", Integer.class);

    public final StringPath name = createString("name");

    public final StringPath ownedBy = createString("ownedBy");

    public final NumberPath<Integer> publishedFlg = createNumber("publishedFlg", Integer.class);

    public final DateTimePath<org.joda.time.LocalDateTime> registeredAt = createDateTime("registeredAt", org.joda.time.LocalDateTime.class);

    public final StringPath sqlType = createString("sqlType");

    public final DateTimePath<org.joda.time.LocalDateTime> updatedAt = createDateTime("updatedAt", org.joda.time.LocalDateTime.class);

    public final com.mysema.query.sql.PrimaryKey<BSqltoolMetadata> sqltoolMetadataPkc = createPrimaryKey(id);

    public QSqltoolMetadata(String variable) {
        super(BSqltoolMetadata.class, forVariable(variable), "PUBLIC", "SQLTOOL_METADATA");
        addMetadata();
    }

    public QSqltoolMetadata(String variable, String schema, String table) {
        super(BSqltoolMetadata.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QSqltoolMetadata(Path<? extends BSqltoolMetadata> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "SQLTOOL_METADATA");
        addMetadata();
    }

    public QSqltoolMetadata(PathMetadata<?> metadata) {
        super(BSqltoolMetadata.class, metadata, "PUBLIC", "SQLTOOL_METADATA");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(createdAt, ColumnMetadata.named("CREATED_AT").withIndex(9).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(deletedFlg, ColumnMetadata.named("DELETED_FLG").withIndex(11).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(description, ColumnMetadata.named("DESCRIPTION").withIndex(4).ofType(Types.VARCHAR).withSize(500).notNull());
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(lockVersion, ColumnMetadata.named("LOCK_VERSION").withIndex(10).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(name, ColumnMetadata.named("NAME").withIndex(3).ofType(Types.VARCHAR).withSize(50).notNull());
        addMetadata(ownedBy, ColumnMetadata.named("OWNED_BY").withIndex(5).ofType(Types.VARCHAR).withSize(32).notNull());
        addMetadata(publishedFlg, ColumnMetadata.named("PUBLISHED_FLG").withIndex(6).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(registeredAt, ColumnMetadata.named("REGISTERED_AT").withIndex(7).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(sqlType, ColumnMetadata.named("SQL_TYPE").withIndex(2).ofType(Types.VARCHAR).withSize(32).notNull());
        addMetadata(updatedAt, ColumnMetadata.named("UPDATED_AT").withIndex(8).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
    }

}


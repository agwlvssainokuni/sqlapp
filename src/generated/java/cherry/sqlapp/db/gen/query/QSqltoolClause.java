package cherry.sqlapp.db.gen.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QSqltoolClause is a Querydsl query type for BSqltoolClause
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSqltoolClause extends com.mysema.query.sql.RelationalPathBase<BSqltoolClause> {

    private static final long serialVersionUID = 1094382625;

    public static final QSqltoolClause sqltoolClause = new QSqltoolClause("SQLTOOL_CLAUSE");

    public final DateTimePath<org.joda.time.LocalDateTime> createdAt = createDateTime("createdAt", org.joda.time.LocalDateTime.class);

    public final StringPath databaseName = createString("databaseName");

    public final NumberPath<Integer> deletedFlg = createNumber("deletedFlg", Integer.class);

    public final StringPath fromClause = createString("fromClause");

    public final StringPath groupByClause = createString("groupByClause");

    public final StringPath havingClause = createString("havingClause");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> lockVersion = createNumber("lockVersion", Integer.class);

    public final StringPath orderByClause = createString("orderByClause");

    public final StringPath paramMap = createString("paramMap");

    public final StringPath selectClause = createString("selectClause");

    public final DateTimePath<org.joda.time.LocalDateTime> updatedAt = createDateTime("updatedAt", org.joda.time.LocalDateTime.class);

    public final StringPath whereClause = createString("whereClause");

    public final com.mysema.query.sql.PrimaryKey<BSqltoolClause> sqltoolClausePkc = createPrimaryKey(id);

    public QSqltoolClause(String variable) {
        super(BSqltoolClause.class, forVariable(variable), "PUBLIC", "SQLTOOL_CLAUSE");
        addMetadata();
    }

    public QSqltoolClause(String variable, String schema, String table) {
        super(BSqltoolClause.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QSqltoolClause(Path<? extends BSqltoolClause> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "SQLTOOL_CLAUSE");
        addMetadata();
    }

    public QSqltoolClause(PathMetadata<?> metadata) {
        super(BSqltoolClause.class, metadata, "PUBLIC", "SQLTOOL_CLAUSE");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(createdAt, ColumnMetadata.named("CREATED_AT").withIndex(11).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(databaseName, ColumnMetadata.named("DATABASE_NAME").withIndex(2).ofType(Types.VARCHAR).withSize(50).notNull());
        addMetadata(deletedFlg, ColumnMetadata.named("DELETED_FLG").withIndex(13).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(fromClause, ColumnMetadata.named("FROM_CLAUSE").withIndex(4).ofType(Types.VARCHAR).withSize(500).notNull());
        addMetadata(groupByClause, ColumnMetadata.named("GROUP_BY_CLAUSE").withIndex(6).ofType(Types.VARCHAR).withSize(500));
        addMetadata(havingClause, ColumnMetadata.named("HAVING_CLAUSE").withIndex(7).ofType(Types.VARCHAR).withSize(500));
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(lockVersion, ColumnMetadata.named("LOCK_VERSION").withIndex(12).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(orderByClause, ColumnMetadata.named("ORDER_BY_CLAUSE").withIndex(8).ofType(Types.VARCHAR).withSize(500));
        addMetadata(paramMap, ColumnMetadata.named("PARAM_MAP").withIndex(9).ofType(Types.VARCHAR).withSize(5000));
        addMetadata(selectClause, ColumnMetadata.named("SELECT_CLAUSE").withIndex(3).ofType(Types.VARCHAR).withSize(500).notNull());
        addMetadata(updatedAt, ColumnMetadata.named("UPDATED_AT").withIndex(10).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(whereClause, ColumnMetadata.named("WHERE_CLAUSE").withIndex(5).ofType(Types.VARCHAR).withSize(500));
    }

}


package cherry.sqlapp.db.gen.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QSqltoolStatement is a Querydsl query type for BSqltoolStatement
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSqltoolStatement extends com.mysema.query.sql.RelationalPathBase<BSqltoolStatement> {

    private static final long serialVersionUID = 1817898365;

    public static final QSqltoolStatement sqltoolStatement = new QSqltoolStatement("SQLTOOL_STATEMENT");

    public final DateTimePath<org.joda.time.LocalDateTime> createdAt = createDateTime("createdAt", org.joda.time.LocalDateTime.class);

    public final StringPath databaseName = createString("databaseName");

    public final NumberPath<Integer> deletedFlg = createNumber("deletedFlg", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> lockVersion = createNumber("lockVersion", Integer.class);

    public final StringPath paramMap = createString("paramMap");

    public final StringPath query = createString("query");

    public final DateTimePath<org.joda.time.LocalDateTime> updatedAt = createDateTime("updatedAt", org.joda.time.LocalDateTime.class);

    public final com.mysema.query.sql.PrimaryKey<BSqltoolStatement> sqltoolStatementPkc = createPrimaryKey(id);

    public QSqltoolStatement(String variable) {
        super(BSqltoolStatement.class, forVariable(variable), "PUBLIC", "SQLTOOL_STATEMENT");
        addMetadata();
    }

    public QSqltoolStatement(String variable, String schema, String table) {
        super(BSqltoolStatement.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QSqltoolStatement(Path<? extends BSqltoolStatement> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "SQLTOOL_STATEMENT");
        addMetadata();
    }

    public QSqltoolStatement(PathMetadata<?> metadata) {
        super(BSqltoolStatement.class, metadata, "PUBLIC", "SQLTOOL_STATEMENT");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(createdAt, ColumnMetadata.named("CREATED_AT").withIndex(6).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(databaseName, ColumnMetadata.named("DATABASE_NAME").withIndex(2).ofType(Types.VARCHAR).withSize(50).notNull());
        addMetadata(deletedFlg, ColumnMetadata.named("DELETED_FLG").withIndex(8).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(lockVersion, ColumnMetadata.named("LOCK_VERSION").withIndex(7).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(paramMap, ColumnMetadata.named("PARAM_MAP").withIndex(4).ofType(Types.VARCHAR).withSize(5000));
        addMetadata(query, ColumnMetadata.named("QUERY").withIndex(3).ofType(Types.VARCHAR).withSize(5000).notNull());
        addMetadata(updatedAt, ColumnMetadata.named("UPDATED_AT").withIndex(5).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
    }

}


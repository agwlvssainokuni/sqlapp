package cherry.sqlapp.db.gen.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QDigit is a Querydsl query type for BDigit
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QDigit extends com.mysema.query.sql.RelationalPathBase<BDigit> {

    private static final long serialVersionUID = -1017105447;

    public static final QDigit digit = new QDigit("DIGIT");

    public final DateTimePath<org.joda.time.LocalDateTime> createdAt = createDateTime("createdAt", org.joda.time.LocalDateTime.class);

    public final NumberPath<Integer> d = createNumber("d", Integer.class);

    public final NumberPath<Integer> deletedFlg = createNumber("deletedFlg", Integer.class);

    public final NumberPath<Integer> lockVersion = createNumber("lockVersion", Integer.class);

    public final DateTimePath<org.joda.time.LocalDateTime> updatedAt = createDateTime("updatedAt", org.joda.time.LocalDateTime.class);

    public final com.mysema.query.sql.PrimaryKey<BDigit> digitPkc = createPrimaryKey(d);

    public QDigit(String variable) {
        super(BDigit.class, forVariable(variable), "PUBLIC", "DIGIT");
        addMetadata();
    }

    public QDigit(String variable, String schema, String table) {
        super(BDigit.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QDigit(Path<? extends BDigit> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "DIGIT");
        addMetadata();
    }

    public QDigit(PathMetadata<?> metadata) {
        super(BDigit.class, metadata, "PUBLIC", "DIGIT");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(createdAt, ColumnMetadata.named("CREATED_AT").withIndex(3).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(d, ColumnMetadata.named("D").withIndex(1).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(deletedFlg, ColumnMetadata.named("DELETED_FLG").withIndex(5).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(lockVersion, ColumnMetadata.named("LOCK_VERSION").withIndex(4).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(updatedAt, ColumnMetadata.named("UPDATED_AT").withIndex(2).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
    }

}


package cherry.sqlapp.db.gen.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QNumberingMaster is a Querydsl query type for BNumberingMaster
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QNumberingMaster extends com.mysema.query.sql.RelationalPathBase<BNumberingMaster> {

    private static final long serialVersionUID = -1631922329;

    public static final QNumberingMaster numberingMaster = new QNumberingMaster("NUMBERING_MASTER");

    public final DateTimePath<org.joda.time.LocalDateTime> createdAt = createDateTime("createdAt", org.joda.time.LocalDateTime.class);

    public final NumberPath<Long> currentValue = createNumber("currentValue", Long.class);

    public final NumberPath<Integer> deletedFlg = createNumber("deletedFlg", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> lockVersion = createNumber("lockVersion", Integer.class);

    public final NumberPath<Long> maxValue = createNumber("maxValue", Long.class);

    public final NumberPath<Long> minValue = createNumber("minValue", Long.class);

    public final StringPath name = createString("name");

    public final StringPath template = createString("template");

    public final DateTimePath<org.joda.time.LocalDateTime> updatedAt = createDateTime("updatedAt", org.joda.time.LocalDateTime.class);

    public final com.mysema.query.sql.PrimaryKey<BNumberingMaster> numberingMasterPkc = createPrimaryKey(id);

    public QNumberingMaster(String variable) {
        super(BNumberingMaster.class, forVariable(variable), "PUBLIC", "NUMBERING_MASTER");
        addMetadata();
    }

    public QNumberingMaster(String variable, String schema, String table) {
        super(BNumberingMaster.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QNumberingMaster(Path<? extends BNumberingMaster> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "NUMBERING_MASTER");
        addMetadata();
    }

    public QNumberingMaster(PathMetadata<?> metadata) {
        super(BNumberingMaster.class, metadata, "PUBLIC", "NUMBERING_MASTER");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(createdAt, ColumnMetadata.named("CREATED_AT").withIndex(8).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(currentValue, ColumnMetadata.named("CURRENT_VALUE").withIndex(6).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(deletedFlg, ColumnMetadata.named("DELETED_FLG").withIndex(10).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(lockVersion, ColumnMetadata.named("LOCK_VERSION").withIndex(9).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(maxValue, ColumnMetadata.named("MAX_VALUE").withIndex(5).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(minValue, ColumnMetadata.named("MIN_VALUE").withIndex(4).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(name, ColumnMetadata.named("NAME").withIndex(2).ofType(Types.VARCHAR).withSize(30).notNull());
        addMetadata(template, ColumnMetadata.named("TEMPLATE").withIndex(3).ofType(Types.VARCHAR).withSize(30).notNull());
        addMetadata(updatedAt, ColumnMetadata.named("UPDATED_AT").withIndex(7).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
    }

}


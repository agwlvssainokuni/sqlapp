package cherry.sqlapp.db.gen.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QDayoffMaster is a Querydsl query type for BDayoffMaster
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QDayoffMaster extends com.mysema.query.sql.RelationalPathBase<BDayoffMaster> {

    private static final long serialVersionUID = -2006209431;

    public static final QDayoffMaster dayoffMaster = new QDayoffMaster("DAYOFF_MASTER");

    public final DateTimePath<org.joda.time.LocalDateTime> createdAt = createDateTime("createdAt", org.joda.time.LocalDateTime.class);

    public final NumberPath<Integer> deletedFlg = createNumber("deletedFlg", Integer.class);

    public final DatePath<org.joda.time.LocalDate> dt = createDate("dt", org.joda.time.LocalDate.class);

    public final NumberPath<Integer> lockVersion = createNumber("lockVersion", Integer.class);

    public final StringPath name = createString("name");

    public final StringPath type = createString("type");

    public final DateTimePath<org.joda.time.LocalDateTime> updatedAt = createDateTime("updatedAt", org.joda.time.LocalDateTime.class);

    public final com.mysema.query.sql.PrimaryKey<BDayoffMaster> dayoffMasterPkc = createPrimaryKey(dt, name);

    public QDayoffMaster(String variable) {
        super(BDayoffMaster.class, forVariable(variable), "PUBLIC", "DAYOFF_MASTER");
        addMetadata();
    }

    public QDayoffMaster(String variable, String schema, String table) {
        super(BDayoffMaster.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QDayoffMaster(Path<? extends BDayoffMaster> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "DAYOFF_MASTER");
        addMetadata();
    }

    public QDayoffMaster(PathMetadata<?> metadata) {
        super(BDayoffMaster.class, metadata, "PUBLIC", "DAYOFF_MASTER");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(createdAt, ColumnMetadata.named("CREATED_AT").withIndex(5).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(deletedFlg, ColumnMetadata.named("DELETED_FLG").withIndex(7).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(dt, ColumnMetadata.named("DT").withIndex(2).ofType(Types.DATE).withSize(8).notNull());
        addMetadata(lockVersion, ColumnMetadata.named("LOCK_VERSION").withIndex(6).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(name, ColumnMetadata.named("NAME").withIndex(1).ofType(Types.VARCHAR).withSize(10).notNull());
        addMetadata(type, ColumnMetadata.named("TYPE").withIndex(3).ofType(Types.VARCHAR).withSize(2).notNull());
        addMetadata(updatedAt, ColumnMetadata.named("UPDATED_AT").withIndex(4).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
    }

}


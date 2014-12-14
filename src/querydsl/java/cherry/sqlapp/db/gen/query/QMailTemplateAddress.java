package cherry.sqlapp.db.gen.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QMailTemplateAddress is a Querydsl query type for BMailTemplateAddress
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QMailTemplateAddress extends com.mysema.query.sql.RelationalPathBase<BMailTemplateAddress> {

    private static final long serialVersionUID = 260936975;

    public static final QMailTemplateAddress mailTemplateAddress = new QMailTemplateAddress("MAIL_TEMPLATE_ADDRESS");

    public final DateTimePath<org.joda.time.LocalDateTime> createdAt = createDateTime("createdAt", org.joda.time.LocalDateTime.class);

    public final NumberPath<Integer> deletedFlg = createNumber("deletedFlg", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> lockVersion = createNumber("lockVersion", Integer.class);

    public final StringPath rcptAddr = createString("rcptAddr");

    public final StringPath rcptType = createString("rcptType");

    public final NumberPath<Long> templateId = createNumber("templateId", Long.class);

    public final DateTimePath<org.joda.time.LocalDateTime> updatedAt = createDateTime("updatedAt", org.joda.time.LocalDateTime.class);

    public final com.mysema.query.sql.PrimaryKey<BMailTemplateAddress> mailTemplateAddressPkc = createPrimaryKey(id);

    public QMailTemplateAddress(String variable) {
        super(BMailTemplateAddress.class, forVariable(variable), "PUBLIC", "MAIL_TEMPLATE_ADDRESS");
        addMetadata();
    }

    public QMailTemplateAddress(String variable, String schema, String table) {
        super(BMailTemplateAddress.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QMailTemplateAddress(Path<? extends BMailTemplateAddress> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "MAIL_TEMPLATE_ADDRESS");
        addMetadata();
    }

    public QMailTemplateAddress(PathMetadata<?> metadata) {
        super(BMailTemplateAddress.class, metadata, "PUBLIC", "MAIL_TEMPLATE_ADDRESS");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(createdAt, ColumnMetadata.named("CREATED_AT").withIndex(6).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(deletedFlg, ColumnMetadata.named("DELETED_FLG").withIndex(8).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(lockVersion, ColumnMetadata.named("LOCK_VERSION").withIndex(7).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(rcptAddr, ColumnMetadata.named("RCPT_ADDR").withIndex(4).ofType(Types.VARCHAR).withSize(512).notNull());
        addMetadata(rcptType, ColumnMetadata.named("RCPT_TYPE").withIndex(3).ofType(Types.VARCHAR).withSize(3).notNull());
        addMetadata(templateId, ColumnMetadata.named("TEMPLATE_ID").withIndex(2).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(updatedAt, ColumnMetadata.named("UPDATED_AT").withIndex(5).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
    }

}


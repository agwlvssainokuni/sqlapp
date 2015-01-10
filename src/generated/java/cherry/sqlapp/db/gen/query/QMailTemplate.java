package cherry.sqlapp.db.gen.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QMailTemplate is a Querydsl query type for BMailTemplate
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QMailTemplate extends com.mysema.query.sql.RelationalPathBase<BMailTemplate> {

    private static final long serialVersionUID = -2131379419;

    public static final QMailTemplate mailTemplate = new QMailTemplate("MAIL_TEMPLATE");

    public final StringPath body = createString("body");

    public final DateTimePath<org.joda.time.LocalDateTime> createdAt = createDateTime("createdAt", org.joda.time.LocalDateTime.class);

    public final NumberPath<Integer> deletedFlg = createNumber("deletedFlg", Integer.class);

    public final StringPath fromAddr = createString("fromAddr");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> lockVersion = createNumber("lockVersion", Integer.class);

    public final StringPath subject = createString("subject");

    public final StringPath templateName = createString("templateName");

    public final DateTimePath<org.joda.time.LocalDateTime> updatedAt = createDateTime("updatedAt", org.joda.time.LocalDateTime.class);

    public final com.mysema.query.sql.PrimaryKey<BMailTemplate> mailTemplatePkc = createPrimaryKey(id);

    public QMailTemplate(String variable) {
        super(BMailTemplate.class, forVariable(variable), "PUBLIC", "MAIL_TEMPLATE");
        addMetadata();
    }

    public QMailTemplate(String variable, String schema, String table) {
        super(BMailTemplate.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QMailTemplate(Path<? extends BMailTemplate> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "MAIL_TEMPLATE");
        addMetadata();
    }

    public QMailTemplate(PathMetadata<?> metadata) {
        super(BMailTemplate.class, metadata, "PUBLIC", "MAIL_TEMPLATE");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(body, ColumnMetadata.named("BODY").withIndex(5).ofType(Types.VARCHAR).withSize(5000).notNull());
        addMetadata(createdAt, ColumnMetadata.named("CREATED_AT").withIndex(7).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(deletedFlg, ColumnMetadata.named("DELETED_FLG").withIndex(9).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(fromAddr, ColumnMetadata.named("FROM_ADDR").withIndex(3).ofType(Types.VARCHAR).withSize(300).notNull());
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(lockVersion, ColumnMetadata.named("LOCK_VERSION").withIndex(8).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(subject, ColumnMetadata.named("SUBJECT").withIndex(4).ofType(Types.VARCHAR).withSize(1000).notNull());
        addMetadata(templateName, ColumnMetadata.named("TEMPLATE_NAME").withIndex(2).ofType(Types.VARCHAR).withSize(30).notNull());
        addMetadata(updatedAt, ColumnMetadata.named("UPDATED_AT").withIndex(6).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
    }

}


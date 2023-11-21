package ugps.myweb.gpsinside.Entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRegistedUser is a Querydsl query type for RegistedUser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRegistedUser extends EntityPathBase<RegistedUser> {

    private static final long serialVersionUID = 63316164L;

    public static final QRegistedUser registedUser = new QRegistedUser("registedUser");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final ListPath<UserBoard, QUserBoard> boards = this.<UserBoard, QUserBoard>createList("boards", UserBoard.class, QUserBoard.class, PathInits.DIRECT2);

    public final StringPath email = createString("email");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public QRegistedUser(String variable) {
        super(RegistedUser.class, forVariable(variable));
    }

    public QRegistedUser(Path<? extends RegistedUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRegistedUser(PathMetadata metadata) {
        super(RegistedUser.class, metadata);
    }

}


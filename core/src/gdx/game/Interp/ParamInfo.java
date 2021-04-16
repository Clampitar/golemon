package gdx.game.Interp;
import gdx.game.Interp.node.*;

public class ParamInfo {

    private TIdent name;

    private Type type;

    public ParamInfo(
            AParam declaration,
            Type type) {

        this.name = declaration.getIdent();
        this.type = type;
    }

    public TIdent getName() {

        return this.name;
    }

    public Type getType() {

        return this.type;
    }


}

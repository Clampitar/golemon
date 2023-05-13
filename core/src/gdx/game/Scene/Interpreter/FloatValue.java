package gdx.game.Scene.Interpreter;

public class FloatValue extends Value{

    private float value;

    public FloatValue(float value){
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "" + this.value;
    }
}

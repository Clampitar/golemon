
package gdx.game.Scene.Interpreter;

public class IntValue
        extends Value {

    private int value;

    public IntValue(
            int value) {

        this.value = value;
    }

    public int getValue() {

        return this.value;
    }

    @Override
    public String toString() {

        return "" + this.value;
    }
}

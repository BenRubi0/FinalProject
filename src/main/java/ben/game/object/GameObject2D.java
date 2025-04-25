package ben.game.object;

import com.raylib.Raylib;

public class GameObject2D extends GameObject {
    public Raylib.Vector2 dimensions;
    public Raylib.Vector2 position;

    public GameObject2D(Raylib.Vector2 dimensions, Raylib.Vector2 position) {
        super(true, true, true);
        this.dimensions = dimensions;
        this.position = position;
    }
}

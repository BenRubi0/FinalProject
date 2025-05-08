// Made by Benjamin Rubio
// For Mr. Gross Software and Programming Dev 2 Class
// Contact at benjamin.rubio@malad.us

package ben.game.object.object_classes.world;

import ben.game.Game;
import ben.game.object.GameObject2D;
import com.raylib.Colors;
import com.raylib.Raylib;

public class FloorObject2D extends GameObject2D {
    public ColliderObject2D collider;
    public float floorY;
    public float floorX;

    private final Raylib.Rectangle renderRect;

    public FloorObject2D(Raylib.Vector2 dimensions, Raylib.Vector2 position) {
        super(dimensions, position);
        this.setObjectGroup("Floor");
        this.collider = new ColliderObject2D(dimensions, position);
        this.renderRect = new Raylib.Rectangle().x(position.x()).y(position.y())
                .width(dimensions.x()).height(dimensions.y());
        this.floorY = this.position.y();
        this.floorX = this.position.x();
    }

    @Override
    public void Render() {
        Raylib.DrawRectangleRec(this.renderRect, Colors.BLUE);

        if (Game.showHitboxes) {
            Raylib.DrawRectangleLinesEx(this.collider.hitbox, 3.5f, Colors.RED);
        }
    }
}

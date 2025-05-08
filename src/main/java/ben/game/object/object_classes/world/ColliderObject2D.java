// Made by Benjamin Rubio
// For Mr. Gross Software and Programming Dev 2 Class
// Contact at benjamin.rubio@malad.us

package ben.game.object.object_classes.world;

import ben.game.object.GameObject2D;
import com.raylib.Raylib;

public class ColliderObject2D extends GameObject2D {
    public Raylib.Rectangle hitbox;

    public ColliderObject2D(Raylib.Vector2 dimensions, Raylib.Vector2 position) {
        super(dimensions, position);
        this.hitbox = new Raylib.Rectangle().x(this.position.x()).y(this.position.y())
                .width(this.dimensions.x()).height(this.dimensions.y());
    }

    public boolean checkHitboxCollision(ColliderObject2D collider) { return Raylib.CheckCollisionRecs(this.hitbox, collider.hitbox); }

    @Override
    public void Update() {
        this.hitbox.x(this.position.x()).y(this.position.y())
                .width(this.dimensions.x()).height(this.dimensions.y());
    }
}

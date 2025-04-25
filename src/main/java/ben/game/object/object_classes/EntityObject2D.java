// Made by Benjamin Rubio
// For Mr. Gross Software and Programming Dev 2 Class
// Contact at benjamin.rubio@malad.us

package ben.game.object.object_classes;

import ben.game.Game;
import ben.game.object.GameObject2D;
import com.raylib.Colors;
import com.raylib.Raylib;

public class EntityObject2D extends GameObject2D {
    public Raylib.Rectangle hitbox;

    public float health;
    private final float maxHealth;
    private final float minHealth;

    public boolean isOnFloor = false;
    public boolean isEntityAlive = true;

    public EntityObject2D(Raylib.Vector2 dimensions, Raylib.Vector2 position, float maxHealth) {
        super(dimensions, position);
        this.minHealth = 0.0f;
        this.maxHealth = maxHealth;
        this.health = this.maxHealth;
        this.hitbox = new Raylib.Rectangle().x(this.position.x()).y(this.position.y())
                .width(this.dimensions.x()).width(this.dimensions.y());
    }

    public void heal(float amount) {
        if ((this.health + amount) < this.maxHealth)
            this.health += amount;
        else this.health = this.maxHealth;
    }

    public void takeDamage(float amount) {
        if ((this.health - amount) > this.minHealth)
            this.health -= amount;
        else this.health = this.minHealth;
    }

    public boolean checkHitboxCollision(Raylib.Rectangle collider) { return Raylib.CheckCollisionRecs(this.hitbox, collider); }

    public void renderHitbox() {
        if (Game.showHitboxes) {
            Raylib.DrawRectangleLinesEx(this.hitbox, 3.5f, Colors.BLUE);
        }
    }

    @Override
    public void Update() {
        // update the entity's hitbox
        this.hitbox.x(this.position.x()).y(this.position.y())
                .width(this.dimensions.x()).height(this.dimensions.y());

        // check entity alive status
        this.isEntityAlive = !(this.health <= minHealth);
    }

    @Override
    public void Render() {
        this.renderHitbox();
    }
}

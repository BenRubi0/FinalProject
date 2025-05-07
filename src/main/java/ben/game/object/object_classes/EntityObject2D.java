// Made by Benjamin Rubio
// For Mr. Gross Software and Programming Dev 2 Class
// Contact at benjamin.rubio@malad.us

package ben.game.object.object_classes;

import ben.game.Game;
import ben.game.object.GameObject;
import ben.game.object.GameObject2D;
import com.raylib.Colors;
import com.raylib.Raylib;

import java.util.ArrayList;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class EntityObject2D extends GameObject2D {
    public ColliderObject2D collider;
    public RigidBodyObject2D rigidBody;

    public float health;
    private final float maxHealth;
    private final float minHealth;

    public boolean isOnFloor = false;
    public boolean isEntityAlive = true;

    public EntityObject2D(Raylib.Vector2 dimensions, Raylib.Vector2 position, float maxHealth) {
        super(dimensions, position);
        this.setObjectGroup("Entities");
        this.minHealth = 0.0f;
        this.maxHealth = maxHealth;
        this.health = this.maxHealth;
        this.collider = new ColliderObject2D(dimensions, position);
        this.rigidBody = new RigidBodyObject2D(dimensions, position, 0.15f);
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

    public void checkFloor() {
        ArrayList<GameObject> floorObjects = this.parentScene.getGameObjectsOfGroup("Floor");

        boolean didCollide = false;

        boolean isInFloor = false;
        float floorLevel = 0.0f;

        for (GameObject object : floorObjects) {
            if (object instanceof FloorObject2D fo2d) {
                if (this.collider.checkHitboxCollision(fo2d.collider)) {
                    // checking y floor collision
                    if ((this.collider.hitbox.y()+this.collider.dimensions.y()-(((this.dimensions.y()/2)*0.1f)+0.5f)) > fo2d.floorY) {
                        didCollide = true;
                        isInFloor = true;
                        floorLevel = fo2d.floorY;
                        break;
                    }

                    if (this.collider.hitbox.y() <
                            (fo2d.collider.hitbox.y() + fo2d.collider.hitbox.height()) &&
                            fo2d.collider.hitbox.y() < (this.collider.hitbox.y() + this.collider.hitbox.height())) {
                        didCollide = true;
                        break;
                    }
                }
            }
        }

        this.isOnFloor = didCollide;

        this.rigidBody.setShouldMoveDown(!this.isOnFloor);

        if (isInFloor) {
            System.out.println("pushing out of floor");
            this.position.y(floorLevel-(this.collider.dimensions.y()+0.05f));
        }
    }

    public void renderHitbox() {
        if (Game.showHitboxes) {
            Raylib.DrawRectangleLinesEx(this.collider.hitbox, 3.5f, Colors.BLUE);
        }
    }

    @Override
    public void Update() {
        // velocity
        this.rigidBody.Update();

        // floor
        this.checkFloor();

        // gravity
        if (!this.isOnFloor)
            this.rigidBody.addGravity();

        // update the entity's hitbox
        this.collider.Update();

        // check entity alive status
        this.isEntityAlive = !(this.health <= minHealth);
    }

    @Override
    public void Render() {
        this.renderHitbox();
    }
}

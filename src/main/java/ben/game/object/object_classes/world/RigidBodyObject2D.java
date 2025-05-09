// Made by Benjamin Rubio
// For Mr. Gross Software and Programming Dev 2 Class
// Contact at benjamin.rubio@malad.us

package ben.game.object.object_classes.world;

import ben.game.object.GameObject2D;
import com.raylib.Raylib;

public class RigidBodyObject2D extends GameObject2D {
    public boolean canMove = true;
    public Raylib.Vector2 velocity = new Raylib.Vector2().x(0.0f).y(0.0f);
    public float velocityDamping = 0.1f;
    public float maxVelocity;
    public float maxSpeed;

    public boolean shouldMoveDown = true;
    public boolean shouldMoveUp = true;
    public boolean shouldMoveLeft = true;
    public boolean shouldMoveRight = true;

    public RigidBodyObject2D(Raylib.Vector2 dimensions, Raylib.Vector2 position) {
        super(dimensions, position);
    }

    public RigidBodyObject2D(Raylib.Vector2 dimensions, Raylib.Vector2 position, float velocityDamping, float maxVelocity, float maxSpeed) {
        super(dimensions, position);
        this.velocityDamping = velocityDamping;
        this.maxVelocity = maxVelocity;
        this.maxSpeed = maxSpeed;
    }

    public void setCanMove(boolean canMove) { this.canMove = canMove; }

    public void setShouldMoveDown(boolean shouldMoveDown) { this.shouldMoveDown = shouldMoveDown; }
    public void setShouldMoveUp(boolean shouldMoveUp) { this.shouldMoveUp = shouldMoveUp; }
    public void setShouldMoveLeft(boolean shouldMoveLeft) { this.shouldMoveLeft = shouldMoveLeft; }
    public void setShouldMoveRight(boolean shouldMoveRight) { this.shouldMoveRight = shouldMoveRight; }

    public void setRightVelocity(float velo) { this.velocity.x(velo); }
    public void setUpVelocity(float velo) { this.velocity.y(velo); }

    public void addRightVelocity(float velo) {
        if (Math.abs(this.velocity.x()) < this.maxVelocity && Math.abs(this.velocity.x()) < this.maxSpeed)
            this.velocity.x(this.velocity.x() + velo);
    }

    public void addUpVelocity(float velo) {
        if (Math.abs(this.velocity.y()) < this.maxVelocity)
            this.velocity.y(this.velocity.y() + velo);
    }

    public void addGravity() { this.velocity.y(this.velocity.y() + (9.8f * (velocityDamping/2))); }

    @Override
    public void Update() {
        if (this.canMove) {
            // right velocity
            if (Math.abs(this.velocity.x()) > 0.0f) {
                this.position.x(this.position.x() + this.velocity.x());
                if (this.velocity.x() < 0.0f && shouldMoveRight) {
                    this.velocity.x(this.velocity.x() + this.velocityDamping);
                } else if (this.velocity.x() >= 0.0f && shouldMoveLeft) { this.velocity.x(this.velocity.x() - this.velocityDamping); }
            }

            if (Math.abs(this.velocity.x()) < this.velocityDamping)
                this.velocity.x(0.0f);

            // up velocity
            if (Math.abs(this.velocity.y()) > 0.0f) {
                this.position.y(this.position.y() + this.velocity.y());
                if (this.velocity.y() < 0.0f && shouldMoveUp) {
                    this.velocity.y(this.velocity.y() + this.velocityDamping);
                } else if (this.velocity.y() > 0.0f && shouldMoveDown) { this.velocity.y(this.velocity.y() - this.velocityDamping); }
                else if (!shouldMoveDown) { this.setUpVelocity(((this.velocity.y() * -1.0f) * (this.dimensions.y() * 0.01f)) + this.velocityDamping); }
            }

            if (Math.abs(this.velocity.y()) < this.velocityDamping)
                this.velocity.y(0.0f);
        }
    }
}

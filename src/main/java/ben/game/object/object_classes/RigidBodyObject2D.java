// Made by Benjamin Rubio
// For Mr. Gross Software and Programming Dev 2 Class
// Contact at benjamin.rubio@malad.us

package ben.game.object.object_classes;

import ben.game.object.GameObject2D;
import com.raylib.Raylib;

public class RigidBodyObject2D extends GameObject2D {
    public Raylib.Vector2 velocity = new Raylib.Vector2().x(0.0f).y(0.0f);
    public float velocityDamping = 0.1f;

    public RigidBodyObject2D(Raylib.Vector2 dimensions, Raylib.Vector2 position) {
        super(dimensions, position);
    }

    public RigidBodyObject2D(Raylib.Vector2 dimensions, Raylib.Vector2 position, float velocityDamping) {
        super(dimensions, position);
        this.velocityDamping = velocityDamping;
    }

    public void addRightVelocity(float velo) { this.velocity.x(this.velocity.x() + velo); }
    public void addUpVelocity(float velo) { this.velocity.y(this.velocity.y() + velo); }

    @Override
    public void Update() {
        // right velocity
        if (Math.abs(this.velocity.x()) > 0.0f) {
            this.position.x(this.position.x() + this.velocity.x());
            if (this.velocity.x() < 0.0f) {
                this.velocity.x(this.velocity.x() + this.velocityDamping);
            } else if (this.velocity.x() >= 0.0f) { this.velocity.x(this.velocity.x() - this.velocityDamping); }
        }

        if (Math.abs(this.velocity.x()) < this.velocityDamping)
            this.velocity.x(0.0f);

        // up velocity
        if (Math.abs(this.velocity.y()) > 0.0f) {
            this.position.y(this.position.y() + this.velocity.y());
            if (this.velocity.y() < 0.0f) {
                this.velocity.y(this.velocity.y() + this.velocityDamping);
            } else if (this.velocity.y() > 0.0f) { this.velocity.y(this.velocity.y() - this.velocityDamping); }
        }

        if (Math.abs(this.velocity.y()) < this.velocityDamping)
            this.velocity.y(0.0f);
    }
}

package ben.game.object.object_classes.world;

import com.raylib.Colors;
import com.raylib.Raylib;

public class EnemyObject2D extends EntityObject2D {
    public boolean attacking = false;
    public Raylib.Rectangle renderRect;

    public EnemyObject2D(Raylib.Vector2 dimensions, Raylib.Vector2 position) {
        super(dimensions, position, 100.0f);
        this.renderRect = new Raylib.Rectangle().x(this.position.x()).y(this.position.y())
                .width(this.dimensions.x()).height(this.dimensions.y());
    }

    public void jump() {
        if (this.isOnFloor)
            this.rigidBody.addUpVelocity(-5.0f);
    }

    public void moveLeft() { this.rigidBody.addRightVelocity(-2.5f); }
    public void moveRight() { this.rigidBody.addRightVelocity(2.5f); }

    @Override
    public void Render() {
        Raylib.DrawRectangleRec(this.renderRect, Colors.BEIGE);
        super.Render();
    }

    @Override
    public void Update() {
        super.Update();
        this.renderRect.x(this.position.x()).y(this.position.y())
                .width(this.dimensions.x()).height(this.dimensions.y());
    }
}

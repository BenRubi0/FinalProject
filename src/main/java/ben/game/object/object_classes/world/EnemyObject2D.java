// Made by Benjamin Rubio
// For Mr. Gross Software and Programming Dev 2 Class
// Contact at benjamin.rubio@malad.us

package ben.game.object.object_classes.world;

import ben.game.global.GameGlobalPlayerStats;
import com.raylib.Colors;
import com.raylib.Raylib;

public class EnemyObject2D extends EntityObject2D {
    public boolean attacking = false;
    public Raylib.Rectangle renderRect;

    private int moveTick = 0;

    public EnemyObject2D(Raylib.Vector2 dimensions, Raylib.Vector2 position) {
        super(dimensions, position, 100.0f);
        this.setObjectGroup("Enemy");
        this.renderRect = new Raylib.Rectangle().x(this.position.x()).y(this.position.y())
                .width(this.dimensions.x()).height(this.dimensions.y());
        this.addOnDeathCallback(() -> GameGlobalPlayerStats.points += 5);
    }

    public void jump() {
        if (this.isOnFloor)
            this.rigidBody.addUpVelocity(-5.0f);
    }

    public void moveLeft() { this.rigidBody.addRightVelocity(-3.5f); }
    public void moveRight() { this.rigidBody.addRightVelocity(3.5f); }

    public void checkForPlayer() {
        PlayerObject2D player = this.parentScene.playerObject;

        if (player != null) {
            if (player.position.x() > this.position.x()) {
                if (this.moveTick == 60)
                    this.moveRight();
            } else if (player.position.x() < this.position.x()) {
                if (this.moveTick == 60)
                    this.moveLeft();
            }
        }
    }

    @Override
    public void Render() {
        Raylib.DrawRectangleRec(this.renderRect, Colors.BEIGE);
        super.Render();
    }

    @Override
    public void Update() {
        super.Update();
        if (this.isEntityAlive) {
            this.renderRect.x(this.position.x()).y(this.position.y())
                    .width(this.dimensions.x()).height(this.dimensions.y());
            this.checkForPlayer();
            if (this.moveTick < 60) {
                this.moveTick++;
            } else if (this.moveTick == 60) {
                this.moveTick = 0;
            }
        }
    }
}

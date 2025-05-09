package ben.game.object.object_classes.world;

import ben.game.object.GameObject;
import com.raylib.Colors;
import com.raylib.Raylib;

import java.util.ArrayList;

public class PlayerBulletObject2D extends EntityObject2D {
    public Raylib.Rectangle renderRect;

    public int shotX;
    public int shotY;

    private int lifeTimeTick = 0;

    public PlayerBulletObject2D(Raylib.Vector2 position) {
        super(new Raylib.Vector2().x(15.0f).y(15.0f), position, 100.0f, false);
        this.setObjectGroup("Bullet");
        this.renderRect = new Raylib.Rectangle().x(this.position.x()).y(this.position.y())
                .width(this.dimensions.x()).height(this.dimensions.y());
        this.shotX = Raylib.GetMouseX();
        this.shotY = Raylib.GetMouseY();
    }

    public void checkEnemyCollision() {
        ArrayList<GameObject> enemies = this.parentScene.getGameObjectsOfGroup("Enemy");

        for (GameObject enemy : enemies) {
            if (enemy instanceof EnemyObject2D enemyObject2D) {
                if (enemyObject2D.collider.checkHitboxCollision(this.collider)) {
                    this.takeDamage(this.health);
                    enemyObject2D.takeDamage(20.0f);
                }
            }
        }
    }

    public void checkLife() {
        if (this.lifeTimeTick == 100) {
            this.takeDamage(this.health);
        }
    }

    @Override
    public void Render() {
        Raylib.DrawRectangleRec(this.renderRect, Colors.GOLD);
        super.Render();
    }

    @Override
    public void Update() {
        super.Update();
        if (this.isEntityAlive) {
            this.renderRect.x(this.position.x()).y(this.position.y())
                    .width(this.dimensions.x()).height(this.dimensions.y());
            this.checkEnemyCollision();

            this.checkLife();

            if (this.lifeTimeTick < 100) {
                this.lifeTimeTick++;
            } else if (this.lifeTimeTick == 100) {
                this.lifeTimeTick = 0;
            }

            this.rigidBody.setVelocity(new Raylib.Vector2().x((this.shotX-this.position.x())*0.05f)
                    .y((this.shotY-this.position.y())*0.05f));
        }
    }
}

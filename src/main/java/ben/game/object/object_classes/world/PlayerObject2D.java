// Made by Benjamin Rubio
// For Mr. Gross Software and Programming Dev 2 Class
// Contact at benjamin.rubio@malad.us

package ben.game.object.object_classes.world;

import ben.game.input.GameKeyInputHandler;
import ben.game.input.GameMouseInputHandler;
import ben.game.object.GameObject;
import com.raylib.Colors;
import com.raylib.Raylib;

import java.util.ArrayList;

public class PlayerObject2D extends EntityObject2D {
    private final Raylib.Rectangle renderRect;

    public PlayerObject2D(Raylib.Vector2 dimensions, Raylib.Vector2 position) {
        super(dimensions, position, 100.0f);
        this.setObjectGroup("Player");
        this.registerKeyBinds();
        this.renderRect = new Raylib.Rectangle().x(this.position.x()).y(this.position.y())
                .width(this.dimensions.x()).height(this.dimensions.y());
    }

    private void registerKeyBinds() {
        // move right
        new GameKeyInputHandler(
                () -> this.rigidBody.addRightVelocity(0.5f),
                Raylib.KEY_D,
                "Moves the player to the right.",
                "Move Right",
                this,
                true
        );

        // move left
        new GameKeyInputHandler(
                () -> this.rigidBody.addRightVelocity(-0.5f),
                Raylib.KEY_A,
                "Moves the player to the left.",
                "Move Left",
                this,
                true
        );

        // jump
        new GameKeyInputHandler(
                () -> {
                    if (this.isOnFloor)
                        this.rigidBody.addUpVelocity(-7.5f);
                },
                Raylib.KEY_SPACE,
                "Makes the player jump up.",
                "Jump",
                this
        );

        // crouch
        new GameKeyInputHandler(
                () -> {
                    if (!this.isOnFloor)
                        this.rigidBody.addUpVelocity(1.0f);
                },
                Raylib.KEY_LEFT_SHIFT,
                "Makes the player crouch down.",
                "Crouch",
                this,
                true
        );

        // shoot
        new GameMouseInputHandler(
                () -> System.out.println("Shooting bullet..."),
                Raylib.MOUSE_BUTTON_LEFT,
                "Shoots a bullet from the player.",
                "Shoot",
                this
        );
    }

    public void checkEnemyCollision() {
        ArrayList<GameObject> enemies = this.parentScene.getGameObjectsOfGroup("Enemy");

        for (GameObject enemy : enemies) {
            if (enemy instanceof EnemyObject2D enemyObject2D) {
                if (enemyObject2D.collider.checkHitboxCollision(this.collider)) {
                    this.takeDamage(5.0f);

                    this.rigidBody.addUpVelocity(-2.0f);
                    enemyObject2D.jump();

                    if (this.position.x() < enemyObject2D.position.x()) {
                        this.rigidBody.setRightVelocity(-2.5f);
                        enemyObject2D.moveRight();
                    } else if (this.position.x() > enemyObject2D.position.x()) {
                        this.rigidBody.setRightVelocity(2.5f);
                        enemyObject2D.moveLeft();
                    }
                }
            }
        }
    }

    @Override
    public void Update() {
        super.Update();
        if (this.isEntityAlive) {
            this.renderRect.x(this.position.x()).y(this.position.y())
                    .width(this.dimensions.x()).height(this.dimensions.y());
            this.checkEnemyCollision();
        }
    }

    @Override
    public void Render() {
        Raylib.DrawRectangleRec(this.renderRect, Colors.RED);
        super.Render();
    }
}

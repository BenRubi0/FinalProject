// Made by Benjamin Rubio
// For Mr. Gross Software and Programming Dev 2 Class
// Contact at benjamin.rubio@malad.us

package ben.game.scene.scenes;

import ben.game.Game;
import ben.game.global.GameGlobalPlayerStats;
import ben.game.object.object_classes.screen.GuiTextLabelObject2D;
import ben.game.object.object_classes.world.EnemyObject2D;
import ben.game.object.object_classes.world.FloorObject2D;
import ben.game.scene.GameScene;
import com.raylib.Colors;
import com.raylib.Raylib;

public class Level1Scene extends GameScene {
    @Override
    public void init() {
        this.addPlayerToScene();

        this.playerObject.addOnDeathCallback(() -> {
            Game.windowContext.setCurrentScene(new TestScreenScene());
        });

        // floors
        FloorObject2D ground = new FloorObject2D(new Raylib.Vector2().x((float) Game.getWindowDimensions().width).y(50.0f),
                new Raylib.Vector2().x(0.0f).y((float) Game.getWindowDimensions().height - 50.0f));

        // entities
        EnemyObject2D enemy = new EnemyObject2D(new Raylib.Vector2().x(50.0f).y(50.0f),
                new Raylib.Vector2().x((float) Game.getWindowDimensions().width /2).y(25.0f));

        // gui
        GuiTextLabelObject2D pointsLabel = new GuiTextLabelObject2D(new Raylib.Vector2().x(60.0f).y(35.0f), "Points: ",
                Colors.GOLD, 35);
        GuiTextLabelObject2D healthLabel = new GuiTextLabelObject2D(new Raylib.Vector2().x(240.0f).y(35.0f), "Health: ",
                Colors.GREEN, 35);

        pointsLabel.addUpdater(() -> {
            pointsLabel.setText("Points: " + GameGlobalPlayerStats.points);
        });

        healthLabel.addUpdater(() -> {
            healthLabel.setText("Health: " + playerObject.health);
        });

        // adding objects
        this.addGameObject(enemy);
        this.addGameObject(ground);

        // adding gui
        this.addGameObject(pointsLabel);
        this.addGameObject(healthLabel);

        // background
        this.setBgColor(Colors.DARKBLUE);
    }
}

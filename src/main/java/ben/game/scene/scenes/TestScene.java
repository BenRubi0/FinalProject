// Made by Benjamin Rubio
// For Mr. Gross Software and Programming Dev 2 Class
// Contact at benjamin.rubio@malad.us

package ben.game.scene.scenes;

import ben.game.object.object_classes.world.FloorObject2D;
import ben.game.object.object_classes.world.PlayerObject2D;
import ben.game.scene.GameScene;
import com.raylib.Colors;
import com.raylib.Raylib;

public class TestScene extends GameScene {
    @Override
    public void init() {
        PlayerObject2D player = new PlayerObject2D(new Raylib.Vector2().x(50).y(50), new Raylib.Vector2().x(50).y(50));
        FloorObject2D floor = new FloorObject2D(new Raylib.Vector2().x(300).y(100), new Raylib.Vector2().x(50).y(300));
        FloorObject2D floor2 = new FloorObject2D(new Raylib.Vector2().x(300).y(100), new Raylib.Vector2().x(350).y(250));
        FloorObject2D floor3 = new FloorObject2D(new Raylib.Vector2().x(300).y(100), new Raylib.Vector2().x(600).y(200));
        this.addGameObject(player);
        this.addGameObject(floor);
        this.addGameObject(floor2);
        this.addGameObject(floor3);
        this.setBgColor(Colors.SKYBLUE);
    }
}

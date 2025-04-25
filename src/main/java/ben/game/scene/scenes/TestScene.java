// Made by Benjamin Rubio
// For Mr. Gross Software and Programming Dev 2 Class
// Contact at benjamin.rubio@malad.us

package ben.game.scene.scenes;

import ben.game.object.object_classes.PlayerObject2D;
import ben.game.scene.GameScene;
import com.raylib.Raylib;

public class TestScene extends GameScene {
    @Override
    public void init() {
        PlayerObject2D player = new PlayerObject2D(new Raylib.Vector2().x(50).y(50), new Raylib.Vector2().x(50).y(50));
        this.addGameObject(player);
    }
}

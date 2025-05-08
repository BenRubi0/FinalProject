// Made by Benjamin Rubio
// For Mr. Gross Software and Programming Dev 2 Class
// Contact at benjamin.rubio@malad.us

package ben.game.scene.scenes;

import ben.game.Game;
import ben.game.object.object_classes.screen.GuiButtonObject2D;
import ben.game.scene.GameScene;
import com.raylib.Raylib;

public class TestScreenScene extends GameScene {
    @Override
    public void init() {
        GuiButtonObject2D button = new GuiButtonObject2D(new Raylib.Vector2().x(100).y(50),
                new Raylib.Vector2().x((float) Game.getWindowDimensions().width / 2).y((float) Game.getWindowDimensions().height / 2),
                "Play", () -> Game.windowContext.setCurrentScene(new TestScene()));
        this.addGameObject(button);
    }
}

// Made by Benjamin Rubio
// For Mr. Gross Software and Programming Dev 2 Class
// Contact at benjamin.rubio@malad.us

package ben.game.input;

import ben.game.object.GameObject;
import com.raylib.Raylib;

public class GameMouseInputHandler extends GameInputHandler {
    public int mouseButtonTrigger;

    public GameMouseInputHandler(Runnable eventHandler, int mouseButtonTrigger, String actionDescription, String actionIdentifier, GameObject parentObject) {
        super(eventHandler, actionDescription, actionIdentifier, parentObject);
        this.mouseButtonTrigger = mouseButtonTrigger;
    }

    @Override
    public void getInput() {
        if (hold) {
            if (Raylib.IsMouseButtonDown(this.mouseButtonTrigger))
                this.action.run();
        } else {
            if (Raylib.IsMouseButtonPressed(this.mouseButtonTrigger))
                this.action.run();
        }
    }
}

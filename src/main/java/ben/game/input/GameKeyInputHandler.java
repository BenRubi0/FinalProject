// Made by Benjamin Rubio
// For Mr. Gross Software and Programming Dev 2 Class
// Contact at benjamin.rubio@malad.us

package ben.game.input;

import ben.game.Game;
import ben.game.object.GameObject;
import com.raylib.Raylib;

public class GameKeyInputHandler extends GameInputHandler {
    public int triggerKey;

    public GameKeyInputHandler(Runnable eventHandler, int eventTriggerKey, String actionDescription, String actionIdentifier, GameObject parentObject) {
        super(eventHandler, actionDescription, actionIdentifier, parentObject);
        this.triggerKey = eventTriggerKey;
        Game.addInputHandlerToGlobalArray(this);
    }

    public GameKeyInputHandler(Runnable eventHandler, int eventTriggerKey, String actionDescription, String actionIdentifier, GameObject parentObject, boolean hold) {
        super(eventHandler, actionDescription, actionIdentifier, parentObject, hold);
        this.triggerKey = eventTriggerKey;
        Game.addInputHandlerToGlobalArray(this);
    }

    public void getInput() {
        if (hold) {
            if (Raylib.IsKeyDown(this.triggerKey))
                this.action.run();
        } else {
            if (Raylib.IsKeyPressed(this.triggerKey))
                this.action.run();
        }
    }
}

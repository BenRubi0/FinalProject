// Made by Benjamin Rubio
// For Mr. Gross Software and Programming Dev 2 Class
// Contact at benjamin.rubio@malad.us

package ben.game.input;

import ben.game.Game;
import ben.game.object.GameObject;
import com.raylib.Raylib;

public class GameInputHandler {
    public GameObject parentObject;

    public int triggerKey;
    public Runnable action;
    public String actionDescription;
    public String actionIdentifier;

    public GameInputHandler(Runnable eventHandler, int eventTriggerKey, String actionDescription, String actionIdentifier, GameObject parentObject) {
        this.triggerKey = eventTriggerKey;
        this.action = eventHandler;
        this.actionDescription = actionDescription;
        this.actionIdentifier = actionIdentifier;
        this.parentObject = parentObject;
        Game.addInputHandlerToGlobalArray(this);
    }

    public void getInput() {
        if (Raylib.IsKeyDown(this.triggerKey))
            this.action.run();
    }
}

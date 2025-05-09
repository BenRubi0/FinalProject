// Made by Benjamin Rubio
// For Mr. Gross Software and Programming Dev 2 Class
// Contact at benjamin.rubio@malad.us

package ben.game.input;

import ben.game.Game;
import ben.game.object.GameObject;

public class GameInputHandler {
    public GameObject parentObject;

    public Runnable action;
    public String actionDescription;
    public String actionIdentifier;
    public boolean hold = false;

    public GameInputHandler(Runnable eventHandler, String actionDescription, String actionIdentifier, GameObject parentObject) {
        this.action = eventHandler;
        this.actionDescription = actionDescription;
        this.actionIdentifier = actionIdentifier;
        this.parentObject = parentObject;
        Game.addInputHandlerToGlobalArray(this);
    }

    public GameInputHandler(Runnable eventHandler, String actionDescription, String actionIdentifier, GameObject parentObject, boolean hold) {
        this.action = eventHandler;
        this.actionDescription = actionDescription;
        this.actionIdentifier = actionIdentifier;
        this.parentObject = parentObject;
        this.hold = hold;
        Game.addInputHandlerToGlobalArray(this);
    }

    public void getInput() {}
}

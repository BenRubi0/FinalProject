// Made by Benjamin Rubio
// For Mr. Gross Software and Programming Dev 2 Class
// Contact at benjamin.rubio@malad.us

package ben.game;

import ben.game.input.GameInputHandler;
import ben.game.window.GameWindowContext;
import ben.game.window.GameWindowDimensions;

import java.util.ArrayList;

public class Game {
    // window context
    public static GameWindowContext windowContext;
    public static GameWindowDimensions getWindowDimensions() { return Game.windowContext.windowDimensions; }

    public static void setWindowContext(GameWindowContext windowContext) { Game.windowContext = windowContext; }
    public static void initGame() { Game.windowContext.createWindow(); }

    // game input
    public final static ArrayList<GameInputHandler> inputHandlers = new ArrayList<>();

    public static void addInputHandlerToGlobalArray(GameInputHandler inputHandler) { inputHandlers.add(inputHandler); }
    public static void getUserInput() {
        for (GameInputHandler inputHandler : inputHandlers) {
            if (inputHandler.parentObject.parentScene == windowContext.getCurrentScene())
                inputHandler.getInput();
        }
    }

    // rendering
    public static boolean showHitboxes = true;
}

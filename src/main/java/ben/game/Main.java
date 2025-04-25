// Made by Benjamin Rubio
// For Mr. Gross Software and Programming Dev 2 Class
// Contact at benjamin.rubio@malad.us

package ben.game;

import ben.game.window.GameWindowContext;
import ben.game.window.GameWindowDimensions;

public class Main {
    public static void main(String[] args) {
        Game.setWindowContext(new GameWindowContext("Fighting Game",
                new GameWindowDimensions(800, 600), false));
        Game.initGame();
    }
}
// Made by Benjamin Rubio
// For Mr. Gross Software and Programming Dev 2 Class
// Contact at benjamin.rubio@malad.us

package ben.game.window;

import com.raylib.Raylib;

public class GameWindowDimensions {
    public int height;
    public int width;

    public GameWindowDimensions(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Raylib.Vector2 vector2() { return new Raylib.Vector2().x(this.width).y(this.height); }
}

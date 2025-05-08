// Made by Benjamin Rubio
// For Mr. Gross Software and Programming Dev 2 Class
// Contact at benjamin.rubio@malad.us

package ben.game.object.object_classes.screen;

import ben.game.object.GameObject2D;
import com.raylib.Raylib;

public class GuiObject2D extends GameObject2D {
    public GuiObject2D(Raylib.Vector2 dimensions, Raylib.Vector2 position) {
        super(dimensions, position);
        this.setObjectGroup("Gui");
    }
}

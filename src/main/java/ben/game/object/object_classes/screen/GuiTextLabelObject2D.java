// Made by Benjamin Rubio
// For Mr. Gross Software and Programming Dev 2 Class
// Contact at benjamin.rubio@malad.us

package ben.game.object.object_classes.screen;

import com.raylib.Raylib;

import java.util.ArrayList;

public class GuiTextLabelObject2D extends GuiObject2D {
    public String text;
    public Raylib.Color textColor;
    public final int fontSize;

    public GuiTextLabelObject2D(Raylib.Vector2 position, String text, Raylib.Color textColor, int fontSize) {
        super(new Raylib.Vector2().x(0.0f).y(0.0f), position);
        this.position.x(this.position.x()-((float) fontSize));
        this.position.y(this.position.y()-((float) fontSize /2));
        this.text = text;
        this.textColor = textColor;
        this.fontSize = fontSize;
    }

    public void setText(String text) { this.text = text; }

    @Override
    public void Render() {
        Raylib.DrawText(this.text, Math.round(this.position.x()), Math.round(this.position.y()), this.fontSize, this.textColor);
        super.Render();
    }

    @Override
    public void Update() {
        super.Update();
    }
}

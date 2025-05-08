// Made by Benjamin Rubio
// For Mr. Gross Software and Programming Dev 2 Class
// Contact at benjamin.rubio@malad.us

package ben.game.object.object_classes.screen;

import com.raylib.Raylib;

public class GuiButtonObject2D extends GuiObject2D {
    public String buttonText;
    public Runnable callback;

    private final Raylib.Rectangle renderRect;
    private boolean isClicked = false;

    public GuiButtonObject2D(Raylib.Vector2 dimensions, Raylib.Vector2 position, String buttonText, Runnable callback) {
        super(dimensions, position);
        this.position.x(this.position.x()-(this.dimensions.x()/2));
        this.position.y(this.position.y()-(this.dimensions.y()/2));
        this.buttonText = buttonText;
        this.callback = callback;
        this.renderRect = new Raylib.Rectangle().x(this.position.x()).y(this.position.y())
                .width(this.dimensions.x()).height(this.dimensions.y());
    }

    @Override
    public void Render() {
        this.isClicked = Raylib.GuiButton(this.renderRect, this.buttonText) == 1;
        super.Render();
    }

    @Override
    public void Update() {
        super.Update();

        if (this.isClicked) {
            this.callback.run();
        }
    }
}

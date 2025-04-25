// Made by Benjamin Rubio
// For Mr. Gross Software and Programming Dev 2 Class
// Contact at benjamin.rubio@malad.us

package ben.game.object;

import ben.game.scene.GameScene;

public class GameObject {
    public GameScene parentScene;

    public boolean shouldRender;
    public boolean shouldClose;
    public boolean shouldUpdate;

    public GameObject() {
        this.shouldUpdate = true;
        this.shouldRender = false;
        this.shouldClose = false;
    }

    public GameObject(boolean shouldUpdate, boolean shouldRender, boolean shouldClose) {
        this.shouldUpdate = shouldUpdate;
        this.shouldRender = shouldRender;
        this.shouldClose = shouldClose;
    }

    public void setParentScene(GameScene parentScene) { this.parentScene = parentScene; }

    public void Update() {}
    public void Render() {}
    public void Close() {}
}
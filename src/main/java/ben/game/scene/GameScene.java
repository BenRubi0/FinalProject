// Made by Benjamin Rubio
// For Mr. Gross Software and Programming Dev 2 Class
// Contact at benjamin.rubio@malad.us

package ben.game.scene;

import ben.game.object.GameObject;
import com.raylib.Raylib;

import java.util.ArrayList;

public abstract class GameScene {
    public ArrayList<GameObject> gameObjects = new ArrayList<>();

    public Raylib.Color bgColor = new Raylib.Color().r((byte) 0).b((byte) 0).g((byte) 0).a((byte) 255);

    public void addGameObject(GameObject object) { this.gameObjects.add(object); object.setParentScene(this); }
    public void removeGameObject(GameObject object) { this.gameObjects.remove(object); object.setParentScene(null); }

    public void setBgColor(Raylib.Color color) { this.bgColor = color; }

    public ArrayList<GameObject> getGameObjectsOfGroup(String groupName) {
        ArrayList<GameObject> objects = new ArrayList<>();

        for (GameObject object : gameObjects) {
            if (object.objectGroup.equals(groupName))
                objects.add(object);
        }

        return objects;
    }

    public void update() {
        for (GameObject object : this.gameObjects) {
            if (object.shouldUpdate)
                object.Update();
        }
    }

    public void render() {
        for (GameObject object : this.gameObjects) {
            if (object.shouldRender)
                object.Render();
        }
    }

    public void close() {
        for (GameObject object : this.gameObjects) {
            if (object.shouldClose)
                object.Close();
        }
    }

    public abstract void init();
}

// Made by Benjamin Rubio
// For Mr. Gross Software and Programming Dev 2 Class
// Contact at benjamin.rubio@malad.us

package ben.game.scene;

import ben.game.object.GameObject;
import ben.game.object.object_classes.world.PlayerObject2D;
import com.raylib.Raylib;

import java.util.ArrayList;

public abstract class GameScene {
    public ArrayList<GameObject> gameObjects = new ArrayList<>();
    public ArrayList<GameObject> gameObjectRemovalList = new ArrayList<>();
    public ArrayList<GameObject> gameObjectAddList = new ArrayList<>();

    public Raylib.Color bgColor = new Raylib.Color().r((byte) 0).b((byte) 0).g((byte) 0).a((byte) 255);

    public PlayerObject2D playerObject;

    public void addGameObject(GameObject object) { this.gameObjectAddList.add(object); }
    public void removeGameObject(GameObject object) { this.gameObjectRemovalList.add(object); }

    public void setBgColor(Raylib.Color color) { this.bgColor = color; }

    public void addPlayerToScene() {
        this.playerObject = new PlayerObject2D(new Raylib.Vector2().x(50).y(50), new Raylib.Vector2().x(50).y(50));
        this.addGameObject(this.playerObject);
    }

    public ArrayList<GameObject> getGameObjectsOfGroup(String groupName) {
        ArrayList<GameObject> objects = new ArrayList<>();

        for (GameObject object : gameObjects) {
            if (object.objectGroup.equals(groupName))
                objects.add(object);
        }

        return objects;
    }

    public void update() {
        for (GameObject object : this.gameObjectRemovalList) {
            this.gameObjects.remove(object);
            object.setParentScene(null);
        }

        this.gameObjectRemovalList.clear();

        for (GameObject object : this.gameObjectAddList) {
            this.gameObjects.add(object);
            object.setParentScene(this);
        }

        this.gameObjectAddList.clear();

        for (GameObject object : this.gameObjects) {
            if (object.shouldUpdate)
                object.Update();
        }
    }

    public void render() {
        for (GameObject object : this.gameObjects) {
            if (object.shouldRender && !object.objectGroup.equals("Gui"))
                object.Render();
        }
    }

    public void renderGui() {
        for (GameObject object : this.getGameObjectsOfGroup("Gui")) {
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

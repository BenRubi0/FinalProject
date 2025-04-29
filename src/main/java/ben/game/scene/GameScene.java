// Made by Benjamin Rubio
// For Mr. Gross Software and Programming Dev 2 Class
// Contact at benjamin.rubio@malad.us

package ben.game.scene;

import ben.game.object.GameObject;

import java.util.ArrayList;

public abstract class GameScene {
    public ArrayList<GameObject> gameObjects = new ArrayList<>();

    public void addGameObject(GameObject object) { this.gameObjects.add(object); object.setParentScene(this); }
    public void removeGameObject(GameObject object) { this.gameObjects.remove(object); object.setParentScene(null); }

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

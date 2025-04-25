// Made by Benjamin Rubio
// For Mr. Gross Software and Programming Dev 2 Class
// Contact at benjamin.rubio@malad.us

package ben.game.window;

import ben.game.Game;
import ben.game.scene.scenes.TestScene;
import ben.game.window.listener.GameListener;
import ben.game.window.listener.GameListenerContext;
import ben.game.scene.GameScene;
import com.raylib.Colors;
import com.raylib.Raylib;

import java.util.ArrayList;

public class GameWindowContext {
    public String windowTitle;
    public GameWindowDimensions windowDimensions;
    public boolean resizable = false;

    private final ArrayList<GameListener> gameListeners = new ArrayList<>();
    private GameScene currentScene = new TestScene();

    private void p_callListenersOfContext(GameListenerContext context) {
        for (GameListener gameListener : this.gameListeners) {
            if (gameListener.listenerContext == context) {
                gameListener.function.run();
            }
        }
    }

    public GameWindowContext() {
        this.windowTitle = "Game";
        this.windowDimensions = new GameWindowDimensions(800, 600);
    }

    public GameWindowContext(String title, GameWindowDimensions dimensions, boolean resizable) {
        this.windowTitle = title;
        this.windowDimensions = dimensions;
        this.resizable = resizable;
    }

    public void setCurrentScene(GameScene scene) { this.currentScene = scene; }

    public GameScene getCurrentScene() { return currentScene; }

    public void addGameListener(GameListener listener) { this.gameListeners.add(listener); }

    public void createWindow() {
        Raylib.InitWindow(this.windowDimensions.width, this.windowDimensions.height, this.windowTitle);
        Raylib.InitAudioDevice();

        Raylib.SetTargetFPS(60);

        if (this.resizable) { Raylib.SetWindowState(Raylib.FLAG_WINDOW_RESIZABLE); }

        this.p_callListenersOfContext(GameListenerContext.ON_OPEN);

        this.currentScene.init();

        System.out.println("[GAME]: Loaded current scene: " + this.getCurrentScene().getClass().getName());

        while (!Raylib.WindowShouldClose()) {
            Game.getUserInput();


            this.p_callListenersOfContext(GameListenerContext.ON_UPDATE);

            this.currentScene.update();

            Raylib.BeginDrawing();
            Raylib.ClearBackground(Colors.BLACK);

            this.p_callListenersOfContext(GameListenerContext.ON_RENDER);

            this.currentScene.render();

            Raylib.EndDrawing();
        }

        this.p_callListenersOfContext(GameListenerContext.ON_CLOSE);

        this.currentScene.close();

        Raylib.CloseWindow();
        Raylib.CloseAudioDevice();
    }
}

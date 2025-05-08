// Made by Benjamin Rubio
// For Mr. Gross Software and Programming Dev 2 Class
// Contact at benjamin.rubio@malad.us

package ben.game.window;

import ben.game.Game;
import ben.game.scene.scenes.TestScene;
import ben.game.scene.scenes.TestScreenScene;
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
    public Raylib.Camera2D camera2D = new Raylib.Camera2D().target(new Raylib.Vector2().x(0.0f).y(0.0f)).offset(new Raylib.Vector2().x(0.0f).y(0.0f));

    private final ArrayList<GameListener> gameListeners = new ArrayList<>();
    private GameScene currentScene = new TestScreenScene();

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

    public void setCurrentScene(GameScene scene) { this.currentScene = scene; this.currentScene.init(); }

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


            Raylib.BeginMode2D(this.camera2D);
            Raylib.BeginDrawing();
            Raylib.ClearBackground(this.currentScene.bgColor);

            this.p_callListenersOfContext(GameListenerContext.ON_RENDER);

            this.currentScene.render();

            Raylib.EndDrawing();
            Raylib.EndMode2D();
        }

        this.p_callListenersOfContext(GameListenerContext.ON_CLOSE);

        this.currentScene.close();

        Raylib.CloseWindow();
        Raylib.CloseAudioDevice();
    }
}

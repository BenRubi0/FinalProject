// Made by Benjamin Rubio
// For Mr. Gross Software and Programming Dev 2 Class
// Contact at benjamin.rubio@malad.us

package ben.game.window.listener;

public class GameListener {
    public GameListenerContext listenerContext;
    public Runnable function;

    public GameListener(GameListenerContext context, Runnable function) {
        this.listenerContext = context;
        this.function = function;
    }
}

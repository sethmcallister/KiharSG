package xyz.sethy.sg;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.sethy.sg.scoreboard.ScoreboardHandler;
import xyz.sethy.sg.scoreboard.ScoreboardThread;
import xyz.sethy.sg.states.GameState;
import xyz.sethy.sg.timers.TimerHandler;
import xyz.sethy.threads.pools.ThreadPool;

/**
 * Created by sethm on 21/12/2016.
 */
public class Main extends JavaPlugin
{
    private static Main instance;
    private Plugin plugin;
    private GameState gameState;
    private ScoreboardHandler scoreboardHandler;
    private TimerHandler timerHandler;
    private ThreadPool threadPool;
    private int BORDER_SIZE;

    public void onEnable()
    {
        instance = this;
        this.plugin = this;
        this.gameState = GameState.PREGAME;
        this.scoreboardHandler = new ScoreboardHandler();
        this.timerHandler = new TimerHandler();

        this.threadPool = new ThreadPool();
        this.threadPool.addThread(new ScoreboardThread());

        this.BORDER_SIZE = 800;
    }

    public GameState getGameState()
    {
        return gameState;
    }

    public static Main getInstance()
    {
        return instance;
    }

    public Plugin getPlugin()
    {
        return plugin;
    }

    public ScoreboardHandler getScoreboardHandler()
    {
        return scoreboardHandler;
    }

    public TimerHandler getTimerHandler()
    {
        return timerHandler;
    }

    public int getBORDER_SIZE()
    {
        return BORDER_SIZE;
    }

    public void setBORDER_SIZE(int BORDER_SIZE)
    {
        this.BORDER_SIZE = BORDER_SIZE;
    }
}

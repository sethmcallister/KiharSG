package xyz.sethy.sg;

import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.sethy.sg.listeners.EnderpearlListener;
import xyz.sethy.sg.listeners.PlayerJoinListener;
import xyz.sethy.sg.scoreboard.ScoreboardHandler;
import xyz.sethy.sg.scoreboard.ScoreboardThread;
import xyz.sethy.sg.states.GameState;
import xyz.sethy.sg.tasks.PreGameTask;
import xyz.sethy.sg.timers.TimerHandler;
import xyz.sethy.threads.pools.ThreadPool;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

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
    private final AtomicInteger online = new AtomicInteger(0);
    private final Collection<Player> players = new LinkedList<>();

    public void onEnable()
    {
        instance = this;
        this.plugin = this;
        this.gameState = GameState.PREGAME;
        this.scoreboardHandler = new ScoreboardHandler();
        this.timerHandler = new TimerHandler();

        this.threadPool = new ThreadPool();
        this.threadPool.addThread(new ScoreboardThread());
        this.threadPool.addThread(new PreGameTask());

        this.BORDER_SIZE = 800;

        this.registerListeners();
    }

    private void registerListeners()
    {
        Bukkit.getServer().getPluginManager().registerEvents(new EnderpearlListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);

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

    public ThreadPool getThreadPool()
    {
        return threadPool;
    }

    public int getBORDER_SIZE()
    {
        return BORDER_SIZE;
    }

    public void setBORDER_SIZE(int BORDER_SIZE)
    {
        this.BORDER_SIZE = BORDER_SIZE;
    }

    public AtomicInteger getOnline()
    {
        return online;
    }

    public Collection<Player> getPlayers()
    {
        return players;
    }
}

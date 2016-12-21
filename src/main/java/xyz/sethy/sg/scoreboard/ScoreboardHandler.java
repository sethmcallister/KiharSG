package xyz.sethy.sg.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.*;
import xyz.sethy.collections.buffer.Buffer;
import xyz.sethy.collections.buffer.BufferCollection;
import xyz.sethy.sg.Main;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by sethm on 21/12/2016.
 */
public class ScoreboardHandler implements Listener
{
    private Buffer<Player, Scoreboard> scoreboards;

    public ScoreboardHandler()
    {
        this.scoreboards = new BufferCollection<>(1000);
        Bukkit.getServer().getPluginManager().registerEvents(this, Main.getInstance().getPlugin());
    }

    public Scoreboard getScoreboard(Player player)
    {
        return scoreboards.get(player);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        org.bukkit.scoreboard.Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        event.getPlayer().setScoreboard(scoreboard);

        Scoreboard scoreboard1 = new Scoreboard(scoreboard, "&6&lKihar SG");
        scoreboards.offer(event.getPlayer(), scoreboard1);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event)
    {
        scoreboards.removeByKey(event.getPlayer());
    }

    @EventHandler
    public void onPlayerKick(PlayerKickEvent event)
    {
        scoreboards.removeByKey(event.getPlayer());
    }
}

package xyz.sethy.sg.tasks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.sethy.sg.Main;
import xyz.sethy.sg.timers.Timer;
import xyz.sethy.sg.timers.TimerType;

/**
 * Created by sethm on 21/12/2016.
 */
public class PreGameTask implements xyz.sethy.threads.Thread
{
    @Override
    public void execute()
    {
        int online = Main.getInstance().getOnline().get();
        if(online > 20)
        {
            Bukkit.getScheduler().scheduleAsyncRepeatingTask(Main.getInstance().getPlugin(), new BukkitRunnable()
            {
                int i = 10;
                @Override
                public void run()
                {
                    if(i != 0)
                    {
                        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&aGame starting in &e" + i + "&aseconds."));
                    }
                    i++;
                    for(Player player : Main.getInstance().getPlayers())
                    {
                        player.setGameMode(GameMode.SURVIVAL);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou have &e1 minuet 30&c of PvP Protection!"));
                        player.teleport(new Location(Bukkit.getWorld("world"), 0, 0, 0));
                        Timer timer = new Timer(TimerType.PVP_TIMER, 90000 + System.currentTimeMillis(), player);
                        Main.getInstance().getTimerHandler().addTimer(timer, player);
                    }
                    this.cancel();
                }
            }, 0L, 20L);
            Main.getInstance().getThreadPool().removeThread(this);
        }
    }
}

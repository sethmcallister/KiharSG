package xyz.sethy.sg.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import xyz.sethy.sg.Main;

/**
 * Created by sethm on 21/12/2016.
 */
public class PlayerJoinListener implements Listener
{
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();
        Main.getInstance().getPlayers().add(player);
        Main.getInstance().getOnline().incrementAndGet();
    }
}

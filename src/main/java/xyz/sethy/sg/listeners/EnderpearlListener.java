package xyz.sethy.sg.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import xyz.sethy.sg.Main;
import xyz.sethy.sg.timers.Timer;
import xyz.sethy.sg.timers.TimerType;

/**
 * Created by sethm on 21/12/2016.
 */
public class EnderpearlListener implements Listener
{
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerInteract(final PlayerInteractEvent event)
    {
        Player player = event.getPlayer();
        if (player.getPlayer().getItemInHand().getType().equals(Material.ENDER_PEARL))
        {
            if ((event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction() == Action.RIGHT_CLICK_BLOCK))
            {
                if(this.timers.containsKey(player) && this.timers.get(player) > System.currentTimeMillis())
                {
                    long millisLeft = this.timers.get(player) - System.currentTimeMillis();
                    double value = millisLeft / 1000.0D;
                    double sec = Math.round(10.0D * value) / 10.0D;
                    event.setCancelled(true);
                    event.getPlayer().updateInventory();
                    event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou cannot use this for another &l" + sec + " seconds&c."));
                    return;
                }
                Main.getInstance().getTimerHandler().addTimer(player, new Timer(TimerType.ENDERPEARL, 16000 + System.currentTimeMillis(), player));
            }
        }
    }
}
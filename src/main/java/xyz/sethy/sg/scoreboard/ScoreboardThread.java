package xyz.sethy.sg.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import xyz.sethy.sg.Main;
import xyz.sethy.sg.timers.Timer;
import xyz.sethy.sg.timers.TimerHandler;
import xyz.sethy.sg.timers.TimerType;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by sethm on 21/12/2016.
 */
public class ScoreboardThread implements xyz.sethy.threads.Thread
{
    private final DecimalFormat FORMAT = new DecimalFormat("0.0");
    private final TimerHandler timerHandler = Main.getInstance().getTimerHandler();

    @Override
    public void execute()
    {
        for(Player player : Main.getInstance().getPlayers())
        {
            Scoreboard scoreboard = Main.getInstance().getScoreboardHandler().getScoreboard(player);
            if(scoreboard == null)
                continue;

            if(timerHandler.getActiveTimers(player) == null)
            {
                timerHandler.getTimers().offer(player, new LinkedList<>());
                scoreboard.update();
                continue;
            }

            scoreboard.clear();
            scoreboard.add(translateString("&7&m-----------"), translateString("&7&m-----------"));
            scoreboard.add(translateString(Main.getInstance().getGameState().getDisplayName()), "");
            scoreboard.add(translateString("&9&lPlayers:"), Main.getInstance().getOnline().get() + "");


            if(timerHandler.hasTimer(player, TimerType.PVP_TIMER))
            {
                Timer timer = timerHandler.getTimerByType(player, TimerType.PVP_TIMER);
                if(timer != null)
                    scoreboard.add(translateString(TimerType.PVP_TIMER.getScore()), format(timer.getTime()));
            }
            if(timerHandler.hasTimer(player, TimerType.TIME))
            {
                Timer timer = timerHandler.getTimerByType(player, TimerType.TIME);
                if(timer != null)
                    scoreboard.add(translateString(TimerType.TIME.getScore()), format(timer.getTime()));
            }
            if(timerHandler.hasTimer(player, TimerType.ENDERPEARL))
            {
                Timer timer = timerHandler.getTimerByType(player, TimerType.ENDERPEARL);
                if(timer != null)
                    scoreboard.add(translateString(TimerType.ENDERPEARL.getScore()), format(timer.getTime()));
            }
            if(timerHandler.hasTimer(player, TimerType.BORDER))
            {
                Timer timer = timerHandler.getTimerByType(player, TimerType.BORDER);
                if(timer != null)
                    scoreboard.add(translateString(TimerType.BORDER.getScore()), Main.getInstance().getBORDER_SIZE() + "(" + format(timerHandler.getTimeUntilBorder()) + ")");
            }
            if(timerHandler.hasTimer(player, TimerType.FEAST))
            {
                Timer timer = timerHandler.getTimerByType(player, TimerType.FEAST);
                if(timer != null)
                    scoreboard.add(translateString(TimerType.FEAST.getScore()), Main.getInstance().getBORDER_SIZE() + "(" + format(timerHandler.getTimeUntilBorder()) + ")");
            }
            scoreboard.add(translateString("&7&m-----------"), translateString("&7&m-----------"));
            scoreboard.update();
        }
    }

    private String format(long millisecond)
    {
        return FORMAT.format(millisecond / 1000.0D);
    }

    private String translateString(String string)
    {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}

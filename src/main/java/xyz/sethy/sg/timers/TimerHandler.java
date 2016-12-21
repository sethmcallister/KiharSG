package xyz.sethy.sg.timers;

import org.bukkit.entity.Player;
import xyz.sethy.collections.buffer.Buffer;
import xyz.sethy.collections.buffer.BufferCollection;

import java.sql.Time;
import java.util.LinkedList;
import java.util.TimerTask;

/**
 * Created by sethm on 21/12/2016.
 */
public class TimerHandler
{
    private final Buffer<Player, LinkedList<Timer>> timers;
    private long timeUntilBorder;

    public TimerHandler()
    {
        this.timers = new BufferCollection<>(10000);
    }

    public LinkedList<Timer> getActiveTimers(Player player)
    {
        LinkedList<Timer> activeTimers = new LinkedList<>();
        if(timers.get(player) == null)
        {
            return activeTimers;
        }
        for(Timer timer : timers.get(player))
        {
            if(timer.getTime() > 0)
            {
                activeTimers.add(timer);
            }
        }
        this.timers.offer(player, activeTimers);
        return activeTimers;
    }

    public void addTimer(Timer timer, Player player)
    {
        LinkedList<Timer> currentActiveTimers = new LinkedList<>();
        currentActiveTimers.addAll(getActiveTimers(player));
        currentActiveTimers.add(timer);
        this.timers.offer(player, currentActiveTimers);
    }

    public Timer getTimerByType(Player player, TimerType timerType)
    {
        LinkedList<Timer> activeTimers = this.getActiveTimers(player);
        for(Timer timer : activeTimers)
        {
            if(timer.getTimerType().equals(timerType))
            {
                return timer;
            }
        }
        return null;
    }

    public boolean hasTimer(Player player, TimerType timerType)
    {
        LinkedList<Timer> activeTimers = this.getActiveTimers(player);
        for(Timer timer : activeTimers)
        {
            if(timer.getTimerType().equals(timerType))
            {
                return true;
            }
        }
        return false;
    }

    public long getTimeUntilBorder()
    {
        return timeUntilBorder;
    }

    public void setTimeUntilBorder(long timeUntilBorder)
    {
        this.timeUntilBorder = timeUntilBorder;
    }

    public Buffer<Player, LinkedList<Timer>> getTimers()
    {
        return timers;
    }
}

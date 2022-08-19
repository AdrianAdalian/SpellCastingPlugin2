package com.perceus.spellcasting2;

import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.utils.PrintUtils;

/**
 * @author Laeven (Zack)
 * @since 0.5.0
 */
public abstract class BukkitClock
{
    private boolean enabled = false;        // If the bukkit runnable clock is enabled
    private int continueAttempts = 5;        // Number of attempts to re-run the clock cycle if it fails
    private int attemptNum = 1;                // Attempt number of running the clock cycle
    private long interval;                    // The interval time in seconds before the next clock cycle
    private BukkitTask clock;
    
    /**
     * Creates a new clock
     * @param clockInterval The interval in seconds or ticks between each execution
     * @param isInSeconds If the interval is in seconds rather than ticks. TRUE = in seconds, FALSE = in ticks
     */
    public BukkitClock(long interval,boolean isInSeconds)
    {
        this.interval = isInSeconds ? (20 * interval) : interval;
    }
    
    /**
     * Creates a new clock
     * @param clockInterval clockSpeed The interval in seconds between each clock cycle
     * @param continueAttempts The number of attempts the clock will try to re-run a cycle that fails
     */
    public BukkitClock(long interval,boolean isInSeconds,int continueAttempts)
    {
        this.interval = isInSeconds ? (20 * interval) : interval;
        this.continueAttempts = continueAttempts;
    }
    
    /**
     * Starts the muting listener
     */
    private void runClock()
    {        
        clock = new BukkitRunnable()
        {
            @Override
            public void run() 
            {
                if(!enabled)
                {
                    cancel();
                }
                
                if(attemptNum > continueAttempts)
                {
                    cancel();
                    PrintUtils.log("Clock was canceled due to failing > 5 times");
                    
                }
                
                try
                {
                    executeCycle();
                    attemptNum = 1;
                }
                catch(Exception e)
                {
                    PrintUtils.log("Clock tripped and threw an exception!");
                    e.printStackTrace();
                    attemptNum++;
                }
            }
        }.runTaskTimer(Eden.getInstance(),0L,interval);
    }
    
    public abstract void executeCycle() throws Exception;
    
    public void start()
    {
        if(this.enabled)
        {
            PrintUtils.log("Clock has already been started. It cannot be started again!");
            return;
        }
        
        this.enabled = true;
        runClock();
    }
    
    public void stop()
    {
        if(!this.enabled)
        {
            PrintUtils.log("Clock cannot be stopped. It is already off!");
            return;
        }
        
        this.enabled = false;
        clock.cancel();
    }
    
    public boolean isEnabled()
    {
        return enabled;
    }
}
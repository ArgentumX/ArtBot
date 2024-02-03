package com.shatun.autoartbot.tasks;

import com.shatun.autoartbot.tasks.interaces.ITimer;

import java.util.ArrayList;
import java.util.List;

public class TickTimer implements ITimer {
    private static List<TickTimer> timers = new ArrayList<>();
    public static void handleTick(){
        for (TickTimer timer : timers){
            timer.currentTime++;
            if (timer.currentTime == timer.waitTime){
                timer.waitTimePassed = true;
                timer.active = false;
            }
        }
    }
    private int waitTime;
    private int currentTime;
    private boolean active = false;
    private boolean waitTimePassed = false;
    public TickTimer(){
        timers.add(this);
    }
    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public boolean isWaitTimePassed() {
        return waitTimePassed;
    }

    @Override
    public void wait(int waitTime) {
        if (waitTime < 1){
            throw new IllegalArgumentException("Cant wait time < 1");
        }
        currentTime = 0;
        this.waitTime = waitTime;
        active = true;
        waitTimePassed = false;
    }
}

package com.shatun.autoartbot.tasks;

import com.shatun.autoartbot.tasks.abstraction.ITimer;

import java.util.ArrayList;
import java.util.List;

public class TickTimer implements ITimer {
    private int waitTime;
    private int currentTime;
    private boolean active = false;
    private boolean waitTimePassed = false;
    private boolean resetOnFinish = false;
    public TickTimer(){
        reset();
    }
    @Override
    public boolean isActive() {
        return active;
    }
    @Override
    public boolean isTimePassed(boolean resetIfPassed) {
        if (waitTimePassed){
            if (resetIfPassed) {
                reset();
            }
            return true;
        }
        return false;
    }
    @Override
    public void wait(int waitTime, boolean resetOnFinish) {
        if (waitTime < 1){
            throw new IllegalArgumentException("Cant wait time < 1");
        }
        reset();
        this.active = true;
        this.waitTime = waitTime;
        this.resetOnFinish = resetOnFinish;
    }
    @Override
    public void reset() {
        currentTime = 0;
        active = false;
        waitTimePassed = false;
        resetOnFinish = false;
    }
    public void handleTimeStep(){
        if (active) {
            currentTime++;
            if (currentTime >= waitTime) {
                if (resetOnFinish){
                    reset();
                }
                else {
                    waitTimePassed = true;
                    active = false;
                }
            }
        }
    }
}

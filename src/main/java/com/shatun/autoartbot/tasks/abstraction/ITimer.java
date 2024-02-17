package com.shatun.autoartbot.tasks.abstraction;

public interface ITimer {
    boolean isActive();
    boolean isTimePassed(boolean resetIfPassed);
    void wait(int waitTime, boolean resetOnFinish);
    void reset();
    void handleTimeStep();
}

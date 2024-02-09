package com.shatun.autoartbot.tasks.abstraction;

public interface ITimer {
    boolean isActive();
    boolean isWaitTimePassed();
    void wait(int waitTime);
}

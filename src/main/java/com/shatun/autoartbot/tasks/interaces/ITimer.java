package com.shatun.autoartbot.tasks.interaces;

public interface ITimer {
    boolean isActive();
    boolean isWaitTimePassed();
    void wait(int waitTime);
}

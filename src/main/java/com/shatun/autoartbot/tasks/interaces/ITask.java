package com.shatun.autoartbot.tasks.interaces;

public interface ITask {
    void handleTick();
    void OnStart();
    void OnFinish();
    boolean isFinished();
}

package com.shatun.autoartbot.tasks.abstraction;

public interface ITask {
    void handleTick();
    void OnStart();
    void OnFinish();
    boolean isFinished();
}

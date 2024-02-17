package com.shatun.autoartbot.tasks.abstraction;

public interface ITask {
    void handleTick();
    void OnStart();
    void OnFinish();
    void Finish();
    boolean isFinished();
}

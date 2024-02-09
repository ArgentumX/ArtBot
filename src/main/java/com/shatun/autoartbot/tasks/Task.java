package com.shatun.autoartbot.tasks;

import com.shatun.autoartbot.tasks.TickTimer;
import com.shatun.autoartbot.tasks.abstraction.ITask;
import com.shatun.autoartbot.tasks.abstraction.ITimer;

public abstract class Task implements ITask {
    protected ITimer timer;
    protected int repeatCount;

    public Task(int repeatCount) {
        this.repeatCount = repeatCount;
        timer = new TickTimer();
    }
    @Override
    public void OnFinish() {
        if (repeatCount != -1){
            if (repeatCount < 1){
                throw new IllegalCallerException("Process was finished extra time");
            }
            else {
                repeatCount -= 1;
            }
        }
        refresh();
        if (needsStartAgain())
            OnStart();
    }
    @Override
    public void handleTick() {}

    @Override
    public void OnStart() {}

    public ITimer getTimer(){
        return timer;
    }
    public boolean needsStartAgain(){
        return isFinished() && (repeatCount == -1 || repeatCount > 0);
    }
    public abstract void refresh();
    public abstract void OnRestart();
}

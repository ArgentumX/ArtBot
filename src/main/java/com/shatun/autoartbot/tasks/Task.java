package com.shatun.autoartbot.tasks;

import com.shatun.autoartbot.tasks.TickTimer;
import com.shatun.autoartbot.tasks.abstraction.ITask;
import com.shatun.autoartbot.tasks.abstraction.ITimer;

public abstract class Task implements ITask {
    protected int repeatCount;

    public Task(int repeatCount) {
        this.repeatCount = repeatCount;
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
        if (needsStartAgain()) {
            refresh();
            OnStart();
        }
    }
    @Override
    public void handleTick() {

    }

    @Override
    public void OnStart() {}

    public boolean needsStartAgain(){
        return isFinished() && (repeatCount == -1 || repeatCount > 0);
    }
    public abstract void refresh();
    public abstract void OnRestart();
}

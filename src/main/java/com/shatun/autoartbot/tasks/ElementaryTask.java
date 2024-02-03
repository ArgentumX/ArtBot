package com.shatun.autoartbot.tasks;

import com.shatun.autoartbot.tasks.interaces.ITask;

public abstract class ElementaryTask implements ITask {
    protected boolean finished = false;
    protected TickTimer timer;
    @Override
    public boolean isFinished(){
        return finished;
    }
    @Override
    public void OnStart(){
        timer = new TickTimer();
    }
}

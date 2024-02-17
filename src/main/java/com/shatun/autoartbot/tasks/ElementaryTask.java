package com.shatun.autoartbot.tasks;

public abstract class ElementaryTask extends Task {
    protected boolean finished = false;

    public ElementaryTask(int repeatCount) {
        super(repeatCount);
    }

    @Override
    public void OnFinish() {
        super.OnFinish();
    }

    @Override
    public void handleTick() {
        super.handleTick();
        if (!finished){
            throw new IllegalCallerException("Cant handle tick after finish process");
        }
    }

    @Override
    public void OnStart() {
        super.OnStart();
    }

    @Override
    public boolean isFinished() {
        return finished;
    }

    @Override
    public void refresh() {
        finished = false;
    }

    @Override
    public void OnRestart() {}

    @Override
    public void Finish() {
        finished = true;
    }
}

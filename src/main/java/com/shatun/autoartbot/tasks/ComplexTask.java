package com.shatun.autoartbot.tasks;

import com.shatun.autoartbot.tasks.abstraction.ITask;

import java.util.List;

public class ComplexTask extends Task {

    private List<Task> taskList;
    private int currentTaskId = 0;

    public ComplexTask(int repeatCount, List<Task> taskList){
        super(repeatCount);
        if (taskList == null || taskList.isEmpty()){
            throw new IllegalArgumentException("Task list cant be empty or null");
        }
        this.taskList = taskList;
    }

    @Override
    public void handleTick() {
        if(isFinished()){
            return;
        }
        taskList.get(currentTaskId).handleTick();
        if(taskList.get(currentTaskId).isFinished() && currentTaskId < taskList.size()){
            currentTaskId++;
        }
    }

    @Override
    public void OnStart() {
        super.OnStart();
    }

    @Override
    public void OnFinish() {
        super.OnFinish();
    }

    @Override
    public void OnRestart() {
    }

    @Override
    public boolean isFinished() {
        return taskList.size() == currentTaskId;
    }


    @Override
    public void refresh() {
        if (!isFinished())
            throw new RuntimeException("It is not allowed to refresh when process has not finished");

        for (Task task : taskList){
            task.refresh();
        }
        currentTaskId = 0;
    }

    @Override
    public void Finish() {

    }
}

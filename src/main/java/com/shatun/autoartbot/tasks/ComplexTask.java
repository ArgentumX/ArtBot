package com.shatun.autoartbot.tasks;

import com.shatun.autoartbot.tasks.interaces.ITask;
import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.List;

public class ComplexTask implements ITask {

    private List<ITask> taskList;
    private int currentTaskId = 0;

    public ComplexTask(List<ITask> taskList){
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
    }

    @Override
    public void OnFinish() {
    }

    @Override
    public boolean isFinished() {
        return taskList.size() == currentTaskId;
    }
}

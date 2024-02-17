package com.shatun.autoartbot.tasks;

import com.shatun.autoartbot.tasks.elementary_task.clearing_area.ClearingAreaTask;
import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.List;

public class TaskConstructor {
    public static ComplexTask getClearingArtAreaTask(){
        List<Task> taskList = new ArrayList<>();
        taskList.add(new ClearingAreaTask(1));
        return new ComplexTask(1, taskList);
    }
}

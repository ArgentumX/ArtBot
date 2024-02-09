package com.shatun.autoartbot.tasks.complex_task;

import com.shatun.autoartbot.tasks.ComplexTask;
import com.shatun.autoartbot.tasks.Task;

import java.util.Collections;
import java.util.List;

public class FullCycleMapArtTask extends ComplexTask {

    public FullCycleMapArtTask(int repeatCount, List<Task> taskList) {
        super(repeatCount, taskList);
    }
}

package com.shatun.autoartbot;

import com.shatun.autoartbot.controllers.ComplexProcessController;
import com.shatun.autoartbot.controllers.InteractionController;
import com.shatun.autoartbot.controllers.InventoryController;
import com.shatun.autoartbot.controllers.interfaces.IComplexProcessController;
import com.shatun.autoartbot.controllers.interfaces.IInteractionController;
import com.shatun.autoartbot.controllers.interfaces.IInventoryController;
import com.shatun.autoartbot.memory.Memory;
import com.shatun.autoartbot.tasks.ComplexTask;
import com.shatun.autoartbot.tasks.Task;
import com.shatun.autoartbot.tasks.TaskConstructor;
import com.shatun.autoartbot.tasks.TickTimer;
import com.shatun.autoartbot.tasks.abstraction.ITimer;
import com.shatun.autoartbot.utils.PlayerUtils;

public class Bot {

    public IInteractionController interactionController;
    public IInventoryController inventoryController;
    public IComplexProcessController processController;
    private Memory memory;
    private ITimer timer;
    private ComplexTask task;
    private static Bot instance;
    public Bot(){
        interactionController = new InteractionController();
        inventoryController = new InventoryController();
        processController = new ComplexProcessController();
        timer = new TickTimer();
        memory = new Memory();
    }
    public static Bot getInstance(){
        if (instance == null){
            instance = new Bot();
        }
        return instance;
    }

    public Memory getMemory() {
        return memory;
    }

    public ITimer getTimer() {
        return timer;
    }
    public ComplexTask getTask() {
        return task;
    }
    public void clearArtArea(){
        PlayerUtils.send("Starts clearing area task");
        task = TaskConstructor.getClearingArtAreaTask();
    }
    public void transferItems(){
        PlayerUtils.send("Starts transfer items task");
        task = TaskConstructor.getStorageTransferTask();
    }

    public void buildArt(String schemName){
        PlayerUtils.send("Starts building art task");
        task = TaskConstructor.getBuildingArtTask(schemName);
    }
    public boolean isActive(){
        return task != null && !task.isFinished();
    }
    public void handleTick(){
        timer.handleTimeStep();
        if (!timer.isActive()){
            task.handleTick();
        }
    }
}

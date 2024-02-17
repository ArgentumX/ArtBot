package com.shatun.autoartbot;

import com.shatun.autoartbot.controllers.ComplexProcessController;
import com.shatun.autoartbot.controllers.InteractionController;
import com.shatun.autoartbot.controllers.InventoryController;
import com.shatun.autoartbot.controllers.interfaces.IComplexProcessController;
import com.shatun.autoartbot.controllers.interfaces.IInteractionController;
import com.shatun.autoartbot.controllers.interfaces.IInventoryController;
import com.shatun.autoartbot.memory.Memory;
import com.shatun.autoartbot.tasks.TickTimer;
import com.shatun.autoartbot.tasks.abstraction.ITimer;

public class Bot {

    public IInteractionController interactionController;
    public IInventoryController inventoryController;
    public IComplexProcessController processController;
    private Memory memory;
    private ITimer timer;
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
}

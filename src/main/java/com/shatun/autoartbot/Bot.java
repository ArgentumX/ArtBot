package com.shatun.autoartbot;

import com.shatun.autoartbot.controllers.ComplexProcessController;
import com.shatun.autoartbot.controllers.InteractionController;
import com.shatun.autoartbot.controllers.InventoryController;
import com.shatun.autoartbot.controllers.interfaces.IComplexProcessController;
import com.shatun.autoartbot.controllers.interfaces.IInteractionController;
import com.shatun.autoartbot.controllers.interfaces.IInventoryController;

public class Bot {

    public IInteractionController interactionController;
    public IInventoryController inventoryController;
    public IComplexProcessController processController;
    private static Bot instance;
    public Bot(){
        interactionController = new InteractionController();
        inventoryController = new InventoryController();
        processController = new ComplexProcessController();
    }
    public static Bot getInstance(){
        if (instance == null){
            instance = new Bot();
        }
        return instance;
    }


}

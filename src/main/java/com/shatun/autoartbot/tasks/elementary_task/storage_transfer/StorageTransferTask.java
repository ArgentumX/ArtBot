package com.shatun.autoartbot.tasks.elementary_task.storage_transfer;

import com.shatun.autoartbot.Bot;
import com.shatun.autoartbot.settings.BotSettings;
import com.shatun.autoartbot.tasks.ElementaryTask;
import com.shatun.autoartbot.utils.PlayerUtils;
import net.minecraft.world.phys.Vec3;

public class StorageTransferTask extends ElementaryTask {
    enum StorageTransferState {
        START,
        GOING_TO_DROP_CHEST,
        OPENED_DROP_CHEST,
        GOING_TO_STORAGE_CHEST,
        OPENED_STORAGE_CHEST
    }
    private StorageTransferState state;
    private int takingTransferItemsStage;
    private boolean tookAllCarpets;
    private int puttingItemsStage;
    private String currentCarpetId;
    public StorageTransferTask(int repeatCount) {
        super(repeatCount);
        takingTransferItemsStage = 0;
        puttingItemsStage = 0;
        tookAllCarpets = false;
        state = StorageTransferState.START;
    }

    @Override
    public void handleTick() {
        super.handleTick();
        switch (state){
            case START:
                goToDropChest();
                break;
            case GOING_TO_DROP_CHEST:
                if (!Bot.getInstance().processController.isGoing()){
                    openDropChest();
                }
                break;
            case OPENED_DROP_CHEST:
                takeTransferItems();
                break;
            case GOING_TO_STORAGE_CHEST:
                if(!Bot.getInstance().processController.isGoing()){
                    puttingItemsStage = 0;
                    openStorageChest();
                }
                break;
            case OPENED_STORAGE_CHEST:
                if (Bot.getInstance().inventoryController.inventoryContains(currentCarpetId)){
                    if (Bot.getInstance().inventoryController.getFreeContainerSpace() < 2){
                        puttingItemsStage++;
                        openStorageChest();
                    }
                    else {
                        Bot.getInstance().inventoryController.putOneStackInOpenedChest(currentCarpetId);
                        Bot.getInstance().getTimer().wait(BotSettings.putInventoryItemTimeout, true);
                    }
                }
                else {
                    Bot.getInstance().inventoryController.closeChest();
                    tryToTransferItems();
                }
                break;
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
    public void Finish() {
        super.Finish();
        tookAllCarpets = false;
        OnFinish();
    }

    private void tryToTransferItems(){
        String anyCarpetId = Bot.getInstance().inventoryController.getAnyCarpetIdInInventory();
        if (anyCarpetId == null){
            if (tookAllCarpets){
                Finish();
            }
            else {
                goToDropChest();
            }
        }
        else {
            goToStorageChest(anyCarpetId);
        }
    }
    private void openDropChest(){
        if (takingTransferItemsStage == 5){
            tookAllCarpets = true;
            takingTransferItemsStage = 0;
            tryToTransferItems();
            return;
        }
        Bot.getInstance().inventoryController.openChest(Bot.getInstance().getMemory().getDropChest().add(0, -1 * takingTransferItemsStage, takingTransferItemsStage));
        state = StorageTransferState.OPENED_DROP_CHEST;
    }

    private void goToDropChest(){
        Bot.getInstance().processController.goTo(Bot.getInstance().getMemory().getDropTransferPosition());
        state = StorageTransferState.GOING_TO_DROP_CHEST;
    }

    private void goToStorageChest(String carpetId){
        if(carpetId == null){
            throw new IllegalArgumentException("Carpet id cant be null");
        }
        currentCarpetId = carpetId;
        Vec3 chest = Bot.getInstance().getMemory().getLootChest(carpetId).add(0, 2, -2);
        Bot.getInstance().processController.goTo(chest.add(0, -2, 4));
        state = StorageTransferState.GOING_TO_STORAGE_CHEST;
    }

    private void takeTransferItems(){
        if (Bot.getInstance().inventoryController.takeCarpets()){
            takingTransferItemsStage++;
            openDropChest();
        }
        else{
            takingTransferItemsStage = 0;
            tryToTransferItems();
        }
    }
    public void openStorageChest(){
        if (puttingItemsStage == 3){
            puttingItemsStage = 0;
        }
        Bot.getInstance().inventoryController.openChest(Bot.getInstance().getMemory().getLootChest(currentCarpetId).add(0, puttingItemsStage, -1 * puttingItemsStage));
        state = StorageTransferState.OPENED_STORAGE_CHEST;
    }

}
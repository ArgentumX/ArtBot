package com.shatun.autoartbot.tasks.elementary_task.building_art;

import com.shatun.autoartbot.Bot;
import com.shatun.autoartbot.art.MapArt;
import com.shatun.autoartbot.settings.BotSettings;
import com.shatun.autoartbot.tasks.ElementaryTask;
import net.minecraft.world.phys.Vec3;

public class BuildingArtTask extends ElementaryTask {
    enum BuildingArtState {
        START,
        SUPPLYING,
        OPENED_LOOT_CONTAINER,
        BUILDING,
        DELIVERING,
        OPENED_DROP_CONTAINER,
        TRYING_SLEEP
    }
    private BuildingArtState state;
    private MapArt art;
    private int supplyingStage;
    private String[] resourcePortion;
    private boolean triedTwice;
    public BuildingArtTask(int repeatCount, MapArt art) {
        super(repeatCount);
        this.art = art;
        state = BuildingArtState.START;
        supplyingStage = -1;
        triedTwice = false;
    }

    @Override
    public void handleTick() {
        super.handleTick();
        switch (state){
            case START:
                deliveryItems();
                break;
            case SUPPLYING:
                if (!Bot.getInstance().processController.isGoing()){
                    Bot.getInstance().inventoryController.openChest(Bot.getInstance().getMemory().getLootChest(resourcePortion[supplyingStage]));
                    state = BuildingArtState.OPENED_LOOT_CONTAINER;
                }
                break;
            case OPENED_LOOT_CONTAINER:
                Bot.getInstance().inventoryController.takeOneItemStackFromContainer();
                Bot.getInstance().inventoryController.closeChest();
                if (supplyingStage == resourcePortion.length - 1){
                    supplyingStage = -1;
                    build();
                }
                else {
                    supplyingStage++;
                    supply();
                    Bot.getInstance().getTimer().wait(BotSettings.putInventoryItemTimeout, true);
                }
                break;
            case BUILDING:
                if (!Bot.getInstance().processController.isBuilding()){
                    if (!triedTwice){
                        build();
                        triedTwice = true;
                    }
                    else {
                        deliveryItems();
                        Bot.getInstance().processController.eat(true);
                        triedTwice = false;
                    }
                }
                break;
            case DELIVERING:
                if (!Bot.getInstance().processController.isGoing()){
                    Bot.getInstance().processController.eat(false);
                    Bot.getInstance().inventoryController.openChest(Bot.getInstance().getMemory().getDropChest());
                    state = BuildingArtState.OPENED_DROP_CONTAINER;
                }
                break;
            case OPENED_DROP_CONTAINER:
                if (!Bot.getInstance().inventoryController.isInventoryEmpty()){
                    Bot.getInstance().inventoryController.putOneStackInOpenedChest();
                    Bot.getInstance().getTimer().wait(BotSettings.putInventoryItemTimeout, true);
                }
                else {
                    Bot.getInstance().inventoryController.closeChest();
                    state = BuildingArtState.TRYING_SLEEP;
                }
                break;
            case TRYING_SLEEP:
                if (!Bot.getInstance().getTimer().isTimePassed(true)){
                    Bot.getInstance().getTimer().wait(BotSettings.sleepTime, false);
                    Bot.getInstance().interactionController.click(Bot.getInstance().getMemory().getDropChest().add(-1.5, 0, 0));
                } else {
                    if (art.allNeededResourcesTaken()){
                        OnFinish();
                    }
                    else {
                        supply();
                    }
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
        finished = true;
        super.OnFinish();
    }

    private void supply(){
        if (supplyingStage == -1){
            resourcePortion = art.takeResourcePortion();
            supplyingStage = 0;
        }
        Vec3 chest = Bot.getInstance().getMemory().getLootChest(resourcePortion[supplyingStage]);
        Bot.getInstance().processController.goTo(chest.add(0, 0, 2));
        state = BuildingArtState.SUPPLYING;
    }

    private void build(){
        Bot.getInstance().processController.build(art.getSchemName(), Bot.getInstance().getMemory().getPaintTopLeftCorner());
        state = BuildingArtState.BUILDING;
    }

    private void deliveryItems(){
        Bot.getInstance().processController.goTo(Bot.getInstance().getMemory().getDropChest().add(-1, 0, 0));
        state = BuildingArtState.DELIVERING;
    }

}

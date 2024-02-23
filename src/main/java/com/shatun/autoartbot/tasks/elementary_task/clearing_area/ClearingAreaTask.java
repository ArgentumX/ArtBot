package com.shatun.autoartbot.tasks.elementary_task.clearing_area;

import com.shatun.autoartbot.Bot;
import com.shatun.autoartbot.settings.BotSettings;
import com.shatun.autoartbot.tasks.ElementaryTask;
import com.shatun.autoartbot.utils.PlayerUtils;

public class ClearingAreaTask extends ElementaryTask {
    enum ClearingAreaState {
        START,
        BREAKING,
        COLLECTING,
        DELIVERING,
        OPENED_CONTAINER,
        TRYING_SLEEP
    }
    private int xBreakingStage;
    private int zBreakingStage;
    private ClearingAreaState state;
    public ClearingAreaTask(int repeatCount) {
        super(repeatCount);
        xBreakingStage = 0;
        zBreakingStage = 0;
        state = ClearingAreaState.START;
    }

    @Override
    public void handleTick() {
        super.handleTick();
        switch (state){
            case START:
                Bot.getInstance().processController.goTo(Bot.getInstance().getMemory().getDropChest().add(-1, 0, 0));
                state = ClearingAreaState.DELIVERING;
                break;
            case DELIVERING:
                if (!Bot.getInstance().processController.isEating()){
                    Bot.getInstance().processController.eat(true);
                }
                if (!Bot.getInstance().processController.isGoing()){
                    Bot.getInstance().processController.eat(false);
                    Bot.getInstance().inventoryController.openChest(Bot.getInstance().getMemory().getDropChest());
                    state = ClearingAreaState.OPENED_CONTAINER;
                }
                break;
            case OPENED_CONTAINER:
                if (!Bot.getInstance().inventoryController.isInventoryEmpty()){
                    Bot.getInstance().inventoryController.putOneStackInOpenedChest();
                    Bot.getInstance().getTimer().wait(BotSettings.putInventoryItemTimeout, true);
                }
                else {
                    Bot.getInstance().inventoryController.closeChest();
                    state = ClearingAreaState.TRYING_SLEEP;
                }
                break;
            case TRYING_SLEEP:
                if (!Bot.getInstance().getTimer().isTimePassed(true)){
                    Bot.getInstance().getTimer().wait(BotSettings.sleepTime, false);
                    Bot.getInstance().interactionController.click(Bot.getInstance().getMemory().getDropChest().add(-1.5, 0, 0));
                } else {
                    if (zBreakingStage > 7) {
                        Finish();
                    } else {
                        Bot.getInstance().processController.allowBreak(true);
                        Bot.getInstance().processController.clearArtSegment(Bot.getInstance().getMemory().getPaintTopLeftCorner(), xBreakingStage, zBreakingStage);
                        state = ClearingAreaState.BREAKING;
                    }
                }
                break;
            case BREAKING:
                if (!Bot.getInstance().processController.isClearingArtArea()) {
                    Bot.getInstance().processController.allowBreak(false);
                    xBreakingStage++;
                    if (xBreakingStage > 7) {
                        xBreakingStage = 0;
                        zBreakingStage++;
                    }
                    state = ClearingAreaState.COLLECTING;
                }
                break;
            case COLLECTING:
                if (!Bot.getInstance().getTimer().isTimePassed(true)) {
                    Bot.getInstance().getTimer().wait(BotSettings.collectItemsTimeout, false);
                    Bot.getInstance().processController.collectItems(true);
                }
                else {
                    Bot.getInstance().processController.collectItems(false);
                    if (xBreakingStage % 4 == 0) {
                        Bot.getInstance().processController.goTo(Bot.getInstance().getMemory().getDropChest().add(-1, 0, 0));
                        state = ClearingAreaState.DELIVERING;
                    } else {
                        Bot.getInstance().processController.clearArtSegment(Bot.getInstance().getMemory().getPaintTopLeftCorner(), xBreakingStage, zBreakingStage);
                        state = ClearingAreaState.BREAKING;
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
        super.OnFinish();
        PlayerUtils.send("Finished clearing area task");
    }

    @Override
    public void Finish() {
        super.Finish();
        OnFinish();
    }
}

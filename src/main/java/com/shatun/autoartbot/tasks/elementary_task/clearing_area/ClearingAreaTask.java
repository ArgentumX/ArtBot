package com.shatun.autoartbot.tasks.elementary_task.clearing_area;

import com.shatun.autoartbot.Bot;
import com.shatun.autoartbot.tasks.ElementaryTask;

public class ClearingAreaTask extends ElementaryTask {
    enum ClearingAreaState {
        START,
        BREAKING,
        COLLECTING,
        DELIVERING,
        OPENED_CONTAINER
    }
    private int xBreakingStage;
    private int zBreakingStage;
    private ClearingAreaState state;
    public ClearingAreaTask(int repeatCount) {
        super(repeatCount);
        xBreakingStage = 0;
        zBreakingStage = 0;
        state = ClearingAreaState.DELIVERING;
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
                if (!Bot.getInstance().processController.isGoing()){
                    Bot.getInstance().inventoryController.openChest(Bot.getInstance().getMemory().getDropChest());
                    state = ClearingAreaState.OPENED_CONTAINER;
                }
                break;
            case OPENED_CONTAINER:
                if (!Bot.getInstance().inventoryController.isInventoryEmpty()){
                    Bot.getInstance().inventoryController.putOneStackInOpenedChest();
                    Bot.getInstance().getTimer().wait(5, true);
                }
                else {
                    if (zBreakingStage > 7){
                        Finish();
                    }
                    else {
                        Bot.getInstance().processController.clearArtSegment(Bot.getInstance().getMemory().getPaintTopLeftCorner(), xBreakingStage, zBreakingStage);
                        state = ClearingAreaState.BREAKING;
                    }
                }
                break;
            case BREAKING:
                if (!Bot.getInstance().processController.isClearingArtArea()) {
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
                    Bot.getInstance().getTimer().wait(480, false);
                    Bot.getInstance().processController.collectItems(true);
                }
                else {
                    Bot.getInstance().processController.collectItems(false);
                    if (xBreakingStage % 4 == 0) {
                        Bot.getInstance().processController.goTo(Bot.getInstance().getMemory().getDropChest().add(-1, 0, 0));
                        state = ClearingAreaState.DELIVERING;
                    } else {
                        Bot.getInstance().processController.clearArtSegment(Bot.getInstance().getMemory().getPaintTopLeftCorner(), xBreakingStage, zBreakingStage);
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
    }

    @Override
    public void Finish() {
        super.Finish();
        OnFinish();
    }
}

package com.shatun.autoartbot.controllers;

import com.shatun.autoartbot.controllers.interfaces.IInteractionController;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.network.protocol.game.ServerboundUseItemOnPacket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class InteractionController implements IInteractionController {

    public void click(Vec3 posDirection){
        Minecraft.getInstance().player.lookAt(EntityAnchorArgument.Anchor.EYES, posDirection);
        BlockHitResult hit = (BlockHitResult) Minecraft.getInstance().player.pick(20.0D, 0.0F, false);
        Minecraft.getInstance().getConnection().send(new ServerboundUseItemOnPacket(InteractionHand.MAIN_HAND, hit, 0));

    }

}

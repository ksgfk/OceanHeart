package com.github.ksgfk.oceanheart.commands;

import com.github.ksgfk.oceanheart.OceanHeart;
import com.google.common.collect.Lists;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

import java.util.List;

public class CommandDimensionTeleport extends CommandBase {
    private final List<String> aliases = Lists.newArrayList(OceanHeart.MODID,"tp","tpdim","tpdimension","teleportdimension","teleport");


    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return null;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

    }
}

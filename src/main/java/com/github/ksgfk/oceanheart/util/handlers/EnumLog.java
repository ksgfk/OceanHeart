package com.github.ksgfk.oceanheart.util.handlers;

import net.minecraft.block.BlockPlanks;
import net.minecraft.util.IStringSerializable;

import java.util.Locale;

public enum  EnumLog implements IStringSerializable {
    YGGDRASILL{
        public BlockPlanks.EnumType supplyPlankColor(){
            return BlockPlanks.EnumType.OAK;
        }
    }
    //, HAB
    ;

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }
}

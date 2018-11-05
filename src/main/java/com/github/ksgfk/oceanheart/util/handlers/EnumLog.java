package com.github.ksgfk.oceanheart.util.handlers;

import net.minecraft.block.BlockPlanks;
import net.minecraft.util.IStringSerializable;

import java.util.Locale;

public enum EnumLog implements IStringSerializable {
    YGGDRASILL(0) {
        public BlockPlanks.EnumType supplyPlankColor() {
            return BlockPlanks.EnumType.OAK;
        }
    }
    , HAB(1)
    ;

    private int meta;
    private static final EnumLog[] META_LOOKUP = new EnumLog[values().length];

    EnumLog(int meta) {
        this.meta = meta;
    }

    public int getMetadata() {
        return this.meta;
    }

    public static EnumLog byMetadata(int meta) {
        if (meta < 0 || meta >= META_LOOKUP.length) {
            meta = 0;
        }

        return META_LOOKUP[meta];
    }

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }

    static {
        for (EnumLog logs$enumtype : values()) {
            META_LOOKUP[logs$enumtype.getMetadata()] = logs$enumtype;
        }
    }
}

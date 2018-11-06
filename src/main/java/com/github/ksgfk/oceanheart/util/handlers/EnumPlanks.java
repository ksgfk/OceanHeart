package com.github.ksgfk.oceanheart.util.handlers;

import net.minecraft.util.IStringSerializable;

import java.util.Locale;

public enum EnumPlanks implements IStringSerializable {
    YGGDRASILL(0, "yggdrasill");

    private int meta;
    private String name;
    private static final EnumPlanks[] META_LOOKUP = new EnumPlanks[values().length];

    EnumPlanks(int meta, String name) {
        this.meta = meta;
        this.name = name;
    }

    public int getMetadata() {
        return this.meta;
    }

    public static EnumPlanks byMetadata(int meta) {
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
        for (EnumPlanks planks$enumtype : values()) {
            META_LOOKUP[planks$enumtype.getMetadata()] = planks$enumtype;
        }
    }

    @Override
    public String toString() {
        return this.name;
    }
}

package com.github.ksgfk.oceanheart.util.handlers;

import net.minecraft.util.IStringSerializable;

import java.util.Locale;

public enum EnumBlocks implements IStringSerializable {
    BASE(0, "base"),
    BRIGHTBLUE(1, "brightblue"),
    BLACK(2, "black"),
    SPECIALBD(3,"specialbd"),
    MACHINE_3(4,"machine_3"),
    MACHINE_1(5,"machine_1"),
    MACHINE_2(6,"machine_2"),
    STONE_1(7,"stone_1");

    private int meta;
    private String name;
    private static final EnumBlocks[] META_LOOKUP = new EnumBlocks[values().length];

    EnumBlocks(int meta, String name) {
        this.meta = meta;
        this.name = name;
    }

    public int getMetadata() {
        return this.meta;
    }

    public static EnumBlocks byMetadata(int meta) {
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
        for (EnumBlocks enumBlocks : values()) {
            META_LOOKUP[enumBlocks.getMetadata()] = enumBlocks;
        }
    }

    @Override
    public String toString() {
        return this.name;
    }

    public int getMeta() {
        return this.meta;
    }
}

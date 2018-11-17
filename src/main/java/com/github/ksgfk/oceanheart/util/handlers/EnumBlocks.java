package com.github.ksgfk.oceanheart.util.handlers;

import net.minecraft.util.IStringSerializable;

import java.util.Locale;

public enum EnumBlocks implements IStringSerializable {
    BASE(0, "base"),
    BRIGHTBLUE(1, "brightblue"),
    BLACK(2, "black"),
    SPECIALBD(3,"specialbd");

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

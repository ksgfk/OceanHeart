package com.github.ksgfk.oceanheart.util.handlers;

import net.minecraft.util.IStringSerializable;

import java.util.Locale;

public enum EnumLeaves implements IStringSerializable {
    YGGDRASILL(0,"yggdrasill");

    private int meta;
    private String name;
    private static final EnumLeaves[] META_LOOKUP = new EnumLeaves[values().length];

    EnumLeaves(int meta,String name) {
        this.meta = meta;
        this.name = name;
    }

    public int getMetadata() {
        return this.meta;
    }

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }

    static {
        for (EnumLeaves leaves$enumtype : values()) {
            META_LOOKUP[leaves$enumtype.getMetadata()] = leaves$enumtype;
        }
    }

    public static EnumLeaves byMetadata(int meta) {
        if (meta < 0 || meta >= META_LOOKUP.length) {
            meta = 0;
        }

        return META_LOOKUP[meta];
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.github.ksgfk.oceanheart.util.handlers;

import net.minecraft.util.IStringSerializable;

import java.util.Locale;

public enum EnumLeaves implements IStringSerializable {
    YGGDRASILL(0), HAB(1);

    private int meta;
    private static final EnumLeaves[] META_LOOKUP = new EnumLeaves[values().length];

    EnumLeaves(int meta) {
        this.meta = meta;
    }

    public int getMetadata() {
        return this.meta;
    }

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }
}

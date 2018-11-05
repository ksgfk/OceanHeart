package com.github.ksgfk.oceanheart.util.handlers;

import net.minecraft.util.IStringSerializable;

import java.util.Locale;

public enum EnumSapling implements IStringSerializable {
    YGGDRASILL(0)
    //,HAB(1)
    ;

    private int meta;
    private static final EnumSapling[] META_LOOKUP = new EnumSapling[values().length];

    EnumSapling(int meta) {
        this.meta = meta;
    }

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }

    public int getMetadata() {
        return meta;
    }

    public static EnumSapling byMetadata(int meta) {
        if (meta < 0 || meta >= META_LOOKUP.length) {
            meta = 0;
        }

        return META_LOOKUP[meta];
    }

    static {
        for (EnumSapling sapling$enumtype : values()) {
            META_LOOKUP[sapling$enumtype.getMetadata()] = sapling$enumtype;
        }
    }
}

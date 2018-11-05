package com.github.ksgfk.oceanheart.util.handlers;

import net.minecraft.util.IStringSerializable;

import java.util.Locale;

public enum EnumLeaves implements IStringSerializable {
    YGGDRASILL
    //, HAB
    ;

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }
}

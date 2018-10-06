package com.github.ksgfk.oceanheart.util.handlers;

import net.minecraft.util.IStringSerializable;

public class EnumHandlers {
    public static enum EnumType implements IStringSerializable {
        CRYSTAL_NATURE(0, "nature_crystal");
        //GOLD_STRANGE(1, "gold_strange");

        private static final EnumType[] META_LOOKUP = new EnumType[values().length];
        private final int meta;
        private final String name, unlocializedName;

        private EnumType(int meta, String name) {
            this(meta, name, name);
        }

        private EnumType(int meta, String name, String unlocializedName) {
            this.meta = meta;
            this.name = name;
            this.unlocializedName = unlocializedName;
        }

        @Override
        public String getName() {
            return this.name;
        }

        public int getMeta() {
            return this.meta;
        }

        public String getUnlocializedName() {
            return this.unlocializedName;
        }

        @Override
        public String toString() {
            return this.name;
        }

        public static EnumType byMetadata(int meta) {
            return META_LOOKUP[meta];
        }

        static {
            for (EnumType enumType : values()) {
                META_LOOKUP[enumType.getMeta()] = enumType;
            }
        }
    }
}

package com.github.ksgfk.oceanheart.common;

import com.github.ksgfk.oceanheart.init.ItemInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabsOceanHeart extends CreativeTabs {
    public static CreativeTabsOceanHeart tabsOceanHeart = new CreativeTabsOceanHeart();

    public CreativeTabsOceanHeart() {
        super("oceanheart");//Set creative tab's name
    }

    public ItemStack getTabIconItem() {
        return new ItemStack(ItemInit.INGOT_CRYSTAL_NATURE);//Set creative tab's icon
    }
}

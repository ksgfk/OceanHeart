package com.github.ksgfk.oceanheart.objects.items;

import com.github.ksgfk.oceanheart.OceanHeart;
import com.github.ksgfk.oceanheart.common.CreativeTabsOceanHeart;
import com.github.ksgfk.oceanheart.init.ItemInit;
import com.github.ksgfk.oceanheart.util.IHasMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemBase extends Item implements IHasMod {
    public ItemBase(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabsOceanHeart.tabsOceanHeart);

        ItemInit.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        OceanHeart.proxy.registerItemRenderer(this, 0, "inventory");
    }
}

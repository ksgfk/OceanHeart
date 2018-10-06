package com.github.ksgfk.oceanheart.objects.tools;

import com.github.ksgfk.oceanheart.OceanHeart;
import com.github.ksgfk.oceanheart.common.CreativeTabsOceanHeart;
import com.github.ksgfk.oceanheart.init.ItemInit;
import com.github.ksgfk.oceanheart.util.IHasMod;
import net.minecraft.item.ItemHoe;

public class ToolHoe extends ItemHoe implements IHasMod {
    public ToolHoe(String name, ToolMaterial materual) {
        super(materual);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabsOceanHeart.tabsOceanHeart);

        ItemInit.ITEMS.add(this);
    }

    public void registerModels() {
        OceanHeart.proxy.registerItemRenderer(this, 0, "inventory");
    }
}

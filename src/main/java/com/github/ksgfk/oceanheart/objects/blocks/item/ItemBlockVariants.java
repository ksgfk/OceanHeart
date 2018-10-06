package com.github.ksgfk.oceanheart.objects.blocks.item;

import com.github.ksgfk.oceanheart.util.IMetaName;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockVariants extends ItemBlock {
    public ItemBlockVariants(Block block) {
        super(block);
        setHasSubtypes(true);
        setMaxDamage(0);

    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }


}

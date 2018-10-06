package com.github.ksgfk.oceanheart.objects.items;

import com.github.ksgfk.oceanheart.init.BlockInit;
import com.github.ksgfk.oceanheart.init.ItemInit;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Smelt {
    public static void registerSmelting() {
        GameRegistry.addSmelting(BlockInit.ORE_OVERWORLD, new ItemStack(ItemInit.INGOT_CRYSTAL_NATURE), 2.0F);
        GameRegistry.addSmelting(BlockInit.ORE_NETHER, new ItemStack(ItemInit.INGOT_CRYSTAL_NATURE), 4.0F);
        GameRegistry.addSmelting(BlockInit.ORE_END, new ItemStack(ItemInit.INGOT_CRYSTAL_NATURE), 1.0F);
        GameRegistry.addSmelting(Blocks.SAPLING, new ItemStack(ItemInit.POWDER_CRYSTAL_NATURE_SMALL), 1.0F);
        GameRegistry.addSmelting(new ItemStack(ItemInit.POWDER_CRYSTAL_NATURE), new ItemStack((ItemInit.INGOT_CRYSTAL_NATURE_DETERIORATION)), 1.0F);
    }
}

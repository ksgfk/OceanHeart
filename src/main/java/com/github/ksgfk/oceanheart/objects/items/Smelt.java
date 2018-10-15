package com.github.ksgfk.oceanheart.objects.items;

import com.github.ksgfk.oceanheart.init.BlockInit;
import com.github.ksgfk.oceanheart.init.ItemInit;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

/*
 *
 * 矿石烧制
 */
public class Smelt {
    public static void registerSmelting() {
        //自然之力系列
        GameRegistry.addSmelting(BlockInit.ORE_OVERWORLD, new ItemStack(ItemInit.INGOT_CRYSTAL_NATURE), 2.0F);
        GameRegistry.addSmelting(BlockInit.ORE_NETHER, new ItemStack(ItemInit.INGOT_CRYSTAL_NATURE), 4.0F);
        GameRegistry.addSmelting(BlockInit.ORE_END, new ItemStack(ItemInit.INGOT_CRYSTAL_NATURE), 1.0F);
        //劣质自然之力
        GameRegistry.addSmelting(Blocks.SAPLING, new ItemStack(ItemInit.POWDER_CRYSTAL_NATURE_SMALL), 1.0F);
        GameRegistry.addSmelting(new ItemStack(ItemInit.POWDER_CRYSTAL_NATURE), new ItemStack((ItemInit.INGOT_CRYSTAL_NATURE_DETERIORATION)), 1.0F);
        //熔岩之魂
        GameRegistry.addSmelting(BlockInit.ORE_LEVE_SOUL,new ItemStack(ItemInit.INGOT_LEVE_SOUL),1.0F);
        //海洋之魂
        GameRegistry.addSmelting(BlockInit.ORE_OCEAN_SOUL,new ItemStack(ItemInit.INGOT_OCEAN_SOUL_UNFORGE),1.0F);
        GameRegistry.addSmelting(new ItemStack(ItemInit.INGOT_OCEAN_SOUL_UNFORGE),new ItemStack(ItemInit.INGOT_OCEAN_SOUL),1.0F);
        //金色传说
        GameRegistry.addSmelting(BlockInit.ORE_GOLD_STRANGE_OVERWORLD,new ItemStack(ItemInit.INGOT_LEGEND),1.0F);
        GameRegistry.addSmelting(BlockInit.ORE_GOLD_STRANGE_END,new ItemStack(ItemInit.INGOT_LEGEND_END),1.0F);
    }
}

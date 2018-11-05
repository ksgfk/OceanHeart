package com.github.ksgfk.oceanheart.util;

import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

/**
 * 需要使用Meta时调用
 */
public interface IHaveMeta {
    public IBlockState getStateFromMeta(int meta);

    public int getMetaFromState(IBlockState state);

    public int damageDropped(IBlockState state);

    /**
     * 获取Enum中数据并在创造栏里显示
     *
     * @param itemIn 大概是创造栏的意思
     * @param items  放入创造的物品
     */
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items);
}

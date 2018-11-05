package com.github.ksgfk.oceanheart.util;

import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;

/**
 * 需要使用Meta时调用
 */
public interface IHaveMeta {
    public IBlockState getStateFromMeta(int meta);
    public int getMetaFromState(IBlockState state) ;
    public int damageDropped(IBlockState state) ;
    public BlockStateContainer createBlockState();
}

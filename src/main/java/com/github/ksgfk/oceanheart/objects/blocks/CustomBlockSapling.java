package com.github.ksgfk.oceanheart.objects.blocks;

import com.github.ksgfk.oceanheart.OceanHeart;
import com.github.ksgfk.oceanheart.common.CreativeTabsOceanHeart;
import com.github.ksgfk.oceanheart.init.BlockInit;
import com.github.ksgfk.oceanheart.init.ItemInit;
import com.github.ksgfk.oceanheart.objects.blocks.item.ItemBlockVariants;
import com.github.ksgfk.oceanheart.util.IHasMod;
import com.github.ksgfk.oceanheart.util.IMetaName;
import com.github.ksgfk.oceanheart.util.handlers.EnumHandlers;
import com.google.common.base.Predicate;
import net.minecraft.block.*;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import javax.annotation.Nullable;
import java.util.Random;

public class CustomBlockSapling extends BlockBush implements IGrowable, IMetaName, IHasMod {
    public static final PropertyEnum<EnumHandlers.EnumType> VARIANT = PropertyEnum.<EnumHandlers.EnumType>create("variant", EnumHandlers.EnumType.class, new Predicate<EnumHandlers.EnumType>() {
        @Override
        public boolean apply(@Nullable EnumHandlers.EnumType input) {
            return input.getMeta() < 2;
        }
    });
    private String name;

    public CustomBlockSapling(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumHandlers.EnumType.CRYSTAL_NATURE));
        setCreativeTab(CreativeTabsOceanHeart.tabsOceanHeart);

        this.name = name;
        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlockVariants(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public void registerModels() {
        for (int a = 0; a < EnumHandlers.EnumType.values().length; a++) {
            OceanHeart.proxy.registerVariantRenderer(Item.getItemFromBlock(this), a, "sapling_" + EnumHandlers.EnumType.values()[a].getName(), "inventory");
        }
    }

    @Override
    public String getSpecialName(ItemStack stack) {
        return EnumHandlers.EnumType.values()[stack.getItemDamage()].getName();
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        return false;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        return false;
    }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {

    }
}

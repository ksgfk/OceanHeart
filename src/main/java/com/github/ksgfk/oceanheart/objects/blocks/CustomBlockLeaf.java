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
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class CustomBlockLeaf extends BlockLeaves implements IMetaName, IHasMod {
    public static final PropertyEnum<EnumHandlers.EnumType> VARIANT = PropertyEnum.<EnumHandlers.EnumType>create("variant", EnumHandlers.EnumType.class, new Predicate<EnumHandlers.EnumType>() {
        @Override
        public boolean apply(@Nullable EnumHandlers.EnumType input) {
            return input.getMeta() < 2;
        }
    });
    private String name;

    public CustomBlockLeaf(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setSoundType(SoundType.WOOD);
        setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumHandlers.EnumType.CRYSTAL_NATURE));
        setCreativeTab(CreativeTabsOceanHeart.tabsOceanHeart);

        this.name = name;
        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlockVariants(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public void registerModels() {
        for (int a = 0; a < EnumHandlers.EnumType.values().length; a++) {
            OceanHeart.proxy.registerVariantRenderer(Item.getItemFromBlock(this), a, "ore_" + EnumHandlers.EnumType.values()[a].getName(), "inventory");
        }
    }

    @Override
    public String getSpecialName(ItemStack stack) {
        return EnumHandlers.EnumType.values()[stack.getItemDamage()].getName();
    }

    @Override
    public BlockPlanks.EnumType getWoodType(int meta) {
        return null;
    }

    @Nonnull
    @Override
    public List<ItemStack> onSheared(@Nonnull ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        return null;
    }
}

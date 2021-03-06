package com.github.ksgfk.oceanheart.objects.blocks;

import com.github.ksgfk.oceanheart.OceanHeart;
import com.github.ksgfk.oceanheart.common.CreativeTabsOceanHeart;
import com.github.ksgfk.oceanheart.init.BlockInit;
import com.github.ksgfk.oceanheart.init.ItemInit;
import com.github.ksgfk.oceanheart.objects.blocks.item.ItemBlockVariants;
import com.github.ksgfk.oceanheart.util.IHasMod;
import com.github.ksgfk.oceanheart.util.IHaveMeta;
import com.github.ksgfk.oceanheart.util.IMetaName;
import com.github.ksgfk.oceanheart.util.handlers.EnumBlocks;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;

import java.util.Objects;

public class BlockSpacecraft extends BlockRotatedPillar implements IHasMod, IHaveMeta, IMetaName {
    public static final PropertyEnum<EnumBlocks> VARIANT = PropertyEnum.create("variant", EnumBlocks.class);
    public static final PropertyEnum<EnumFacing.Axis> AXIS = PropertyEnum.<EnumFacing.Axis>create("axis", EnumFacing.Axis.class);

    public BlockSpacecraft(String name, Material material) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setSoundType(SoundType.STONE);
        this.setDefaultState(this.getDefaultState().withProperty(AXIS, EnumFacing.Axis.Y));
        setCreativeTab(CreativeTabsOceanHeart.tabsOceanHeart);

        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlockVariants(this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));
    }

    @Override
    public int damageDropped(IBlockState state) {
        return state.getValue(VARIANT).getMetadata();
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        for (EnumBlocks enumBlocks : EnumBlocks.values()) {
            items.add(new ItemStack(this, 1, enumBlocks.getMetadata()));
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        //if (meta <= 4 || meta >= 8) {
        /*
        IBlockState iblockstate = this.getDefaultState().withProperty(VARIANT, EnumBlocks.values()[meta & 4]);
        EnumFacing.Axis enumfacing$axis = EnumFacing.Axis.Y;
        int i = meta & 12;

        if (i == 4) {
            enumfacing$axis = EnumFacing.Axis.X;
        } else if (i == 8) {
            enumfacing$axis = EnumFacing.Axis.Z;
        }

        return iblockstate.withProperty(AXIS, enumfacing$axis).withProperty(VARIANT, EnumBlocks.values()[meta & 4]);
        */
        //} else {
        //return this.getDefaultState().withProperty(VARIANT, EnumBlocks.byMetadata(meta));
        //}
        IBlockState iblockstate = this.getDefaultState().withProperty(VARIANT, EnumBlocks.values()[meta]);
        return iblockstate;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = state.getValue(VARIANT).getMeta();

        /*
        EnumFacing.Axis enumfacing$axis = (EnumFacing.Axis)state.getValue(AXIS);

        if (enumfacing$axis == EnumFacing.Axis.X)
        {
            i |= 4;
        }
        else if (enumfacing$axis == EnumFacing.Axis.Z)
        {
            i |= 8;
        }
        */
        return i;
    }

    @Override
    public BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, VARIANT, AXIS);
    }

    @Override
    public void registerModels() {
        for (int a = 0; a < EnumBlocks.values().length; a++) {
            OceanHeart.proxy.registerVariantRenderer(Item.getItemFromBlock(this), a, "spacecraft_block_" + EnumBlocks.values()[a].getName(), "inventory");
        }
    }

    @Override
    public String getSpecialName(ItemStack stack) {
        return EnumBlocks.values()[stack.getItemDamage()].getName();
    }
}

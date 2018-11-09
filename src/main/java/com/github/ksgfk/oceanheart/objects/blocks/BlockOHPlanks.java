package com.github.ksgfk.oceanheart.objects.blocks;

import com.github.ksgfk.oceanheart.OceanHeart;
import com.github.ksgfk.oceanheart.common.CreativeTabsOceanHeart;
import com.github.ksgfk.oceanheart.init.BlockInit;
import com.github.ksgfk.oceanheart.init.ItemInit;
import com.github.ksgfk.oceanheart.objects.blocks.item.ItemBlockVariants;
import com.github.ksgfk.oceanheart.util.IHasMod;
import com.github.ksgfk.oceanheart.util.IHaveMeta;
import com.github.ksgfk.oceanheart.util.IMetaName;
import com.github.ksgfk.oceanheart.util.handlers.EnumPlanks;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class BlockOHPlanks extends Block implements IMetaName, IHasMod, IHaveMeta {
    private static final PropertyEnum<EnumPlanks> VARIANT = PropertyEnum.create("variant", EnumPlanks.class);

    public BlockOHPlanks(String name) {
        super(Material.WOOD);
        setUnlocalizedName(name);
        setRegistryName(name);
        setSoundType(SoundType.WOOD);
        setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumPlanks.YGGDRASILL));
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
        for (EnumPlanks blockplanks$enumtype : EnumPlanks.values()) {
            items.add(new ItemStack(this, 1, blockplanks$enumtype.getMetadata()));
        }
    }

    /*
        @Override
        public IBlockState getStateFromMeta(int meta) {
            return this.getDefaultState().withProperty(VARIANT, EnumPlanks.byMetadata(meta));
        }
    */
    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(VARIANT).getMetadata();
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(world.getBlockState(pos)));
    }

    @Override
    public BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, VARIANT);
    }

    @Override
    public void registerModels() {
        for (int a = 0; a < EnumPlanks.values().length; a++) {
            OceanHeart.proxy.registerVariantRenderer(Item.getItemFromBlock(this), a, "planks_" + EnumPlanks.values()[a].getName(), "inventory");
        }
    }

    @Override
    public String getSpecialName(ItemStack stack) {
        return EnumPlanks.values()[stack.getItemDamage()].getName();
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
        switch (stack.getMetadata()) {
            case 0:
                tooltip.set(0, TextFormatting.RED + I18n.format("tile.planks_yggdrasill.name"));
        }
    }
}

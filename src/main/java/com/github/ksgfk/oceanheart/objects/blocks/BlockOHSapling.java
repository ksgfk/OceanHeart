package com.github.ksgfk.oceanheart.objects.blocks;

import com.github.ksgfk.oceanheart.OceanHeart;
import com.github.ksgfk.oceanheart.common.CreativeTabsOceanHeart;
import com.github.ksgfk.oceanheart.init.BlockInit;
import com.github.ksgfk.oceanheart.init.ItemInit;
import com.github.ksgfk.oceanheart.objects.blocks.item.ItemBlockVariants;
import com.github.ksgfk.oceanheart.util.IHasMod;
import com.github.ksgfk.oceanheart.util.IHaveMeta;
import com.github.ksgfk.oceanheart.util.IMetaName;
import com.github.ksgfk.oceanheart.util.handlers.EnumSapling;
import com.github.ksgfk.oceanheart.world.gen.generators.WorldGenYggdrasillTree;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;

import java.util.Objects;
import java.util.Random;

public class BlockOHSapling extends BlockBush implements IGrowable, IMetaName, IHasMod, IHaveMeta {

    public static final IProperty<EnumSapling> VARIANT = PropertyEnum.create("variant", EnumSapling.class);

    public BlockOHSapling(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
        this.setDefaultState(blockState.getBaseState().withProperty(VARIANT, EnumSapling.YGGDRASILL));
        setCreativeTab(CreativeTabsOceanHeart.tabsOceanHeart);

        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlockVariants(this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));
    }

    @Override
    public void registerModels() {
        for (int a = 0; a < EnumSapling.values().length; a++) {
            OceanHeart.proxy.registerVariantRenderer(Item.getItemFromBlock(this), a, "saplings_" + EnumSapling.values()[a].getName(), "inventory");
        }
    }

    @Override
    public String getSpecialName(ItemStack stack) {
        return EnumSapling.values()[stack.getItemDamage()].getName();
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        return true;
    }

    /**
     * 能不能使用骨粉
     *
     * @param worldIn 世界
     * @param rand    随机数
     * @param pos     坐标
     * @param state   方块
     * @return 催熟速度
     */
    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        return (double) worldIn.rand.nextFloat() < 0.45D;
    }

    @Override
    @Deprecated
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(VARIANT, EnumSapling.byMetadata(meta));

    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumSapling) state.getValue(VARIANT)).getMetadata();
    }

    @Override
    public int damageDropped(IBlockState state) {
        return ((EnumSapling) state.getValue(VARIANT)).getMetadata();
    }

    @Override
    public BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, VARIANT);
    }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        if (worldIn.isRemote || !TerrainGen.saplingGrowTree(worldIn, rand, pos)) {
            return;
        }

        switch (state.getValue(VARIANT)) {
            case YGGDRASILL:
                /*生成世界树,方块更改通知true,倾斜true,生成树*/
                new WorldGenYggdrasillTree(true).setSloped(true).generate(worldIn, rand, pos);
                break;
            default:
                System.out.println("No tree to generate");
                break;
        }

        worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 4);
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        for (EnumSapling enumSapling : EnumSapling.values()) {
            items.add(new ItemStack(this, 1, enumSapling.getMetadata()));
        }
    }


}

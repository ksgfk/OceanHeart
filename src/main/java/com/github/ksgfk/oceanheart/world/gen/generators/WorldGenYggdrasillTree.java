package com.github.ksgfk.oceanheart.world.gen.generators;

import com.github.ksgfk.oceanheart.init.BlockInit;
import com.github.ksgfk.oceanheart.objects.blocks.BlockOHLeaf;
import com.github.ksgfk.oceanheart.objects.blocks.BlockOHLog;
import com.github.ksgfk.oceanheart.util.handlers.EnumLeaves;
import com.github.ksgfk.oceanheart.util.handlers.EnumLog;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class WorldGenYggdrasillTree extends WorldGenAbstractTree {

    private static final IBlockState LOG = BlockInit.LOGS.getDefaultState().withProperty(BlockOHLog.VARIANT, EnumLog.YGGDRASILL);
    private static final IBlockState LEAF = BlockInit.LEAVES.getDefaultState().withProperty(BlockOHLeaf.VARIANT, EnumLeaves.YGGDRASILL).withProperty(BlockLeaves.CHECK_DECAY, Boolean.FALSE);

    private int minHeight;
    private World world;
    private BlockPos blockPos;
    private Random rand;

    public WorldGenYggdrasillTree(boolean notify, int minTreeHeightIn) {
        super(notify);
        this.minHeight = minTreeHeightIn;
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        this.world = worldIn;
        this.blockPos = position;
        this.rand = new Random(rand.nextLong());
        System.out.println("==========================开始生成世界树==========================");
        spawnMainLogs(this.world, this.blockPos, LOG);
        //spawnLeaves(this.world, position, LEAF);
        System.out.println("================================================================");
        return true;
    }

    private void spawnMainLogs(World world, BlockPos pos, IBlockState logs) {

        System.out.println("------------------------------树干------------------------------");
        double x = 0;
        double r = 16;
        double y;
        for (; x < r; x++) {
            y = Math.sqrt(r * r - x * x);
            this.setBlockAndNotifyAdequately(world, pos.north((int) y).east((int) x), logs);
            this.setBlockAndNotifyAdequately(world, pos.east((int) y).north((int) x), logs);
        }
        x = 0;
        System.out.println("东北部分生成完毕");
        for (; x < r; x++) {
            y = Math.sqrt(r * r - x * x);
            this.setBlockAndNotifyAdequately(world, pos.north((int) y).west((int) x), logs);
            this.setBlockAndNotifyAdequately(world, pos.west((int) y).north((int) x), logs);
        }
        x = 0;
        System.out.println("西北部分生成完毕");

        for (; x < r; x++) {
            y = Math.sqrt(r * r - x * x);
            this.setBlockAndNotifyAdequately(world, pos.south((int) y).east((int) x), logs);
            this.setBlockAndNotifyAdequately(world, pos.east((int) y).south((int) x), logs);
        }
        x = 0;
        System.out.println("东南部分生成完毕");
        for (; x < r; x++) {
            y = Math.sqrt(r * r - x * x);
            this.setBlockAndNotifyAdequately(world, pos.south((int) y).west((int) x), logs);
            this.setBlockAndNotifyAdequately(world, pos.west((int) y).south((int) x), logs);
        }
        x = 0;
        System.out.println("西南部分生成完毕");
    }

    private void spawnLeaves(World world, BlockPos pos, IBlockState leaves) {
        for (int a = 1; a < 6; a++) {
            this.setBlockAndNotifyAdequately(this.world, pos.east(a), leaves);
            this.setBlockAndNotifyAdequately(this.world, pos.south(a), leaves);
            this.setBlockAndNotifyAdequately(this.world, pos.west(a), leaves);
            this.setBlockAndNotifyAdequately(this.world, pos.north(a), leaves);
            this.setBlockAndNotifyAdequately(this.world, pos.up(a), leaves);
        }
    }
}

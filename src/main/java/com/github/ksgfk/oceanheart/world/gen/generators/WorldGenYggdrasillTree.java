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

/**
 * 生成世界树
 */
public class WorldGenYggdrasillTree extends WorldGenAbstractTree {
    /*原木*/
    private static final IBlockState LOG = BlockInit.LOGS.getDefaultState().withProperty(BlockOHLog.VARIANT, EnumLog.YGGDRASILL);
    /*叶子*/
    private static final IBlockState LEAF = BlockInit.LEAVES.getDefaultState().withProperty(BlockOHLeaf.VARIANT, EnumLeaves.YGGDRASILL).withProperty(BlockLeaves.CHECK_DECAY, Boolean.FALSE);
    /*暂时用不到以下的变量
    /*生成树的世界
    private World world;
    /*生成树的位置,以树苗为中心
    private BlockPos pos;
    /*随机数
    private Random rand;
    */

    /**
     * 构造方法
     *
     * @param notify 树叶是否会枯萎
     */
    public WorldGenYggdrasillTree(boolean notify) {
        super(notify);
    }

    /**
     * 生成树的主要方法
     *
     * @param worldIn  生成树的世界
     * @param rand     随机数
     * @param position 生成树的位置,以树苗为中心
     * @return 是否生成树
     */
    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        System.out.println("===========开始生成世界树===========");
        spawnMainLogs(worldIn, position, LOG);
        //spawnLeaves(this.world, position, LEAF);
        System.out.println("===================================");
        return true;
    }

    /**
     * 生成主要树干
     *
     * @param world 生成树的世界
     * @param pos   生成树的位置,以树苗为中心
     * @param logs  组成树干的方块,因为生成的是世界树,这里填变量LOG
     */
    private void spawnMainLogs(World world, BlockPos pos, IBlockState logs) {
        /*开始计时*/
        long startTime = System.nanoTime();
        System.out.println("-------------树干-------------");
        /*圆的公式:r^2 = x^2 + y^2*/
        /*圆的x轴坐标*/
        double x = 0;
        /*圆的int类型x轴坐标*/
        int intX;
        /*上一个圆的int类型x轴坐标*/
        int tempX;
        /*圆半径*/
        double r = 16;
        /*圆的y轴坐标*/
        double y = r + 1;
        /*圆的int类型y轴坐标*/
        int intY;
        /*上一个圆的int类型y轴坐标*/
        int tempY;

        while (x < r) {
            tempY = (int) y;
            tempX = (int) x;
            y = Math.sqrt(r * r - x * x);
            x += 0.1;
            intY = (int) y;
            intX = (int) x;

            if (intY != tempY || intX != tempX) {
                this.setBlockAndNotifyAdequately(world, pos.up(1).north(intY).east(intX), logs);
                this.setBlockAndNotifyAdequately(world, pos.up(1).north(intY).west(intX), logs);
                this.setBlockAndNotifyAdequately(world, pos.up(1).south(intY).east(intX), logs);
                this.setBlockAndNotifyAdequately(world, pos.up(1).south(intY).west(intX), logs);
                for (int a = 0; a < intY; a++) {
                    this.setBlockAndNotifyAdequately(world, pos.up(1).north(a).east(intX), logs);
                    this.setBlockAndNotifyAdequately(world, pos.up(1).north(a).west(intX), logs);
                    this.setBlockAndNotifyAdequately(world, pos.up(1).south(a).east(intX), logs);
                    this.setBlockAndNotifyAdequately(world, pos.up(1).south(a).west(intX), logs);
                }
            }
        }

        System.out.println("西南部分生成完毕");
        /*计时结束*/
        long endTime = System.nanoTime();
        System.out.println("生成树干耗时：" + (endTime - startTime) + "ns");
    }

    /**
     * 生成主要树干
     *
     * @param world  生成树的世界
     * @param pos    生成树的位置,以树苗为中心
     * @param leaves 组成树叶的方块,因为生成的是世界树,这里填变量LEAF
     */
    private void spawnLeaves(World world, BlockPos pos, IBlockState leaves) {
        for (int a = 1; a < 6; a++) {
            this.setBlockAndNotifyAdequately(world, pos.east(a), leaves);
            this.setBlockAndNotifyAdequately(world, pos.south(a), leaves);
            this.setBlockAndNotifyAdequately(world, pos.west(a), leaves);
            this.setBlockAndNotifyAdequately(world, pos.north(a), leaves);
            this.setBlockAndNotifyAdequately(world, pos.up(a), leaves);
        }
    }
}

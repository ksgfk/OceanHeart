package com.github.ksgfk.oceanheart.world.gen.generators;

import com.github.ksgfk.oceanheart.init.BlockInit;
import com.github.ksgfk.oceanheart.objects.blocks.BlockOHLeaf;
import com.github.ksgfk.oceanheart.objects.blocks.BlockOHLog;
import com.github.ksgfk.oceanheart.objects.blocks.BlockOHSapling;
import com.github.ksgfk.oceanheart.util.handlers.EnumLeaves;
import com.github.ksgfk.oceanheart.util.handlers.EnumLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class WorldGenYggdrasillTree extends WorldGenAbstractTree {

    private static final IBlockState LOG = BlockInit.LOGS.getDefaultState().withProperty(BlockOHLog.VARIANT, EnumLog.YGGDRASILL);
    private static final IBlockState LEAF = BlockInit.LEAVES.getDefaultState().withProperty(BlockOHLeaf.VARIANT, EnumLeaves.YGGDRASILL);

    private final int minHeight;

    public WorldGenYggdrasillTree() {
        super(true);
        this.minHeight = 12;
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        int height = this.minHeight + rand.nextInt(3);
        boolean flag = true;

        int x = position.getX();
        int y = position.getY();
        int z = position.getZ();

        for (int yPos = y; yPos <= y + 1 + height; yPos++) {
            int b0 = 2;
            if (yPos == y) {
                b0 = 1;
            }
            if (yPos >= y + 1 + height - 2) {
                b0 = 2;
            }

            BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();

            for (int xPos = x - b0; xPos <= x + b0 && flag; xPos++) {
                for (int zPos = z - b0; xPos < -z + b0 && flag; zPos++) {
                    if (yPos >= 0 && yPos < worldIn.getHeight()) {
                        if (!this.isReplaceable(worldIn, new BlockPos(xPos, yPos, zPos))) {
                            flag = false;
                        }
                    } else {
                        flag = false;
                    }
                }
            }
        }
        if (!flag) {
            return false;
        } else {
            BlockPos down = position.down();
            IBlockState state = worldIn.getBlockState(down);
            boolean isSoil = state.getBlock().canSustainPlant(state, worldIn, down, EnumFacing.UP, (BlockOHSapling) BlockInit.SAPLINGS);

            if (isSoil && y < worldIn.getHeight() - height - 1) {
                state.getBlock().onPlantGrow(state, worldIn, down, position);

                for (int yPos = y - 3 + height; yPos < y + height; yPos++) {
                    int b1 = yPos - (y + height);
                    int b2 = 1 - b1 / 2;

                    for (int xPos = z - b2; xPos <= x + b2; xPos++) {
                        int b3 = xPos - x;
                        for (int zPos = z - b2; zPos <= z + b2; zPos++) {
                            int b4 = zPos - z;
                            if (Math.abs(b3) != b2 || Math.abs(b4) != b2 || rand.nextInt(2) != 0 && b1 != 0) {
                                BlockPos treePos = new BlockPos(xPos, yPos, zPos);
                                IBlockState treeState = worldIn.getBlockState(treePos);

                                if (treeState.getBlock().isAir(treeState, worldIn, treePos) || treeState.getBlock().isAir(treeState, worldIn, treePos)) {
                                    this.setBlockAndNotifyAdequately(worldIn, treePos, LEAF);
                                    this.setBlockAndNotifyAdequately(worldIn, treePos.add(0, -0.25 * height, 0), LEAF);
                                    this.setBlockAndNotifyAdequately(worldIn, treePos.add(0, -0.5 * height, 0), LEAF);
                                }
                            }
                        }
                    }
                }

                for (int logHeight = 0; logHeight < height; logHeight++) {
                    BlockPos up = position.up(logHeight);
                    IBlockState logState = worldIn.getBlockState(up);

                    if (logState.getBlock().isAir(logState, worldIn, up) || logState.getBlock().isLeaves(logState, worldIn, up)) {
                        this.setBlockAndNotifyAdequately(worldIn, position.up(logHeight), LOG);
                    }
                }
                return true;
            }
        }
        return true;
    }
}

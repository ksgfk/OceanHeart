package com.github.ksgfk.oceanheart.world.gen.generators;

import net.minecraft.world.gen.feature.WorldGenTrees;

public class WorldGenNatureTrue extends WorldGenTrees {
    public WorldGenNatureTrue(boolean p_i2027_1_) {
        super(p_i2027_1_);
    }
/*
    public static final IBlockState LOG_NATURE = BlockInit.LOGS.getDefaultState().withProperty(BlockOHLog.VARIANT, EnumHandlers.EnumType.CRYSTAL_NATURE);
    public static final IBlockState LEAF = BlockInit.BLOCK_NATURAL_FORCE_CRYSTAL.getStateFromMeta(0);

    private final int minHeight;

    public WorldGenNatureTrue() {
        super(false);
        this.minHeight = 1;
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
                        this.setBlockAndNotifyAdequately(worldIn, position.up(logHeight), LOG_NATURE);
                    }
                }
                return true;
            }
        }
        return true;
    }
    */
}

package com.github.ksgfk.oceanheart.world.gen;

import com.github.ksgfk.oceanheart.init.BlockInit;
import com.github.ksgfk.oceanheart.objects.blocks.BlockOres;
import com.github.ksgfk.oceanheart.util.handlers.EnumHandlers;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGenCustomOres implements IWorldGenerator {
    private WorldGenerator ore_nether_nature_crystal, ore_overworld_nature_crystal, ore_end_nature_crystal;

    public WorldGenCustomOres() {
        ore_nether_nature_crystal = new WorldGenMinable(BlockInit.ORE_NETHER.getDefaultState().withProperty(BlockOres.VARIANT, EnumHandlers.EnumType.CRYSTAL_NATURE), 0, BlockMatcher.forBlock(Blocks.NETHERRACK));
        ore_overworld_nature_crystal = new WorldGenMinable(BlockInit.ORE_OVERWORLD.getDefaultState().withProperty(BlockOres.VARIANT, EnumHandlers.EnumType.CRYSTAL_NATURE), 1, BlockMatcher.forBlock(Blocks.STONE));
        ore_end_nature_crystal = new WorldGenMinable(BlockInit.ORE_END.getDefaultState().withProperty(BlockOres.VARIANT, EnumHandlers.EnumType.CRYSTAL_NATURE), 0, BlockMatcher.forBlock(Blocks.END_STONE));
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.getDimension()) {
            case -1:
                runGenerator(ore_nether_nature_crystal, world, random, chunkX, chunkZ, 1, 0, 100);
                break;
            case 0:
                runGenerator(ore_overworld_nature_crystal, world, random, chunkX, chunkZ, 1, 0, 5);
                break;
            case 1:
                runGenerator(ore_end_nature_crystal, world, random, chunkX, chunkZ, 1, 0, 256);
                break;
        }
    }

    private void runGenerator(WorldGenerator gen, World world, Random random, int chunkX, int chunkZ, int chance, int minHeight, int maxHeight) {
        if (minHeight > maxHeight || minHeight < 0 || maxHeight > 256) throw new IllegalArgumentException("矿物生成超出世界边界");

        int heightDiff = maxHeight - minHeight + 1;
        for (int a = 0; a < chance; a++) {
            int x = chunkX * 16 + random.nextInt(16);
            int y = minHeight + random.nextInt(heightDiff);
            int z = chunkZ * 16 + random.nextInt(16);

            gen.generate(world, random, new BlockPos(x, y, z));
        }
    }
}

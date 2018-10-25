package com.github.ksgfk.oceanheart.world.gen;

import com.github.ksgfk.oceanheart.init.BlockInit;
import com.github.ksgfk.oceanheart.objects.blocks.BlockOres;
import com.github.ksgfk.oceanheart.util.handlers.EnumHandlers;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeOcean;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGenCustomOres implements IWorldGenerator {
    private WorldGenerator ore_nether_nature_crystal, ore_overworld_nature_crystal, ore_end_nature_crystal;
    private WorldGenerator ore_nether_gold_strange, ore_overworld_gold_strange, ore_end_gold_strange;
    private WorldGenerator ore_nether_leve_soul, ore_overworld_oecan_soul;

    public WorldGenCustomOres() {
        ore_nether_nature_crystal = new WorldGenMinable(BlockInit.ORE_NETHER.getDefaultState().withProperty(BlockOres.VARIANT, EnumHandlers.EnumType.CRYSTAL_NATURE), 0, BlockMatcher.forBlock(Blocks.DIAMOND_BLOCK));
        ore_overworld_nature_crystal = new WorldGenMinable(BlockInit.ORE_OVERWORLD.getDefaultState().withProperty(BlockOres.VARIANT, EnumHandlers.EnumType.CRYSTAL_NATURE), 1, BlockMatcher.forBlock(Blocks.DIAMOND_BLOCK));
        ore_end_nature_crystal = new WorldGenMinable(BlockInit.ORE_END.getDefaultState().withProperty(BlockOres.VARIANT, EnumHandlers.EnumType.CRYSTAL_NATURE), 0, BlockMatcher.forBlock(Blocks.DIAMOND_BLOCK));

        ore_overworld_gold_strange = new WorldGenMinable(BlockInit.ORE_GOLD_STRANGE_OVERWORLD.getDefaultState(), 1, BlockMatcher.forBlock(Blocks.DIAMOND_ORE));
        ore_end_gold_strange = new WorldGenMinable(BlockInit.ORE_GOLD_STRANGE_END.getDefaultState(), 1, BlockMatcher.forBlock(Blocks.DIAMOND_BLOCK));

        ore_nether_leve_soul = new WorldGenMinable(BlockInit.ORE_LEVE_SOUL.getDefaultState(), 1, BlockMatcher.forBlock(Blocks.DIAMOND_BLOCK));

        ore_overworld_oecan_soul = new WorldGenMinable(BlockInit.ORE_OCEAN_SOUL.getDefaultState(), 1, BlockMatcher.forBlock(Blocks.DIAMOND_BLOCK));
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.getDimension()) {
            case -1:
                //runGenerator(ore_nether_nature_crystal, world, random, chunkX, chunkZ, 0, 0, 100);
                //runGenerator(ore_nether_gold_strange, world, random, chunkX, chunkZ, 1, 0, 100);
                runGenerator(ore_nether_leve_soul, world, random, chunkX, chunkZ, 1, 0, 255);
                break;
            case 0:
                runGenerator(ore_overworld_nature_crystal, world, random, chunkX, chunkZ, 2, 0, 5);
                runGenerator(ore_overworld_gold_strange, world, random, chunkX, chunkZ, 1, 0, 256);
                runOceanGenerator(ore_overworld_oecan_soul, world, random, chunkX, chunkZ, 1, 0, 255);
                break;
            case 1:
                //runGenerator(ore_end_nature_crystal, world, random, chunkX, chunkZ, 0, 0, 256);
                runGenerator(ore_end_gold_strange, world, random, chunkX, chunkZ, 1, 0, 256);
                break;
        }
    }

    private void runGenerator(WorldGenerator gen, World world, Random random, int chunkX, int chunkZ, int chance, int minHeight, int maxHeight) {
        if (minHeight > maxHeight || minHeight < 0 || maxHeight > 256) throw new IllegalArgumentException("矿物生成超出世界边界");

        int x, y, z;

        int heightDiff = maxHeight - minHeight + 1;
        for (int a = 0; a < chance; a++) {
            x = chunkX * 16 + random.nextInt(16);
            y = minHeight + random.nextInt(heightDiff);
            z = chunkZ * 16 + random.nextInt(16);

            gen.generate(world, random, new BlockPos(x, y, z));
        }
    }

    private void runOceanGenerator(WorldGenerator gen, World world, Random random, int chunkX, int chunkZ, int chance, int minHeight, int maxHeight) {
        if (minHeight > maxHeight || minHeight < 0 || maxHeight > 256) throw new IllegalArgumentException("矿物生成超出世界边界");

        int x, y, z;
        int heightDiff = maxHeight - minHeight + 1;

        for (int a = 0; a < chance; a++) {
            x = chunkX * 16 + random.nextInt(16);
            y = minHeight + random.nextInt(heightDiff);
            z = chunkZ * 16 + random.nextInt(16);
            BlockPos blockPos = new BlockPos(x, y, z);//获取矿石坐标

            if (world.getBiome(blockPos).getBiomeClass().equals(BiomeOcean.class)) {//如果是Ocean生物群系才生成矿石
                gen.generate(world, random, blockPos);
            }
        }
    }
}

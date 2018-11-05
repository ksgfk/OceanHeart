package com.github.ksgfk.oceanheart.world.gen.generators;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGenCustomTree implements IWorldGenerator {
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {

    }
/*
    private final WorldGenerator NATURE = new WorldGenNatureTrue();

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.getDimension()) {
            case 1:
                break;
            case 0:
                runGenerator(NATURE, world, random, chunkX, chunkZ, 3, -1, 0, Biome.class);
                break;
            case -1:
                break;
        }
    }

    private void runGenerator(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, double chancesToSpawn, int minHeight, int maxHeight, Class<?>... classes) {
        if (chancesToSpawn < 1) {
            if (random.nextDouble() < chancesToSpawn) {
                chancesToSpawn = 1;
            } else {
                chancesToSpawn = 0;
            }

            ArrayList<Class<?>> classArrayList = new ArrayList<Class<?>>(Arrays.asList(classes));
            int heightDiff = maxHeight - minHeight + 1;
            for (int i = 0; i < chancesToSpawn; i++) {
                BlockPos pos = new BlockPos(chunkX * 16 + 10 + random.nextInt(15), minHeight + random.nextInt(heightDiff), chunkZ * 16 + 10 + random.nextInt(15));
                if (minHeight < 0) {
                    pos = world.getHeight(pos);
                    Class<?> biome = world.provider.getBiomeForCoords(pos).getClass();
                    if (classArrayList.contains(biome) || classes.length == 0) {
                        generator.generate(world, random, pos);
                    }
                }
            }
        }
    }

    public static void register() {
        GameRegistry.registerWorldGenerator(new WorldGenCustomTree(), 0);
    }
    */
}

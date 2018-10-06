package com.github.ksgfk.oceanheart.init;

import com.github.ksgfk.oceanheart.world.biomes.BiomeCopper;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class BiomeInit {//Biome:生物群落
    public static final Biome COPPER=new BiomeCopper();
    public static void registerBiome(){
        initBiome(COPPER,"Copper",BiomeManager.BiomeType.WARM,Type.HILLS, Type.MOUNTAIN,Type.DEAD);
    }
    private static Biome initBiome(Biome biome, String name, BiomeManager.BiomeType biomeType, Type... types){
        biome.setRegistryName(name);
        ForgeRegistries.BIOMES.register(biome);
        BiomeDictionary.addTypes(biome,types);
        BiomeManager.addBiome(biomeType,new BiomeManager.BiomeEntry(biome,10));
        BiomeManager.addSpawnBiome(biome);
        return biome;
    }
}

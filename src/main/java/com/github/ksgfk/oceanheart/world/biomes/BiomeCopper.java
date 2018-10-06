package com.github.ksgfk.oceanheart.world.biomes;

import com.github.ksgfk.oceanheart.init.BlockInit;
import com.github.ksgfk.oceanheart.util.handlers.EnumHandlers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAnvil;
import net.minecraft.block.BlockDirt;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

public class BiomeCopper extends Biome {
    public BiomeCopper(){
        super(new BiomeProperties("Copper").setBaseHeight(1.5F).setHeightVariation(1.2F).setTemperature(0.9F).setRainDisabled().setWaterColor(16711680));


    }
}

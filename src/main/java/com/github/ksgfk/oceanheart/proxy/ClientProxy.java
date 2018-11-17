package com.github.ksgfk.oceanheart.proxy;

import com.github.ksgfk.oceanheart.OceanHeart;
import com.github.ksgfk.oceanheart.gui.GuiOHMainMenu;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;

import java.util.Objects;

import static com.github.ksgfk.oceanheart.OceanHeart.logger;

public class ClientProxy extends CommonProxy {
    @Override
    public void registerItemRenderer(Item item, int meta, String id) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Objects.requireNonNull(item.getRegistryName()), id));
    }

    @Override
    public void registerVariantRenderer(Item item, int meta, String filename, String id) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(new ResourceLocation(OceanHeart.MODID, filename), id));
    }

    @Override
    public void registerEventHandlers() {

        MinecraftForge.EVENT_BUS.register(GuiOHMainMenu.class);

        logger.info("EventHandlers register successfully");
    }
}

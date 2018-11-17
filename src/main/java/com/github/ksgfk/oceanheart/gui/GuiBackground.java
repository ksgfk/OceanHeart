package com.github.ksgfk.oceanheart.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class GuiBackground extends GuiScreen {

    private ResourceLocation[] layerTextures;
    private int layerTick;

    public GuiBackground(ResourceLocation texture, int layers) {
        this.layerTextures = new ResourceLocation[layers];
        for (int i = 0; i < layers; i++) {
            this.layerTextures[i] = new ResourceLocation(texture.getResourceDomain(), texture.getResourcePath() + "_" + i + ".png");
        }
    }

    @Override
    public void initGui() {
        super.initGui();
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
    }
}

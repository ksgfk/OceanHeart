package com.github.ksgfk.oceanheart.entity.render;

import com.github.ksgfk.oceanheart.OceanHeart;
import com.github.ksgfk.oceanheart.entity.EntityKillerWhale;
import com.github.ksgfk.oceanheart.entity.model.ModelKillerWhale;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderKillerWhale extends RenderLiving<EntityKillerWhale> {
    public static final ResourceLocation TEXTURES = new ResourceLocation(OceanHeart.MODID + ":textures/entity/killer_whale.png");

    public RenderKillerWhale(RenderManager manager) {
        super(manager, new ModelKillerWhale(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityKillerWhale entity) {
        return TEXTURES;
    }

    @Override
    protected void applyRotations(EntityKillerWhale entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
}

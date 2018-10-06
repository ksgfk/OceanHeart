package com.github.ksgfk.oceanheart.entity.render;

import com.github.ksgfk.oceanheart.entity.EntitySuperGolem;
import com.github.ksgfk.oceanheart.entity.model.ModelSuperGolem;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderSuperGolem extends RenderLiving<EntitySuperGolem> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("oceanheart:textures/entity/super_golem.png");

    public RenderSuperGolem(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelSuperGolem(), 0.5F);
    }

    @Nullable
    protected ResourceLocation getEntityTexture(EntitySuperGolem entity) {
        return TEXTURES;
    }

    protected void applyRotations(EntitySuperGolem entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
}

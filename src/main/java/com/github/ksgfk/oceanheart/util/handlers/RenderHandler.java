package com.github.ksgfk.oceanheart.util.handlers;

import com.github.ksgfk.oceanheart.entity.EntityKillerWhale;
import com.github.ksgfk.oceanheart.entity.EntitySuperGolem;
import com.github.ksgfk.oceanheart.entity.render.RenderKillerWhale;
import com.github.ksgfk.oceanheart.entity.render.RenderSuperGolem;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderHandler {
    public static void registerEntityRenders() {
        RenderingRegistry.registerEntityRenderingHandler(EntitySuperGolem.class, new IRenderFactory<EntitySuperGolem>() {
            @SideOnly(Side.CLIENT)
            @Override
            public Render<? super EntitySuperGolem> createRenderFor(RenderManager manager) {
                return new RenderSuperGolem(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityKillerWhale.class, new IRenderFactory<EntityKillerWhale>() {
            @Override
            public Render<? super EntityKillerWhale> createRenderFor(RenderManager manager) {
                return new RenderKillerWhale(manager);
            }
        });
    }
}

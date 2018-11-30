package com.github.ksgfk.oceanheart.entity.render;

import codechicken.lib.render.CCModel;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.OBJParser;
import com.github.ksgfk.oceanheart.entity.EntityAbyssWither;
import com.github.ksgfk.oceanheart.util.IRenderModel;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class RenderAbyssWither extends Render<EntityAbyssWither> implements IRenderModel<EntityAbyssWither> {
    public static final ResourceLocation model_abysswither_2 = new ResourceLocation("oceanheart:models/entity/abysswither.obj");
    public static final ResourceLocation TEXTURE = new ResourceLocation("oceanheart:textures/entity/abysswither_2.png");
    private CCModel model1;

    public RenderAbyssWither(RenderManager renderManager) {
        super(renderManager);
        try {
            model1 = OBJParser.parseModels(model_abysswither_2).get("Box013");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityAbyssWither entity) {
        //return null;
        return TEXTURE;
    }

    @Override
    public void doRender(EntityAbyssWither entity, double x, double y, double z, float entityYaw, float partialTicks) {
        this.bindEntityTexture(entity);
        this.render((EntityAbyssWither) entity, x, y, z, entityYaw, partialTicks);
    }

    @Override
    public void render(EntityAbyssWither entity, double x, double y, double z, float yaw, float partialTicks) {
        GlStateManager.pushMatrix();

        GlStateManager.translate((float) x, (float) y, (float) z);
        //GlStateManager.rotate(rocket.prevRotationYaw + (rocket.rotationYaw - rocket.prevRotationYaw) * partialTicks - 90.0F, 0.0F, 1.0F, 0.0F);
        //GlStateManager.rotate(90, 0, 0, 1);
        //GlStateManager.rotate(rocket.prevRotationPitch + (rocket.rotationPitch - rocket.prevRotationPitch) * partialTicks, 0.0F, 0.0F, 1.0F);
        GlStateManager.scale(0.1F, 0.1F, 0.1F);
        GlStateManager.enableColorMaterial();

        CCRenderState ccrs = CCRenderState.instance();
        ccrs.reset();

        ccrs.startDrawing(4, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);

        try {
            model1.render(ccrs);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ccrs.draw();

        GlStateManager.popMatrix();
    }
}

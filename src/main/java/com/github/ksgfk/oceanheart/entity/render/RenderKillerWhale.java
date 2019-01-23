package com.github.ksgfk.oceanheart.entity.render;

import com.github.ksgfk.oceanheart.entity.EntityKillerWhale;
import com.github.ksgfk.oceanheart.entity.model.ModelKillerWhale;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

/*
@SideOnly(Side.CLIENT)
public class RenderKillerWhale extends Render<EntityKillerWhale> implements IRenderModel<EntityKillerWhale> {

    public static final ResourceLocation MODEL_KILLERWHALE = new ResourceLocation("oceanheart:models/entity/killerwhale.obj");
    public static final ResourceLocation TEXTURE = new ResourceLocation("oceanheart:textures/entity/killerwhale.png");
    private CCModel model;

    public RenderKillerWhale(RenderManager renderManager) {
        super(renderManager);
        try {
            model = OBJParser.parseModels(MODEL_KILLERWHALE).get("Box002");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityKillerWhale entity) {
        return null;
    }

    @Override
    public void render(EntityKillerWhale entity, double x, double y, double z, float yaw, float partialTicks) {
        GlStateManager.pushMatrix();

        GlStateManager.translate((float) x, (float) y, (float) z);
        //GlStateManager.rotate(rocket.prevRotationYaw + (rocket.rotationYaw - rocket.prevRotationYaw) * partialTicks - 90.0F, 0.0F, 1.0F, 0.0F);
        //GlStateManager.rotate(90, 0, 0, 1);
        //GlStateManager.rotate(rocket.prevRotationPitch + (rocket.rotationPitch - rocket.prevRotationPitch) * partialTicks, 0.0F, 0.0F, 1.0F);
        GlStateManager.scale(0.05F, 0.05F, 0.05F);
        GlStateManager.enableColorMaterial();

        CCRenderState ccrs = CCRenderState.instance();
        ccrs.reset();

        ccrs.startDrawing(4, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);

        try {
            model.render(ccrs);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ccrs.draw();

        GlStateManager.popMatrix();
    }

    @Override
    public void doRender(EntityKillerWhale entity, double x, double y, double z, float entityYaw, float partialTicks) {
        this.bindEntityTexture(entity);
        this.render((EntityKillerWhale) entity, x, y, z, entityYaw, partialTicks);
    }

}
*/
@SideOnly(Side.CLIENT)
public class RenderKillerWhale extends RenderLiving<EntityKillerWhale> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("oceanheart:textures/entity/killerwhale.png");

    public RenderKillerWhale(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelKillerWhale(), 0.5F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityKillerWhale entity) {
        return TEXTURES;
    }

    protected void applyRotations(EntityKillerWhale entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
}
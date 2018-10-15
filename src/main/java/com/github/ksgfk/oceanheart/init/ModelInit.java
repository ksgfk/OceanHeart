package com.github.ksgfk.oceanheart.init;

import com.github.ksgfk.oceanheart.OceanHeart;
import net.minecraftforge.client.model.obj.OBJLoader;

/*
 *模型类，暂时没用
 * KSGFK创建
 */
public class ModelInit {
    public void init() throws Exception {
        OBJLoader.INSTANCE.addDomain(OceanHeart.MODID);
        //ModelLoaderRegistry.getModel(new ResourceLocation("oceanheart:models/entity/entity_flowerpot.obj"));
    }
}

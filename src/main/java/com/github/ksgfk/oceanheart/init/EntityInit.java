package com.github.ksgfk.oceanheart.init;

import com.github.ksgfk.oceanheart.OceanHeart;
import com.github.ksgfk.oceanheart.entity.EntityKillerWhale;
import com.github.ksgfk.oceanheart.entity.EntitySuperGolem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityInit {
    public static void registerEntities() {
        EntityRegistry.registerModEntity(new ResourceLocation(OceanHeart.MODID+":super_golem"),EntitySuperGolem.class,"super_golem",121,OceanHeart.instance,32,20,true,000000,000000);
        EntityRegistry.registerModEntity(new ResourceLocation(OceanHeart.MODID+"killer_whale"), EntityKillerWhale.class,"killer_whale",122,OceanHeart.instance,32,20,true,111111,111111);
    }
}

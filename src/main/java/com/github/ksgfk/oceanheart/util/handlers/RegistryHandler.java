package com.github.ksgfk.oceanheart.util.handlers;

import com.github.ksgfk.oceanheart.init.BlockInit;
import com.github.ksgfk.oceanheart.init.EntityInit;
import com.github.ksgfk.oceanheart.init.ItemInit;
import com.github.ksgfk.oceanheart.init.ModelInit;
import com.github.ksgfk.oceanheart.util.IHasMod;
import com.github.ksgfk.oceanheart.world.gen.WorldGenCustomOres;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class RegistryHandler {
    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
    }

    @SubscribeEvent
    public static void onModRegister(ModelRegistryEvent event) {
        for (Item item : ItemInit.ITEMS) {
            if (item instanceof IHasMod) {
                ((IHasMod) item).registerModels();
            }
        }
        for (Block block : BlockInit.BLOCKS) {
            if (block instanceof IHasMod) {
                ((IHasMod) block).registerModels();
            }
        }
    }

    public static void otherRegisteries() {
        GameRegistry.registerWorldGenerator(new WorldGenCustomOres(), 0);
        //GameRegistry.registerWorldGenerator(new WorldGenCustomTree(), 0);
    }

    public static void perInitRegistries() {
        RenderHandler.registerEntityRenders();
        EntityInit.registerEntities();
        try {
            new ModelInit().init();
            System.out.println("Cross Init successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

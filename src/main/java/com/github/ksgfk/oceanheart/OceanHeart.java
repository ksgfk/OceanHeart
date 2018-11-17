package com.github.ksgfk.oceanheart;

import com.github.ksgfk.oceanheart.common.Config;
import com.github.ksgfk.oceanheart.event.EventHandler;
import com.github.ksgfk.oceanheart.objects.items.Smelt;
import com.github.ksgfk.oceanheart.proxy.ClientProxy;
import com.github.ksgfk.oceanheart.proxy.CommonProxy;
import com.github.ksgfk.oceanheart.util.handlers.RegistryHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = OceanHeart.MODID, name = OceanHeart.NAME, version = OceanHeart.VERSION, acceptedMinecraftVersions = "1.12.2")
public class OceanHeart {
    public static final String MODID = "oceanheart";
    public static final String NAME = "Ocean Heart";
    public static final String VERSION = "@version@";
    public static final String CLIENT = "com.github.ksgfk.oceanheart.proxy.ClientProxy";
    public static final String COMMON = "com.github.ksgfk.oceanheart.proxy.CommonProxy";

    public static Logger logger = LogManager.getLogger("OceanHeart");

    @Instance
    public static OceanHeart instance;

    @SidedProxy(clientSide = OceanHeart.CLIENT, serverSide = OceanHeart.COMMON)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        RegistryHandler.otherRegisteries();
        RegistryHandler.perInitRegistries();
        new Config(event);
        new ClientProxy().registerEventHandlers();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        Smelt.registerSmelting();
        //Recipe.register();
        EventHandler.init();

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}

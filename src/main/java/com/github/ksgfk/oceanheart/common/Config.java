package com.github.ksgfk.oceanheart.common;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import static com.github.ksgfk.oceanheart.OceanHeart.logger;

public class Config {
    private static Configuration config;

    public static String underline;

    public Config(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        config = new Configuration(event.getSuggestedConfigurationFile());

        config.load();
        load();
    }

    private static void load() {
        logger.info("Started loading config. ");
        String comment;

        comment = "测试用";
        underline = config.get(Configuration.CATEGORY_GENERAL, "test", "其实这条没啥用", comment).getString();

        config.save();
        logger.info("Finished loading config. ");
    }
}

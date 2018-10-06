package com.github.ksgfk.oceanheart.common;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

public class Config {
    private static Configuration config;
    private static Logger logger;

    public static String underline;
    public static String swordAtlantislang;
    public static String swordNatureInfo_1;
    public static String swordNatureInfo_2;
    public static String swordBaldrInfo_1;
    public static String swordBaldrInfo_2;
    public static String swordBaldrInfo_3;
    public static String swordBaldrInfo_4;
    public static String swordBaldrInfo_5;
    public static String swordDeepSeaInfo_1;
    public static String swordDeepSeaInfo_2;
    public static String swordDruidHeartInfo_1;
    public static String swordExplorer_1;
    public static String swordFirehug_1;
    public static String swordFirehug_2;
    public static String swordFirehug_3;
    public static String swordMorgesinfo_1;
    public static String swordSargartinfo_1;
    public static String swordSunInfo_1;
    public static String swordTrailblazerInfo_1;
    public static String swordTrailblazerInfo_2;
    public static String swordTrailblazerInfo_3;
    public static String swordTrailblazerInfo_4;
    public static String swordTrailblazerInfo_5;
    public static String swordVanInfo_1;
    public static String swordVanInfo_2;
    public static String swordVanInfo_3;
    public static String swordVanInfo_4;
    public static String swordYanInfo_1;
    public static String swordYanhug_2;
    public static String swordYanhug_3;
    public static String swordYanhug_4;

    public static String ingotLegend;

    public static String on;
    public static String off;

    public Config(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        config = new Configuration(event.getSuggestedConfigurationFile());

        config.load();
        load();
    }

    private static void load() {
        logger.info("Started loading config. ");
        String comment;

        comment = "Underline";
        underline = config.get(Configuration.CATEGORY_GENERAL, "underline", "----------", comment).getString();
        comment = "Sword Atlantis's info.";
        swordAtlantislang = config.get(Configuration.CATEGORY_GENERAL, "swordAtlantislang", "Lost sword", comment).getString();
        comment = "Sword Nature's info.";
        swordNatureInfo_1 = config.get(Configuration.CATEGORY_GENERAL, "swordNatureInfo_1", "Each attack returns its own blood volume based on 25% of the enemy's current blood volume.", comment).getString();
        swordNatureInfo_2 = config.get(Configuration.CATEGORY_GENERAL, "swordNatureInfo_2", "The enemy is a player who randomly responds with 0-3 blood volume per hit.", comment).getString();
        comment = "The Last Light's info";
        swordBaldrInfo_1 = config.get(Configuration.CATEGORY_GENERAL, "swordBaldrInfo_1", "\"Holy light! This enemy seems worth fighting for!\" --- A paladin who asked not to be named", comment).getString();
        swordBaldrInfo_2 = config.get(Configuration.CATEGORY_GENERAL, "swordBaldrInfo_2", "Holy Light Energy: ", comment).getString();
        swordBaldrInfo_3 = config.get(Configuration.CATEGORY_GENERAL, "swordBaldrInfo_3", "When the main hand has this sword, you can communicate with the god of the Holy Light. After 25 seconds of recharge, you can release a range of healing effects.", comment).getString();
        swordBaldrInfo_4 = config.get(Configuration.CATEGORY_GENERAL, "swordBaldrInfo_4", "You have been treated ", comment).getString();
        swordBaldrInfo_5 = config.get(Configuration.CATEGORY_GENERAL, "swordBaldrInfo_5", " 's holy light", comment).getString();
        comment = "Sword of Deep Sea's info";
        swordDeepSeaInfo_1 = config.get(Configuration.CATEGORY_GENERAL, "swordDeepSeaInfo_1", "Legend has it that this sword is made of sea spar and rare minerals from the deep sea. With this swordman, even if you can’t swim before, you can swim like a fish in the depths of the sea.", comment).getString();
        swordDeepSeaInfo_2 = config.get(Configuration.CATEGORY_GENERAL, "swordDeepSeaInfo_2", "Passive: It will quickly return to life in water and has no oxygen limitation. It has a certain resistance to magma.", comment).getString();
        comment = "Heart of Dryad's info";
        swordDruidHeartInfo_1 = config.get(Configuration.CATEGORY_GENERAL, "swordDruidHeartInfo_1", "Balance, wildness, guardian", comment).getString();
        comment = "Sword of Explorer's info";
        swordExplorer_1 = config.get(Configuration.CATEGORY_GENERAL, "swordExplorerInfo_1", "\"When you light the bleeding, you are dead, even if you are creating\" ---???", comment).getString();
        comment = "Sword of Fire Hug's info";
        swordFirehug_1 = config.get(Configuration.CATEGORY_GENERAL, "swordFirehugInfo_1", "Chaos, order, reincarnation.", comment).getString();
        swordFirehug_2 = config.get(Configuration.CATEGORY_GENERAL, "swordFirehugInfo_2", "Confusion degree: ", comment).getString();
        swordFirehug_3 = config.get(Configuration.CATEGORY_GENERAL, "swordFirehugInfo_3", "Release a super compressed energy point when the confusion degree is full", comment).getString();
        comment = "Morges's info";
        swordMorgesinfo_1 = config.get(Configuration.CATEGORY_GENERAL, "swordMorgesInfo_1", "Void? The end of the earth? The universe? No one knows where it comes from.", comment).getString();
        comment = "Sargart's info";
        swordSargartinfo_1 = config.get(Configuration.CATEGORY_GENERAL, "swordSargartinfo_1", "\"Forever, don't, touch it\" --???", comment).getString();
        comment = "Sun's info";
        swordSunInfo_1 = config.get(Configuration.CATEGORY_GENERAL, "swordSunInfo_1", "\"The world is the only one in the world\" --???", comment).getString();
        comment = "Trailblazer's info";
        swordTrailblazerInfo_1 = config.get(Configuration.CATEGORY_GENERAL, "swordTrailblazerInfo_1", "The remains of the ancient times have almost no energy.", comment).getString();
        swordTrailblazerInfo_2 = config.get(Configuration.CATEGORY_GENERAL, "swordTrailblazerInfo_2", "Inflicts 1% damage to the enemy's current energy.", comment).getString();
        swordTrailblazerInfo_3 = config.get(Configuration.CATEGORY_GENERAL, "swordTrailblazerInfo_3", "Energy is full.", comment).getString();
        swordTrailblazerInfo_4 = config.get(Configuration.CATEGORY_GENERAL, "swordTrailblazerInfo_4", "Inflicts 1000 additional damage to the enemy.", comment).getString();
        swordTrailblazerInfo_5 = config.get(Configuration.CATEGORY_GENERAL, "swordTrailblazerInfo_5", "Energy:", comment).getString();
        comment = "Van's info";
        swordVanInfo_1 = config.get(Configuration.CATEGORY_GENERAL, "swordVanInfo_1", "Tears and rain", comment).getString();
        swordVanInfo_2 = config.get(Configuration.CATEGORY_GENERAL, "swordVanInfo_2", "Rage value: ", comment).getString();
        swordVanInfo_3 = config.get(Configuration.CATEGORY_GENERAL, "swordVanInfo_3", "Pressing the right button when the anger value is full can immunize any damage for a certain period of time.", comment).getString();
        swordVanInfo_4 = config.get(Configuration.CATEGORY_GENERAL, "swordVanInfo_4", "Ahhhhhhhhhhhhhhhh!", comment).getString();
        comment = "Yan's info";
        swordYanInfo_1 = config.get(Configuration.CATEGORY_GENERAL, "swordYanInfo_1", "Never play this sword at home. Why do you ask? Because it will burn your house.", comment).getString();
        swordYanhug_2 = config.get(Configuration.CATEGORY_GENERAL, "swordYanhug_2", "Fire Switch: ", comment).getString();
        swordYanhug_3 = config.get(Configuration.CATEGORY_GENERAL, "swordYanhug_3", "Current energy: ", comment).getString();
        swordYanhug_4 = config.get(Configuration.CATEGORY_GENERAL, "swordYanhug_4", "When the energy is full,the sword will summon a lot of TNT from the sky.", comment).getString();
        comment = "Switch info";
        on = config.get(Configuration.CATEGORY_GENERAL, "on", "On", comment).getString();
        off = config.get(Configuration.CATEGORY_GENERAL, "off", "Off", comment).getString();

        config.save();
        logger.info("Finished loading config. ");
    }

    public static Logger logger() {
        return logger;
    }
}

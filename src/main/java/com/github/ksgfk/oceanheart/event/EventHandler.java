package com.github.ksgfk.oceanheart.event;

import com.github.ksgfk.oceanheart.OceanHeart;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import static com.github.ksgfk.oceanheart.OceanHeart.logger;

@Mod.EventBusSubscriber(modid = OceanHeart.MODID)
public class EventHandler {
    private static final String swordinfinityName = "item.avaritia:infinity_sword";
    private static final String trailblazerName = "item.trailblazer_sword";
    private static final String swordDeepSeaName = "item.deepsea_sword";
    private static final String swordbaldrName = "item.baldr_sword";
    private static final String swordVanName = "item.van_sword";
    private static final String opName_1 = "kabi";
    private static final String opName_2 = "KSGFK";
    private static final float swordDeepSeaBloodReturaRate = 0.02F;

    public static final EventBus EVENT_BUS = new EventBus();

    public static void init() {
        EVENT_BUS.register(EventHandler.class);
    }

    @SubscribeEvent
    public static void trSwordPowerlost(LivingDeathEvent event) {
        if (event.getEntity() instanceof EntityPlayer) {//死亡实体是玩家
            EntityPlayer player = (EntityPlayer) event.getEntity();
            ItemStack item = player.getHeldItemMainhand();
            ItemStack item2 = player.getHeldItem(EnumHand.OFF_HAND);
            String handItemName = item.getUnlocalizedName();
            String handItemName2 = item2.getUnlocalizedName();
            if (handItemName.equals(trailblazerName)) {
                try {
                    item.getTagCompound().setInteger("levelup", item.getTagCompound().getInteger("levelup") / 2);//死亡时，如果主手是开拓者，掉一半能量
                } catch (NullPointerException e) {
                    logger.error("No nbt key called \"levelup\" on this sword");
                }
            }
            if (handItemName2.equals(trailblazerName)) {
                try {
                    item2.getTagCompound().setInteger("levelup", item.getTagCompound().getInteger("levelup") / 2);//死亡时，如果副手是开拓者，掉一半能量
                } catch (NullPointerException e) {
                    logger.error("No nbt key called \"levelup\" on this sword");
                }
            }
        }
    }

    @Cancelable
    public static class hitBlockBoom extends net.minecraftforge.event.entity.player.PlayerEvent {
        public final BlockPos pos;
        public final World world;

        public hitBlockBoom(EntityPlayer player, BlockPos pos, World world) {
            super(player);
            this.pos = pos;
            this.world = world;
        }
    }

    @SubscribeEvent
    public static void deepSeaProteect(LivingAttackEvent event) {
        if (event.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntity();
            ItemStack item = player.getHeldItemMainhand();
            ItemStack item1 = player.getHeldItem(EnumHand.OFF_HAND);
            String handItemName = item.getUnlocalizedName();
            String handItemName1 = item1.getUnlocalizedName();
            float health = 0.1F;
            if (handItemName.equals(swordDeepSeaName)) {
                if (player.isInLava()) {
                    int chooseOfStoneGate = (int) (Math.random() * 2);
                    if (chooseOfStoneGate == 0) {
                        event.setCanceled(true);
                    } else {
                        if (player.getHealth() < 20) {
                            if ((player.getHealth() + health) >= 20) {
                                player.setHealth(20);
                            } else {
                                player.setHealth(player.getHealth() + health);
                            }
                        }
                    }
                }
            }
            if (handItemName1.equals(swordDeepSeaName)) {
                if (player.isInLava()) {
                    int chooseOfStoneGate = (int) (Math.random() * 2);
                    if (chooseOfStoneGate == 0) {
                        event.setCanceled(true);
                    } else {
                        if (player.getHealth() < 20) {
                            if ((player.getHealth() + health) >= 20) {
                                player.setHealth(20);
                            } else {
                                player.setHealth(player.getHealth() + health);
                            }
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void deepSeaFoodandAir(TickEvent.PlayerTickEvent event) {
        ItemStack item = event.player.getHeldItemMainhand();
        ItemStack item1 = event.player.getHeldItem(EnumHand.OFF_HAND);
        String handItemName = item.getUnlocalizedName();
        String handItemName1 = item1.getUnlocalizedName();
        float playerHealth = event.player.getHealth();
        if (event.player.isInWater()) {
            if (handItemName.equals(swordDeepSeaName)) {
                event.player.setAir(300);
                if (playerHealth < 20) {
                    if ((event.player.getMaxHealth() * swordDeepSeaBloodReturaRate + event.player.getHealth()) >= 20) {
                        event.player.setHealth(20);
                    } else {
                        event.player.setHealth(event.player.getMaxHealth() * swordDeepSeaBloodReturaRate + event.player.getHealth());
                    }
                }
            }
            if (handItemName1.equals(swordDeepSeaName)) {
                event.player.setAir(300);
                if (playerHealth < 20) {
                    if ((event.player.getMaxHealth() * swordDeepSeaBloodReturaRate + event.player.getHealth()) >= 20) {
                        event.player.setHealth(20);
                    } else {
                        event.player.setHealth(event.player.getMaxHealth() * swordDeepSeaBloodReturaRate + event.player.getHealth());
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void baldrTreatCD(TickEvent.PlayerTickEvent event) {
        ItemStack item = event.player.getHeldItemMainhand();
        String handItemName = item.getUnlocalizedName();

        if (handItemName.equals(swordbaldrName)) {
            int baldrCount = item.getTagCompound().getInteger("count");
            if (baldrCount < 1000) {
                item.getTagCompound().setInteger("count", baldrCount + 1);
            }
        }
    }

    @SubscribeEvent
    public static void vanRageCount(TickEvent.PlayerTickEvent event) {
        ItemStack item = event.player.getHeldItemMainhand();
        String handItemName = item.getUnlocalizedName();
        World world = event.player.world;

        if (handItemName.equals(swordVanName)) {
            int rageCount = item.getTagCompound().getInteger("count");
            int tick = item.getTagCompound().getInteger("tick");
            boolean vanSwitch = item.getTagCompound().getBoolean("switch");

            if (vanSwitch == true) {
                if (tick > 0) {
                    world.setRainStrength(2);
                    item.getTagCompound().setInteger("tick", tick - 1);
                    item.getTagCompound().setInteger("count", 0);
                } else if (tick == 0) {
                    item.getTagCompound().setInteger("count", 0);
                    item.getTagCompound().setBoolean("switch", false);
                    item.getTagCompound().setInteger("tick", 100);
                }
            }
        }
    }

    @SubscribeEvent
    public static void vanRageNeverDie(LivingAttackEvent event) {
        if (event.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntity();
            ItemStack item = player.getHeldItemMainhand();
            String handItemName = item.getUnlocalizedName();

            if (handItemName.equals(swordVanName)) {
                if (item.getTagCompound().getBoolean("switch") == true) {
                    event.setCanceled(true);
                }
            }
        }
    }

    @SubscribeEvent
    public static void infinitySwordCancel(AttackEntityEvent event) {
        EntityPlayer player = event.getEntityPlayer();
        Entity target = event.getTarget();

        if (player.getHeldItemMainhand().getUnlocalizedName().equals(swordinfinityName) & target instanceof EntityPlayer) {
            event.setCanceled(true);
        }
    }

    /*
        @SubscribeEvent
        public static void attackEvent(LivingAttackEvent event) {
            if (event.getEntity() instanceof EntityMob) {
                System.out.println();
            }
        }
    */

    /**
     * 获取虎鲸坐标
     *
     * @param event 生成事件
     */
    /*
    @SubscribeEvent
    public static void getEntityKillerWhalePos(EntityEvent event) {
        if (event.getEntity() instanceof EntityKillerWhale) {
            EntityKillerWhale entityKillerWhale = (EntityKillerWhale) event.getEntity();
            Vec3d vec3d = entityKillerWhale.getPositionVector();

            logger.info("生成虎鲸:" + vec3d.toString());
        }
    }
    */
}

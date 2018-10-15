package com.github.ksgfk.oceanheart.util;

import net.minecraft.util.DamageSource;

/*
 *KSGFK创建于2018/10/14
 *伤害类型
 */
public class DamageSourceHandlers extends DamageSource {

    public static final DamageSource PHYSIC = (new DamageSourceHandlers("physic")).setPhysicDamage();

    private boolean physicDamage;

    public DamageSourceHandlers(String damageTypeIn) {
        super(damageTypeIn);
    }

    public DamageSourceHandlers setPhysicDamage() {
        this.physicDamage = true;
        return this;
    }

    public static DamageSourceHandlers causePhysicDamage() {
        return (new DamageSourceHandlers("physic")).setPhysicDamage();
    }
}

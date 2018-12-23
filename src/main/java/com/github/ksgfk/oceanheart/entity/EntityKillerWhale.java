package com.github.ksgfk.oceanheart.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class EntityKillerWhale extends EntityAnimal {
    float wanderFactor;

    public EntityKillerWhale(World worldIn) {
        super(worldIn);
        setSize(2, 2);
        setHealth(50);
        setRotationYawHead(90);

        this.wanderFactor = 16.0F;
    }

    @Nullable
    @Override
    public EntityAgeable createChild(EntityAgeable ageable) {
        return null;
    }

/*
    @Override
    protected void initEntityAI() {
        this.tasks.addTask(5, new AIRandomFly(this));
    }
*/
    // [VanillaCopy] from EntityGhast but we use wanderFactor instead, we also stop moving when we have a target
    public static class AIRandomFly extends EntityAIBase {
        private final EntityKillerWhale parentEntity;

        public AIRandomFly(EntityKillerWhale ghast) {
            this.parentEntity = ghast;
            this.setMutexBits(1);
        }

        @Override
        public boolean shouldExecute() {
            EntityMoveHelper entitymovehelper = this.parentEntity.getMoveHelper();
            if (!entitymovehelper.isUpdating()) {
                return parentEntity.getAttackTarget() == null;
            } else {
                double d0 = entitymovehelper.getX() - this.parentEntity.posX;
                double d1 = entitymovehelper.getY() - this.parentEntity.posY;
                double d2 = entitymovehelper.getZ() - this.parentEntity.posZ;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                return parentEntity.getAttackTarget() == null && (d3 < 1.0D || d3 > 3600.0D);
            }
        }

        @Override
        public boolean shouldContinueExecuting() {
            return false;
        }

        @Override
        public void startExecuting() {
            Random random = this.parentEntity.getRNG();
            double d0 = this.parentEntity.posX + (double) ((random.nextFloat() * 2.0F - 1.0F) * parentEntity.wanderFactor);
            double d1 = this.parentEntity.posY + (double) ((random.nextFloat() * 2.0F - 1.0F) * parentEntity.wanderFactor);
            double d2 = this.parentEntity.posZ + (double) ((random.nextFloat() * 2.0F - 1.0F) * parentEntity.wanderFactor);
            this.parentEntity.getMoveHelper().setMoveTo(d0, d1, d2, 1.0D);
        }
    }
}


package com.github.ksgfk.oceanheart.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityOHFireBall extends EntityFireball {
    private float explosionPower;

    private EntityOHFireBall(World worldIn) {
        super(worldIn);
    }

    public EntityOHFireBall(World worldIn, float explosionPower) {
        super(worldIn);
        this.explosionPower = explosionPower;
    }

    public EntityOHFireBall(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ, float explosionPower) {
        super(worldIn);
        this.shootingEntity = shooter;
        this.setSize(1.0F, 1.0F);
        this.setLocationAndAngles(shooter.posX, shooter.posY, shooter.posZ, shooter.rotationYaw, shooter.rotationPitch);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        accelX = accelX + this.rand.nextGaussian() * 0.4D;
        accelY = accelY + this.rand.nextGaussian() * 0.4D;
        accelZ = accelZ + this.rand.nextGaussian() * 0.4D;
        double d0 = (double) MathHelper.sqrt(accelX * accelX + accelY * accelY + accelZ * accelZ);
        this.accelerationX = accelX / d0 * 0.1D;
        this.accelerationY = accelY / d0 * 0.1D;
        this.accelerationZ = accelZ / d0 * 0.1D;
        this.explosionPower = explosionPower;
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (!this.world.isRemote) {
            if (result.entityHit != null) {
                result.entityHit.attackEntityFrom(DamageSource.causeFireballDamage(this, this.shootingEntity), 30.0F);
                this.applyEnchantments(this.shootingEntity, result.entityHit);
            }
            boolean flag = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this.shootingEntity);
            this.world.newExplosion(null, this.posX, this.posY, this.posZ, this.getExplosionPower(), flag, flag);
            this.setDead();
        }
    }

    public float getExplosionPower() {
        return explosionPower;
    }

    public void setExplosionPower(float explosionPower) {
        this.explosionPower = explosionPower;
    }
}

package com.github.ksgfk.oceanheart.util;

public interface IRenderModel<K> {
    /**
     * 渲染模型的接口
     *
     * @param entity       生物类
     * @param x            翻译矩阵坐标用的x?
     * @param y            翻译矩阵坐标用的y?
     * @param z            翻译矩阵坐标用的z?
     * @param yaw          坐标偏转?
     * @param partialTicks 局部tick?
     */
    public void render(K entity, double x, double y, double z, float yaw, float partialTicks);
}

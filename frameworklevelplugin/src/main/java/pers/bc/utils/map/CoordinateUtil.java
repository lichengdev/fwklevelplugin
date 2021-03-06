package pers.bc.utils.map;

/**
 * 经纬度相关的阿一些操作
 * @qualiFild nc.pub.itf.tools.map.CoordinateUtil.java<br>
 * @author：licheng<br>
 * @date Created on 2019-8-12<br>
 * @version 1.0<br>
 */
public final class CoordinateUtil
{
    
    /**
     * *********************************************************** <br>
     * 说明： <br>
     * 计算地球上任意两点(经纬度)距离
     * @see <br>
     * @param long1 第一点经度
     * @param lat1 第一点纬度
     * @param long2 第二点经度
     * @param lat2 第二点纬度
     * @return 返回距离 单位：米<br>
     * @double <br>
     * @methods nc.pub.itf.tools.map.CoordinateUtil#distance <br>
     * @author licheng <br>
     * @date Created on 2019-8-12 <br>
     * @time 上午10:33:36 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public final static double distance(double long1, double lat1, double long2, double lat2)
    {
        double a, b, R;
        R = 6378137; // 地球半径
        lat1 = lat1 * Math.PI / 180.0;
        lat2 = lat2 * Math.PI / 180.0;
        a = lat1 - lat2;
        b = (long1 - long2) * Math.PI / 180.0;
        double d;
        double sa2, sb2;
        sa2 = Math.sin(a / 2.0);
        sb2 = Math.sin(b / 2.0);
        d = 2 * R * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1) * Math.cos(lat2) * sb2 * sb2));
        return d;
    }
}

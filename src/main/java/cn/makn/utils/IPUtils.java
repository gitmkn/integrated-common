package cn.makn.utils;

import javax.print.DocFlavor;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: IP工具类
 * @author: makn
 * @version: V1.0
 * @date: 2020/12/29 9:34
 */
public class IPUtils {

    private static ReentrantLock lock = new ReentrantLock();
    private static String lockIP = null;

    /**
     * @param
     * @return
     * @Description: 获取本地IP
     * @author makn
     * @date 2021/1/7 14:48
     */
    public static String getLocalHostIP() {
        try {
            lock.lock();
            if (BusiUtils.isNull(lockIP)) {
                try {
                    lockIP = InetAddress.getLocalHost().getHostAddress();
                } catch (UnknownHostException e) {
                    throw new RuntimeException("An exception occurred in obtaining the local IP address");
                }
            }
        } finally {
            lock.unlock();
        }
        return lockIP;
    }

    /**
     * ip地址转成long型数字
     * 将IP地址转化成整数的方法如下：
     * 1、通过String的split方法按.分隔得到4个长度的数组
     * 2、通过左移位操作（<<）给每一段的数字加权，第一段的权为2的24次方，第二段的权为2的16次方，第三段的权为2的8次方，最后一段的权为1
     *
     * @param strIp
     * @return
     */
    public static long ipToLong(String strIp) {
        String[] ips = strIp.split("\\.");
        int ipFour = 0;
        //因为每个位置最大255，刚好在2进制里表示8位
        for (String ip4 : ips) {
            Integer ip4a = Integer.parseInt(ip4);
            //这里应该用+也可以,但是位运算更快
            ipFour = (ipFour << 8) | ip4a;
        }
        return ipFour;
    }

    /**
     * 将十进制整数形式转换成127.0.0.1形式的ip地址
     * 将整数形式的IP地址转化成字符串的方法如下：
     * 1、将整数值进行右移位操作（>>>），右移24位，右移时高位补0，得到的数字即为第一段IP。
     * 2、通过与操作符（&）将整数值的高8位设为0，再右移16位，得到的数字即为第二段IP。
     * 3、通过与操作符吧整数值的高16位设为0，再右移8位，得到的数字即为第三段IP。
     * 4、通过与操作符吧整数值的高24位设为0，得到的数字即为第四段IP。
     *
     * @param longIp
     * @return
     */
    public static String longToIP(long longIp) {
        StringBuffer sb = new StringBuffer("");
        // 直接右移24位
        sb.append(String.valueOf((longIp >>> 24)));
        sb.append(".");
        // 将高8位置0，然后右移16位
        sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16));
        sb.append(".");
        // 将高16位置0，然后右移8位
        sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8));
        sb.append(".");
        // 将高24位置0
        sb.append(String.valueOf((longIp & 0x000000FF)));
        return sb.toString();
    }

    // 单元测试
    public static void main(String[] args) {
        System.out.println(ipToLong("127.0.0.1"));
        System.out.println(longToIP(2130706433));
        System.out.println("获取本地IP：" + getLocalHostIP());
    }
}

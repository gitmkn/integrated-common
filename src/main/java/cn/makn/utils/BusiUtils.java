package cn.makn.utils;

import java.util.Collection;
import java.util.Map;

/**
 * @Description: 公共处理工具类
 * @author makn
 * @date 2021/1/7 11:01
 * @param
 * @return
 */
public class BusiUtils {

    /**
     *  判断两值是否相等
     * @param s
     * @param t
     * @return
     */
    public static boolean isEquals(String s, String t){
        return s.equals(t);
    }
    /**
     *  是否等于Y
     * @param str
     * @return
     */
    public static boolean isEqualsY(String str){
        return isEquals("Y", str);
    }

    /**
     * 判断对象是否为空
     * @param obj
     * @return
     */
    public static boolean isNull(Object obj){
        if(obj == null){
            return true;
        }
        if(String.class.isInstance(obj)){
            return obj == null || ((String) obj).length() == 0;
        } else if (Collection.class.isInstance(obj)) {
            return ((Collection<?>)obj).size() == 0;
        } else if (Map.class.isInstance(obj)) {
            return ((Map<?, ?>) obj).size() == 0;
        } else {
            return obj == null;
        }
    }

    /**
     * 判断对象不为空
     * @param obj
     * @return
     */
    public static boolean isNotNull(Object obj){
        return !isNull(obj);
    }

    /**
     * 对象为空（包括list, string）, 返回dest
     * @param source
     * @param dest
     * @param <T>
     * @return
     */
    public static <T> T nvl(T source, T dest){
        return isNull(source) ? dest : source;
    }

    /**
     * 对象是否存在null
     * @param objs
     * @return
     */
    public static boolean existNull(Object... objs){
        for(int i = 0; i < objs.length; i++){
            if (isNull(objs[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * 多字符拼接
     * @param strs
     * @return
     */
    public static String appendString(String... strs) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            if (isNotNull(strs[i])) {
                sb.append(strs[i]);
            }
        }
        return sb.toString();
    }

    /**
     * 对象是否都不为空
     * @param objs
     * @return
     */
    public static boolean isNotNullAll(Object... objs){
        for (int i = 0; i < objs.length; i++) {
            if(isNotNull(objs[i])){
                return true;
            }
        }
        return false;
    }

}

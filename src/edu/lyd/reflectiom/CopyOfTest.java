package edu.lyd.reflectiom;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 针对泛型数组的扩展
 *
 * @version Array.newInstance(Class type, int length)
 *
 */
public class CopyOfTest {

    /**
     * 错误的扩展方法
     *
     * @version 此处的newArray在初始化时，即为Object类型，这样的数组后面是无法转换为其他对象数组的
     *          仅有一开始为其他对象的数组，临时转为Object[],这样的Object[]才可以进行转换
     *          （java数组会记住创建数组时，new表达式使用的元素类型）
     *
     * @param a
     * @param newLength
     * @return
     */
    public static Object[] badCopyOf (Object[] a, int newLength) {

        //创造Object类型的数组，这种后期无法进行转换
        Object[] newArray = new Object[newLength];
        System.arraycopy(a,0,newArray,0,Math.min(a.length,newLength));
        return newArray;
    }

    /**
     * 可以使用的数组扩展方法
     * @return
     */
    public static Object goodCopyOf(Object a, int newLength) {
        Class cl = a.getClass();
        if(!cl.isArray()){
            return null;
        }

        //得到数组类型
        Class componentType = cl.getComponentType();
        int length = Array.getLength(a);

        //创造新数组（创造指定类型的新数组）
        Object newArray = Array.newInstance(componentType,newLength);
        System.arraycopy(a,0,newArray,0,Math.min(length,newLength));

        return newArray;
    }

    public static void main(String[] args) {

        int[] a = {1,2,3};
        a = (int[]) goodCopyOf(a,10);
        System.out.println(Arrays.toString(a));

        String[] b = {"李白", "杜甫", "白居易"};
       // b = (String[]) badCopyOf(b,10);
    }

}

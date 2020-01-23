package edu.lyd.reflectiom;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 利用反射调用任意方法
 *
 * @version method.invoke(Object obj, Object...args)
 */
public class MethodTableTest {

   public static void main(String[] args) throws NoSuchMethodException {

       Method square = MethodTableTest.class.getMethod("square", double.class);
       Method sqrt = Math.class.getMethod("sqrt", double.class);

       printTable(1,10,10,square);
       printTable(1,10,10,sqrt);
   }


    public static double square (double x) {
        return x*x;
    }

    public static void printTable (double from, double to, int n, Method f) {
        System.out.println(f);

        double dx = (to - from) / (n-1);
        for (double x = from; x <= to; x += dx){

            try {
                double y = (double) f.invoke(null,x);

                System.out.println(x + ":" +y);

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

    }
}

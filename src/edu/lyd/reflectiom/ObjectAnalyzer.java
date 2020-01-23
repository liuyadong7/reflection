package edu.lyd.reflectiom;


import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/**
 * 利用反射分析对象，实现通用的toString方法
 *
 * @version 分析对象的关键在于对象的属性值，利用Field类型的对象f.get(obj),
 *          可以获得obj属性的值
 */
public class ObjectAnalyzer {

    private ArrayList<Object> visited = new ArrayList<>();

    public String toString(Object obj) {

        if(obj == null){
            return  "null";
        }

        //已经分析过的对象忽略
        if(visited.contains(obj)){
            return "...";
        }

        visited.add(obj);
        Class cl =obj.getClass();

        /*
        字符串
         */
        if(cl == String.class){
            return (String) obj;
        }

        /*
        数组
         */
        if(cl.isArray()){
            String r = cl.getComponentType() + "[]{";   //得到什么类型的数组

            //Array.getLength(obj)  返回数组长度
            for(int i = 0; i < Array.getLength(obj); i++){

                if(i > 0){
                   r += ",";
                }
                Object val = Array.get(obj,i);
                //确定数组的对象是否一个基本数据类型
                if (cl.getComponentType().isPrimitive()) {
                    r += val;
                } else {
                    //非基本数据类型，采取递归来调取toString方法
                    r += toString(val);
                }
            }

            return r+"}";
        }

        /*
        针对对象（除去String）
         */
        String r = cl.getName();

        do{
            r += "[";
            Field[] fields = cl.getDeclaredFields();

            //开放私有属性的权限，反射可以访问
            AccessibleObject.setAccessible(fields, true);

            for (Field f : fields) {
                if(!Modifier.isStatic(f.getModifiers())){
                    if (!r.endsWith("[")) {
                        r += ",";
                    }
                    r += f.getName() + "=";

                    try {
                        Class t = f.getType();
                        Object val = f.get(obj);

                        if (t.isPrimitive()) {
                            r += val;
                        } else {
                            r += toString(val);
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            r += "]";
            cl = cl.getSuperclass();
        }while(cl != null);

        return r;
    }

}

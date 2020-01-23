package edu.lyd.reflectiom;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

/**
 * 检查类的结构
 */
public class ReflectionTest {

    /**
     * 检查构造器
     * @param cl
     */
    public static void printConstructors(Class cl) {

        Constructor[] constructors = cl.getDeclaredConstructors(); //得到此类或接口的所有构造方法

        for (Constructor c : constructors) {
            String name = c.getName();
            System.out.print("   ");
            String modifiers = Modifier.toString(c.getModifiers());
            if(modifiers.length() >0){
                System.out.print(modifiers + " ");
            }
            System.out.print(name + " (");

            /*
            获取此构造器的参数类型
             */
            Class[] paramType = c.getParameterTypes();
            for (int j = 0; j < paramType.length; j++){
                if(j>0){
                    System.out.print(",");
                }
                System.out.print(paramType[j].getName());
            }
            System.out.println(");");
        }
    }

    /**
     * 检查方法
     * @param cl
     */
    public static void printMethods(Class cl){

        Method[] methods = cl.getDeclaredMethods();

        for (Method m : methods) {
            /*
            得到返回类型
             */
            Class retType = m.getReturnType();
            String name = m.getName();

            System.out.print("   ");
            String modifiers = Modifier.toString(m.getModifiers());
            if(modifiers.length()>0){
                System.out.print(modifiers + " ");
            }
            System.out.print(retType.getName() + " " + name +" (");

            /*
            获取此方法的参数类型
             */
            Class[] paramType = m.getParameterTypes();
            for (int j = 0; j < paramType.length; j++){
                if(j>0){
                    System.out.print(",");
                }
                System.out.print(paramType[j].getName());
            }
            System.out.println(");");
        }
    }

    /**
     * 检查属性
     * @param cl
     */
    public static void printFields(Class cl){

        Field[] fields = cl.getDeclaredFields();

        for (Field f : fields) {
            Class paramType = f.getType();      //获取属性的类型
            String name = f.getName();

            System.out.print("   ");
            String modifier = Modifier.toString(f.getModifiers());
            if(modifier.length()>0){
                System.out.print(modifier + " ");
            }
            System.out.print(paramType + " ");
            System.out.print(name);
            System.out.println(";");
        }

    }

    public static void main(String[] args) {

        String name;

        /*
        得到name的值
         */
        if(args.length >0 ){
            name = args[0];
        } else {
            Scanner in = new Scanner(System.in);
            System.out.println("输入Class的类路径：");
            name = in.next();
        }

        /*
        处理类
         */
        try {
            Class cl = Class.forName(name);
            Class superCl = cl.getSuperclass();

            String modifiers = Modifier.toString(cl.getModifiers());    //获得访问修饰符
            if(modifiers.length() > 0){
                System.out.print(modifiers + " ");
            }
            System.out.print("Class " + name);
            if(superCl != null && superCl != Object.class){
                System.out.print(" extends " + superCl.getName());
            }
            System.out.print(" { \n");
            System.out.println();
            System.out.println("   ****************构造器***************");
            printConstructors(cl);
            System.out.println();
            System.out.println("   ****************方法***************");
            printMethods(cl);
            System.out.println();
            System.out.println("   ****************属性***************");
            printFields(cl);
            System.out.println("}");

         } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.exit(0);
    }

}

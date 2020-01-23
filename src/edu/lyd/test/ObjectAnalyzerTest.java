package edu.lyd.test;

import edu.lyd.reflectiom.ObjectAnalyzer;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ObjectAnalyzerTest {

    public static void main(String[] args) {

        ArrayList<Integer> list = new ArrayList<>();

        for (int i=0; i<5; i++){

            list.add(i*i);
        }

        System.out.println(new ObjectAnalyzer().toString(list));
    }

}

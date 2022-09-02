package edu.yu.da;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class StockYourBookshelfTest {
    @Test
    public void testBasics(){
        Map<String, List<Integer>> seforimClassToTypePrices = new HashMap<>();
        List<Integer> A = new ArrayList<>();
        A.add(1);
        A.add(4);
        A.add(7);
        seforimClassToTypePrices.put("4A", A);
        List<Integer> B = new ArrayList<>();
        B.add(2);
        B.add(6);
        B.add(4);
        seforimClassToTypePrices.put("1B", B);
        List<Integer> C = new ArrayList<>();
        C.add(1);
        C.add(4);
        C.add(10);
        seforimClassToTypePrices.put("2C", C);
        List<Integer> D = new ArrayList<>();
        D.add(9);
        D.add(3);
        D.add(2);
        seforimClassToTypePrices.put("3D", D);
        StockYourBookshelfI bookS = new StockYourBookshelf();
        System.out.println(bookS.maxAmountThatCanBeSpent(14, seforimClassToTypePrices));
        System.out.println(bookS.solution()); ///2,4,2,7

    }
    @Test
    public void testCantStock(){
        Map<String, List<Integer>> seforimClassToTypePrices = new HashMap<>();
        List<Integer> A = new ArrayList<>();
        A.add(3);
        A.add(4);
        //A.add(5);
        seforimClassToTypePrices.put("Ay", A);
        List<Integer> B = new ArrayList<>();
        B.add(5);
        B.add(6);
        //B.add();
        seforimClassToTypePrices.put("Be", B);
        StockYourBookshelfI bookS = new StockYourBookshelf();
        System.out.println(bookS.maxAmountThatCanBeSpent(7, seforimClassToTypePrices));
        System.out.println(bookS.solution());
    }

    @Test
    public void testOneClass(){
        Map<String, List<Integer>> seforimClassToTypePrices = new HashMap<>();
        List<Integer> A = new ArrayList<>();
        A.add(3);
       // A.add(4);
        //A.add(5);
        seforimClassToTypePrices.put("Ay", A);
        List<Integer> B = new ArrayList<>();
        B.add(5);
       // B.add(6);
        //B.add();
        seforimClassToTypePrices.put("Be", B);
        List<Integer> C = new ArrayList<>();
        B.add(7);
        // B.add(6);
        //B.add();
        seforimClassToTypePrices.put("C", C);
        StockYourBookshelfI bookS = new StockYourBookshelf();
        System.out.println(bookS.maxAmountThatCanBeSpent(8, seforimClassToTypePrices));
        System.out.println(bookS.solution());
    }


}
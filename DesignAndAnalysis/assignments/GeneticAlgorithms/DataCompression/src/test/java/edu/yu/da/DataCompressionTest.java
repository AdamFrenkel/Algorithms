package edu.yu.da;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class DataCompressionTest {
    @Test
    public void test1(){
        List<String> list= new ArrayList<>();
        list.add("apple");
        list.add("carrot");
        list.add("banana");
        list.add("Fish");
        list.add("Meat");
        list.add("Cow");
        list.add("apples");
        list.add("carrots");
        list.add("bananas");
        list.add("hello");
        list.add("challah rolls");
        list.add("grape juice");
        list.add("school of fish");
        list.add("WOW! Meat");
        list.add("Okay Cow");
        list.add("apple pie");
        list.add("carrot cake");
        list.add("banana pie");
        list.add("Fishing");
        list.add("Meaty");
        list.add("Coward");
        list.add("apple pies");
        list.add("carrot color");
        list.add("banana shape");
        list.add("Fish food");
        list.add("Meatsss");
        list.add("Cowsss");
        list.add("apple32");
        list.add("carrot32");
        list.add("banana33");
        list.add("Fish4");
        list.add("Meat54");
        list.add("Cow44");
        list.add("town");
        list.add("apple44");
        list.add("carrot vv");
        list.add("bananakjsdnb");
        list.add("oafsdhkdFish");
        list.add("WOW! Meat");
        list.add("Okay Cow");
        list.add("two apple");
        list.add("thress carrot");
        list.add("fik banana");
        list.add("ssFish");
        list.add("Mdfeat");
        list.add("Cofgw");
        list.add("grape juice");
        list.add("school of fish");
        list.add("Meats");
        list.add("Cows");
        list.add("Megat");
        list.add("Cgow");
        list.add("fik banana");
        list.add("ssFish");
        list.add("Mdfeat");
        list.add("Cofgw");
        list.add("apgple");
        list.add("cargrot");
        list.add("banagna");
        list.add("Figsh");
        list.add("Megat");
        list.add("Cgow");
        DataCompression dc = new DataCompression(list);
        int beat = DataCompressionI.bytesCompressed(list);
        System.out.println(beat);
        assertEquals(beat, dc.firstNComp);
        GeneticAlgorithmConfig gac = new GeneticAlgorithmConfig(30,1000000,beat/1.05, GeneticAlgorithmConfig.SelectionType.TOURNAMENT,.05,.84);
        DataCompressionI.SolutionI s = dc.solveIt(gac);
        if(s != null) {
            System.out.println(s.relativeImprovement());
            System.out.println(s.nGenerations());
            List<String> newList = s.getList();
           // list.sort();
            Collections.sort(list);
            Collections.sort(newList);
            assertEquals(list, newList);
        }else{
            fail();
        }

    }
    @Test
    public void Test2() {
      //  for (int j = 0; j < 5; j++) {
            int leftLimit = 97; // letter 'a'
            int rightLimit = 122; // letter 'z'
            int sizeOfList = (int) ((Math.random() * (50)) + 25);
            List<String> list = new ArrayList<>();
            for (int i = 0; i < sizeOfList; i++) {
                int targetStringLength = (int) ((Math.random() * (6)) + 3);
                Random random = new Random();

                String generatedString = random.ints(leftLimit, rightLimit + 1)
                        .limit(targetStringLength)
                        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                        .toString();

                list.add(generatedString);
            }
            DataCompression dc = new DataCompression(list);
            int beat = DataCompressionI.bytesCompressed(list);
            System.out.println("Size to beat: " + beat);
            GeneticAlgorithmConfig gacR = new GeneticAlgorithmConfig(30, 10000, beat / 1.03, GeneticAlgorithmConfig.SelectionType.ROULETTE, .05, .84);
            DataCompressionI.SolutionI sR = dc.solveIt(gacR);
            System.out.println("Roul");
            if (sR != null) {
                System.out.println("Relative Improvement: " + sR.relativeImprovement());
                System.out.println("Gens: " + sR.nGenerations());
                List<String> newList = sR.getList();
                // list.sort();
                Collections.sort(list);
                Collections.sort(newList);
                assertEquals(list, newList);
            } else {
                System.out.println("Nope Roul");
            }
            beat = DataCompressionI.bytesCompressed(list);
            GeneticAlgorithmConfig gac = new GeneticAlgorithmConfig(30, 10000, beat / 1.03, GeneticAlgorithmConfig.SelectionType.TOURNAMENT, .05, .84);
            DataCompressionI.SolutionI s = dc.solveIt(gac);
            System.out.println("Tourn");
            if (s != null) {
                System.out.println("Relative Improvement: " + s.relativeImprovement());
                System.out.println("Gens: " + s.nGenerations());
                List<String> newList = s.getList();
                // list.sort();
                Collections.sort(list);
                Collections.sort(newList);
                assertEquals(list, newList);
            } else {
                System.out.println("Nope tourney");
            }


       // }
    }
    @Test
    public void test3(){
        List<String> list = Arrays.asList("AAAAA", "BBBBB", "AAAAA", "BBBBB", "AAAAA", "BBBBB","AAAAA","BBBBB","AAAAA","BBBBB");
        int beat = DataCompressionI.bytesCompressed(list);
        System.out.println(beat);
        DataCompression dc = new DataCompression(list);
        assertEquals(beat, dc.firstNComp);
        GeneticAlgorithmConfig gac = new GeneticAlgorithmConfig(2,1000000,beat / 1.5, GeneticAlgorithmConfig.SelectionType.ROULETTE,.05,.84);
        DataCompressionI.SolutionI s = dc.solveIt(gac);
        if(s != null) {
            System.out.println(s.relativeImprovement());
            System.out.println(s.nGenerations());
            List<String> newList = s.getList();
            // list.sort();
            Collections.sort(list);
            Collections.sort(newList);
            assertEquals(list, newList);
        }else{
            fail();
        }
    }


}
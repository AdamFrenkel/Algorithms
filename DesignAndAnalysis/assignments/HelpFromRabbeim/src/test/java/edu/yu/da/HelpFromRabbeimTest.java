package edu.yu.da;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class HelpFromRabbeimTest {
    @Test
    public void test1(){
        HelpFromRabbeim hfr = new HelpFromRabbeim();
        List<HelpFromRabbeimI.HelpTopics> hts1 = new ArrayList<>();
        hts1.add(HelpFromRabbeimI.HelpTopics.MISHNAYOS);
        HelpFromRabbeimI.Rebbe r1 = new HelpFromRabbeimI.Rebbe(1,hts1);
        List<HelpFromRabbeimI.Rebbe> rebbes = new ArrayList<>();
        rebbes.add(r1);
        Map<HelpFromRabbeimI.HelpTopics,Integer> htMap = new HashMap<>();
        htMap.put(HelpFromRabbeimI.HelpTopics.MISHNAYOS, 1);
        System.out.println(hfr.scheduleIt(rebbes,htMap));
    }

    @Test
    public void test2(){
        HelpFromRabbeim hfr = new HelpFromRabbeim();
        List<HelpFromRabbeimI.HelpTopics> hts1 = new ArrayList<>();
        hts1.add(HelpFromRabbeimI.HelpTopics.BAVA_KAMMA);
        hts1.add(HelpFromRabbeimI.HelpTopics.BROCHOS);
        hts1.add(HelpFromRabbeimI.HelpTopics.SANHEDRIN);
        HelpFromRabbeimI.Rebbe r1 = new HelpFromRabbeimI.Rebbe(1,hts1);
        List<HelpFromRabbeimI.Rebbe> rebbes = new ArrayList<>();
        rebbes.add(r1);
        List<HelpFromRabbeimI.HelpTopics> hts2 = new ArrayList<>();
        hts2.add(HelpFromRabbeimI.HelpTopics.BAVA_KAMMA);
        HelpFromRabbeimI.Rebbe r2 = new HelpFromRabbeimI.Rebbe(2,hts2);
        rebbes.add(r2);
        List<HelpFromRabbeimI.HelpTopics> hts3 = new ArrayList<>();
        hts3.add(HelpFromRabbeimI.HelpTopics.BAVA_KAMMA);
        HelpFromRabbeimI.Rebbe r3 = new HelpFromRabbeimI.Rebbe(3,hts3);
        rebbes.add(r3);
        Map<HelpFromRabbeimI.HelpTopics,Integer> htMap = new HashMap<>();
        htMap.put(HelpFromRabbeimI.HelpTopics.BAVA_KAMMA, 2);
        htMap.put(HelpFromRabbeimI.HelpTopics.BROCHOS,1);
        System.out.println(hfr.scheduleIt(rebbes,htMap));
    }

    @Test
    public void test3(){
        HelpFromRabbeim hfr = new HelpFromRabbeim();
        List<HelpFromRabbeimI.HelpTopics> hts1 = new ArrayList<>();
        hts1.add(HelpFromRabbeimI.HelpTopics.BAVA_KAMMA);
        hts1.add(HelpFromRabbeimI.HelpTopics.BROCHOS);
        hts1.add(HelpFromRabbeimI.HelpTopics.SANHEDRIN);
        HelpFromRabbeimI.Rebbe r1 = new HelpFromRabbeimI.Rebbe(1,hts1);
        List<HelpFromRabbeimI.Rebbe> rebbes = new ArrayList<>();
        rebbes.add(r1);
        List<HelpFromRabbeimI.HelpTopics> hts2 = new ArrayList<>();
        hts2.add(HelpFromRabbeimI.HelpTopics.BAVA_KAMMA);
        hts2.add(HelpFromRabbeimI.HelpTopics.BROCHOS);
        HelpFromRabbeimI.Rebbe r2 = new HelpFromRabbeimI.Rebbe(2,hts2);
        rebbes.add(r2);
        List<HelpFromRabbeimI.HelpTopics> hts3 = new ArrayList<>();
        hts3.add(HelpFromRabbeimI.HelpTopics.BAVA_KAMMA);
        HelpFromRabbeimI.Rebbe r3 = new HelpFromRabbeimI.Rebbe(3,hts3);
        rebbes.add(r3);
        Map<HelpFromRabbeimI.HelpTopics,Integer> htMap = new HashMap<>();
        htMap.put(HelpFromRabbeimI.HelpTopics.BAVA_KAMMA, 1);
        htMap.put(HelpFromRabbeimI.HelpTopics.SANHEDRIN, 1);
        htMap.put(HelpFromRabbeimI.HelpTopics.BROCHOS,1);
        Map<Integer, HelpFromRabbeimI.HelpTopics> a = hfr.scheduleIt(rebbes,htMap);
        assertEquals(a.get(1), HelpFromRabbeimI.HelpTopics.SANHEDRIN);
        assertEquals(a.get(2), HelpFromRabbeimI.HelpTopics.BROCHOS);
        assertEquals(a.get(3), HelpFromRabbeimI.HelpTopics.BAVA_KAMMA);


    }
    @Test
    public void sample(){
        HelpFromRabbeim hfr = new HelpFromRabbeim();
        List<HelpFromRabbeimI.Rebbe> rebbes = new ArrayList<>();
        List<HelpFromRabbeimI.HelpTopics> hts1 = new ArrayList<>();
        hts1.add(HelpFromRabbeimI.HelpTopics.BROCHOS);
        hts1.add(HelpFromRabbeimI.HelpTopics.MUSSAR);
        HelpFromRabbeimI.Rebbe A = new HelpFromRabbeimI.Rebbe(1,hts1);
        rebbes.add(A);
        Map<HelpFromRabbeimI.HelpTopics,Integer> htMap = new HashMap<>();
        htMap.put(HelpFromRabbeimI.HelpTopics.BROCHOS,1);
        System.out.println(hfr.scheduleIt(rebbes,htMap));
    }


}
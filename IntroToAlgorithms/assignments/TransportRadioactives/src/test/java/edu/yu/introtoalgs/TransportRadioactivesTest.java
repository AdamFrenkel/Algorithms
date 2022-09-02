package edu.yu.introtoalgs;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class TransportRadioactivesTest {
    @Test
    public void testBasic1() {
        System.out.println((TransportRadioactives.transportIt(3, 2)));
    }
    @Test
    public void testBasic2() {
        List<TransportationState> list = (TransportRadioactives.transportIt(4, 3));
        Iterator<TransportationState> iter = list.listIterator();
        TransportationState ts = iter.next();
        assertEquals(4,ts.getMithiumSrc());
        assertEquals(3,ts.getCathiumSrc());
        assertEquals(TransportationState.Location.SRC,ts.truckLocation());
        assertEquals(0, ts.getMithiumDest());
        assertEquals(0,ts.getCathiumDest());
        ts = iter.next();
        assertEquals(3,ts.getMithiumSrc());
        assertEquals(2,ts.getCathiumSrc());
        assertEquals(TransportationState.Location.DEST,ts.truckLocation());
        assertEquals(1, ts.getMithiumDest());
        assertEquals(1,ts.getCathiumDest());
        ts = iter.next();
        assertEquals(3,ts.getMithiumSrc());
        assertEquals(3,ts.getCathiumSrc());
        assertEquals(TransportationState.Location.SRC,ts.truckLocation());
        assertEquals(1, ts.getMithiumDest());
        assertEquals(0,ts.getCathiumDest());
        ts = iter.next();
        assertEquals(2,ts.getMithiumSrc());
        assertEquals(2,ts.getCathiumSrc());
        assertEquals(TransportationState.Location.DEST,ts.truckLocation());
        assertEquals(2, ts.getMithiumDest());
        assertEquals(1,ts.getCathiumDest());
        ts = iter.next();
        assertEquals(3,ts.getMithiumSrc());
        assertEquals(2,ts.getCathiumSrc());
        assertEquals(TransportationState.Location.SRC,ts.truckLocation());
        assertEquals(1, ts.getMithiumDest());
        assertEquals(1,ts.getCathiumDest());
        ts = iter.next();
        assertEquals(2,ts.getMithiumSrc());
        assertEquals(1,ts.getCathiumSrc());
        assertEquals(TransportationState.Location.DEST,ts.truckLocation());
        assertEquals(2, ts.getMithiumDest());
        assertEquals(2,ts.getCathiumDest());
        ts = iter.next();
        assertEquals(2,ts.getMithiumSrc());
        assertEquals(2,ts.getCathiumSrc());
        assertEquals(TransportationState.Location.SRC,ts.truckLocation());
        assertEquals(2, ts.getMithiumDest());
        assertEquals(1,ts.getCathiumDest());
        ts = iter.next();
        assertEquals(1,ts.getMithiumSrc());
        assertEquals(1,ts.getCathiumSrc());
        assertEquals(TransportationState.Location.DEST,ts.truckLocation());
        assertEquals(3, ts.getMithiumDest());
        assertEquals(2,ts.getCathiumDest());


    }
    @Test
    public void testBasic3() {
        System.out.println((TransportRadioactives.transportIt(5, 1)));
    }
    @Test
    public void testBasic4() {
        System.out.println((TransportRadioactives.transportIt(6, 5)));
    }
    @Test
    public void testBasic5() {
        //TODO: Fix this case
        System.out.println((TransportRadioactives.transportIt(1, 1)));

    }


}
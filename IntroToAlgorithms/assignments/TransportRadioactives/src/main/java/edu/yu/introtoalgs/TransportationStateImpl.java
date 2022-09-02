package edu.yu.introtoalgs;

import java.util.Objects;

/** Implements the TransportationState interface.
 *
 *
 * Students may ONLY use the specified constructor, and may (perhaps even
 * encouraged to) add as many other methods as they choose.
 *
 * @author Avraham Leff
 */

public class TransportationStateImpl implements TransportationState {
  private int miSrc;
  private int miDst;
  private int caSrc;
  private int caDst;
  private Location truckLocation;
  private final int totalMi;
  private final int totalCa;
//  private int miTrk;
//  private int caTrk;

  /** Constructor:
   *
   * @param mithiumAtSrc amount of mithium at the src location, must be >= 0
   * @param cathiumAtSrc amount of cathium at the src location, must be >= 0
   * @param truckLocation location of the truck, must not be null
   * @param totalMithium sum of mithium amounts at src + dest, must be > 0
   * @param totalCathium sum of cathium amounts at src + dest, must be > 0
   *
   * @Students: you may NOT USE ANY OTHER CONSTRUCTOR SIG
   */
  public TransportationStateImpl(final int mithiumAtSrc,
                                 final int cathiumAtSrc,
                                 final Location truckLocation,
                                 final int totalMithium,
                                 final int totalCathium)
  {
    if(mithiumAtSrc<0){
      throw new IllegalArgumentException("too little mith");
    }
    if(cathiumAtSrc<0){
      throw new IllegalArgumentException("too little cath");
    }
    if(truckLocation == null){
      throw new IllegalArgumentException("null truck");
    }
    if(totalCathium<=0 || totalMithium<=0){ //****** need clarification
      throw new IllegalArgumentException("illeagal totals");
    }
    this.miSrc = mithiumAtSrc;
    this.caSrc = cathiumAtSrc;
    this.truckLocation = truckLocation;
    this.totalMi =totalMithium;
    this.totalCa = totalCathium;

  } // constructor

  protected void setMiSrc(int x){
    this.miSrc = x;
  }
  protected void setMiDst(int x){
    this.miDst = x;
  }
  protected void setCaSrc(int x){
    this.caSrc = x;
  }
  protected void setCaDst(int x){
    this.caDst = x;
  }
  protected void setTruckLoc(Location x){
    this.truckLocation = x;
  }


  @Override
  public int getMithiumSrc() { return miSrc; }

  @Override
  public int getCathiumSrc() { return caSrc; }
    
  @Override
  public int getMithiumDest() { return totalMi - miSrc; }
    
  @Override
  public int getCathiumDest() { return totalCa - caSrc; }
    
  @Override
  public Location truckLocation() { return truckLocation; }

  @Override
  public int getTotalMithium() { return totalMi; }

  @Override
  public int getTotalCathium() { return totalCa; }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TransportationStateImpl that = (TransportationStateImpl) o;
    return miSrc == that.miSrc && miDst == that.miDst && caSrc == that.caSrc && caDst == that.caDst && totalMi == that.totalMi && totalCa == that.totalCa && truckLocation == that.truckLocation;
  }

  @Override
  public int hashCode() {
    return Objects.hash(miSrc, miDst, caSrc, caDst, truckLocation, totalMi, totalCa);
  }

  @Override
  public String toString() {
//    miTrk = totalMi -miSrc -miDst;
//    caTrk = totalCa -caSrc -caDst;
    return "{ "
            + miSrc + " mi "
            + caSrc + " ca at src.\n    " +
            + (totalMi-miSrc) + " mi "
            + (totalCa-caSrc) + " ca dest.\n"
       //     + miTrk + " mi and " + caTrk + " ca in truck.\n"
            + " Truck is at " + truckLocation +
            ".}\n";
  }
}   // class
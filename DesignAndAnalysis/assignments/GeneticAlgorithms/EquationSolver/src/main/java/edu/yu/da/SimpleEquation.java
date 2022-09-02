package edu.yu.da;

/** Stubbed implementation of the SimpleEquationI interface.
 *
 * @author Avraham Leff
 */

import java.util.*;

public class SimpleEquation implements SimpleEquationI {
  /** Constructor.
   *
   * Students MAY NOT define any other constructor signature.  They
   * MAY change the stubbed implemention in any way they choose.
   */
  public SimpleEquation() {

  }

  @Override
  public SolutionI solveIt(final GeneticAlgorithmConfig gac) {
    int initPopSize = gac.getInitialPopulationSize();
    double threshold = gac.getThreshold();
    GeneticAlgorithmConfig.SelectionType selection = gac.getSelectionType();
    ArrayList<Chromosome> population = new ArrayList<>();
    for(int i = 0; i < initPopSize; i++){
      Chromosome c = new Chromosome();
      if(c.fitness >= threshold){
        return(this.finish(c,1));
      }
      population.add(c);


    }
//    System.out.println(population);
//    population.sort(reverseComparator);  // first step put initial population in order from most fit to least fit
 //System.out.println(population);

    int maxGen = gac.getMaxGenerations();
    List<Chromosome> children = new ArrayList<Chromosome>();
      for(int gen = 1; gen<=maxGen; gen++) {

          if (selection == GeneticAlgorithmConfig.SelectionType.ROULETTE) {
              // for(int gen = 1; gen<=maxGen; gen++) {
              if(population.size() >= 2000){
                  //population.sort(reverseComparator);
                  Iterator<Chromosome> iter = population.iterator();
                  ArrayList<Chromosome> newPop = new ArrayList<>();
                  for(int i = 0; i < population.size()/2; i++){
                      newPop.add(iter.next());
                  }
                  population = newPop;
               //   Collections.shuffle(population);
              }
              children = this.roulette(population, gac.getMutationProbability(), gac.getCrossoverProbability(), population.size());
          } else {
              children = this.tournament(population, gac.getMutationProbability(), gac.getCrossoverProbability());
          }
           /**
            * Checking fitness
            */
          for(Chromosome child : children){

              if(child.fitness>=threshold){
                  return(this.finish(child,gen));
              }
          }
          /**
           * Creating the gen
           */
//         // if(gen != maxGen) {
//          List<Chromosome> newPop = new ArrayList<>();
//          population.addAll(children);
//          population.sort(reverseComparator);
//          Iterator<Chromosome> popIter = population.listIterator();
//          for(int i = 0; i <initPopSize ; i++){
//              if(popIter.hasNext()) {
//                  newPop.add(popIter.next());
//              }
//          }
//        //  System.out.println(newPop.get(0));  here is the main print
//          if(gen != maxGen) {
//              Collections.shuffle(newPop);
//              population = newPop;
//          } else{
//              Chromosome best = newPop.get(0);
//              return this.finish(best, gen);
//          }
             // population
         // }
          population.addAll(children);
          if(gen == maxGen) {
              population.sort(reverseComparator);
              Chromosome best = population.get(0);
              return this.finish(best, gen);
          }


      }
   // System.out.println(children);
    return null;
  }

    private List<Chromosome> roulette(ArrayList<Chromosome> population, double mutationProbability, double crossoverProbability, int size) {
        ArrayList<Chromosome> parents = new ArrayList<>();
        population.sort(reverseComparator);
   //     Iterator<Chromosome> iter1 = population.listIterator();
        Iterator<Chromosome> iter2 = population.listIterator();
        //Iterator<Chromosome> iter3 = population.listIterator();
        //Iterator<Chromosome> iter4 = population.listIterator();
//        ArrayList<Chromosome> quarter = new ArrayList<>();
//       // System.out.println("pop" + population);
//        for(int i = 0; i <size/4; i++){
//            if(iter1.hasNext()){
//                quarter.add(iter1.next());
//               // System.out.println("quarter " + quarter);
//            }
//        }
        //Collections.shuffle(quarter);
        ArrayList<Chromosome> tenth = new ArrayList<>();
        for(int i = 0; i < size/10; i++){
            if(iter2.hasNext()){
                tenth.add(iter2.next());
            }
        }
       Collections.shuffle(tenth);
//        ArrayList<Chromosome> fifth = new ArrayList<>();
//        for(int i = 0; i < size/5; i++){
//            if(iter3.hasNext()){
//                fifth.add(iter3.next());
//            }
//        }
       // Collections.shuffle(fifth);
//        ArrayList<Chromosome> top = new ArrayList<>();
//        for(int i = 0; i < size/40; i++){
//            if(iter4.hasNext()){
//                top.add(iter4.next());
//            }
//        }
        //Collections.shuffle(top);
        //Collections.shuffle(population);
        //List[] wheel = new ArrayList[]{population,quarter,tenth,fifth,top};
        List[] wheel = new ArrayList[]{population,tenth};
        for(int j = 0; j< size/10; j++){
            int slice =  (int)(Math.random() * (2));
           // System.out.println(slice);
            boolean didntFind = true;
            while(didntFind) {
                if (wheel[slice].size() != 0) {
                    int pre = parents.size();
                    parents.add((Chromosome) wheel[slice].remove(0));
                    int post = parents.size();
                    if (pre != post){
                        didntFind = false;
                    }
                }else{
                    slice =  (slice + 1) % 2;
                }
            }
        }
//        ArrayList<Chromosome> parentsList = new ArrayList<>(parents);
//        parentsList.sort(reverseComparator);
//        System.out.println(parentsList.get(0));
        return this.mate(parents,mutationProbability,crossoverProbability);

    }

    private List<Chromosome> tournament(List<Chromosome> population, double mutationProbability, double crossoverProbability) {
    //    List<Chromosome> popCopy = List.copyOf(population);
//        Collections.shuffle(popCopy);
    List<Chromosome> parents = new ArrayList<>();
    Iterator<Chromosome> iter = population.iterator();
   // System.out.println("pop = " + population);
    ///for(int i = 1; i<=population.size()/5; i++) {//rounds //Will turn into while loop with iter bn
    while(iter.hasNext()) {
      List<Chromosome> round = new ArrayList<>();
     for (int j = 1; j <= 3; j++) {//teams per round
       if(iter.hasNext()) {
         round.add(iter.next());
       }
     }
//          if(i == 33){
//            round.add(iter.next()); //last round has 4 teams
//          }
      round.sort(reverseComparator);
      if (!round.isEmpty()) {
        parents.add(round.get(0));
      }
    }

   // }
   // System.out.println("parents = " + parents);
      /**
       *Mating
       */

    List<Chromosome> children = this.mate(parents,mutationProbability,crossoverProbability);

    return children;
  }

  private List<Chromosome> mate(Collection<Chromosome> parents, double mutationProb, double crossoverProb) {
    List<Chromosome> children = new ArrayList<>();
   // parents.sort(reverseComparator);
    Iterator<Chromosome> iter = parents.iterator();
      int crossPoint = (int)(Math.random() * (3));
   // Random rd = new Random(); // creating Random object
    while(iter.hasNext()) {
      Chromosome mom = iter.next();
      if(mutationProb < Math.random()) {
          if (!(iter.hasNext())) {
              children.add(new Chromosome(mom.xValue, mom.yValue));
              break;
          }
          Chromosome dad = iter.next();
          if (crossoverProb < Math.random()) { // if not crossing over
              children.add(new Chromosome(mom.xValue, mom.yValue));
              children.add(new Chromosome(dad.xValue, dad.yValue));
          } else {
              int[] xVal1 = new int[7];
              int[] yVal1 = new int[7];
              int[] xVal2 = new int[7];
              int[] yVal2 = new int[7];
              for (int j = 0; j <= 6; j++) {
                  if (j < crossPoint || j>crossPoint+3) {
                     // System.out.println("here");
                      xVal1[j] = mom.xBinaryArr[j];
//                  System.out.println(mom.xBinaryArr[j]);
//                  System.out.println(xVal1[j]);
                      yVal1[j] = mom.yBinaryArr[j];
                      xVal2[j] = dad.xBinaryArr[j];
                      yVal2[j] = dad.yBinaryArr[j];
                  } else {
                      xVal1[j] = dad.xBinaryArr[j];
                      yVal1[j] = dad.yBinaryArr[j];
                      xVal2[j] = mom.xBinaryArr[j];
                      yVal2[j] = mom.yBinaryArr[j];
                  }
              }

//          for(int i = 0; i<=6 ; i ++){
//              System.out.println(xVal1[i]);
//          }
              children.add(new Chromosome(xVal1, yVal1));
              children.add(new Chromosome(xVal2, yVal2));
//        String binaryString1X = String.valueOf(mom.xBinaryString.subSequence(0, 3)) + dad.xBinaryString.subSequence(4, 6);
//        String binaryString1Y = String.valueOf(mom.yBinaryString.subSequence(0, 3)) + dad.yBinaryString.subSequence(4, 6);
//        children.add(new Chromosome(Integer.parseInt(binaryString1X,2),Integer.parseInt(binaryString1Y,2)));
//        String binaryString2X = String.valueOf(dad.xBinaryString.subSequence(0, 3)) + mom.xBinaryString.subSequence(4, 6);
//        String binaryString2Y = String.valueOf(dad.yBinaryString.subSequence(0, 3)) + mom.yBinaryString.subSequence(4, 6);
//        children.add(new Chromosome(Integer.parseInt(binaryString2X,2),Integer.parseInt(binaryString2Y,2)));
          }
      }else{
          Chromosome newC = new Chromosome(mom.xValue, mom.yValue);
          newC.mutate();
          children.add(newC);
      }


    }
    //  System.out.println("children = " + children);
    return children;
  }

  private SolutionI finish(Chromosome chrom, int gen) {
    return new SolutionI() {

      @Override
      public int getX() {
        return chrom.xValue;
      }

      @Override
      public int getY() {
        return chrom.yValue;
      }

      @Override
      public double fitness() {
        return chrom.fitness;
      }

      @Override
      public int nGenerations() {
        return gen;
      }

        @Override
        public String toString() {
            return "fitness = " + chrom.fitness +
                    "\nx = " + chrom.xValue +
                    "\ny = " + chrom.yValue +
                    "\ngenerations = " + gen;
        }
    };
  }

  private class Chromosome implements Comparator<Chromosome>{
    private int xValue;
    int[] xBinaryArr = new int[7];
    String xBinaryString;
    private int yValue;
    int[] yBinaryArr = new int[7];
    String yBinaryString;
    double fitness;

    public Chromosome() {
      xValue = (int) ((Math.random() * (100)));
      yValue = (int) ((Math.random() * (100)));
      this.create();
//        for(int i = 0; i<=6 ; i ++) {
//            System.out.println("at end");
//            System.out.println(xBinaryArr[i]);
//            System.out.println(yBinaryArr[i]);
//        }
    }
    public Chromosome(int x, int y) {
      xValue = x;
      yValue = y;
      this.create();
    }
      public Chromosome(int[] x, int[] y) {
          setXValue(x);
          setYValue(y);
          fitness = 6 * xValue - (xValue * xValue) + 4 * yValue - (yValue * yValue);//6x - x^2 + 4y - y^2
          xBinaryString = String.valueOf(xValue);
          yBinaryString = String.valueOf(yValue);
      }
    private void create(){
     //   System.out.println(xValue);
      xBinaryString = Integer.toBinaryString(xValue);
      // System.out.println(xBinaryString);
      for(int i = xBinaryString.length() - 1; i >=0; i--){
        xBinaryArr[i] = xBinaryString.charAt(i) - 48;
//          System.out.println("Here " + xBinaryString.charAt(i));
//          System.out.println(xBinaryArr[i]);
      }
      yBinaryString = Integer.toBinaryString(yValue);
        for(int i = yBinaryString.length() - 1; i >=0; i--){
            yBinaryArr[i] = yBinaryString.charAt(i) -48;
        }
//        for(int i = 0; i<=6 ; i ++) {
//            System.out.println("at beg");
//            System.out.println(xBinaryArr[i]);
//            System.out.println(yBinaryArr[i]);
//        }
      fitness = 6 * xValue - (xValue * xValue) + 4 * yValue - (yValue * yValue);//6x - x^2 + 4y - y^2
//        for(int i = 0; i<=6 ; i ++) {
//            System.out.println("mid");
//            System.out.println(xBinaryArr[i]);
//            System.out.println(yBinaryArr[i]);
//        }
    }

    @Override
    public String toString() {
      return "Chromosome{" +
              "fitness=" + fitness +
              '}';
    }

    public double getFitness() {
      return fitness;
    }

    public void setXValue(int[] intArr){
        if(intArr.length != 7){
            System.out.println("problem with setXValue");
        }
        xBinaryArr = intArr;
        xValue = 0;
        double multiplier = .5;
        for(int j = 6; j >= 0 ; j--){
            multiplier *=2;
            xValue += (intArr[j] * multiplier);
        }
        fitness = 6 * xValue - (xValue * xValue) + 4 * yValue - (yValue * yValue);//6x - x^2 + 4y - y^2
      }



      public void setYValue(int[] intArr){
          if(intArr.length != 7){
              System.out.println("problem with setXValue");
          }
          yBinaryArr = intArr;
          yValue = 0;
          double multiplier = .5;
          for(int j = 6; j >= 0 ; j--){
              multiplier *=2;
              yValue += (intArr[j] * multiplier);
          }
          fitness = 6 * xValue - (xValue * xValue) + 4 * yValue - (yValue * yValue);//6x - x^2 + 4y - y^2
      }

    //@Override
    public int compareTo(Chromosome o1, Chromosome o2) {
      if(o1.getFitness() > o2.getFitness()){
        return 1;
      }
      if(o1.getFitness() < o2.getFitness()){
        return -1;
      }
      return 0;
    }

    @Override
    public int compare(Chromosome o1, Chromosome o2) {
      if(o1.getFitness() > o2.getFitness()){
        return 1;
      }
      if(o1.getFitness() < o2.getFitness()){
        return -1;
      }
      return 0;
    }

      public void mutate() {
          int rand = (int)(Math.random() * ((13) + 1)); //random number 0- 13
          if(rand<=6){
              if(xBinaryArr[rand] == 0){
                  xBinaryArr[rand] = 1;
              } else {
                  xBinaryArr[rand] = 0;
              }
              setXValue(xBinaryArr);
          } else{
              if(yBinaryArr[rand- 7] == 0){
                  yBinaryArr[rand - 7] = 1;
              } else {
                  yBinaryArr[rand - 7] = 0;
              }
              setXValue(yBinaryArr);
          }
      }
  }
  public static Comparator<Chromosome> reverseComparator = new Comparator<Chromosome>() {

    @Override
    public int compare(Chromosome o1, Chromosome o2) {
      if(o1.getFitness() < o2.getFitness()){
        return 1;
      }
      if(o1.getFitness() > o2.getFitness()){
        return -1;
      }
      return 0;
    }
  };
} // public class SimpleEquation

package edu.yu.da;

import java.sql.SQLOutput;
import java.util.*;

public class DataCompression implements DataCompressionI {
    ArrayList<Chromosome> population = new ArrayList<>();
    List<String> original;
    int firstNComp = 0;
    /** Constructor.
     *
     * @param original the list whose elements we want to reorder
     * to reduce the
     * number of bytes when compressing the list.
     */
    public DataCompression ( final List<String> original) {
        this.original = original;
        firstNComp = DataCompressionI.bytesCompressed(original);
    }
    @Override
    public int nCompressedBytesInOriginalList() {
        return firstNComp;
    }

    private class Chromosome{
        List<String> strings = new ArrayList<>();
        int fitness;
//        private Chromosome(List<String> parent){
//            for(String s : parent){
//                String add = new String(s);
//                strings.add(add);
//
//            }
////            List<String> strings1 = List.copyOf(parent);
////            strings = new ArrayList<>(Integer.)
//            Collections.shuffle(strings);
//            this.fitness = DataCompressionI.bytesCompressed(strings);
////            System.out.println("parent size = " + parent.size());
////            System.out.println("strings size = " + strings.size());
//        }
        private Chromosome(List<String> parent, boolean copy){
            for(String s : parent){
                String add = new String(s);
                strings.add(add);
            }
            if(!copy){
                Collections.shuffle(strings);
            }
            this.fitness = DataCompressionI.bytesCompressed(strings);
//            System.out.println("parent size = " + parent.size());
//            System.out.println("strings size = " + strings.size());
        }
    }

    public static Comparator<Chromosome> comparator = new Comparator<Chromosome>() {

        @Override
        public int compare(Chromosome o1, Chromosome o2) {
            if (o1.fitness > o2.fitness) {
                return 1;
            }
            if (o1.fitness < o2.fitness) {
                return -1;
            }
            return 0;
        }
    };


    @Override
    public SolutionI solveIt(GeneticAlgorithmConfig gac) {
        double threshold = gac.getThreshold();
        GeneticAlgorithmConfig.SelectionType selection = gac.getSelectionType();
        Chromosome first = new Chromosome(original, true);
        population.add(first);
        if (first.fitness <= threshold) {
            return this.finish(first,1);
        }
        for (int i = 0; i < gac.getInitialPopulationSize() - 1; i++) { //-1 because added OG in first step
            Chromosome n = new Chromosome(original, false);
            population.add(n);
            if (n.fitness <= threshold) {
                return this.finish(n,1);
            }
        }


      //  System.out.println("before selection type");
        int maxGen = gac.getMaxGenerations();
        List<Chromosome> children = new ArrayList<>();
        for (int gen = 1; gen <= maxGen; gen++) {
            if (selection == GeneticAlgorithmConfig.SelectionType.ROULETTE) {
                // for(int gen = 1; gen<=maxGen; gen++) {
                if (population.size() >= 2_000) {
                    //population.sort(reverseComparator);
                    Iterator<Chromosome> iter = population.iterator();
                    ArrayList<Chromosome> newPop = new ArrayList<>();
                    for (int i = 0; i < population.size() / 2; i++) {
                        newPop.add(iter.next());
                    }
                    population = newPop;
                    //   Collections.shuffle(population);
                }
                  children = this.roulette(population, gac.getMutationProbability(), gac.getCrossoverProbability(), population.size());
            } else {
                if (population.size() >= 2_000) { //450,000 is great
                    population.sort(comparator);
                    Iterator<Chromosome> iter = population.iterator();
                    ArrayList<Chromosome> newPop = new ArrayList<>();
                    for (int i = 0; i < population.size() / 2; i++) {
                        newPop.add(iter.next());
                    }
                    population = newPop;
                    Collections.shuffle(population);
                }
                   children = this.tournament(population, gac.getMutationProbability(), gac.getCrossoverProbability());
            }
            /**
             * Checking fitness
             */
            int max = Integer.MIN_VALUE;
            for (Chromosome child : children) {

                if (child.fitness <= threshold) {
                      return(this.finish(child,gen));
//                    return null;
                }
                if(child.fitness>max){
                    max = child.fitness;
                }
            }
         //   System.out.println(max);
            population.addAll(children);
          //  System.out.println(children.get(0).fitness);
        }
        return null;
    }

    private SolutionI finish(Chromosome first, int gen) {
        SolutionI a = new SolutionI() {
            @Override
            public List<String> getList() {
                return first.strings;
            }

            @Override
            public List<String> getOriginalList() {
                return original;
            }

            @Override
            public double relativeImprovement() {
                double og = DataCompressionI.bytesCompressed(original);
                double nw = DataCompressionI.bytesCompressed(first.strings);
                return og/nw;
            }

            @Override
            public int nGenerations() {
                return gen;
            }
        };
        return a;
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
            for (int j = 1; j <= 10; j++) {//teams per round
                if(iter.hasNext()) {
                    round.add(iter.next());
                }
            }
//          if(i == 33){
//            round.add(iter.next()); //last round has 4 teams
//          }
            round.sort(comparator);
            if (!round.isEmpty()) {
                parents.add(round.get(0));
            }
        }

        // }
        // System.out.println("parents = " + parents);
        /**
         *Mating
         */
        //System.out.println("before mate");
        List<Chromosome> children = this.mate(parents,mutationProbability,crossoverProbability);

        return children;
    }

    private List<Chromosome> mate(Collection<Chromosome> parents, double mutationProb, double crossoverProb) {
        List<Chromosome> children = new ArrayList<>();
        // parents.sort(reverseComparator);
        Iterator<Chromosome> iter = parents.iterator();
        //int crossPoint = (int)(Math.random() * (3));
        // Random rd = new Random(); // creating Random object
        while(iter.hasNext()) {
        //    System.out.println("mating process");
            Chromosome mom = iter.next();

            Set<String> pairs = new HashSet<>();
            List<String> doubles1 = new ArrayList<>();
            List<String> doubles2 = new ArrayList<>();
            for(String s :  mom.strings){
                int og = pairs.size();
                pairs.add(s);
                int nw = pairs.size();
                if(og == nw){
                    doubles1.add(s);
                    doubles2.add(s);
                }
            }
          //  System.out.println("mating process 1");
            if(mutationProb < Math.random()) {
          //      System.out.println("mating process 2");
                if (!(iter.hasNext())) {
                //    System.out.println("mating process 3");
                    children.add(new Chromosome(mom.strings,true));
                    break;
                }
                Chromosome dad = iter.next();
                if (crossoverProb < Math.random()) { // if not crossing over
                 //   System.out.println("no cross");
                    children.add(new Chromosome(mom.strings,true));
                    children.add(new Chromosome(dad.strings,true));
                } else {
                 //   System.out.println("about to start");
                    int count = 1;
                    int split = original.size()/2;
                    Set<String> child1Set = new HashSet<>();
                    List<String> child1 = new ArrayList<>();
                    for(String m : mom.strings){
                     //   System.out.println("child 1 creation");
                        int og = child1Set.size();
                        child1Set.add(m);
                        int nw = child1Set.size();
                        if(nw == og){
                            if(doubles1.contains(m)){
                                child1.add(m);
                                doubles1.remove(m);
                            }
                        }else{
                            child1.add(m);
                        }
                        if(count>= split){
                            break;
                        }
                        count++;
                    }
                    count = 1;
                    for(String d : dad.strings){
                       // System.out.println(count + "in dad");
                        if(count<= split){
                            count++;
                            continue;
                        }
                        int og = child1Set.size();
                        child1Set.add(d);
                        int nw = child1Set.size();
                        if(nw == og){
                            if(doubles1.contains(d)){
                                child1.add(d);
                                doubles1.remove(d);
                            }
                        }else{
                            child1.add(d);
                        }
                        count++;

                    }
                    for(String m : mom.strings){
                        //   System.out.println("child 1 creation");
                        int og = child1Set.size();
                        child1Set.add(m);
                        int nw = child1Set.size();
                        if(nw == og){
                            if(doubles1.contains(m)){
                                child1.add(m);
                                doubles1.remove(m);
                            }
                        }else{
                            child1.add(m);
                        }
                    }
                  //  while(child1.size())
                    children.add(new Chromosome(child1, true));
                    count = 1;
                    List<String> child2 = new ArrayList<>();
                    Set<String> child2Set = new HashSet<>();
                    for(String d : dad.strings){
                        // System.out.println(count + "in mom");
                        int og = child2Set.size();
                        child2Set.add(d);
                        int nw = child2Set.size();
                        if(nw == og){
                            if(doubles2.contains(d)){
                                child2.add(d);
                                doubles2.remove(d);
                            }
                        }else{
                            child2.add(d);
                        }
                        if(count>= split){
                            break;
                        }
                        count++;
                    }
                    count = 1;
                    for(String m : mom.strings){
                        // System.out.println(count + "in dad");
                        if(count<= split){
                            count++;
                            continue;
                        }
                        int og = child2Set.size();
                        child2Set.add(m);
                        int nw = child2Set.size();
                        if(nw == og){
                            if(doubles2.contains(m)){
                                child2.add(m);
                                doubles2.remove(m);
                            }
                        }else{
                            child2.add(m);
                        }
                        count++;
                        //System.out.println("mated a child");
                    }
                    for(String d : dad.strings){
                        // System.out.println(count + "in mom");
                        int og = child2Set.size();
                        child2Set.add(d);
                        int nw = child2Set.size();
                        if(nw == og){
                            if(doubles2.contains(d)){
                                child2.add(d);
                                doubles2.remove(d);
                            }
                        }else{
                            child2.add(d);
                        }
                    }
                    assert(child2.containsAll(original));
                    children.add(new Chromosome(child2, true));
//                    List<String> childAStrings = new ArrayList<>();
//                    for(String s : child1){
//                        childAStrings.add(s);
//                    }
                    //System.out.println("size check:");
//                    System.out.println(dad.strings.size());
//                    System.out.println(child1.size());
//                    System.out.println(dad.strings.size());
//                    System.out.println(child2.size());

//                    Set<String> child = new HashSet<>();
//                    Iterator<String> iterA = mom.strings.iterator();
//                    Iterator<String> iterB = dad.strings.iterator();
//                    int twenty = 0;
//                    while (child.size() < original.size()) {
//                        twenty++;
//                        System.out.println("child size : " + child.size());
//                        System.out.println("og size " + original.size());
//                        //System.out.println("iter a size " + iterA.);
//                       // System.out.println("iter b size");
//                        if(child.size()<original.size()/2 || !iterB.hasNext()){
//                            System.out.println("first half");
//                            if(iterA.hasNext()){
//                                System.out.println("iter a has mamish");
//                                child.add(iterA.next());
//                                if(iterB.hasNext()){
//                                    iterB.next();
//                                }
//                            }
//                        }else{
//                            System.out.println("second half");
//                            child.add(iterB.next());
//                        }
//                        if(20 == twenty){
//                            break;
//                        }
//                    }
//                    List<String> childAStrings = new ArrayList<>();
//                    for(String s : child){
//                        childAStrings.add(s);
//                    }
//                    System.out.println(childAStrings);
////          for(int i = 0; i<=6 ; i ++){
////              System.out.println(xVal1[i]);
////          }
//                  //  children.add(new Chromosome(xVal1, yVal1));
//                  //  children.add(new Chromosome(xVal2, yVal2));
                }
            }else{
               // System.out.println("yes cross");
                Chromosome newC = new Chromosome(mom.strings,true);
                int size = original.size() - 1;
                int rand1 =  (int) ((Math.random() * (size - 1)));
                int rand2 =  (int) ((Math.random() * (size - 2)));
                if(rand2 == rand1){
                    rand2 =  (int) ((Math.random() * size));
                    if(rand2 == rand1){
                        rand2 =  (int) ((Math.random() * size));
                    }
                }
                //assert(newC.strings.containsAll(original));
                String replace = newC.strings.remove(rand1);
                newC.strings.add(rand2, replace);
                //assert(newC.strings.containsAll(original));
//                newC.mutate();
                children.add(newC);
            }


        }

        //  System.out.println("children = " + children);
      // System.out.println("done mating");
        return children;
    }

    private List<Chromosome> roulette(ArrayList<Chromosome> population, double mutationProbability, double crossoverProbability, int size) {
        ArrayList<Chromosome> parents = new ArrayList<>();
        population.sort(comparator);
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


}

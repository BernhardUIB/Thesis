import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class main {
    public static void main(String[] args){
        sim Simulation = new sim(2500,10,00,300);
       // sim Simulation2 = new sim(400,20,20,1);
        int runNumb = 1000;
        ArrayList<ArrayList<Integer>> simulations = Simulation.run(runNumb);
        ArrayList<Integer> infectedTotalList = new ArrayList<>();
        for(int i = 0; i < simulations.size(); i++){
            int sum = 0;
            for(int j = 0; j < simulations.get(i).get(j); j++){
                sum += simulations.get(i).get(j);
            }
            infectedTotalList.add(sum);
        }
        int min = Collections.min(infectedTotalList);
        int max = Collections.max(infectedTotalList);

        System.out.println(simulations);
        double average = 0;
        for(int a = 0; a < infectedTotalList.size(); a++){
            average += infectedTotalList.get(a);
        }
        average = average/infectedTotalList.size();
        System.out.println(average + " " + simulations.get(0).size());
        System.out.println("min: " + min + ", max: " + max + ", avg: " + average + ", N: " + Simulation.N + ", I: " + Simulation.I + ", P: " + Simulation.P + ", T: " + Simulation.T + ", runNumb: " + runNumb);
       // Simulation2.run(2);


    }
}

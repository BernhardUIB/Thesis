import java.io.*;
import java.util.ArrayList;


public class main {
    public static void main(String[] args) {
        for (int z = 0; z <= 5000; z+=500) { //Loop through with varying number of uptake
            sim Simulation = new sim(5000, 4, z, 50, true);
            // sim Simulation2 = new sim(400,20,20,1); You can potentially create multiple simulations
            int runNumb = 1000; //Number of times to be ran
            ArrayList<ArrayList<Integer>> simulations = Simulation.run(runNumb);
            ArrayList<Integer> infectedTotalList = new ArrayList<>();
            double[] averagesPerDay = new double[Simulation.T];
            for (int i = 0; i < simulations.size(); i++) {
                int sum = 0;
                for (int j = 0; j < simulations.get(i).size(); j++) {
                    sum += simulations.get(i).get(j);
                    averagesPerDay[j] += simulations.get(i).get(j);
                }
                infectedTotalList.add(sum);

            }

            double average = 0;

            for (int a = 0; a < infectedTotalList.size(); a++) {
                average += infectedTotalList.get(a);
            }
            average = average / infectedTotalList.size();
            for (int b = 0; b < averagesPerDay.length; b++) {
                averagesPerDay[b] = averagesPerDay[b] / simulations.size();
            }
          writeAudit(average, averagesPerDay, Simulation.N, Simulation.I, Simulation.P, Simulation.T, simulations.size(), Simulation.uniform);
        }
    }
    public static void writeAudit(double average, double[] averagesPerDay,int N, int I, int P, int T, int runNumb,boolean uniform) {
        File whereWrite = new File("D:\\Bernhard\\Documents\\CSV\\NewResFriends5.csv"); //You would need to change this file path to somewhere you want to write your results

        try {
            FileWriter fw = new FileWriter(whereWrite, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            if(whereWrite.length() == 0){
                for(int i = 0; i < averagesPerDay.length; i++) pw.print("average daily cases day " + (i+1) + ",");
                pw.print("sum" + "," + "N" + "," + "I" + "," + "P" + "," + "T" + "," + "runNumb" + "," + "uniform");
                pw.println();
            }
            for(double value: averagesPerDay) pw.print(value + ",");
            pw.print(average + "," + N + "," + I + "," + P + "," + T + "," + runNumb + "," + uniform);
            pw.println();
            pw.flush();
            pw.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

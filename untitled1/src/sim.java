import java.util.ArrayList;

public class sim {
    int N; //number of people in the simulation
    int I; //number of infected on day0
    double P; //probability of phone
    int T; //duration of simulation in days?
    NodeGenerator gen;
    ArrayList<Node> nodes;
    public sim(int N, int I, int P, int T){
        this.N = N;
        this.I = I;
        this.P = P;
        this.T = T;
        this.gen = new NodeGenerator(N,I,P);
        this.nodes = gen.createNodes();
    }

    public void run(int N) { //number of simulations to be ran with current parameters
        ArrayList<String> result = new ArrayList<>();
        for (int simRan = 0; simRan < N; simRan++) { //iterate over multiple sims of same set of nodes.
            for(int days = 0; days < T; days++) {
                //System.out.println(nodes.get(0).id);
                edge a = new edge(nodes.get(0).id,nodes.get(1).id);
                nodes.get(0).contacts.add(nodes.get(1));
                nodes.get(1).contacts.add(nodes.get(0));


            for(int e = 0; e < nodes.get(0).friendz.size(); e++){
                System.out.println(nodes.get(0).friendz.get(e));
            }




            }
            //below are calculations that can be made on the set of nodes in current sim.
            double no = 0;
            double onetwo = 0;
            double threefive = 0;
            double sixnine = 0;
            double tenmore = 0;
            for (Node node : nodes) {
               // System.out.println(node.friends);
                if (node.friends == 0.0) no += 1;
                if (0.0 < node.friends && node.friends < 3.0) onetwo += 1;
                if (3.0 < node.friends && node.friends < 5.0) threefive += 1;
                if (5.0 < node.friends && node.friends < 10.0) sixnine += 1;
                if (10.0 < node.friends) tenmore += 1;
            }
            result.add(Integer.toString(nodes.size()));
            result.add(Double.toString(no));
            result.add(Double.toString(onetwo));
            result.add(Double.toString(threefive));
            result.add(Double.toString(sixnine));
            result.add(Double.toString(tenmore));
        }
        /*for (int a = 0; a < N*6; a=a+6) {
            //Can paste in the comments under between the print and % calculation to also see the numbers.
            System.out.println("Simulation ran for " + result.get(a) + " nodes.");
            System.out.println("Percentage of no friends:  " + ((Double.parseDouble(result.get(a+1)) / 500) * 100)); //" + no + "
            System.out.println("Percentage of 1-2 friends:  " + ((Double.parseDouble(result.get(a+2)) / 500) * 100)); //" + onetwo + "
            System.out.println("Percentage of 3-5 friends:  " + ((Double.parseDouble(result.get(a+3)) / 500) * 100)); //" + threefive + "
            System.out.println("Percentage of 6-9 friends:  " + ((Double.parseDouble(result.get(a+4)) / 500) * 100)); //" + sixnine + "
            System.out.println("Percentage of ten or more friends:  " + ((Double.parseDouble(result.get(a+5)) / 500) * 100)); //" + tenmore + "
        }*/
    }

}
/*
tid i timer? dager?
kjører en sim med
n noder
i smittede
p smitteprobability
t tid


 */
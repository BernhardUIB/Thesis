import java.util.ArrayList;

public class sim {
    int N; //number of people in the simulation
    int I; //number of infected on day0
    double P; //probability of phone
    int T; //duration of simulation in days/(hours)?
    NodeGenerator gen;
    ArrayList<Node> nodes;
    public sim(int N, int I, double P, int T){
        this.N = N;
        this.I = I;
        this.P = P;
        this.T = T;
        this.gen = new NodeGenerator(N,I,P);
        this.nodes = gen.createNodes();
    }

    public void run(int N){ //number of simulations to be ran with current parameters
        double no = 0;
        double onetwo = 0;
        double threefive = 0;
        double sixnine = 0;
        double tenmore = 0;
        for(Node node: nodes){
            System.out.println(node.friends);
            if(node.friends == 0.0) no += 1;
            if(0.0 < node.friends && node.friends < 3.0) onetwo += 1;
            if(3.0 < node.friends && node.friends < 5.0) threefive += 1;
            if(5.0 < node.friends && node.friends < 10.0) sixnine += 1;
            if(10.0 < node.friends) tenmore += 1;
        }
        //Can paste in the comments under between the print and % calculation to also see the numbers.
        System.out.println("Simulation ran for " + nodes.size() + " nodes.");
        System.out.println("Percentage of no friends:  " + ((no/500)*100)); //" + no + "
        System.out.println("Percentage of 1-2 friends:  " + ((onetwo/500)*100)); //" + onetwo + "
        System.out.println("Percentage of 3-5 friends:  " + ((threefive/500)*100)); //" + threefive + "
        System.out.println("Percentage of 6-9 friends:  " + ((sixnine/500)*100)); //" + sixnine + "
        System.out.println("Percentage of ten or more friends:  " + ((tenmore/500)*100)); //" + tenmore + "

    }


//NodeGenerator(500,2,0.1);
}
/*
tid i timer? dager?
kjører en sim med
n noder
i smittede
p smitteprobability
t tid


 */
import java.util.ArrayList;
import java.util.Random;

public class sim {
    Random random;
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
        for(int simRan = 0; simRan < N; simRan++) { //iterate over multiple sims of same set of nodes.
            random = new Random();

           for(int a = 0; a < I; a++){
               int rand_infected = random.nextInt(N);
               //if(nodes.get(rand_infected) == Node.State.Susceptible){
                 //   System.out.println("Stuff");
             //  }
           }
            for(int days = 0; days < T; days++) {
                System.out.println("Hei");

            }


        }
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
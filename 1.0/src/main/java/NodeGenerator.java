import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class NodeGenerator {
    Random random;

    ArrayList<Node> nodes;
    public  NodeGenerator(int N,int I,int P){ //N is number of nodes, I is number of infected, P is number of phone users (not probability to be phone user)
        nodes = new ArrayList<Node>();
        for(int i = 0; i < N; i++){
                nodes.add(new Node(i));
        }

        //Node a = new Node(1,24,familyRand,workRand,friendRand,resistanceRand,false);
    }
    public ArrayList<Node> createNodes(){
        return nodes;
    }
}

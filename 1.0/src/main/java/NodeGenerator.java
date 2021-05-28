import java.util.ArrayList;
import java.util.Random;

public class NodeGenerator {
    Random random;
    double resistanceRand;

    ArrayList<Node> nodes;
    public  NodeGenerator(int N,int I,int P){ //N is number of nodes, I is number of infected, P is number of phone users (not probability to be phone user)
        nodes = new ArrayList<Node>();
        random = new Random();

        for(int i = 0; i < N; i++){
                nodes.add(new Node(i,resistanceRand));
        }

        //Node a = new Node(1,24,familyRand,workRand,friendRand,resistanceRand,false);
    }
    public ArrayList<Node> createNodes(){

        return nodes;
    }
}

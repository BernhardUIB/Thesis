import java.util.ArrayList;

public class NodeGenerator {

    ArrayList<Node> nodes;
    public  NodeGenerator(int N,int I,int P,boolean uniform){ //N is number of nodes, I is number of infected, P is number of phone users (not probability to be phone user)
        nodes = new ArrayList<>();
        for(int i = 0; i < N; i++){
                Node a = new Node(i,uniform);
                nodes.add(a);
        }

    }
    public ArrayList<Node> createNodes(){
        return nodes;
    }
}

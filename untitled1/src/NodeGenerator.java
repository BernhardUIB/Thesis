import java.util.ArrayList;
import java.util.Random;

public class NodeGenerator {
    Random random;
    double familyRand;
    int age;
    double workRand;
    double friendRand;
    double resistanceRand;
    ArrayList<Node> friendz;
    ArrayList<Node> nodes;
    public  NodeGenerator(int N,int I,int P){ //N is number of nodes, I is number of infected, P is number of phone users (not probability to be phone user)
        nodes = new ArrayList<Node>();
        random = new Random();

        for(int i = 0; i < N; i++){
            age = random.nextInt();
            familyRand = random.nextGaussian();
            workRand = random.nextGaussian();
            friendRand = Math.round((random.nextGaussian()*2.65)+6);
            if(friendRand < 0) friendRand = 0;
            resistanceRand = random.nextGaussian();
            if (i%5==0){
                nodes.add(new Node(i,age,familyRand,workRand,friendRand,resistanceRand,true));
            }
            else{
                nodes.add(new Node(i,age,familyRand,workRand,friendRand,resistanceRand,false));
            }
        }

        //Node a = new Node(1,24,familyRand,workRand,friendRand,resistanceRand,false);
    }
    public ArrayList<Node> createNodes(){

        return nodes;
    }
}

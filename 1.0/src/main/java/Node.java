import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;

public class Node {
    int id; //id
    double resistance; //resistance to contagion to represent genes
    State state;
    int infectedTime;
    int friends;
    double friendz;
    boolean isolated;
    boolean hasPhone;

    enum State{Susceptible,Infected,Recovered} //SIR
    ArrayList<Node> contacts; //close contacts
    ArrayList<Node> friendList;
    boolean quarantined;
    int quarantineTimer;

    public Node(int id,boolean uniform){
        Random random = new Random();
        this.id = id;
        resistance = random.nextGaussian();
        state = State.Susceptible;
        infectedTime = 14;
        friends = 9;
        friendz = random.nextGaussian()*3.5+9; //3.5+2.5 for 3, 3.5+3.76 for 4, 3.5+4.9 for 5, 3.5+5.93 for 6, 3.5*6.98 for 7, 3.5*8 for 8, 3.5*9 for 9
        quarantined = false;
        quarantineTimer = 14;
        contacts = new ArrayList<>();
        friendList = new ArrayList<>();
        this.isolated = false;
        hasPhone = false;
    }

    public void updateNode(){ //update a nodes internal clock to see if it should no longer track a closecontact/be infected/be in quarantine/die?
        if(state == State.Infected) {
            infectedTime --;
            if(infectedTime == 0){
                state = State.Recovered;
                infectedTime = 14;
                this.isolated = false;
            }
            if((infectedTime > 0 && infectedTime <= 8)){
                if(infectedTime == 8){
                    for(Node n: this.contacts){
                        if(n.state == State.Recovered) continue;
                        if(n.quarantined) continue;
                        if(ThreadLocalRandom.current().nextDouble(1) > 0.8) {
                          n.quarantined = true;
                        }
                    }
                }
                this.isolated = true;
            }
            if((infectedTime > 0 && infectedTime <= 9) && (hasPhone)){
                if(infectedTime == 9){
                    for(Node n: this.contacts){
                        if(n.state == State.Recovered) continue;
                        if(n.hasPhone) n.quarantined = true;
                    }
                }
                this.isolated = true;
            }
        }

        if(quarantined){
            quarantineTimer--;
            if(quarantineTimer == 0) this.quarantined = false;
        }
    }

    public State checkState(){
        switch (state) {
            case Susceptible: return state;
            case Infected: return state;
            case Recovered: return state;
        }
        return state;
    }
    public int meetNumber(){
        if(friendList.size() == 0){
            return 0;
        }
        int a = ThreadLocalRandom.current().nextInt(0, friendList.size());
        if((isolated || this.state == State.Recovered)){
            return 0;
        }
        return a;
    }
    public double setResistance(){
        Double d = Math.abs(resistance);
        String[] decimals = Double.toString(d).split("\\.");
        if(Double.parseDouble(decimals[0]) >= 1.0) d = d - Double.parseDouble(decimals[0]);


        return d;
    }
}

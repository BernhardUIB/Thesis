import java.sql.Array;
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
    ArrayList<Integer> contacts; //close contacts
    ArrayList<Node> friendList;
    boolean quarantined;
    int quarantineTimer;

    public Node(int id){
        Random random = new Random();
        this.id = id;
        resistance = random.nextGaussian();
        state = State.Susceptible;
        infectedTime = 14;
        friends = 4;
        friendz = random.nextGaussian()*3.5+5;
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
            if((infectedTime > 0 && infectedTime <= 7) && (!hasPhone)){
                if(infectedTime == 7){
                    for(Node n: this.friendList){
                        if(ThreadLocalRandom.current().nextDouble(1) > 0.2) {
                            if (ThreadLocalRandom.current().nextDouble(1) > 0.6) {
                                n.quarantined = true;
                                n.quarantineTimer = 14;
                            }
                        }
                    }
                }
                this.isolated = true;
            }
            if((infectedTime > 0 && infectedTime <= 8) && (hasPhone)){
                if(infectedTime == 8){
                    for(Node n: this.friendList){
                        if(ThreadLocalRandom.current().nextDouble(1) > 0.4) {
                            n.quarantined = true;
                            n.quarantineTimer = 14;
                        }
                    }
                }
                this.isolated = true;
            }
        }
        if(quarantined){
            quarantineTimer--;
            if(quarantineTimer ==0) quarantined = false;
        }
        //close contact duration
        //quarantine duration
        //infected duration
    }

    public State checkState(){
        switch (state) {
            case Susceptible: return state;
            case Infected: return state;
            case Recovered: return state;
        }
        return state;
    }
    public boolean isQuarantine(){
        if(quarantined) return true;
        return false;
    }
    public int meetNumber(){
        if(friendList.size() == 0){
            return 0;
        }
        int a = ThreadLocalRandom.current().nextInt(0, friendList.size());
        if((isolated || this.state == State.Recovered) ||(quarantined)){
            return 0;
        }
        return a;
    }
}

/*
 edges til naboer(random generert i en range)
 state frisk/infected/(risikabel?)/immun
 (kanskje familie/jobb)
 alder (for å endre dødelighet, og vaksineringsstrat)
 smitteresistance (rng for gener) Gaussian-random
 binær ja/nei på om den har MCT eller ikke (alle har TCT default)
 smitteevne? Ha dette på edge? Gaussian-random


 en node skal kunne puttes i karantene (fjernes midlertidig ut av nettet)
 hastigheten på fra den er smittet til den er i karantene endres. Typ 3-5dager ved TCT, og 1-2dager MCT?


 */

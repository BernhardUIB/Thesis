import java.util.ArrayList;
public class Node {
    int id; //id
    double resistance; //resistance to contagion to represent genes
    enum State{Susceptible,Infected,Recovered} //SIR
    ArrayList<Node> contacts; //close contacts
    boolean quarantine;

    public Node(int id,double resistance){
        this.id = id;
        this.resistance = resistance;
        State state = State.Susceptible;
    }

    public void updateNode(){ //update a nodes internal clock to see if it should no longer track a closecontact/be infected/be in quarantine/die?
        //close contact duration
        //quarantine duration
        //infected duration
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

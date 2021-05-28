import java.util.ArrayList;
public class Node {
    int id; //id
    int age; //age of a node
    double family; //familySize
    double work; //typical work environment size
    double friends; //should be number of friends of a node
    ArrayList<Node> friendz; //should be friends of a node
    double resistance; //resistance to contagion to represent genes
    enum State{Susceptible,Infected,Recovered} //SIR
    boolean phoneUser; //MCT or TCT
    ArrayList<Node> contacts; //close contacts
    boolean quarantine;
    //if phoneUser -> androidUser/iPhoneUser

    public Node(int id,int age,double family, double work, double friends, double resistance,boolean phoneUser){
        this.id = id;
        this.age = age;
        this.family = family;
        this.work = work;
        this.friends = friends;
        this.resistance = resistance;
        this.phoneUser = phoneUser;
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

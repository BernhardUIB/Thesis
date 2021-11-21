import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.MultiGraph;


public class sim {
    int N; //number of people in the simulation
    int I; //number of infected on day0
    int P; //probability of phone
    int T; //duration of simulation in days?
    boolean uniform;
    NodeGenerator gen;
    ArrayList<Node> nodes;
    ArrayList<Integer> initInf;
    ArrayList<Integer> phoneUsers;
    int rand_user = 0;
    public sim(int N, int I, int P, int T, boolean uniform){
        this.N = N;
        this.I = I;
        this.P = P;
        this.T = T;
        this.uniform = uniform;
    }

    public ArrayList<ArrayList<Integer>> run(int runNumb) { //number of simulations to be ran with current parameters
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        for(int simRan = 0; simRan < runNumb; simRan++) { //iterate over multiple sims for same parameters.
            this.gen = new NodeGenerator(N,I,P,uniform);
            this.initInf = new ArrayList<>();
            this.phoneUsers = new ArrayList<>();
            this.nodes = new ArrayList<>();
            this.nodes = gen.createNodes();
            int EdgeCounter = 0;
            ArrayList<Node> infectedTotal = new ArrayList<>();
            ArrayList<Integer> resultCurrentSim = new ArrayList<>();
            result.add(resultCurrentSim);
            ArrayList<String> ids = new ArrayList<>();
            Graph graph = new MultiGraph("Network");
            for(Node n: nodes){
                n.resistance = n.setResistance();

                if(n.friendz < 0) n.friendz = 0;
                n.friendz = (int)Math.round(n.friendz);
                ArrayList<Node> list = (ArrayList<Node>) nodes.clone();
                Collections.shuffle(list);
                graph.addNode(Integer.toString(n.id));
                if(uniform)n.friendz = n.friends;
                while(n.friendz > n.friendList.size()){ //edit friends to friendz for distribution
                    if(list.size() == 0){
                        break;
                    }
                    Node randomNode = list.remove(0);
                    if(randomNode.id != n.id){ //cant be-friend yourself
                        if(!n.friendList.contains(randomNode) && (randomNode.friendList.size() < randomNode.friendz)){ //edit friends to friendz for distribution
                            n.friendList.add(randomNode);
                            randomNode.friendList.add(n);
                        }
                    }
                }
            }
            while(initInf.size() < I){
                rand_user = ThreadLocalRandom.current().nextInt(0, N);
                if(!initInf.contains(rand_user)){
                    if (nodes.get(rand_user).checkState() == Node.State.Susceptible) {
                        initInf.add(rand_user);
                        nodes.get(rand_user).state = Node.State.Infected;
                    }
                }
            }
            while(phoneUsers.size() < P){
                rand_user = ThreadLocalRandom.current().nextInt(0, N);
                if(!phoneUsers.contains(rand_user)){
                    if (!nodes.get(rand_user).hasPhone) {
                        phoneUsers.add(rand_user);
                        nodes.get(rand_user).hasPhone = true;
                    }
                }
            }
            for(int days = 1; days < T+1; days++) {

                ArrayList<Edge> tempDeleted = new ArrayList<Edge>();
                ArrayList<Node> infectedToday = new ArrayList<>();
                for(int w = 0; w < N*5; w++){
                    String s =  String.format("%0"+Integer.toString(T).length()+"d",days)+ String.format("%07d",w);
                    ids.add(s);
                }
                for(Node n: nodes){
                    int canMeet = n.meetNumber();
                    double randomEncounter = ThreadLocalRandom.current().nextDouble(1);
                    if(randomEncounter > 0.25) {
                        boolean meet = true;
                        int meetAttempt = 0;
                        while (meet && meetAttempt < 5) {
                            int randomFriend = ThreadLocalRandom.current().nextInt(0, N);
                            int friendId = nodes.get(randomFriend).id;
                            if (friendId == n.id) continue; //cannot meet yourself
                            if ((nodes.get(friendId).isolated || n.isolated) || (nodes.get(friendId).state == Node.State.Recovered)){
                                meetAttempt++;
                                continue;
                            }
                            if((n.quarantined) || nodes.get(friendId).quarantined) {
                                if(ThreadLocalRandom.current().nextDouble(1) > 0.4) {
                                    meetAttempt++;
                                    continue;
                                }
                            }
                            graph.addEdge(ids.get(EdgeCounter), graph.getNode(n.id), graph.getNode(friendId));
                            EdgeCounter++;
                            nodes.get(n.id).contacts.add(nodes.get(friendId));
                            nodes.get(friendId).contacts.add(nodes.get(n.id));
                            meet = false;
                            if (n.checkState() == Node.State.Infected || nodes.get(friendId).checkState() == Node.State.Infected) {
                                if (nodes.get(friendId).resistance - Math.random() > 0.25) {
                                    nodes.get(friendId).state = Node.State.Infected;
                                    nodes.get(friendId).quarantined = false;
                                }
                                if (n.resistance - Math.random() > 0.25) {
                                    nodes.get(n.id).state = Node.State.Infected;
                                    nodes.get(n.id).quarantined = false;

                                }
                            }
                        }
                    }

                    for(int s = 0; s < canMeet; s++){
                        int randomFriend = (int)(Math.random()*(n.friendList.size()-1));
                        int friendId = n.friendList.get(randomFriend).id;
                        if(friendId == n.id) continue; //cannot meet yourself
                        if((nodes.get(friendId).isolated || n.isolated) || (nodes.get(friendId).state == Node.State.Recovered)){
                            continue;
                        }
                        if((n.quarantined) || nodes.get(friendId).quarantined) {
                            if(ThreadLocalRandom.current().nextDouble(1) > 0.4) {
                                continue;
                            }
                        }
                        graph.addEdge(ids.get(EdgeCounter),graph.getNode(n.id),graph.getNode(friendId)); //Add graphical edge
                        EdgeCounter++;

                        nodes.get(n.id).contacts.add(nodes.get(friendId)); //add logical edge
                        nodes.get(friendId).contacts.add(nodes.get(n.id));
                        if(n.checkState() == Node.State.Infected || nodes.get(friendId).checkState() == Node.State.Infected) { //If any of the persons in a meet is infected

                            if(nodes.get(friendId).resistance-Math.random() > 0.25){
                                nodes.get(friendId).state = Node.State.Infected;
                                nodes.get(friendId).quarantined = false;
                            }
                            if(n.resistance-Math.random() > 0.25){
                                nodes.get(n.id).state = Node.State.Infected;
                                nodes.get(n.id).quarantined = false;
                            }
                        }
                    }

                    n.updateNode();

                    //System.out.println(n.id + " " + n.checkState() + " " + n.infectedTime);
                }

                int finalDays = days;
                graph.edges().forEach(edge -> {
                    int x = Integer.parseInt(edge.getId().substring(0,Integer.toString(T).length()));;
                    if(finalDays - 14 == x){
                        tempDeleted.add(edge);
                    }
                });
                if(!tempDeleted.isEmpty()) { //graph edge deletion og contact list removal på begge sider.
                    for(int q = 0; q < tempDeleted.size(); q++){
                        Node n = nodes.get(tempDeleted.get(q).getNode0().getIndex());
                        Node friend = nodes.get(tempDeleted.get(q).getNode1().getIndex());

                        nodes.get(n.id).contacts.remove(nodes.get(friend.id));
                        nodes.get(friend.id).contacts.remove(nodes.get(n.id));
                        graph.removeEdge(tempDeleted.get(q));
                    }
                }
                for(int removeIds = EdgeCounter; removeIds< ids.size(); removeIds++){
                    ids.remove(removeIds);
                }
                for(Node n: nodes){
                    if(n.checkState() == Node.State.Infected){
                        if(!infectedTotal.contains(n)){
                            infectedToday.add(n);
                        }
                    }
                }
                //System.out.println("Quarantine: " + quarantineNumber + " infected: " + infectedToday.size() + " infectedTotal: " + infectedTotal.size());
                infectedTotal.addAll(infectedToday);
               // System.out.println("Infected today: " + infectedToday.size()); //Dette skal skriver til .csv fil for å plotte til graf.
                //System.out.println("Total infected: " + infectedTotal.size());
                result.get(simRan).add(infectedToday.size());
            }
           //System.out.println("Total infected: " + infectedTotal.size());
            System.out.println("Simulation done: " + simRan + " " + P);
        }
        return result;
    }
}
/*
tid i timer? dager?
kjører en sim med
n noder
i smittede
p antall phones
t tid


 */
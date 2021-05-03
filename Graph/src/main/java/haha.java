import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.ViewerListener;
import org.graphstream.ui.view.ViewerPipe;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class haha implements ViewerListener {
    protected boolean loop = true;

    public static void main(String args[]) {
        System.setProperty("org.graphstream.ui", "swing");
        new haha();
    }
    public haha() {
        // We do as usual to display a graph. This
        // connect the graph outputs to the viewer.
        // The viewer is a sink of the graph.
        Graph graph = new SingleGraph("Clicks");
        /*
        graph.addNode("A" );
        graph.addNode("B" );
        graph.addNode("C" );
        graph.addEdge("AB", "A", "B");
        graph.addEdge("BC", "B", "C");
        graph.addEdge("CA", "C", "A");
         */

        for(int i = 0; i<1000;i++){
            String node_id = Integer.toString(i);
            graph.addNode(node_id);
        }

        ArrayList<Integer> infected = new ArrayList<>();

        Random rand = new Random();
        for(int i = 0; i<10;i++){
            int rand_infected = rand.nextInt(99);
            graph.getNode(rand_infected).setAttribute("ui.style", "fill-color: rgb(255, 0, 0);");
            infected.add(rand_infected);

        }






        Viewer viewer = graph.display();

        // The default action when closing the view is to quit
        // the program.
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);

        // We connect back the viewer to the graph,
        // the graph becomes a sink for the viewer.
        // We also install us as a viewer listener to
        // intercept the graphic events.
        ViewerPipe fromViewer = viewer.newViewerPipe();
        fromViewer.addViewerListener(this);
        fromViewer.addSink(graph);

        // Then we need a loop to do our work and to wait for events.
        // In this loop we will need to call the
        // pump() method before each use of the graph to copy back events
        // that have already occurred in the viewer thread inside
        // our thread.
        int counter = 0;
        while(loop) {
            counter++;
            fromViewer.pump(); // or fromViewer.blockingPump(); in the nightly builds
            int id = (int)(Math.random() * 99) + 0;
            int rand2 = (int)(Math.random() * 99) + 0;
            int rand3 = (int)(Math.random() * 99) + 0;

            graph.addEdge(Integer.toString(counter), rand2, rand3);

            for(int i = 0; i < 1000; i++){
                int j = 0;
                for(graph.getNode(i);j < graph.getNode(i).getDegree(); j++){
                    System.out.println(graph.getNode(i).getEdge(j));
                }
            }


            if(infected.contains(rand2) || infected.contains(rand3)){
                graph.getNode(String.valueOf(rand3)).setAttribute("ui.style", "fill-color: rgb(255, 0, 0);");
                graph.getNode(String.valueOf(rand2)).setAttribute("ui.style", "fill-color: rgb(255, 0, 0);");



            }


            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // here your simulation code.
            // You do not necessarily need to use a loop, this is only an example.
            // as long as you call pump() before using the graph. pump() is non
            // blocking.  If you only use the loop to look at event, use blockingPump()
            // to avoid 100% CPU usage. The blockingPump() method is only available from
            // the nightly builds.
        }
    }

    public void viewClosed(String id) {
        loop = false;
    }

    public void buttonPushed(String id) {
        System.out.println("Button pushed on node "+id);
    }

    public void buttonReleased(String id) {
        System.out.println("Button released on node "+id);
    }

    public void mouseOver(String id) {
        System.out.println("Need the Mouse Options to be activated");
    }

    public void mouseLeft(String id) {
        System.out.println("Need the Mouse Options to be activated");
    }
}
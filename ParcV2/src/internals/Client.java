package internals;


import java.util.Random;

public class Client extends Thread {
    int numCli;
    Billetterie billetterie;
    private int nbBillets;
    Attraction[] attractions;
    public String etat;
    private Random rand = new Random();



    public Client(Billetterie b, Attraction[] attractions, int numCli) {
        this.nbBillets = rand.nextInt(4)+1;
        this.billetterie = b;
        this.attractions = attractions;
        this.numCli = numCli;
    }


    public void run() {
        try {
            etat = "INIT";

            billetterie.acheterBillet(nbBillets);
            etat = "ENTERED";

            attractions[0].TraitementClient(numCli);
            etat = "RIDE1";
            etat = "TRANSIT";

            attractions[1].TraitementClient(numCli);
            etat = "RIDE2";

            // on suppose que etat = out

        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

}



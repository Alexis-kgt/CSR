package backend;

import internals.Attraction;
import internals.Billetterie;
import internals.Client;
import internals.Navette;
import internals.Responsable;

public class Parc {

    static final int nbClients = 30;
    private Client[] clients = new Client[nbClients];
    private Attraction[] attractions = new Attraction[2];
    private Navette[] navettes = new Navette[4];
    private Billetterie billetterie = new Billetterie(500);
    Responsable responsable = new Responsable(billetterie);


    public Parc() {

        int i;

        for (i = 0; i < 2; i++) {
            attractions[i] = new Attraction(i);

        }

        for (i = 0; i < 4; i++) {
            navettes[i] = new Navette(attractions[i/2], i);
            navettes[i].start();
        }

        creerClients();

        for (i = 0; i < nbClients; i++)
            clients[i].start();


        responsable.start();
    }


    public static void main(String[] args) {

        new Parc();

    }

    public void creerClients() {
        for (int i = 0; i < clients.length; ++i)
            clients[i] = new Client(billetterie, attractions, i);
    }

    public Client[] getClients() {
        return clients;
    }

    public Navette[] getNavettes() {
        return navettes;
    }
}

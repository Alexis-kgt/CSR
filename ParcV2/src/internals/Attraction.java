package internals;


public class Attraction {

    private int attraction;
    Navette navetteQuai;


    public Attraction(int a) {
        this.attraction = a;
    }

    synchronized void arret(Navette navette) {
        while (navetteQuai != null) {

            try {
                System.out.println(navette.numNav + "Attend de rentrer");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        navetteQuai = navette;
        System.out.println("navette " + navette.numNav + " au quai");
    }

    synchronized void depart() {

        int n = navetteQuai.nbPlaces - navetteQuai.getNbPlacesDispo();
        System.out.println("la navette " + navetteQuai.numNav + " vient de partir avec " + n + " personnes!");
        navetteQuai = null;

        notifyAll();
    }

    public synchronized void TraitementClient(int numCli) {
        while (navetteQuai == null || navetteQuai.getNbPlacesDispo() == 0) {
            System.out.println("le client " + numCli + " attend la navette.");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        navetteQuai.enlevePlace();
        System.out.println("le client " + numCli + " monte dans la navette" + attraction + "." + navetteQuai.getNbPlacesDispo() + "places restantes");
    }
}

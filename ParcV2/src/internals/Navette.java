package internals;

public class Navette extends Thread {

	Attraction attraction;
	static final int nbPlaces = 20;
	private int nbPlacesDispo = nbPlaces;
	public String etat;
	int numNav;

	public Navette(Attraction a, int numNav) {
		this.attraction = a;
	this.numNav = numNav;
	}

	public void run() {
		while (true) {
			etat = "TRAVELLING";

			try {
				sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			attraction.arret(this);

			etat = "STOPPED";

			try {
				sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			attraction.depart();
			try {
				sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			etat = "TRAVELLING";

			nbPlacesDispo = nbPlaces;

		}
	}

	public int getNbPlacesDispo() {
		return nbPlacesDispo;
	}

	public void enlevePlace() {
		this.nbPlacesDispo --;
	}
}

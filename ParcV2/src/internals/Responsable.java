package internals;

public class Responsable extends Thread {

	Billetterie billetterie;


	public Responsable(Billetterie b) {
		this.billetterie = b;
	}

	int stockInit = billetterie.stockInit;

	public void run() {
		while (true) {
			billetterie.recharger(stockInit);
		}
	}
}

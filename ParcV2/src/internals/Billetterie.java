package internals;

public class Billetterie {
	int stock;
	final static int stockInit = 50;
	private boolean doitAttendre = true;
	
	static final int tpsAchat = 50;

	public Billetterie(int stock) {
		this.stock = stock;
	}

	public synchronized void acheterBillet(int nbBillets) throws InterruptedException {
		while (this.stock < nbBillets) {
			doitAttendre = false;
			notifyAll();
			System.out.println("le client veut acheter   " + nbBillets + "billet(s)");
			wait();
			
			
		}
		System.out.println("le client a acheté "+ nbBillets + "billet(s)");
		Thread.sleep(tpsAchat);
		this.stock = this.stock - nbBillets;
		notifyAll();

	}

	public synchronized void recharger(int stockInit) {
		while(doitAttendre) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		stock+=stockInit;
		doitAttendre = true;
		System.out.println("le responsable vient de rajouter "+stockInit+" billets");
		notifyAll();
		
		
		
		
	}
}

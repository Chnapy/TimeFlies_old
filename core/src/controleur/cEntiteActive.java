/*
 * 
 * 
 * 
 */
package controleur;

import gameplay.entite.EntiteActive;

/**
 * cEntiteActive.java
 *
 */
public class cEntiteActive implements Runnable {

	private final Thread thread;
	private boolean tourActif;

	private EntiteActive entite;

	public cEntiteActive() {
		thread = new Thread(this);
	}

	public void start(EntiteActive ent) {
		entite = ent;
		tourActif = true;
		thread.start();
	}

	public void stop() {
		tourActif = false;
	}

	@Override
	public void run() {
		while (tourActif) {

		}
	}

}

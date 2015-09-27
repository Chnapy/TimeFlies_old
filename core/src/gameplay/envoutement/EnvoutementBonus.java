/*
 * 
 * 
 * 
 */
package gameplay.envoutement;

import gameplay.caracteristique.Carac;
import gameplay.entite.Entite;
import gameplay.map.Tuile;
import general.Orientation;
import vue.hud.chatbox.chattext.vChatText;
import vue.hud.chatbox.vChatBox;

/**
 * EnvoutementBonus.java
 * Représente un bonus, temporaire, appliqué à une entité active.
 *
 */
public abstract class EnvoutementBonus extends Envoutement {

	private Carac carac;
	private int valeur;

	/**
	 *
	 *
	 * @param nom
	 * @param duree
	 * @param c
	 * @param _valeur
	 */
	public EnvoutementBonus(String nom, int duree, Carac c, int _valeur) {
		super(nom, duree);
		carac = c;
		valeur = _valeur;
	}

	@Override
	public void actionDebutTour() {

	}

	@Override
	public void actionFinTour() {

	}

	@Override
	public void actionDebutTourGlobal() {

	}

	@Override
	public void actionFinTourGlobal() {

	}

	@Override
	public void actionDebutEnvoutement() {
		if (valeur > 0) {
			getCible().getCaracPhysique().add(carac, valeur);
		} else {
			getCible().getCaracPhysique().supp(carac, valeur);
		}
		vChatBox.chatCombatPrint(getCible().getNom() + " recoit l'envoutement bonus " + getNom() + " et " 
				+ (valeur > 0 ? "gagne " : "perd ") + valeur + " " + carac + ".", vChatText.ChatTextType.COMBAT);
	}

	@Override
	public void actionFinEnvoutement() {
		if (valeur > 0) {
			getCible().getCaracPhysique().supp(carac, valeur);
		} else {
			getCible().getCaracPhysique().add(carac, valeur);
		}
		vChatBox.chatCombatPrint(getCible().getNom() + " recoit l'envoutement bonus " + getNom() + " et " 
				+ (valeur > 0 ? "perd " : "gagne ") + valeur + " " + carac + ".", vChatText.ChatTextType.COMBAT);
	}

	@Override
	public void actionLancerEntite(Orientation oriLanceur, boolean ccritique) {
		
	}

	@Override
	public void lancerTuile(Tuile cible, Entite lanceur, Orientation oriLanceur, boolean ccritique) {

	}

}

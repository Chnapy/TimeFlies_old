/*
 * 
 * 
 * 
 */
package gameplay.sort;

import gameplay.effet.Effet;
import gameplay.entite.Entite;
import gameplay.entite.EntiteActive;
import gameplay.map.Tuile;
import general.Orientation;
import vue.hud.chatbox.chattext.vChatText;
import vue.hud.chatbox.vChatBox;

/**
 * SortPassifBonus.java
 * Représente un sort passif donnant un bonus à son utilisateur, de manière
 * permannente.
 *
 */
public abstract class SortPassifBonus extends SortPassif {

	/**
	 *
	 * @param nom
	 * @param description
	 * @param niveau
	 * @param effets
	 * @param index
	 */
	public SortPassifBonus(String nom, String description, Niveau niveau,
			Effet[] effets,
			int index) {

		super(nom, description, niveau, effets, index);
	}

	@Override
	public void lancerSort(Entite cibleEntite, Tuile cibleTuile, EntiteActive lanceur, Orientation oriAttaque, boolean critique) {
		vChatBox.chatCombatPrint("Sort passif à bonus active : " + getNom(), vChatText.ChatTextType.COMBAT);
		cibleEntite.recoitSort(getTabEffets(), cibleEntite, oriAttaque, critique);
	}

}

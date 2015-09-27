/*
 * 
 * 
 * 
 */
package controleur;

import gameplay.entite.Entite;
import gameplay.entite.EntiteActive;
import gameplay.entite.Personnage;
import gameplay.map.EtatTuile;
import gameplay.map.Map;
import gameplay.map.Tuile;
import gameplay.sort.SortActif;
import gameplay.sort.pileaction.Action;
import general.Mode;
import general.Orientation;
import general.Tourable;
import java.awt.Point;
import vue.Vue;
import vue.hud.chatbox.chattext.vChatText;
import vue.hud.chatbox.vChatBox;

/**
 * ControleurSort.java
 *
 */
public class ControleurSort implements Tourable {

	private final ControleurPrincipal controleurPrincipal;

	private final Vue vue;

	private EntiteActive entiteEnCours;

	private SortActif sortEnCours;

	public ControleurSort(ControleurPrincipal controleurPrincipal, Vue _vue) {
		this.controleurPrincipal = controleurPrincipal;
		vue = _vue;
	}

	public void survolTuile(int x, int y) {
		//Afficher zone action

		if (vue.getVmap().getTabVtuiles()[y][x].getMinietat() == EtatTuile.ZONEPORTEE) {
			vue.afficherAction(sortEnCours.getZoneAction().getZoneFinale(), new Point(x, y));
		} else {
			vue.clearActionTuile();
		}
	}

	public void clicSurTuile(int x, int y) {
		Point pt = new Point(x, y);
		//Lancement de sort sur toute la zone action
		if (vue.getVmap().getTabVtuiles()[y][x].getMinietat() == EtatTuile.ZONEPORTEE) {
			if (entiteEnCours.tempsDispo() >= sortEnCours.getTempsAction()) {
				controleurPrincipal.setPrecOriAttaque(controleurPrincipal.getOriAttaque());
				controleurPrincipal.setOriAttaque(controleurPrincipal.getOrientation(entiteEnCours.getCaracSpatiale().getPosition(), pt));
//					System.out.println(oriAttaque);
				Boolean critique = controleurPrincipal.isCoupCritique(entiteEnCours.getCaracSpatiale().getOrientation(), controleurPrincipal.getOriAttaque());
				if (controleurPrincipal.getOriAttaque() != controleurPrincipal.getPrecOriAttaque()) {
					controleurPrincipal.addAction(new Action(pt, entiteEnCours.getOrienter(), controleurPrincipal.getOriAttaque(), controleurPrincipal.getPrecOriAttaque(), critique));
					critique = controleurPrincipal.isCoupCritique(entiteEnCours.getCaracSpatiale().getOrientation(), controleurPrincipal.getOriAttaque());
				}
				controleurPrincipal.addAction(new Action(pt, sortEnCours, controleurPrincipal.getOriAttaque(), controleurPrincipal.getPrecOriAttaque(), critique));
				entiteEnCours.subTime(sortEnCours.getTempsAction());
			}
		}
	}

	/**
	 * Passage en mode sort Affichage de la zone de portée
	 *
	 * @param index
	 */
	public void modeSort(int index) {
		SortActif sort = entiteEnCours.getSort(index);
		if (sort.getCooldownActu() == 0) {
			entiteEnCours.setEtat(Mode.SORT);
			sortEnCours = entiteEnCours.setSortEnCours(index);
			Point depart = entiteEnCours.getLastPosition();
			vue.afficherPortee(sortEnCours.getZonePortee().getZoneFinale(), depart);
		} else {
			vChatBox.chatCombatPrint("Vous ne pouvez pas lancer le sort " + sort.getNom() + ". Le cooldown est de " + sort.getCooldownActu() + " tours.", vChatText.ChatTextType.IMPORTANT);
		}
	}

	public void update(EntiteActive entite, Action action, Map map) {
		vChatBox.chatCombatPrint(entite.getNom() + " lance le sort " + action.getSort().getNom() + ".", vChatText.ChatTextType.COMBAT);
		Tuile[] tuilesTouchees = map.getTuilesAction(action.getSort().getZoneAction().getZoneFinale(), new Point(action.getPoint().x, action.getPoint().y));
		for (Tuile t : tuilesTouchees) {
			lancerSort(entite, action.getSort(), t, action.getOriAttaque(), action.isCritique());
		}
		action.getSort().resetCooldown();
		vue.getVjeu().addSort(action.getSort().getIndex(), action.getSort().getTempsAction(), entiteEnCours.getCaracSpatiale().getPosition(), action.getPoint());
		for (Tuile t : tuilesTouchees) {
			vue.getVmap().getTabVtuiles()[t.getPosition().y][t.getPosition().x].addGhostAction();
		}
	}
	
	
	public void lancerSort(Entite lanceur, SortActif sort, Tuile tuileCible, Orientation oriAttaque, boolean critique) {
		EntiteActive persoCible = controleurPrincipal.getPerso(tuileCible);
		tuileCible.recoitSort(sort.getTabEffets(), lanceur, oriAttaque, critique);
		if (persoCible != null) {
			persoCible.recoitSort(sort.getTabEffets(), lanceur, oriAttaque, critique);
		}
	}

	@Override
	public void nouveauTour(ControleurPrincipal controleur, EntiteActive _entiteEnCours, Object... objs) {
		entiteEnCours = _entiteEnCours;
	}

	@Override
	public void finTour(ControleurPrincipal controleur, EntiteActive entiteEnCours, Object... objs) {
		for (SortActif sort : entiteEnCours.getTabSortActif()) {
			sort.subCooldown();
		}
	}

	@Override
	public void enTour(ControleurPrincipal controleur, EntiteActive entiteEnCours, Object... objs) {
	}

	public SortActif getSortEnCours() {
		return sortEnCours;
	}

	public void setSortEnCours(SortActif sortEnCours) {
		this.sortEnCours = sortEnCours;
	}

}

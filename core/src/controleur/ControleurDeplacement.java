/*
 * 
 * 
 * 
 */
package controleur;

import com.badlogic.gdx.utils.Array;
import gameplay.entite.EntiteActive;
import gameplay.map.Map;
import gameplay.map.Tuile;
import gameplay.map.Type;
import gameplay.sort.pileaction.Action;
import general.Mode;
import general.Tourable;
import java.awt.Point;
import vue.hud.chatbox.chattext.vChatText;
import vue.hud.chatbox.vChatBox;
import vue.jeu.map.vMap;

/**
 * ControleurDeplacement.java
 *
 */
public class ControleurDeplacement implements Tourable {

	private final ControleurPrincipal controleurPrincipal;

	private final Map map;

	private final vMap vueMap;

	private EntiteActive entiteEnCours;

	//Chemin (liste de points) de l'entité active à la tuile ciblée
	private Array<Point> path;

	private Point lastPosFixe;

	public ControleurDeplacement(ControleurPrincipal controleurPrincipal, Map _map, vMap _vueMap) {
		this.controleurPrincipal = controleurPrincipal;
		map = _map;
		vueMap = _vueMap;
	}

	public void survolTuile(int x, int y) {
		Tuile tuile = map.getTabTuiles()[y][x];
		vueMap.clearColorTuile();
		//Déplacement
		Point depart = entiteEnCours.getLastPosition();
		if (!depart.equals(tuile.getPosition())
				&& !tuile.getType().equals(Type.OBSTACLE)
				&& !tuile.getType().equals(Type.TROU)
				&& !tuile.isOccupe()) {
			path = map.getChemin(depart, new Point(x, y));
			if (path != null) {
				vueMap.colorTuile(path);
			}
		} else {
			path = null;
		}
	}

	public void clicSurTuile(int x, int y) {
//			System.out.println(path);
		if (path != null) {
			deplacer();
			if (path.first().equals(entiteEnCours.getCaracSpatiale().getPosition())) {
				path.removeIndex(0);
			}
			vueMap.ghostPath(path);
			vueMap.clearColorTuile();
			if (path.size > 0) {
				lastPosFixe = path.peek();
			}
			path = null;
		}
	}

	private void deplacer() {
		for (int i = 0; i < path.size; i++) {
			controleurPrincipal.setPrecOriAttaque(controleurPrincipal.getOriAttaque());
			if (i == 0) {
				controleurPrincipal.setOriAttaque(controleurPrincipal.getOrientation(entiteEnCours.isEnDeplacement() ? lastPosFixe : entiteEnCours.getCaracSpatiale().getPosition(), path.get(i)));
			} else {
				controleurPrincipal.setOriAttaque(controleurPrincipal.getOrientation(path.get(i - 1), path.get(i)));
			}
			Boolean critique = controleurPrincipal.isCoupCritique(entiteEnCours.getCaracSpatiale().getOrientation(), controleurPrincipal.getOriAttaque());
			if (controleurPrincipal.getOriAttaque() != controleurPrincipal.getPrecOriAttaque()) {
				controleurPrincipal.addAction(new Action(path.get(i), entiteEnCours.getOrienter(), controleurPrincipal.getOriAttaque(), controleurPrincipal.getPrecOriAttaque(), critique));
				critique = controleurPrincipal.isCoupCritique(entiteEnCours.getCaracSpatiale().getOrientation(), controleurPrincipal.getOriAttaque());//todo utiliser l'orientation finale
			}
			controleurPrincipal.addAction(new Action(path.get(i), entiteEnCours.getDeplacer(), controleurPrincipal.getOriAttaque(), controleurPrincipal.getPrecOriAttaque(), critique));
		}
	}

	/**
	 * Passage en mode déplacement
	 *
	 */
	public void modeDeplacement() {
		entiteEnCours.setEtat(Mode.DEPLACEMENT);
		controleurPrincipal.controleurSort.setSortEnCours(null);
		vueMap.clearMiniTuiles();
	}

	public void update(EntiteActive entite, Action action) {
		entite.setEnDeplacement(true);
		action.getSort().lancerSort(entite, map.getTabTuiles()[action.getPoint().x][action.getPoint().y], entite, action.getOriAttaque(), action.isCritique());
		vueMap.getTabVtuiles()[action.getPoint().y][action.getPoint().x].clearGhostPath();
	}

	@Override
	public void nouveauTour(ControleurPrincipal controleur, EntiteActive _entiteEnCours, Object... objs) {
		entiteEnCours = _entiteEnCours;
		path = null;
		lastPosFixe = null;
		map.setTuileOccupe(false, entiteEnCours.getCaracSpatiale().getPosition().y, entiteEnCours.getCaracSpatiale().getPosition().x);
	}

	@Override
	public void finTour(ControleurPrincipal controleur, EntiteActive entiteEnCours, Object... objs) {
		map.setTuileOccupe(true, entiteEnCours.getCaracSpatiale().getPosition().y, entiteEnCours.getCaracSpatiale().getPosition().x);
	}

	@Override
	public void enTour(ControleurPrincipal controleur, EntiteActive entiteEnCours, Object... objs) {
	}

}

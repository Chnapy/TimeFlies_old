/*
 * 
 * 
 * 
 */
package gameplay.map;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.utils.Array;
import gameplay.map.pathfinding.Chemin;
import gameplay.map.pathfinding.Finder;
import gameplay.map.pathfinding.Heuristique;
import java.awt.Point;

/**
 * Map.java
 * Représente la map du combat.
 * Gère égelement le pathfinding.
 *
 */
public class Map implements IndexedGraph<Tuile> {

	private final Tuile[][] tabTuiles;

	//Pathfinding
	private final Finder finder;
	private final Chemin chemin;
	private final Heuristique heuristique;

	/**
	 *
	 * @param plan	map représentée sous forme d'état de tuile (simple, obstacle,
	 *             etc...)
	 */
	public Map(Etat[][] plan) {
		tabTuiles = new Tuile[plan.length][plan[0].length];
		init(plan);
		finder = new Finder(this);
		chemin = new Chemin();
		heuristique = new Heuristique();
	}

	/**
	 * Rempli la map depuis le plan.
	 * Génère les connections entre les tuiles pour le pathfinding.
	 *
	 * @param plan
	 */
	private void init(Etat[][] plan) {
		int x, y;
		for (y = 0; y < plan.length; y++) {
			for (x = 0; x < plan[0].length; x++) {
				tabTuiles[y][x] = new Tuile(plan[y][x], new Point(x, y), y + x);
			}
		}

		//Définitions des connections pour le pathfinding
		for (y = 0; y < plan.length; y++) {
			for (x = 0; x < plan[0].length; x++) {
				if (y - 1 >= 0 && (!tabTuiles[y - 1][x].getEtat().equals(Etat.TROU) && !tabTuiles[y - 1][x].getEtat().equals(Etat.OBSTACLE))) {
					tabTuiles[y][x].addConnection(tabTuiles[y - 1][x]);
				}
				if (y + 1 < plan.length && (!tabTuiles[y + 1][x].getEtat().equals(Etat.TROU) && !tabTuiles[y + 1][x].getEtat().equals(Etat.OBSTACLE))) {
					tabTuiles[y][x].addConnection(tabTuiles[y + 1][x]);
				}
				if (x - 1 >= 0 && (!tabTuiles[y][x - 1].getEtat().equals(Etat.TROU) && !tabTuiles[y][x - 1].getEtat().equals(Etat.OBSTACLE))) {
					tabTuiles[y][x].addConnection(tabTuiles[y][x - 1]);
				}
				if (x + 1 < plan[0].length && (!tabTuiles[y][x + 1].getEtat().equals(Etat.TROU) && !tabTuiles[y][x + 1].getEtat().equals(Etat.OBSTACLE))) {
					tabTuiles[y][x].addConnection(tabTuiles[y][x + 1]);
				}
			}
		}
	}

	public Tuile[][] getTabTuiles() {
		return tabTuiles;
	}

	/**
	 * Calcul du chemin à partir d'un point source et d'un point destination.
	 *
	 * @param source
	 * @param dest
	 * @return liste des points (positions des tuiles) parcourus par le chemin
	 */
	public Array<Point> getChemin(Point source, Point dest) {

		//Remplissage du chemin dans 'chemin'
		if (!finder.searchNodePath(tabTuiles[source.y][source.x], tabTuiles[dest.y][dest.x], heuristique, chemin)) {
			//Si aucun chemin n'a été trouvé
			System.err.println("Aucun chemin trouvé !");
			return null;
		}
		System.out.println("De " + source + " à " + dest + " " + chemin);

		Array<Point> ret = new Array<>(chemin.size);
		for (int i = 1; i < chemin.size; i++) {	//On ne prend pas la 1ère valeur !
			ret.add(chemin.get(i).getPosition());
		}

		//Purge de 'chemin' pour le prochain calcul
		chemin.clear();

		return ret;
	}

	@Override
	public int getNodeCount() {
		return tabTuiles.length * tabTuiles[0].length;
	}

	@Override
	public Array<Connection<Tuile>> getConnections(Tuile n) {
		return n.getConnections();
	}

}

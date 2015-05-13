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
import java.awt.Dimension;
import java.awt.Point;

/**
 * Map.java
 * Représente la map du combat.
 * Gère également le pathfinding.
 *
 */
public class Map implements IndexedGraph<Tuile> {

	//Tableau des différentes tuiles de la map
	private final Tuile[][] tabTuiles;

	//Dimension x/y de la map
	private final Dimension dimension;

	//Pathfinding
	private final Finder finder;
	private final Chemin chemin;
	private final Heuristique heuristique;

	/**
	 *
	 * @param plan	map représentée sous forme d'état de tuile (simple, obstacle,
	 *             etc...)
	 */
	public Map(Type[][] plan) {
		tabTuiles = new Tuile[plan.length][plan[0].length];
		dimension = new Dimension(plan.length, plan[0].length);
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
	private void init(Type[][] plan) {
		int x, y;
		for (y = 0; y < plan.length; y++) {
			for (x = 0; x < plan[0].length; x++) {
				tabTuiles[y][x] = new Tuile(plan[y][x], new Point(x, y), y * plan[0].length + x);
			}
		}

		//Définitions des connections pour le pathfinding
		for (y = 0; y < plan.length; y++) {
			for (x = 0; x < plan[0].length; x++) {
				generateConnections(y, x);
			}
		}
	}

	/**
	 * Génère les connections pour la pathfinding
	 *
	 * @param y
	 * @param x
	 */
	private void generateConnections(int y, int x) {
		if (y - 1 >= 0 && (!tabTuiles[y - 1][x].getType().equals(Type.TROU) && !tabTuiles[y - 1][x].getType().equals(Type.OBSTACLE) && !tabTuiles[y - 1][x].isOccupe())) {
			tabTuiles[y][x].addConnection(tabTuiles[y - 1][x]);
		}
		if (y + 1 < dimension.width && (!tabTuiles[y + 1][x].getType().equals(Type.TROU) && !tabTuiles[y + 1][x].getType().equals(Type.OBSTACLE) && !tabTuiles[y + 1][x].isOccupe())) {
			tabTuiles[y][x].addConnection(tabTuiles[y + 1][x]);
		}
		if (x - 1 >= 0 && (!tabTuiles[y][x - 1].getType().equals(Type.TROU) && !tabTuiles[y][x - 1].getType().equals(Type.OBSTACLE) && !tabTuiles[y][x - 1].isOccupe())) {
			tabTuiles[y][x].addConnection(tabTuiles[y][x - 1]);
		}
		if (x + 1 < dimension.height && (!tabTuiles[y][x + 1].getType().equals(Type.TROU) && !tabTuiles[y][x + 1].getType().equals(Type.OBSTACLE) && !tabTuiles[y][x + 1].isOccupe())) {
			tabTuiles[y][x].addConnection(tabTuiles[y][x + 1]);
		}
	}

	/**
	 * Rends la tuile ciblée occupé ou non
	 *
	 * @param occupe
	 * @param y
	 * @param x
	 */
	public void setTuileOccupe(boolean occupe, int y, int x) {
		tabTuiles[y][x].setOccupe(occupe);
		if (occupe) {
			tabTuiles[y][x].clearConnections();
		} else {
			generateConnections(y, x);
		}
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
//		System.out.println("De " + source + " à " + dest + " " + chemin);

		Array<Point> ret = new Array<>(chemin.size);
		for (int i = 1; i < chemin.size; i++) {	//On ne prend pas la 1ère valeur !
			ret.add(chemin.get(i).getPosition());
		}

		//Purge de 'chemin' pour le prochain calcul
		chemin.clear();

		return ret;
	}

	/**
	 * Récupère les tuiles ciblées dans une zone précise
	 *
	 * @param zone
	 * @param cible
	 * @return
	 */
	public Tuile[] getTuilesAction(boolean[][] zone, Point cible) {
		Array<Tuile> ret = new Array<Tuile>();

		for (int y = cible.y + zone.length / 2 - Math.abs(zone.length % 2 - 1), j = 0;
				y > cible.y - zone.length / 2 - zone.length % 2 && j < zone.length;
				y--, j++) {
			for (int x = cible.x - zone[0].length / 2 + Math.abs(zone[0].length % 2 - 1), i = 0;
					x < cible.x + zone[0].length / 2 + zone[0].length % 2 && i < zone[0].length;
					x++, i++) {
				if (zone[j][i] && y >= 0 && x >= 0 && y < tabTuiles.length && x < tabTuiles[0].length) {
					ret.add(tabTuiles[y][x]);
				}
			}
		}

		return ret.toArray(Tuile.class);
	}

	public Tuile[][] getTabTuiles() {
		return tabTuiles;
	}

	@Override
	public int getNodeCount() {
		return tabTuiles.length * tabTuiles[0].length;
	}

	@Override
	public Array<Connection<Tuile>> getConnections(Tuile n) {
		return n.getConnections();
	}

	public Dimension getMapDimension() {
		return dimension;
	}
}

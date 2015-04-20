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
 *
 */
public class Map implements IndexedGraph<Tuile> {

	private Tuile[][] tabTuiles;
	
	//Pathfinding
	private final Finder finder;
	private final Chemin chemin;
	private final Heuristique heuristique;

	/**
	 *
	 * @param plan
	 */
	public Map(Etat[][] plan) {
		tabTuiles = new Tuile[plan.length][plan[0].length];
		init(plan);
		finder = new Finder(this);
		chemin = new Chemin();
		heuristique = new Heuristique();
	}

	private void init(Etat[][] plan) {
		for (int i = 0; i < plan.length; i++) {
			for (int j = 0; j < plan[0].length; j++) {
				tabTuiles[i][j] = new Tuile(plan[i][j], new Point(i, j), i + j);
			}
		}

		//Définitions des connections pour le pathfinding
		for (int i = 0; i < plan.length; i++) {
			for (int j = 0; j < plan[0].length; j++) {
				if (i - 1 >= 0 && (!tabTuiles[i - 1][j].getEtat().equals(Etat.TROU) || !tabTuiles[i - 1][j].getEtat().equals(Etat.OBSTACLE))) {
					tabTuiles[i][j].addConnection(tabTuiles[i - 1][j]);
				}
				if (i + 1 < plan.length && (!tabTuiles[i + 1][j].getEtat().equals(Etat.TROU) || !tabTuiles[i + 1][j].getEtat().equals(Etat.OBSTACLE))) {
					tabTuiles[i][j].addConnection(tabTuiles[i + 1][j]);
				}
				if (j - 1 >= 0 && (!tabTuiles[i][j - 1].getEtat().equals(Etat.TROU) || !tabTuiles[i][j - 1].getEtat().equals(Etat.OBSTACLE))) {
					tabTuiles[i][j].addConnection(tabTuiles[i][j - 1]);
				}
				if (j + 1 < plan[0].length && (!tabTuiles[i][j + 1].getEtat().equals(Etat.TROU) || !tabTuiles[i][j + 1].getEtat().equals(Etat.OBSTACLE))) {
					tabTuiles[i][j].addConnection(tabTuiles[i][j + 1]);
				}
			}
		}
	}

	public Tuile[][] getTabTuiles() {
		return tabTuiles;
	}

	public Point[] getChemin(Point source, Point dest) {
		Tuile src = tabTuiles[1][0];
		Tuile end = tabTuiles[3][2];
		finder.searchNodePath(src, end, heuristique, chemin);
		System.out.println(chemin);
		chemin.clear();
		return null;//todo
	}

	@Override
	public int getNodeCount() {
		return tabTuiles.length + tabTuiles[0].length;
	}

	@Override
	public Array<Connection<Tuile>> getConnections(Tuile n) {
		return n.getConnections();
	}

}

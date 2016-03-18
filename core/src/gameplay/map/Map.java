/*
 * 
 * 
 * 
 */
package gameplay.map;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Bresenham2;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import controleur.ControleurPrincipal;
import static gameplay.map.Type.TROU;
import gameplay.map.pathfinding.Chemin;
import gameplay.map.pathfinding.Finder;
import gameplay.map.pathfinding.Heuristique;
import general.MyBresenham;
import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

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

	//Bresenham
	private final Bresenham2 bresenham;

	/**
	 *
	 * @param mapPlan
	 */
	public Map(MapSerializable mapPlan) {
		Type[][] plan = mapPlan.tuiles;
		tabTuiles = new Tuile[plan.length][plan[0].length];
		dimension = new Dimension(plan.length, plan[0].length);
		init(plan);
		finder = new Finder(this);
		chemin = new Chemin();
		heuristique = new Heuristique();
		bresenham = new MyBresenham(plan[0].length, plan.length);
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
				tabTuiles[y][x] = new Tuile(plan[y][x], new GridPoint2(x, y), y * plan[0].length + x);
			}
		}

		//Définitions des connections pour le pathfinding
		for (y = 0; y < plan.length; y++) {
			for (x = 0; x < plan[0].length; x++) {
				generateConnections(y, x);
			}
		}
	}

	public void setControleur(ControleurPrincipal controleur) {
		for (Tuile[] colonne : tabTuiles) {
			for (Tuile tuile : colonne) {
				tuile.setControleur(controleur);
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
	public Array<GridPoint2> getChemin(GridPoint2 source, GridPoint2 dest) {

		//Remplissage du chemin dans 'chemin'
		if (!finder.searchNodePath(tabTuiles[source.y][source.x], tabTuiles[dest.y][dest.x], heuristique, chemin)) {
			//Si aucun chemin n'a été trouvé
			System.err.println("Aucun chemin trouvé !");
			return null;
		}
//		System.out.println("S." + source.x + ":" + source.y + " D." + dest.x + ":" + dest.y);

		Array<GridPoint2> ret = new Array<>(chemin.size);
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
	public Tuile[] getTuilesAction(boolean[][] zone, GridPoint2 cible) {
		Array<Tuile> ret = new Array<Tuile>();
		ret.add(tabTuiles[cible.y][cible.x]);

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

	/**
	 * Défini les lignes de vue de la zone du sort par rapport aux obstacles de
	 * la map.
	 *
	 * @param zoneIntermediaire	boolean[][] la zone du sort sans prise en compte
	 *                          de la topologie de la map (obstacles, ...)
	 * @param depart	           GridPoint2 position du personnage
	 * @return	boolean[][] la zone finale qui prend en compte la topologie de la
	 *         map
	 */
	public boolean[][] getZoneFinale(boolean[][] zoneIntermediaire, GridPoint2 depart) {
		boolean[][] zoneFinale = new boolean[zoneIntermediaire.length][zoneIntermediaire[0].length];
		for (boolean[] colonne : zoneFinale) {
			Arrays.fill(colonne, false);
		}

		int x, y;	//Position x/y par rapport à la zone intermédiaire
		int dy = depart.y - zoneIntermediaire.length / 2;	//Ecart en Y entre y=0 de zoneIntermediaire et y=0 de tabTuiles
		int dx = depart.x - zoneIntermediaire[0].length / 2;	//Ecart en X entre x=0 de zoneIntermediaire et x=0 de tabTuiles
		int fx, fy;	//Position x/y par rapport à tabTuiles
		Array<GridPoint2> listePoints;	//Points récupérés de tracerSegment()

		//Parcours de zoneIntermediaire
		for (y = 0; y < zoneIntermediaire.length; y++) {
			for (x = 0; x < zoneIntermediaire[0].length; x++) {
				//On défini les positions pour tabTuiles
				fx = x + dx;
				fy = y + dy;

				//Si le point est dans la map et est ciblable
				if (fy >= 0 && fx >= 0 && fy < tabTuiles.length && fx < tabTuiles[0].length
						&& zoneIntermediaire[y][x]
						&& tabTuiles[fy][fx].isCiblable()) {

					//On lance Bresenham et on récupère les Points renvoyés
					listePoints = bresenham.line(depart.x, depart.y, fx, fy);

					//On défini les positions pour zoneIntermediaire
					int py = fy - dy;
					int px = fx - dx;

					if (isCiblable(listePoints, depart, new GridPoint2(fx, fy), dx, dy)) {
						zoneFinale[py][px] = true;
					}
					
				}
			}
		}

		return zoneFinale;
	}

	private boolean isCiblable(Array<GridPoint2> listePoints, GridPoint2 depart, GridPoint2 arrive, int dx, int dy) {
		if (depart.equals(arrive)) {
			return true;
		}
		if (!tabTuiles[arrive.y][arrive.x].isParcourable() && !tabTuiles[arrive.y][arrive.x].isOccupe()) {
			return false;
		}
		boolean ciblable = true;
		GridPoint2 p;
		for (int i = 0; i < listePoints.size; i++) {
			p = listePoints.get(i);
			//Si le point est le point de départ, on passe au suivant
			if (p.x == depart.x && p.y == depart.y) {
				continue;
			}

			if (tabTuiles[p.y][p.x].isLastVisible() && ciblable) {
				ciblable = p.x == arrive.x && p.y == arrive.y;
			}

			if (tabTuiles[p.y][p.x].getType().equals(TROU)) {
				continue;
			}

			//Si le point est un obstacle, on arrete
			if (!tabTuiles[p.y][p.x].isParcourable() && (p.x != arrive.x || p.y != arrive.y)) {
				return false;
			}

			//On défini les positions pour zoneIntermediaire
			int py = p.y - dy;
			int px = p.x - dx;

			//Si le point est pas dans la map
			if (py < 0 || px < 0) {
				return false;
			}
		}
		return ciblable;
	}

	public static MapSerializable getMapSerializable(FileHandle file) {
		try {
			ObjectInputStream inputFile = new ObjectInputStream(file.read());
			MapSerializable smap = (MapSerializable) inputFile.readObject();
			return smap;
		} catch (IOException | ClassNotFoundException ex) {
			System.err.println(file.file().getAbsolutePath());
			Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
}

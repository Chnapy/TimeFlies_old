/*
 * 
 * 
 * 
 */
package gameplay.map;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.DefaultConnection;
import com.badlogic.gdx.ai.pfa.indexed.IndexedNode;
import com.badlogic.gdx.utils.Array;
import gameplay.effet.Effet;
import gameplay.entite.Entite;
import gameplay.invocation.Invocation;
import static gameplay.map.EtatTuile.NORMAL;
import general.Orientation;
import java.awt.Point;

/**
 * Tuile.java
 * Représente une tuile (une case) de la map.
 *
 */
public class Tuile implements IndexedNode<Tuile> {

	//Type de la tuile (simple, obstacle, ...)
	private Type type;

	//Etat de la tuile (normal, zonesort, ...)
	private EtatTuile etat;

	//Position x/y
	private final Point position;

	//Pathfinding
	private final int index;
	private final Array<Connection<Tuile>> connections;

	//Occupation par une entité
	private boolean occupe;

	/**
	 *
	 * @param t
	 * @param pos
	 * @param index
	 */
	public Tuile(Type t, Point pos, int index) {
		type = t;
		etat = NORMAL;
		position = pos;
		this.index = index;
		connections = new Array<>();
	}

	public Type getType() {
		return type;
	}

	public EtatTuile getEtat() {
		return etat;
	}

	public void setEtat(EtatTuile etat) {
		this.etat = etat;
	}

	public Point getPosition() {
		return position;
	}

	@Override
	public int getIndex() {
		return index;
	}

	public boolean isOccupe() {
		return occupe;
	}

	public void setOccupe(boolean occupe) {
		this.occupe = occupe;
	}

	@Override
	public Array<Connection<Tuile>> getConnections() {
		return connections;
	}

	/**
	 * Debug
	 * Affichage des connections de la tuile
	 */
	public void connectionsSout() {
		System.out.println("---");
		connections.forEach((con) -> {
			System.out.println("[" + con.getFromNode().position.x + ":" + con.getFromNode().position.y + " - " + con.getToNode().position.x + ":" + con.getToNode().position.y + "]");
		});
		System.out.println("---");
	}

	/**
	 * Détruit les connections de la tuile
	 */
	public void clearConnections() {
		connections.clear();
	}

	/**
	 * La tuile recoit le sort et invoque l'invocation ect si besoins
	 *
	 * @param effets
	 * @param lanceur
	 * @param oriAttaque
	 * @param critique
	 */
	public void recoitSort(Effet[] effets, Entite lanceur, Orientation oriAttaque, boolean critique) {
		for (Effet effet : effets) {
			for (int j = 0; j < effet.getDeclencheur().size; j++) {
				if (effet.getDeclencheur().get(j) instanceof Invocation) {
					((Invocation) effet.getDeclencheur().get(j)).invoquer(this.getPosition());
				} else {
					effet.lancerEffetTuile(this, lanceur, oriAttaque, critique);
				}
			}
		}
	}

	/**
	 * Ajoute une connexion depuis une tuile adjacente
	 *
	 * @param adjacent
	 */
	public void addConnection(Tuile adjacent) {
		connections.add(new DefaultConnection<>(this, adjacent));
	}

}

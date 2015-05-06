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
import java.awt.Point;

/**
 * Tuile.java
 * Représente une tuile (une case) de la map.
 *
 */
public class Tuile implements IndexedNode<Tuile> {

	private Type type;
	private EtatTuile etat;
	private final Point position;
	private final int index;
	private final Array<Connection<Tuile>> connections;

	private boolean occupe;	//La tuile est occupée par une entité

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

	public void clearConnections() {
		connections.clear();
	}

	/**
	 * la tuile recoit le sort et invoque l'invocation ect si besoins
	 *
	 * @param effets
	 * @param lanceur
	 */
	public void recoitSort(Effet[] effets, Entite lanceur) {
		for (int i = 0; i < effets.length; i++) {
			for (int j = 0; j < effets[i].getDeclencheur().size; j++) {
				if (effets[i].getDeclencheur().get(j) instanceof Invocation) {
					((Invocation) effets[i].getDeclencheur().get(j)).invoquer(this.getPosition());
				} else {
					//TODO effets qui change la tuile bitch
				}
			}
		}
	}

	public void addConnection(Tuile adjacent) {
		connections.add(new DefaultConnection<>(this, adjacent));
	}

}

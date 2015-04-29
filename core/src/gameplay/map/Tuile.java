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

	public Point getPosition() {
		return position;
	}

	@Override
	public int getIndex() {
		return index;
	}

	@Override
	public Array<Connection<Tuile>> getConnections() {
		return connections;
	}

	public void addConnection(Tuile adjacent) {
		connections.add(new DefaultConnection<>(this, adjacent));
	}

}

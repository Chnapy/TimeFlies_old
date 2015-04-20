/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameplay.map.pathfinding;

import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.utils.Array;
import gameplay.map.Tuile;
import java.util.Iterator;

/**
 *
 * @author Stage
 */
public class Chemin extends Array<Tuile> implements GraphPath<Tuile> {

	@Override
	public int getCount() {
		return super.size;
	}

	@Override
	public Tuile get(int i) {
		return super.get(i);
	}

	@Override
	public void add(Tuile n) {
		super.add(n);
	}

	@Override
	public void clear() {
		super.clear();
	}

	@Override
	public void reverse() {
		super.reverse();
	}

	@Override
	public Iterator<Tuile> iterator() {
		return super.iterator();
	}

	@Override
	public String toString() {
		String ret = "Chemin :\n";
		for(Tuile tuile : this) {
			ret += tuile.getPosition().x + " " + tuile.getPosition().y + "\n";
		}
		return ret;
	}

}

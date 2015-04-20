/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameplay.map.pathfinding;

import com.badlogic.gdx.ai.pfa.Heuristic;
import gameplay.map.Tuile;

/**
 *
 * @author Stage
 */
public class Heuristique implements Heuristic<Tuile> {

	@Override
	public float estimate(Tuile start, Tuile end) {
//		if (end.getEtat().equals(Etat.OBSTACLE) || end.getEtat().equals(Etat.TROU)) {
//			return 0;
//		}
//		if (Math.abs(end.getPosition().x - start.getPosition().x) + Math.abs(end.getPosition().y - start.getPosition().y) > 1) {
//			return 0;
//		}
		return 0;
	}

}

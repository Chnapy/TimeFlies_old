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
		return 0;
	}

}

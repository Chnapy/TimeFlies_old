/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameplay.map.pathfinding;

import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import gameplay.map.Tuile;

/**
 *
 * @author Stage
 */
public class Finder extends IndexedAStarPathFinder<Tuile> {

	public Finder(IndexedGraph<Tuile> graph) {
		super(graph);
	}

	
}

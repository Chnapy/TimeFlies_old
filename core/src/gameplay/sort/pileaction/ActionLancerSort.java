package gameplay.sort.pileaction;

import gameplay.sort.SortActif;

import java.awt.Point;

/**
 * 
 * @author ydardot
 * action de lancer un sort sur une position donné
 */
public class ActionLancerSort extends Action{
	private SortActif sort;
	public ActionLancerSort(Point p1,SortActif sort) {
		super(p1);
		this.sort = sort;
	}
	
	/**
	 * 
	 * @return le sort de l'action
	 */
	public SortActif getSort(){
		return this.sort;
	}
}

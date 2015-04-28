/*
 * 
 * 
 * 
 */
package gameplay.effet;

import com.badlogic.gdx.utils.Array;


/**
 * Declenchable.java
 * Représente la condition pour qu'un effet se déclenche.
 *
 */
public class Declenchable {

	private Effet effet;
	private int minimum;
	private int maximum;

	/**
	 *
	 * @param ef
	 * @param min
	 * @param max
	 */
	public Declenchable(Effet ef, int min, int max) {
		effet = ef;
		minimum = min;
		maximum = max;
	}
	/**
	 * permet de savoir si le déclancheur peu se déclanger avec les effets 
	 * 
	 * @param effets
	 * @return true si les effects contienne le déclancheur sinon false
	 */
	public boolean canDeclanche(Array<Effet> effets){
		if(effet.getListBalus() != null || effet.getListBalus().size!=0)
			return this.balusCanDeclanche(effets);
		if(effet.getListEnvoutements()!=null || effet.getListEnvoutements().size!=0)
			return this.envoutementsCanDeclanche(effets);
		if(effet.getPlacement()!=null)
			return this.placementCanDeclanch(effets);
		if(effet.getInvocation()!=null)
			return true;
		return false;
	}
	/**
	 * test si le placement est le bon
	 * @param effets
	 * @return true si le placement es dans l'interval min/max
	 */
	private boolean placementCanDeclanch(Array<Effet> effets) {
		//TODO bah placement = une pos donc pas de distance donc pas de min/max
		return true;
	}
	/**
	 * test si l'envoutement est le bon
	 * @param effets
	 * @return true si l'envoutement es dans l'interval min/max
	 */
	private boolean envoutementsCanDeclanche(Array<Effet> effets) {
		for (int i = 0; i < effets.size; i++) {
			for (int j = 0; j < effets.get(i).getListEnvoutements().size; j++) {
				if(effets.get(i).getListEnvoutements().get(j).getNom().equals(effet.getListEnvoutements().get(0).getNom()) 
						&& effets.get(i).getListEnvoutements().get(j).getDuree()<=minimum 
						&& effets.get(i).getListEnvoutements().get(j).getDuree()>=maximum);
					return true;
			}
		}
		return false;
	}
	/**
	 * test si le balus est le bon
	 * @param effets
	 * @return true si le balus es dans l'interval min/max
	 */
	private boolean balusCanDeclanche(Array<Effet> effets) {
		for (int i = 0; i < effets.size; i++) {
			for (int j = 0; j < effets.get(i).getListBalus().size; j++) {
				if(effets.get(i).getListBalus().get(j).equals(effet.getListBalus().get(0)) 
						&& effets.get(i).getListBalus().get(j).getNombre()>=minimum 
						&& effets.get(i).getListBalus().get(j).getNombre()<=maximum );
					return true;
			}
		}
		return false;
	}

}

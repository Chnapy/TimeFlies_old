/*
 * 
 * 
 * 
 */
package gameplay.effet;

import com.badlogic.gdx.utils.Array;
import gameplay.envoutement.Envoutement;
import gameplay.invocation.Invocation;
import java.util.Objects;

/**
 * Effet.java
 * Représente un effet.
 * Peut être (un ou plusieurs) :
 * - un ou des bonus/malus de caractéristiques physiques
 * - un ou des envoutements
 * - un placement
 * - une invocation
 *
 */
public class Effet {

	private Array<Balus> listBalus;
	private Array<Envoutement> listEnvoutements;
	private Placement placement;
	private Invocation invocation;

	/**
	 *
	 * @param balus
	 * @param envoutements
	 * @param place
	 * @param invoc
	 */
	public Effet(Array<Balus> balus, Array<Envoutement> envoutements, Placement place, Invocation invoc) {
		listBalus = balus;
		listEnvoutements = envoutements;
		placement = place;
		invocation = invoc;
	}
	
	/**
	 * equals en fonction de toutes les listes
	 * l'effet comparer a des Balus/Envoutement/placement/invocation en plus 
	 * la fonction retournera true
	 */
	public boolean equals(Object o){
		if(!(o instanceof Effet))
			return false;
		}
		Effet effet = (Effet) o;
		if (this.listBalus != null || this.listBalus.size != 0) {
			for (int i = 0; i < this.listBalus.size; i++) {
				if (!effet.listBalus.contains(this.listBalus.get(i), false)) {
					return false;
				}
			}
		}
		if (this.listEnvoutements != null || this.listEnvoutements.size != 0) {
			for (int i = 0; i < this.listEnvoutements.size; i++) {
				if (!effet.listEnvoutements.contains(this.listEnvoutements.get(i), false)) {
					return false;
				}
			}
		}
		if (this.placement != null) {
			if (!this.placement.equals(effet.placement)) {
				return false;
			}
		}
		if (this.invocation != null) {
			if (!this.invocation.equals(effet.invocation)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 97 * hash + Objects.hashCode(this.listBalus);
		hash = 97 * hash + Objects.hashCode(this.listEnvoutements);
		hash = 97 * hash + Objects.hashCode(this.placement);
		hash = 97 * hash + Objects.hashCode(this.invocation);
		return hash;
	}

	/**
	 * 
	 * @return liste des balus
	 */
	public Array<Balus> getListBalus() {
		return listBalus;
	}

	/**
	 * 
	 * @return liste des envoutements
	 */
	public Array<Envoutement> getListEnvoutements() {
		return listEnvoutements;
	}

	/**
	 * 
	 * @return le placement
	 */
	public Placement getPlacement() {
		return placement;
	}
	
	/**
	 * 
	 * @return l'invocation
	 */
	public Invocation getInvocation() {
		return invocation;
	}

}

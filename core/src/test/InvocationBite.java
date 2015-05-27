package test;

import java.awt.Point;

import gameplay.caracteristique.CaracteristiquePhysique;
import gameplay.caracteristique.Orientation;
import gameplay.effet.Effet;
import gameplay.entite.Entite;
import gameplay.invocation.InvocationMobile;
import gameplay.sort.SortActif;
import gameplay.sort.SortPassif;

public class InvocationBite extends InvocationMobile{

	public InvocationBite( ) {
		super("une bite", 0, 0, Orientation.N, new SortPassif[]{},new SortActif[]{ new SortQuiFaitMal(new Effet[] {})}, new CaracteristiquePhysique(100, 100, 100, 100, 10, 10, 0, 0, 1, 1),
				1, 1);
	}

	@Override
	public void invoquer(Point point) {
		System.out.println("Invocation suprème de la bite des enfert");
		
	}

	@Override
	public boolean canDeclencher(Effet effet, int min, int max) {
		return false;
	}

	@Override
	public void lancer(Entite cible, int pourcentageSupp) {
		System.out.println("je lance que du sperm pd");
	}

}

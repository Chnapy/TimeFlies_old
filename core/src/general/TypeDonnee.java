/*
 * 
 * 
 * 
 */
package general;

/**
 * TypeDonnee
 * Enum
 */
public enum TypeDonnee {

	NOM("icons/nom.png", "Nom de l'entite"),
	VIE("icons/vie.png", "Vie restante / vie maxi de l'entite. Une fois la vie de l'entite à 0, elle rentre dans l'etat \"A terre\", et meurt au bout de 2 tours sans intervention exterieure."),
	TEMPSACTION("icons/tempsaction.png", "Temps d'action restant / temps d'action maxi de l'entite. Le temps d'action est utilisé pour toute action."),
	TEMPSSUPP("icons/tempssupp.png", "Temps d'action supplémentaire. Est utilise par une action si le temps d'action ne suffit pas."),
	FATIGUE("icons/fatigue.png", "Fatigue en %. Le temps d'action en est tout autant réduit."),
	VITESSEACTION("icons/vitesseaction.png", "Vitesse d'action en %. Influence le temps d'action demandé par les actions."),
	DEGATS("icons/degats.png", "Dégats causés par l'action."),
	ZONEPORTEE("icons/zoneportee.png", "Zone de portée de l'action."),
	ZONEACTION("icons/zoneaction.png", "Zone d'action de l'action. Peut toucher plusieurs cibles."),
	COOLDOWN("icons/cooldown.png", "Nombre de tours avant réutilisation du sort.");
	
	private final String path;
	private final String bulle;
	
	TypeDonnee(String pathTexture, String _bulle) {
		path = pathTexture;
		bulle = _bulle;
	}
	
	public String getPath() {
		return path;
	}
	
	public String getBulle() {
		return bulle;
	}

}

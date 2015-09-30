/*
 * 
 * 
 * 
 */
package vue.jeu.entites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import general.EtatGraphique;
import static general.EtatGraphique.STAY;
import static general.EtatGraphique.WALK;
import general.Orientation;
import static general.Orientation.EST;
import static general.Orientation.NORD;
import static general.Orientation.OUEST;
import static general.Orientation.SUD;

/**
 * AnimationManager.java
 *
 */
public class AnimationManager {

	//E/perso1_walk_E
	public final String nom_perso;
	private final Animation[][] etats;

	private float stateTime;
	private EtatGraphique lastEtat;
	private Orientation lastOrientation;

	public AnimationManager(String nomPerso, float stay_duration, float walk_duration) {
		nom_perso = nomPerso;
		stateTime = 0;
		lastEtat = null;
		lastOrientation = null;
		
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("perso/" + nom_perso + "/" + nom_perso + ".atlas"));
		Animation[] stayArray = {
			new Animation(stay_duration, atlas.findRegions(NORD.min + "/" + nom_perso + "_" + STAY + "_" + NORD.min)),
			new Animation(stay_duration, atlas.findRegions(SUD.min + "/" + nom_perso + "_" + STAY + "_" + SUD.min)),
			new Animation(stay_duration, atlas.findRegions(OUEST.min + "/" + nom_perso + "_" + STAY + "_" + OUEST.min)),
			new Animation(stay_duration, atlas.findRegions(EST.min + "/" + nom_perso + "_" + STAY + "_" + EST.min)),};
		Animation[] walkArray = {
			new Animation(walk_duration, atlas.findRegions(NORD.min + "/" + nom_perso + "_" + WALK + "_" + NORD.min)),
			new Animation(walk_duration, atlas.findRegions(SUD.min + "/" + nom_perso + "_" + WALK + "_" + SUD.min)),
			new Animation(walk_duration, atlas.findRegions(OUEST.min + "/" + nom_perso + "_" + WALK + "_" + OUEST.min)),
			new Animation(walk_duration, atlas.findRegions(EST.min + "/" + nom_perso + "_" + WALK + "_" + EST.min)),};

		etats = new Animation[][]{
			stayArray,
			walkArray
		};

		for (Animation anim : stayArray) {
			for (TextureRegion region : anim.getKeyFrames()) {
				region.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
			}
		}

		for (Animation anim : walkArray) {
			for (TextureRegion region : anim.getKeyFrames()) {
				region.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
			}
		}
	}

	public TextureRegion getFrame(EtatGraphique e, Orientation o, float delta) {
		if (e != lastEtat || o != lastOrientation) {
			stateTime = 0;
			lastEtat = e;
			lastOrientation = o;
		}
		stateTime += delta;
		return etats[e.tabIndex][o.tabIndex].getKeyFrame(stateTime, true);
	}

}

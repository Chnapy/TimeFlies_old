/*
 * 
 * 
 * 
 */
package general;

import com.badlogic.gdx.math.Bresenham2;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

/**
 * MyBresenham.java
 *
 */
public class MyBresenham extends Bresenham2 {

	private static final int REC_WIDTH = 50;
	private static final int HALF_REC = REC_WIDTH / 2;

	private final Polygon[][] listeRec;

	public MyBresenham(int width, int height) {

		listeRec = new Polygon[height][width];

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				listeRec[i][j] = new Polygon(new float[]{
					0,
					0,
					0,
					REC_WIDTH,
					REC_WIDTH,
					REC_WIDTH,
					REC_WIDTH,
					0
				});
				listeRec[i][j].setPosition(j * REC_WIDTH + j, i * REC_WIDTH + i);
			}
		}
	}

	@Override
	public Array<GridPoint2> line(int startX, int startY, int endX, int endY, Pool<GridPoint2> pool, Array<GridPoint2> output) {

		for (int i = 0; i < listeRec.length; i++) {
			for (int j = 0; j < listeRec[0].length; j++) {
				if (Intersector.intersectSegmentPolygon(
						new Vector2(listeRec[startY][startX].getX() + HALF_REC, listeRec[startY][startX].getY() + HALF_REC),
						new Vector2(listeRec[endY][endX].getX() + HALF_REC, listeRec[endY][endX].getY() + HALF_REC),
						listeRec[i][j]
				)) {
					GridPoint2 point = pool.obtain();
					point.set(j, i);
					output.add(point);
				}
			}
		}
		return output;
	}

}

/*
 * 
 * 
 * 
 */
package general;

/**
 * GridPointFloat2.java
 *
 */
public class GridPointFloat2 {

	public float x;
	public float y;

	public GridPointFloat2(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public GridPointFloat2(GridPointFloat2 point) {
		x = point.x;
		y = point.y;
	}

	public GridPointFloat2 set(float x, float y) {
		this.x = x;
		this.y = y;
		return this;
	}

	public GridPointFloat2 set(GridPointFloat2 point) {
		x = point.x;
		y = point.y;
		return this;
	}

}

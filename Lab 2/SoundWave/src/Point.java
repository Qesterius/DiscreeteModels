public class Point {

	public Point nNeighbor;
	public Point wNeighbor;
	public Point eNeighbor;
	public Point sNeighbor;
	public float nVel;
	public float eVel;
	public float wVel;
	public float sVel;
	public float pressure;
	private int sinInput;

	public static Integer []types = {0,1,2}; // 0-air,1-wall,2-soundSource
	int type;

	public Point() {
		clear();
		type=0;
	}

	public void clicked() {
		pressure = 1;
	}
	
	public void clear() {
		// TODO: clear velocity and pressure
		nVel = 0;
		eVel = 0;
		wVel = 0;
		sVel = 0;
		pressure = 0;
	}

	public void updateVelocity() {
		if(type == 0)
		{
			// TODO: velocity update
			nVel -= nNeighbor.getPressure() - getPressure();
			sVel -= sNeighbor.getPressure() - getPressure();
			eVel -= eNeighbor.getPressure() - getPressure();
			wVel -= wNeighbor.getPressure() - getPressure();
		}
	}

	public void updatePresure() {
		// TODO: pressure update
		if(type == 0)
		{
			pressure -= 0.5 * (nVel+sVel+eVel+wVel);

		}
		else if(type == 2)
		{
			sinInput = ((sinInput+1)%(360));
			double radians = Math.toRadians(sinInput);
			pressure = (float) (Math.sin(radians));
		}
	}

	public float getPressure() {
		return pressure;
	}
}
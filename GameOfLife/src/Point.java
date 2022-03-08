import java.util.ArrayList;
import java.util.Random;

public class Point {
	Random random;
	private ArrayList<Point> neighbors;
	private int currentState;
	private int nextState;
	private int numStates = 6;
	private int MODE;
	
	public Point( int _MODE ) {
		currentState = 0;
		nextState = 0;
		neighbors = new ArrayList<Point>();
	    MODE = _MODE;
	    random = new Random();
	}

	public void clicked() {
		currentState=(++currentState)%numStates;	
	}
	
	public int getState() {
		return currentState;
	}

	public void setState(int s) {
		currentState = s;
	}

	public void calculateNewState() {

	    if( MODE == 1){
            //TODO: insert logic which updates according to currentState and
            //number of active neighbors
            int _numOfAliveNeighbors = numOfAliveNeighbors();
            if(_numOfAliveNeighbors == 3)
            {
                nextState = 1;
            }
            else if(_numOfAliveNeighbors==2)
            {
                nextState = currentState;
            }
		}
		else if(MODE==0){
		    if( currentState>0 )
		        nextState = currentState-1;
		    else if(currentState==0 && numOfAliveNeighbors()>0){
		        nextState = 6;

		    }

		}
	}

	public void changeState() {
		currentState = nextState;
	}
	
	public void addNeighbor(Point nei) {
		neighbors.add(nei);
	}
	
	//TODO: write method counting all active neighbors of THIS point
	public int numOfAliveNeighbors(){
        int result =0;
        for(Point neighbor: neighbors)
        {
            result += neighbor.getState();
        }
        return result;
	}

	public void drop(){
	    if( random.ints(0, 100).findFirst().getAsInt() <5)
	    {
	        setState(6);
	    }

	}
}

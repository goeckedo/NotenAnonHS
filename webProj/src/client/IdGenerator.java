package client;

public class IdGenerator {
	
	private int min,max;
	
	public IdGenerator(int min, int max) {
		super();
		this.min = min;
		this.max = max;
	}
	
	public IdGenerator(int max) {
		super();
		this.min = 0;
		this.max = max;
	}
	
	public int[] generateID(){
	    int[] lottery = new int[max];
	    int randomNum;
	    
	    for (int i = 0; i < max; i++) {
	        randomNum = (int) (Math.random() * max)+min; 			// Get random num
	        for (int j = 0; j < i; j++) {
	            if (lottery[j] == randomNum) 					// Nummer doppelt ? 
	            {
	                randomNum = (int) (Math.random() * max)+min; 	// Falls doppelt mach neu
	                j = -1;						
	            }

	        }
	        lottery[i] = randomNum;
	    }

	    for (int i = 0; i < lottery.length; i++)
	        System.out.print(lottery[i] + " ");
	    
		return lottery;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}
	
	
	
}

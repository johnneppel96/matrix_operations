package saddlePointMatrix;

public class RowThread extends Thread {
	//this is simply to keep track of what row index from the matrix that the following row-array belongs to
	private int rowIndexNum; 
	private int[] rowFromMatrix;
	private int minNumFromRow=0;
	
	public RowThread(int rowIndexNum, int[] rowFromMatrix) {
		this.rowIndexNum=rowIndexNum;
		this.rowFromMatrix=rowFromMatrix;
	}
	
	public void run() {
		 //first assumes that the first element is the smallest
		int smallestValue=rowFromMatrix[0];
		for(int i=0;i<rowFromMatrix.length; i++) {
			//if the element at that index is less than the current smallest value
			if(rowFromMatrix[i]<smallestValue) { 
				smallestValue=rowFromMatrix[i];
			}
			
		}
		this.minNumFromRow=smallestValue;
	}
	
	
	public int getRowIndexNum() {
		return this.rowIndexNum;
	}
	
	public int getMinValueFromRow() {
		return minNumFromRow;
	}

}

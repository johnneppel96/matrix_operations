package saddlePointMatrix;

public class ColumnThread extends Thread {
	private int colIndexNum; 
	private int[] colFromMatrix;
	private int MAXNumFromRow=0;
	
	public ColumnThread(int colIndexNumber, int[] columnFromMatrix) {
		this.colIndexNum=colIndexNumber;
		this.colFromMatrix= columnFromMatrix;
	}
	
	public void run() {
		int largestValue= colFromMatrix[0]; //first assumes that the first element is the LARGEST
		for(int i=0;i<colFromMatrix.length; i++) {
			if(colFromMatrix[i]>largestValue) { //if the element at that index is greater than the current largest value
				largestValue=colFromMatrix[i];
			}
			
		}
		this.MAXNumFromRow=largestValue;
	}
	
	
	public int getColIndexNum() {
		return this.colIndexNum;
	}
	
	public int getMaxNumFromCol() {
		return MAXNumFromRow;
	}
}

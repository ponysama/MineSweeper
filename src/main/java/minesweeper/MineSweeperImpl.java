package minesweeper;

public class MineSweeperImpl implements MineSweeper{
	
	private MineHole[][] myMineField;

	public void setMineField(int N, int M, String mineField) throws IllegalFieldSizeException {
		// TODO Auto-generated method stub
		if(N<=0 || M<=0) throw new IllegalFieldSizeException("MineField size is illegal");
		this.myMineField = new MineHole[N][M];
		int currentCharPointer = 0;
		for(int row =0;row<N;row++){
			for(int col = 0; col<M; col++){
				this.myMineField[row][col] = new MineHole(inFirstColumn(col),inLastColumn(col,M),inLastRow(row,N),hasMine(mineField.charAt(currentCharPointer)),row,col);
				currentCharPointer ++;
			}
			currentCharPointer ++;
		}
	}

	public String getHintField() throws IllegalStateException {
		// TODO Auto-generated method stub
		return null;
	}
	
	private boolean inFirstColumn(int col){
		return (col==0);
	}
	
	private boolean inLastColumn(int col, int lastCol){
		return (col==lastCol-1);
	}
	
	private boolean inLastRow(int row, int lastRow){
		return (row==lastRow-1);
	}
	
	private boolean hasMine(char c){
		return (c=='*');
	}
	
	class MineHole {
		boolean inFirstColumn;
		boolean inLastColumn;
		boolean inLastRow;
		boolean hasMine;
		int row,column;
		private int neiborMineCount = 0;
		
		MineHole(boolean firCol, boolean lastCol,boolean lastRow, boolean hasMine,int row,int col){
			this.inFirstColumn = firCol;
			this.inLastColumn = lastCol;
			this.inLastRow = lastRow;
			this.hasMine = hasMine;
			this.row = row;
			this.column = col;
		}
		
		void checkNeibors(){
			if(this.inLastRow){
				if(!this.inLastColumn){
					this.exchangeInfo(myMineField[this.row][this.row+1]);
				}
			}
			else if(this.inFirstColumn && this.inLastColumn){
				this.exchangeInfo(myMineField[this.row+1][this.column]);
				
			}
			else if(this.inFirstColumn){
				this.exchangeInfo(myMineField[this.row][this.column+1]);
				this.exchangeInfo(myMineField[this.row+1][this.column]);
				this.exchangeInfo(myMineField[this.row+1][this.column+1]);
			}
			else if(this.inLastColumn){
				this.exchangeInfo(myMineField[this.row+1][this.column]);
				this.exchangeInfo(myMineField[this.row-1][this.column-1]);
			}
			else{
				this.exchangeInfo(myMineField[this.row][this.column+1]);
				this.exchangeInfo(myMineField[this.row+1][this.column]);
				this.exchangeInfo(myMineField[this.row+1][this.column+1]);
				this.exchangeInfo(myMineField[this.row-1][this.column-1]);
			}
		}
		
		void exchangeInfo(MineHole neibor){
			if(this.hasMine && !neibor.hasMine){
				neibor.increment();
			}
			if(!this.hasMine && neibor.hasMine){
				this.increment();
			}
		}
		
		void increment(){
			this.neiborMineCount++;
		}
		
		int getNeiborMineCount(){
			return this.neiborMineCount;
		}
	}
	
	public class IllegalFieldSizeException extends Exception{
		public IllegalFieldSizeException(String message) {
	        super(message);
	    }
	}
	
}
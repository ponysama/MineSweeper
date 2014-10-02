package minesweeper;

import minesweeper.MineSweeperImpl.IllegalFieldSizeException;

public class testme{
	public static void main(String args[]) throws IllegalFieldSizeException{
		MineSweeper haha = new MineSweeperImpl();
		haha.setMineField(3, 4, "*...\n..*.\n....");
		
	}
}
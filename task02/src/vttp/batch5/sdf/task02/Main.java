package vttp.batch5.sdf.task02;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {

		String fileName = args[0];
		System.out.println("Processing: " +  fileName + "\n");
		File f = new File(fileName);

		if (!f.exists()){
			System.out.println("Erorr: file does not exist.");
		}
		else {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String lineRead = "";

			String[][] board = {
				{" ", " ", " "},
				{" ", " ", " "},
				{" ", " ", " "}
				};

			Map <int[], Integer> utility = new HashMap<>();			

			System.out.println("Board: ");
			int lineCount = 0;
			while ((lineRead = br.readLine())!=null){
				System.out.println(lineRead); // print board
				String[] terms = lineRead.split("");

				for (int i = 0 ; i < terms.length ; i++){ // assign board to node and freeSpaces as key to utility map
					board[lineCount][i] = terms[i];
					if (terms[i].equals(".")){
						int[] position = {lineCount, i};
						utility.put(position, 0);
					}
				}
				lineCount += 1;
			}
			System.out.println("------------------------------------");

			for (int[] freePosition : utility.keySet()){
				String[][] boardCopy = copyBoard(board);
				boardCopy[freePosition[0]][freePosition[1]] = "X";
				// evaluate score of move at the current free position and assign it as value to this key 
				int score = evaluateMove(freePosition, boardCopy);
				utility.put(freePosition, score);
			}
			for (int[] move : utility.keySet()){
				int row = move[0];
				int col = move[1];
				System.out.println("y="+row + ", x="+col + ", utility=" + utility.get(move));
			}
			br.close();
			fr.close();
		}
		
	}

	public static String[][] copyBoard(String[][] board){
		String[][] boardCopy = {
            {" ", " ", " "},
            {" ", " ", " "},
            {" ", " ", " "}
            };

		for (int i = 0 ; i < 3 ; i++){
			for (int j = 0 ; j < 3 ; j ++){
				boardCopy[i][j] = board[i][j];
			}
		}
		return boardCopy;

	}

	public static int evaluateMove(int[] freePosition, String[][] board){
		board[freePosition[0]][freePosition[1]] = "X";
		if (playerCanWin(board)){
			return 1;
		}
		else if (cpuCanWin(board)){
			return -1;
		}
		else {
			return 0;
		}
	}

	public static boolean playerCanWin(String[][] board){
        // check all horizontal rows
        if (board[0][0].equals("X") && board[0][1].equals("X") && board[0][2].equals("X")){
            return true;
        }
        else if (board[1][0].equals("X") && board[1][1].equals("X") && board[1][2].equals("X")){
            return true;
        }
        else if (board[2][0].equals("X") && board[2][1].equals("X") && board[2][2].equals("X")){
            return true;
        }
        // check all vertical cols
        else if (board[0][0].equals("X") && board[1][0].equals("X") && board[2][0].equals("X")){
            return true;
        }
        else if (board[0][1].equals("X") && board[1][1].equals("X") && board[2][1].equals("X")){
            return true;
        }
        else if (board[0][2].equals("X") && board[1][2].equals("X") && board[2][2].equals("X")){
            return true;
        }
        // check two diagonals
        else if (board[0][0].equals("X") && board[1][1].equals("X") && board[2][2].equals("X")){
            return true;
        }
        else if (board[0][2].equals("X") && board[1][1].equals("X") && board[2][0].equals("X")){
            return true;
        }
        return false;
    }

	public static boolean cpuCanWin(String[][] board){
		// check rows
		int row = 0;
		while (row < 3) {
			int oCounter = 0;
			int spaceCounter = 0;
			for (int j = 0 ; j < 3 ; j++){
				if (board[row][j].equals("O")){
					oCounter += 1;
				}
				else if (board[row][j].equals(".")){
					spaceCounter += 1;
				}
			}
			if (oCounter == 2 && spaceCounter == 1){
				return true;
			}
			row += 1;
		}
		// check col
		int col = 0;
		while (col < 3){
			int oCounter = 0;
			int spaceCounter = 0;
			for (int i = 0 ; i < 3 ; i ++){
				if (board[i][col].equals("O")){
					oCounter += 1;
				}
				else if (board[i][col].equals(".")){
					spaceCounter += 1;
				}
			}
			if (oCounter == 2 && spaceCounter == 1){
				return true;
			}
			col += 1;
		}
		// check diagonals
		ArrayList<int[]> diagonal1 = new ArrayList<>();
		ArrayList<int[]> diagonal2 = new ArrayList<>();
		diagonal1.add(new int[] {0,0});
		diagonal1.add(new int[] {1,1});
		diagonal1.add(new int[] {2,2});

		diagonal2.add(new int[] {0,2});
		diagonal2.add(new int[] {1,1});
		diagonal2.add(new int[] {2,0});

		int d1oCounter = 0;
		int d1spaceCounter = 0;
		for (int[] diagPosition : diagonal1){
			if (board[diagPosition[0]][diagPosition[1]].equals("O")){
				d1oCounter += 1;
			}
			else if (board[diagPosition[0]][diagPosition[1]].equals(".")){
				d1spaceCounter += 1;
			}
		}
		if (d1oCounter==2 && d1spaceCounter == 1){
			return true;
		}

		int d2oCounter = 0;
		int d2spaceCounter = 0;
		for (int[] diagPosition : diagonal2){
			if (board[diagPosition[0]][diagPosition[1]].equals("O")){
				d2oCounter += 1;
			}
			else if (board[diagPosition[0]][diagPosition[1]].equals(".")){
				d2spaceCounter += 1;
			}
		}
		if (d2oCounter==2 && d2spaceCounter == 1){
			return true;
		}
		return false;
	}
}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	
	static ArrayList<Integer> playerPositions = new ArrayList<>();
	static ArrayList<Integer> cpuPositions = new ArrayList<>();

	public static void main(String[] args) {
		char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
							  {'-', '+', '-', '+', '-'},
							  {' ', '|', ' ', '|', ' '},
							  {'-', '+', '-', '+', '-'},
							  {' ', '|', ' ', '|', ' '}};
	
		printGameBoard(gameBoard);
		
		while(true) {
			Scanner input = new Scanner(System.in);
			System.out.println("Enter you placement (1-9): ");
			int playerPlacement = input.nextInt();
			while(playerPositions.contains(playerPlacement) || cpuPositions.contains(playerPlacement)) {
				System.out.println("That spot is already taken!");
				playerPlacement = input.nextInt();
			}
			placePiece(gameBoard, playerPlacement, "player");
			String winResult = checkWinner();
			if(winResult.length() > 0) {
				System.out.println(winResult);
				break;
			}
			
			Random rand = new Random();
			int cpuPlacement = rand.nextInt(9) + 1;
			while(playerPositions.contains(cpuPlacement) || cpuPositions.contains(playerPlacement)) {
				cpuPlacement = rand.nextInt(9) + 1;
			}
			placePiece(gameBoard, cpuPlacement, "cpu");
			
			printGameBoard(gameBoard);
			
			winResult = checkWinner();
			if(winResult.length() > 0) {
				System.out.println(winResult);
				break;
			}
			
		}
		
		printGameBoard(gameBoard);
		
	}
	
//	---END MAIN---

	public static void printGameBoard(char[][] gameBoard) {
		for(char[] row : gameBoard) {
			for(char c : row) {
				System.out.print(c);
			}
			System.out.println();
		}
	}
	
	public static void placePiece(char[][] gameBoard, int placement, String user) {
		
		char symbol = ' ';
		
		if(user.equals("player")) {
			symbol = 'X';
			playerPositions.add(placement);
		}
		else if(user.equals("cpu")) {
			symbol = 'O';
			cpuPositions.add(placement);
		}
		
		
		switch(placement) {
			case 1:
				gameBoard[0][0] = symbol;
				break;
			case 2:
				gameBoard[0][2] = symbol;
				break;
			case 3:
				gameBoard[0][4] = symbol;
				break;
			case 4:
				gameBoard[2][0] = symbol;
				break;
			case 5:
				gameBoard[2][2] = symbol;
				break;
			case 6:
				gameBoard[2][4] = symbol;
				break;
			case 7:
				gameBoard[4][0] = symbol;
				break;
			case 8:
				gameBoard[4][2] = symbol;
				break;
			case 9:
				gameBoard[4][4] = symbol;
				break;
			default:
				break;
		}
	}
	
	public static String checkWinner() {
		
		List topRow = Arrays.asList(1, 2, 3);
		List midRow = Arrays.asList(4, 5, 6);
		List btmRow = Arrays.asList(7, 8, 9);
		List leftCol = Arrays.asList(1, 4, 7);
		List midCol = Arrays.asList(2, 5, 8);
		List rightCol = Arrays.asList(3, 6, 9);
		List firstDiag = Arrays.asList(1, 5, 9);
		List secondDiag = Arrays.asList(3, 5, 7);
		
		List<List> winConditions = new ArrayList<>();
		winConditions.add(topRow);
		winConditions.add(midRow);
		winConditions.add(btmRow);
		winConditions.add(leftCol);
		winConditions.add(midCol);
		winConditions.add(rightCol);
		winConditions.add(firstDiag);
		winConditions.add(secondDiag);
		
		for(List l : winConditions) {
			if(playerPositions.containsAll(l)) {
				return "Congrats, you won!";
			}
			else if(cpuPositions.containsAll(l)) {
				return "Sorry, CPU won!";
			}
			else if(playerPositions.size() + cpuPositions.size() == 9) {
				return "It's a tie!";
			}
		
		
		}
		return "";
	}

}

/**
 * COSC 1020 - Lab 6
 * OOP for creating a map, the lab section includes three methods, 
 * @author Alex Alarcon, Gavin Kunish
 * Instructor Jain
 * TA-BOT:MAILTO  alex.alarcon@marquette.edu gavin.kunish@marquette.edu
 */
import java.util.Random;
/** 
 * The Map class is used to create and manipulate voting maps. The value of a
 * cell on the map denotes the party for which the majority of the population 
 * of that cell votes for. For instance, in the following map, PARTY_X is the
 * choice of voters in three cells, while PARTY_O is preferred in the rest of
 * the map:
 *   O  X  O
 *   X  O  X
 *   O  O  O
 * A map is always a square, i.e. the total width and length are always equal.
 * The size of the map above is 3.   
 */
public class Map {
	Random RAD = new Random();
	
    private static final char PARTY_X = 'X'; // the symbol for PARTY_X on the map
    private static final char PARTY_O = 'O'; // the symbol for PARTY_O on the map
    private static int countX1,totalMarks1,countX,totalMarks; //counters
    
    private int size; // the map size, which is the size of one dimension
    private char[][] vote; // the array holding the value of each map cell

    /** 
     * TODO PROJECT: Accessor for the vote instance variable 
     * The accessor should return a copy of the vote instance variable instead of
     * a reference to the memory location of the vote array, so that privacy
     * leaks are avoided.
     * @return a reference to a char[][] array
     */
    public char[][] getVote(){
    	vote = new char[this.size][this.size];
    	char[][] copy = vote.clone();
        return copy;
    }

    private double probability = RAD.nextDouble();

	private int[] sequence;
    // the probability with which a cell on the map votes for PARTY_X

    /** 
     * TODO LAB: Accessor for the probability instance variable
     * @return the double value of the probability
     */
    public double getProbability() {
    	return probability;
    }

    /**
     * TODO LAB: Initializes a Map object by creating and filling in the vote array.
     * @param size The size of the map to be produced. For instance, if a 5x5 map
     * should be generated, then size should have the value 5.
     * @param probability The probability with which PARTY_X wins a cell on the
     * map.
     */
    public Map(int size, double probability) {
    	this.probability = probability;
    	this.size = size;
    	this.vote = getVote();
    	fillVote();
    }

    /** 
     * TODO LAB: Fills the vote array with appropriate values depending on the value
     * of the probability instance variable.
     * The constructor should call this method.
     */
    private void fillVote() {
    	for(int r = 0; r<vote.length;r++) {
    		for (int co= 0; co<vote[r].length;co++) {
    			if(getProbability()>RAD.nextDouble()) {
    				vote[r][co]= PARTY_X;
    				countX++;
    				totalMarks++;
    			}
    			else {
    				vote[r][co]= PARTY_O;
    				totalMarks++;
    			}
    		}
    	}
    }

    /**
     * TODO PROJECT: Initializes a Map object by creating and filling in the vote 
     * array. 
     * The constructor creates the vote array and checks whether the sequence is 
     * valid (see the description of the isValidSequence method). If it is, then 
     * it populates the vote array with values guided by a sequence (see the 
     * description in the fillVote(int[]) method.)
     * @param size The size of the map to be produced. For instance, if a 5x5 map
     * should be generated, then size should have the value 5.
     * @param sequence The list of integers denoting the presence of each party in the
     * voting map.
     */    
    public Map(int size, int[] sequence) {
    	this.sequence =sequence;
    	this.size = size;
    	this.vote = getVote();
    	if(isValidSequence(this.sequence)) {
    		 fillVote(this.sequence);
    	}
    }

    /**
     * TODO PROJECT: Fills the vote array with values based on the sequence of 
     * integers provided as input. For example, if the input list for an array of
     * size 5 contains the numbers {1, 7, 8, 3, 4, 2}, then the vote array should
     * be constructed as follows:
     * - the first cell should vote for PARTY_X
     * - the next 7 cells should vote for PARTY_O
     * - the following 8 cells should vote for PARTY_X
     * - the next 3 cells should vote for PARTY_O
     * - the following 4 cells should vote for PARTY_X
     * - the final 2 cells should vote for PARTY_O
     * The resulting array of this example, when printed, will look like this:
     *   X  O  O  O  O
     *   O  O  O  X  X
     *   X  X  X  X  X
     *   X  O  O  O  X
     *   X  X  X  O  O 
     * As illustrated by the example, the numbers in the sequence denote how many
     * cells vote for each of the two parties. When considering the next cell of 
     * a cell at the end of a row, we move to the first cell of the next row. 
     * @param sequence The list of integers denoting thepresence of each party 
     * in the voting map.
     */
    private void fillVote(int[] sequence) {
    	int index = 0;
    	int r=0,co=0;
    	this.sequence = sequence;
    	while(index<this.sequence.length) {
    		if(index%2==0) {
    			for(int count=0;count<this.sequence[index];count++) {
    				if(co==vote.length) {
    					r++;
    					co = 0;
    				}
    				vote[r][co]= PARTY_X;
    				countX++;
    				totalMarks++;
    				co++;
    			}
    			index++;
    		}
    		else {
    			for(int count=0;count<this.sequence[index];count++) {
    				if(co==vote.length) {
    					r++;
    					co = 0;
    				}
    				vote[r][co]= PARTY_O;
    				totalMarks++;
    				co++;
    			}
    			index++;
    		}
    	}
    }
    		

    /**
     * TODO PROJECT: Verifies that the sequence of integers which is supposed to 
     * be used for the construction of the vote array is valid. A sequence is
     * valid, if the sum of its elements is equal to size * size.
     * @param sequence The list of integers denoting the presence of each party 
     * in the voting map.
     * @return a boolean value denoting whether the argument is a valid sequence 
     */
    private boolean isValidSequence(int[] sequence) {
    	int sum = 0;
    	this.sequence = sequence;
    	for(int i = 0; i<this.sequence.length; i++) {
    		sum+= this.sequence[i];
    	}
    	if (sum == size*size){
    		return true;
    	}
    	else {
    		System.out.print("No can do, sequence doesn't add up to total marks.");
    		return false;
    	}
    			
    }    
    
    /** 
     * TODO LAB: Prints the map row after row. 
     * The output formatting for the printing of each cell consists of
     * System.out.printf("%3c", vote[i][j]);
     */
    public void printArry() {
    	for(int r=0; r<vote.length; r++) {
    		for(int c=0;c<vote.length;c++) {
    			System.out.print(vote[r][c]);
    		}
    }
    }
    public void printMap() {
    	int i =0;
    	for(int r=0; r<vote.length; r++) {
    		for(int c=0;c<vote.length;c++) {
    			if(i<vote.length-1) {
    			System.out.printf("%3c",vote[r][c]);
    			i++;
    			}
    			else {
    				System.out.printf("%3c\n",vote[r][c]);
    				i=0;
    			}
    		}
    	}
    }

    /** 
     * TODO LAB: Calculates the number of cells voting for PARTY_X over the total
     * number of cells on the map. 
     * @return a double value between 0.0 and 1.0
     */
    public double countPercentageX() {
	return (double)countX/totalMarks;
    }

    /**
     * TODO PROJECT: Determines whether two maps are equal.
     * Two Map objects are considered equal, if their vote arrays contain the 
     * same values at the same array positions.
     * @param m The Map to which this object will be compared to.
     * @return a boolean value denoting whether the two Map objects are equal
     */
    public boolean equals(Map m) {
	return false;
    }

    /**
     * TODO PROJECT: The method is only supposed to be called on Map objects of
     * size 3. Given a 3x3 vote array, the method determines whether there exists
     * at least one way to draw voting districts so that PARTY_X can win 2 out of
     * 3 districts or more. Each district in a 3x3 array consists of 3 cells, so
     * there can only be 3 districts in total. Recall that district cells need to
     * be adjacent to each other: neighboring cells in a row or column, but not 
     * on a diagonal.
     * The method does not need to construct the drawing of the districts. It 
     * only needs to determine whether there exists a drawing that results in
     * PARTY_X being the winner.
     * Hint: Start designing your solution by examining the number of cells 
     * PARTY_X may have in the vote array and whether there are cases that this
     * number makes it so that PARTY_X can never win or PARTY_X will definitely
     * win.
     * @return a boolean value denoting whether there exists at least one drawing
     * of the districts that will proclaim PARTY_X as the election winner.  
     */
    public boolean canXWin() {
    	if (size==3) {
    		if(countX<4) {
    			return false;
    		}
    		else if(countX<5) {
    			return true;
    		}
    	}
    	return false;
    }
}

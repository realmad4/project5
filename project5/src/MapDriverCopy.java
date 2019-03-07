/* This class is given for your assistance in testing. The output of your 
 * program should look similar to the following:

  O  X  X  O  O
  O  X  X  X  X
  X  X  X  X  O
  O  X  X  X  X
  X  X  X  X  O
Actual: 0.720
Target: 0.700

  O  O  O  O  X  O  X  O
  O  O  O  X  O  X  X  X
  O  O  X  O  O  O  O  O
  X  O  O  X  O  O  O  O
  X  O  X  X  O  O  X  O
  X  O  X  X  O  X  O  O
  O  O  X  O  X  X  O  O
  O  X  O  O  X  O  O  X
Actual: 0.359
Target: 0.300

 */
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

public class MapDriverCopy {
    public static void main(String[] args) {
		
	Map m1 = new Map(3, 0.5);
	m1.printMap();
	System.out.printf("Actual: %.3f\nTarget: %.3f\n\n",
			  m1.countPercentageX(), m1.getProbability());
	
	Map m2 = new Map(8, 0.3);
	m2.printMap();
	System.out.printf("Actual: %.3f\nTarget: %.3f\n",
			  m2.countPercentageX(), m2.getProbability());
	int arry[] = {1,7,8,3,4,2};
	Map m3 = new Map(5,arry);
	//m3.printArry();
	System.out.println("");
	m3.printMap();
	System.out.printf("Actual: %.3f\nTarget: %.3f\n",
			  m3.countPercentageX(), m3.getProbability());
	m1.printMap();
    }
}

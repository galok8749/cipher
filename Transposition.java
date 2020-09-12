import java.util.*;

public class Transposition {
	public static String msg;

	public static void doEncryption() {
		Scanner sc = new Scanner(System.in);

		System.out.println("Encryption...............");
		System.out.print("enter plain text    ->");
		msg = sc.nextLine().toUpperCase().replace(" ", "");
	
		System.out.print("key size    ->");
		int keysize = sc.nextInt();
		System.out.println("enter key");
		int[] key  = new int[keysize];
	
		for(int i = 0; i < keysize; i++)
			key[i] = sc.nextInt();
	


		int extraLength = msg.length() % keysize;
		int dummyCharacters = keysize- extraLength;		
		if(extraLength != 0) {
			for(int i = 0; i < dummyCharacters; i++)
				msg +=".";
		}

		int row = msg.length() / keysize;
		int col = keysize;

		System.out.println("plain text in matrix form");
		
		for(int i = 0; i < key.length; i++) {
			System.out.print(key[i]+" ");
		}
		System.out.println();

		char[][] grid = new char[row][col];
		int k = 0;
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				grid[i][j] = msg.charAt(k);
				++k;
			}
		}

		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				System.out.print(grid[i][j]+" ");
			}
			System.out.println();
		}


		int z = 0;
		String cipherText = "";
		while(z < keysize) {
			for(int j = 0; j < keysize; j++) {
				if(key[j] == z+1) {
					for(int r = 0; r < row; r++)
						cipherText += grid[r][j];
				}
			}
			++z;
		}
		System.out.println("cipherText    ->" + cipherText);
	}

	

	public static List<List<Integer>> permute(int[] nums) {
    	List<List<Integer>> results = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) {
           	return results;
        }
        List<Integer> result = new ArrayList<>();
        findAllkeys(nums, results, result);
        return results;
    }

    public static void findAllkeys(int[] nums, List<List<Integer>> results, List<Integer> result) {
       	if (nums.length == result.size()) {
           	List<Integer> temp = new ArrayList<>(result);
           	results.add(temp);
       	}           
       	for (int i=0; i<nums.length; i++) {
           	if (!result.contains(nums[i])) {
               	result.add(nums[i]);
               	findAllkeys(nums, results, result);
               	result.remove(result.size() - 1);
           	}
       	}
    }

    public static void doDecryption() {

    	System.out.println("Decryption...............");
		Scanner sc = new Scanner(System.in);
		System.out.print("enter cipher test    ->");
		String cipherText = sc.nextLine().toUpperCase().replace(" ", "");

    	
		for(int i = 1; i <= 8; i++) {
			int keyFound = 0;
			int[] arr = new int[i];
			for(int j = 0; j < i; j++) {
				arr[j] = j+1;
			}
			List<List<Integer>> keyList = permute(arr);

			int[] key = new int[i];

			for (int a = 0; a < keyList.size(); a++) { 
            	for (int b = 0; b < keyList.get(a).size(); b++) {  
            		key[b] = keyList.get(a).get(b);
            	} 
            	keyFound = findCipher(cipherText, key, i);
            	if(keyFound == 1)
            	 	break;
        	}
        	if(keyFound == 1)
        		break;
		}
    }

	public static int findCipher(String cipherText, int[] key, int keysize) {

		int row = cipherText.length() / keysize;
		int col = keysize;
		char[][] grid = new char[row][col];

		int z = 0;
		int k = 0;
		while(z < keysize) {
			for(int j = 0; j < keysize; j++) {
				if(key[j] == z+1) {
					for(int i = 0; i < row; i++) {
						grid[i][j] = cipherText.charAt(k++);
					}
				}
			}
			z++;
		}

		String plainText = "";
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				plainText += grid[i][j];
			}
		}

		if(msg.equals(plainText)) {
 			System.out.println("********************  KEY FOUND!!!  ********************************************\n");
 			String str = "";
 			int idx = 0;
 			while(idx < plainText.length() && plainText.charAt(idx) != '.') {
 				str += plainText.charAt(idx);
 				idx++;
 			}

			System.out.println("plain text    ->" + str);
			System.out.println("key length    ->" + keysize);
			
			System.out.println("cipherText in matrix form");
			System.out.println("key is : ");
			for(int i = 0; i < keysize; i++) {
				System.out.print(key[i]+" ");
			}
			System.out.println();

			for(int i = 0; i < row; i++) {
				for(int j = 0; j < col; j++) {
					//plainText += grid[i][j];
					System.out.print(grid[i][j]+" ");
				}
				System.out.println();
			}
			System.out.println("\n**********************************************************************");
			return 1;
		}
		return 0;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		doEncryption();
		doDecryption();
	}
}

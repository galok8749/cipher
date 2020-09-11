import java.util.*;

public class Transposition {

	public static void doEncryption() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Encryption...............\n");
		System.out.println("enter plain text");
		// converting plaintext to capital letters
		String plainText = sc.nextLine().toUpperCase().replace(" ", "");

		System.out.println("key");
		// converting key to capital letters
		String key = sc.nextLine().toUpperCase();
		// assigning number to the key
		int[] keyToNumberList = assignKeyToNumber(key);
		
		// printing key
		for(int i = 0; i < key.length(); i++) {
			System.out.print(key.charAt(i)+" ");
		}
		System.out.println();
		// printing number corresponding to key
		for(int i = 0; i < keyToNumberList.length; i++) {
			System.out.print(keyToNumberList[i]+" ");
		}
		System.out.println();


		int extraLength = plainText.length() % key.length();
		int dummyCharacters = key.length() - extraLength;		
		if(extraLength != 0) {
			for(int i = 0; i < dummyCharacters; i++)
				plainText +=".";
		}

		int row = plainText.length() / key.length();
		int col = key.length();

		char[][] grid = new char[row][col];
		int k = 0;
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				grid[i][j] = plainText.charAt(k);
				++k;
			}
		}
		// printing plaintext in matrix form
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				System.out.print(grid[i][j]+" ");
			}
			System.out.println();
		}

		// encription
		int z = 0;
		String cipherText = "";
		while(z < key.length()) {
			for(int j = 0; j < key.length(); j++) {
				if(keyToNumberList[j] == z+1) {
					for(int r = 0; r < row; r++)
						cipherText += grid[r][j];
				}
			}
			++z;
		}
		// printing cipher text
		System.out.println("cipherText :" + cipherText);
	}

	public static int[] assignKeyToNumber(String  key) {
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int[] keyToNumberList = new int[key.length()];
		int cnt = 1;
		for(int i = 0; i < 26; i++) {
			for(int j = 0; j < key.length(); j++) {
				if(key.charAt(j) == alphabet.charAt(i)) {
					keyToNumberList[j] = cnt++;
				}
			}
		}
		return keyToNumberList;
	}

	public static void doDecryption() {
		System.out.println("Decryption...............\n");
		Scanner sc = new Scanner(System.in);
		System.out.print("enter cipher test ");
		String msg = sc.nextLine().toUpperCase().replace(" ", "");
		System.out.print("enter key ");
		String key = sc.nextLine().toUpperCase().replace(" ", "");
		int[] keyToNumberList = assignKeyToNumber(key);

		// printing key
		for(int i = 0; i < key.length(); i++) {
			System.out.print(key.charAt(i)+" ");
		}
		System.out.println();
		// printing number corresponding to key
		for(int i = 0; i < keyToNumberList.length; i++) {
			System.out.print(keyToNumberList[i]+" ");
		}
		System.out.println();


		int row = msg.length() / key.length();
		int col = key.length();
		char[][] grid = new char[row][col];

		// checking all possible key
		// List<List<Integer>> keyArray = findPermutation(keyToNumberList);

		// System.out.println("permutation of array");
		// for(List<Integer> perm:keyArray)
		// 	System.out.println(perm);
		int z = 0;
		int k = 0;
		while(z < key.length()) {
			for(int j = 0; j < keyToNumberList.length; j++) {
				if(keyToNumberList[j] == z+1) {
					for(int i = 0; i < row; i++) {
						grid[i][j] = msg.charAt(k++);
					}
				}
			}
			z++;
		}
		String plainText = "";
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				plainText += grid[i][j];
				System.out.print(grid[i][j]+" ");
			}
			System.out.println();
		}

		System.out.println("plain text : " + plainText);

	}

	// public static List<List<Integer>> findPermutation(int[] arr) {
	// 	List<List<Integer>> list = new ArrayList<>();
	// 	permuteHelper(list, new ArrayList<>(), arr);
	// 	return list;
	// }

	// public static void permuteHelper(List<List<Integer>> list, List<Integer> resultList, int[] arr) {
	// 	if(resultList.size() == arr.length) {
	// 		list.add(new ArrayList<>(resultList));
	// 	}
	// 	else {
	// 		for(int i = 0; i < arr.length; i++) {
	// 			if(resultList.contains(arr[i]))
	// 				continue;
	// 			resultList.add(arr[i]);
	// 			permuteHelper(list, resultList, arr);
	// 			resultList.remove(resultList.size());
	// 		}
	// 	}
	// }

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		doEncryption();
		doDecryption();
	}
}
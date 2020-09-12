import java.util.ArrayList;

public class KeyGenerator {
	
	static long keyCount = 0;
	static ArrayList<Integer> inUse = new ArrayList<Integer>();
	public static void generateKeysRecursive( int callID, int keySize ) {
		for (int i = 1; i <= 26; i++) {
			inUse.set(callID - 1, i);
			
			if ( keySize > callID ) {
				if ( !doClash( callID ) ) { // Otherwise unnecessary calls when already a clash is present such as 1, 1, x..
					generateKeysRecursive(callID + 1, keySize);
				}
			}
			else {
				if ( !doClash( callID ) ) {
					for ( int a = 0; a < inUse.size(); a++) {
						System.out.print(inUse.get(a) + " ");
					}
					System.out.println("");
					keyCount++;
				}
			}
		}
	}
	
	// Checks if two integers are being repeated in the key
	public static boolean doClash( int size ) {
		for ( int a = 0; a < size; a++) {
			
			if ( a != size - 1 ) {
				for ( int b = a + 1; b < size; b++) {
					if ( inUse.get(a) == inUse.get(b) )
						return true;
				}
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		for ( int a = 0; a < 8; a++) {
			inUse.add(0);
		}
		generateKeysRecursive( 1, 8 ); // Change second parameter to print all keys of that size
		System.out.println(keyCount);
	}

}
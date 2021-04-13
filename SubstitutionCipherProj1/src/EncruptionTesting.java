import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

	public class EncruptionTesting {
		public static char plainText[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
		'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
		'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', ' ', };
		public static char cipherText[] = { 'F', 'K', 'L', 'R', 'T', 'U', 'V', 'W',
		'G', 'I', 'H', 'Z', 'A', 'C', 'N', 'B',
		'O', 'X', 'D', 'Q', 'M', 'S', 'P', 'E', 'J', 'Y', ' ', };
		
		public static String doEncrypt(String s) {
		char c[] = new char[(s.length())];
		for (int i = 0; i < s.length(); i++) {
		for (int j = 0; j < 26; j++) {
		if (plainText[j] == s.charAt(i)) {
		c[i] = cipherText[j];
		break;
		}
		}
		}
		return (new String(c));
		}
		
		
		public static String doDecrypt(String s) {
			
			char p1[] = new char[(s.length())];
			for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < 26; j++) {
			if (cipherText[j] == s.charAt(i)) {
			p1[i] = plainText[j];
			break;
			}
			}
			}
			return (new String(p1));
			}
			@SuppressWarnings("resource")
			public static void main(String args[]) throws IOException {
		
				String key ="'a=F','b=K','c=L','d=R','e=T','f=U','g=V','h=W','i=G','j=I','k=H','l=Z','m=A','n=C','o=N','p=B',\n" +"'q=O','r=X','s=D','t=Q','u=M','v=S','w=P','x=E','y=J','z=Y'";
			
			
			Scanner kb = new Scanner(System.in);
			System.out.println("Enter a String to encrypt: ");
			String plainString = kb.nextLine().toLowerCase();
			String plainTextChange = plainString.replace(" ", "");
			String plainTextChange1 = plainString.replace("!", "");
			String plainTextChange2 = plainString.replace("@", "");
			String plainTextChange3 = plainString.replace("#", "");
			String plainTextChange4 = plainString.replace("$", "");
			String plainTextChange5 = plainString.replace("%", "");
			String plainTextChange6 = plainString.replace("^", "");
			String plainTextChange7 = plainString.replace("&", "");
			String plainTextChange8 = plainString.replace("*", "");
			String plainTextChange9 = plainString.replace("(", "");
			String plainTextChange10 = plainString.replace(")", "");
			String plainTextChange11 = plainString.replace("-", "");
			String plainTextChange12 = plainString.replace("=", "");
			String plainTextChange13 = plainString.replace("?", "");
			String plainTextChange14 = plainString.replace("/", "");
			String plainTextChange15 = plainString.replace(".", "");
			String plainTextChange16 = plainString.replace(",", "");
			String plainTextChange17 = plainString.replace("'", "");

			String cipherString = doEncrypt(plainString);
			System.out.println("Alice's View \n");
			System.out.println("Key: " + key + "\n");
			System.out.println("Plain Text: " + plainString + "\n");
			System.out.println("Cipher Text: " + cipherString + "\n");
			System.out.println("Bob's View \n");
			System.out.println("Encrypted message: " + cipherString + "\n");
			System.out.println("Decrypted message: " + doDecrypt(cipherString) + "\n");
			kb.close();
			
			char[] capital = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
			'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
			'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
			
			char[] small = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
			'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
			't', 'u', 'v', 'w', 'x', 'y', 'z' };
			
			
			
			File file = new File("gutenberg.txt"); // input
		
			// the gutenberg.txt file I made is comprised of multiple books from
			
			// gutenberg website and other various sites from the web.
			BufferedInputStream bin = new BufferedInputStream(new
			FileInputStream(file));
			byte[] buffer = new byte[(int) file.length()];
			bin.read(buffer);
			String fileStr = new String(buffer);
			String cipherBuffer = doEncrypt(fileStr);
			File outFile = new File("output.txt");
			try {
			PrintWriter output = new PrintWriter(outFile);
			output.println(cipherBuffer);
			output.close();
			} catch (IOException ex) {
			System.out.printf("Error", ex);
			}
			Scanner scan;
			try {
			scan = new Scanner(new
			File("output.txt"));
			
			} catch (Exception e) {
			System.out.println("File not found");
			return;
			}
			int[] count = new int[26];
			int myTotal = 0;
			while (scan.hasNextLine()) {
			String line = scan.nextLine();
			// System.out.println("Line read: " + line);
			char[] digit = line.toCharArray();
			for (int i = 0; i < digit.length; i++) {
			for (int j = 0; j < 26; j++) {
			if (digit[i] == capital[j] || digit[i] == small[j]) {
			count[j]++;
			myTotal = myTotal + 1;
			break;
			}
			}
			}
			}
			System.out.println("\n Eve's View \n");
			System.out.println("");
			System.out.println("Comlete count \n");
			for (int i = 0; i < 26; i++) {
			System.out.print(" " + small[i] + " :");
			System.out.print(" Number of Times Occurred: " + count[i]);
			if (count[i] > 0)
			System.out.println(" Percentage: " + (((float) count[i] /
			myTotal) * 100) + "%");
			else
			System.out.println(" 0%");
			}
			}
			}
/*
 * AUTHOR: Baldip Singh
 * Vigenere Cipher
*/
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class vignerecipher {
   
	public static void main(String[] args) throws IOException {
        @SuppressWarnings("resource")
        Scanner input = new Scanner(System.in);
       
       //Alice's Encryption Part
        System.out.println("Alice:");
        System.out.println("\nEnter Key:   " );
        String key = input.nextLine();
        System.out.println("\nEnter Message:  ");
        String plainString = input.nextLine();
        
        char phrase[] = plainString.toCharArray();
        String result = "";

        for (int i = 0; i < phrase.length; i++) {
            if (phrase[i] != ' ') {
                result += phrase[i];
            }           
        }
        
        String encrypted = doEncrypt(result, key);
        System.out.println("Encrypted Message:  " +encrypted);
        
       //Bob's Decryption Part
        System.out.println("\n Bob:");
        System.out.println("Decryption: " + doDecrypt(encrypted, key));
        
        //Eve's Passive-Attacker on the CipherText-Only
        
        char[] capital = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
    			'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
    			'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
    			
    			char[] small = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
    			'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
    			't', 'u', 'v', 'w', 'x', 'y', 'z' };
        
        
    	File file = new File("gutenberg.txt"); // input of corpus
	
		BufferedInputStream bin = new BufferedInputStream(new
		FileInputStream(file));
		byte[] buffer = new byte[(int) file.length()];
		bin.read(buffer);
		String fileStr = new String(buffer);
		String cipherBuffer = doEncrypt(fileStr, key);
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
	
		System.out.println("\n\n\n\n Eve: ");
		System.out.println("");
		System.out.println("Complete count \n");
		
		for (int i = 0; i < 26; i++) {
		System.out.print(" " + small[i] + " :");
		//System.out.print(" Number of Times Occurred: " + count[i]);
		if (count[i] > 0)
		System.out.println(" Percentage: " + (((float) count[i] /
		myTotal) * 100) + "%");
		else
		System.out.println(" 0%");
		}
        
  
    }// End of Main

    static String doEncrypt(String text, final String key)
    {
      
        String texts= text;
        String keys= key;
        
        char key1[] = new char[keys.length()];
            for(int i=0;i<keys.length();i++)
            {
                key1[i]=keys.charAt(i);
            }
           
            int key_length[]=new int[key1.length];
            char plaintext[]=new char[texts.length()];
            int plainnumb[]=new int [texts.length()];
         
            int ss[]=new int[texts.length()];
            char result[]=new char[texts.length()];
           
            for(int i=0;i<texts.length();i++)
            {
            	plaintext[i]=texts.charAt(i);
            	plainnumb[i]=(int)plaintext[i]-97;
            }
            for(int i=0;i<key1.length;i++)
            {
            	key_length[i]=(int)key1[i]-97;
            }
            String encryptedstring;
            for(int i=0;i<texts.length();i++)
            {
                ss[i]=(key_length[i%keys.length()]+plainnumb[i])%26;
                result[i]=(char)(ss[i]+97);
            }
            encryptedstring = String.copyValueOf(result);
            return encryptedstring;
            
        } //End of doEncrypt

    static String doDecrypt(String text, final String key)
        {
       
            String texts=text;
            String keys=key;
            
            char hlist[]=new char[texts.length()];
            int intlist[]=new int[texts.length()];
           
            for(int i=0;i<texts.length();i++)
            {
                hlist[i]=texts.charAt(i);
                intlist[i]=hlist[i];
                intlist[i]=(int)intlist[i]-97;
            }
            char keychar[]=new char[keys.length()];
            int keychar2[]=new int[keys.length()];
           
            for(int i=0;i<keys.length();i++)
            {
                keychar[i]=keys.charAt(i);
                keychar2[i]=keychar[i];
                keychar2[i]=(int)keychar2[i]-97;
               
            }
            int m[]=new int[texts.length()];
            char ref[]=new char[texts.length()];
          
            for(int i=0;i<texts.length();i++)
            {
                m[i]=intlist[i]-keychar2[i%keys.length()];
            }
            String decryptedstring;
            for(int i=0;i<texts.length();i++)
            {
                if(m[i]<0)
                {
                    m[i]=m[i]+26;
                }
                ref[i]=(char) (m[i]+97);
            }
            decryptedstring = String.copyValueOf(ref);
            return decryptedstring;
           
        } //End of doDecrypt
    
} //End of Class

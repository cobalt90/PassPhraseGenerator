/*
 * This Class is responsible for taking String input, Hashing input,
 * tokenizing the hash and then using each token to find a word within the
 * wordlist.
 * 
 * NOTES:
 * The purpose of the App is to create easily remembered unique passwords
 * based on a users master password and choice of service, 
 * HOWEVER... some of the words in the Word List are not common or easily
 * remembered and the current word list lacks any words over 4 letters. This 
 * will be dealt with down the road.
 * 
 * IF the word list is changed "post release" any passwords that are made after
 * the change will be different from any made prior to the change even based
 * upon same input. If this is an issue it can be remedied by simply archiving
 * the older word list in the App and allowing users to select to use an older
 * word list if they choose (if it is for the purpose of retrieving an older 
 * pass phrase otherwise what is the point assuming that newer lists have the 
 * contents of older lists)
 * 
 * Even if archiving of the older word lists is not preferred, the feature to
 * allow users to select the word list used would be wise as it could easily
 * facilitate use of other language word lists... EDITED! However after some
 * thought though this feature might have its place, but it may be smarter to 
 * simply convert the word lists into XML and include different word lists in 
 * different languages as a resource and let the localization on Android 
 * decide on which word list to use.
 * 
 * As this App has the Password crazed in mind, it might be prudent to add
 * another input to the master password field and service field for the 
 * purposes of the user being able to change their pass phrase without having
 * to change their master password or the name of the service they are using,
 * thus also adding another layer of protection in case some body figures out 
 * the master password. Think of it as a user defined salt. If that idea is a
 * go I can implement in literally 30 seconds.
 */

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class PassEncryptor {

	/*
	 * Standard Main, used to call methods.
	 * (Will be scrubbed in Android)
	 */
	public static void main(String[] args) {
		System.out.println(args[0]);
		System.out.println(args[1]);
		
		String mastPas = args[0];
		mastPas += args[1];
		
		System.out.println(GenPass(TokenizeHash(GetHash(mastPas), 8)));
	}
	
	/*
	 *  Hashes the master password and service string using SHA-512.
	 *  returns hashed result.
	 */
	public static String GetHash(String master)
	{
		MessageDigest mDigest;
		try {
			mDigest = MessageDigest.getInstance("SHA-512");
			byte[] result = mDigest.digest(master.getBytes());
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < result.length; i++) {
	            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        System.out.println(sb.toString());
	        return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
        return null;	
	}
	
	/*
	 *  Turns Hash into array of strings each one will be used to find a single word
	 *  from the wordlist.
	 *  Could also in the future play with creating an algorithm that tokenizes
	 *  a more random selection of the hex string.
	 *  
	 *  NOTE: At this point the option is there to allow a user to select up to
	 *  an 8 character pass phrase. And at this point the results of a smaller
	 *  selection would yield a similar result to that of a longer one (just with)
	 *  the last words missing.
	 *  
	 *  I could change it so that if a different number of words is selected that
	 *  could effect the end result but at this point its just a thought.
	 */
	
	public static String[] TokenizeHash(String mastHash, int noTok)
	{
		if (noTok > 8)
			noTok = 8;
		String tokenHash[] = new String[noTok];
		for (int i = 0; i < noTok * 16; i += 16)
		{
			/*
			 *  NOT A BUG, I only use 15 of the 16 characters otherwise
			 *  the hex string to 'Long' conversion is too large.
			 *  
			 *  Note: I could probably use even less than 15 hex characters
			 *  and increase the maximum pass phrase size and still have a
			 *  sufficiently random outcome.
			 */
			tokenHash[i/16] = mastHash.substring(i,i+15);
		}
		return tokenHash;
	}
	
	/*
	 * Takes the tokenized Hash and retrieves a word from the list for each
	 * token.
	 */
	public static String GenPass(String[] tokenHash)
	{
		String passPhrase = WordList.wordList[(int) (Long.parseLong(tokenHash[0],16) % WordList.wordList.length)];
		
		
		for (int i = 1; i < tokenHash.length; i++)
		{
			passPhrase += " ";
			passPhrase += WordList.wordList[(int) (Long.parseLong(tokenHash[i],16) % WordList.wordList.length)];
			
		}
		
		
		return passPhrase;
	}

}

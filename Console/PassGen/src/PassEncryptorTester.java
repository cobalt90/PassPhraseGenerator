import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/*
 * All hash codes are based on Hashes I have personally tested and confirmed
 * working in baseline application. 
 */
public class PassEncryptorTester {

	@Test
	public void testGetHash() {
		String passService[] = {
				"hawoofacebook.com",
				"IlikeAsparagusgmail.com",
				"greeneggsandhamhotmail.com",
				"greeneggsandhammyspace.com",
				"GoneFishing90facebook.com"
		};
		String hashCodes[] = {
				"86fb3b63e7c084c1c65e263170290d2c63031e470329d496f40ca43389b9d520e45d6f8107ca494429b27788243914d974e9868560c3ea224319de327d1f50e0",
				"8fc8e9bb3a61cdb433436dc011bb7cc39aebca0b5d5149f71a1106873c8a9a6b1ef86b26861e6373260b0bab4d44291dfb3a0bbbd249ccfa6e58ed06f9e74293",
				"4d7466a8eaee4715794010c467bfa07e645c7f5fb76f2c13a3326991a4e52d0ebc09687f87143535ca9d7437656a6a1b19ef0ddd41755101dd8dc02df5aa4b5b",
				"14742190468a2ac12fd6394be0ba2fee6c0052e329db628e24b84f11de1a34af3715c75b1a53b2a34065564bdbcc89091aba900419d8cdb9f8d9029a2ae96600",
				"2f9a75af31c99244b0c951627020802633b20f7a3126fbd339f357f8bc04bc529b0de0d740dc1cd3e269025aede02c3c7fd2b280b9e0e8cf6c06276ed5626f1a"
		};
		for(int i = 0; i < 5; i++)
			assertEquals("Consistant HashCode " + i + 1, hashCodes[i], PassEncryptor.GetHash(passService[i]));
	}

	@Test
	/*
	 * These tests could probably be more thorough and be rewritten.
	 * So far I am just testing whether the tokenizer splits the hash
	 * into the correct number of parts and whether those parts actually
	 * represent a fragment of the original hash.
	 */
	public void testTokenizeHash() {
		
		String hashCodes[] = {
				"86fb3b63e7c084c1c65e263170290d2c63031e470329d496f40ca43389b9d520e45d6f8107ca494429b27788243914d974e9868560c3ea224319de327d1f50e0",
				"8fc8e9bb3a61cdb433436dc011bb7cc39aebca0b5d5149f71a1106873c8a9a6b1ef86b26861e6373260b0bab4d44291dfb3a0bbbd249ccfa6e58ed06f9e74293",
				"4d7466a8eaee4715794010c467bfa07e645c7f5fb76f2c13a3326991a4e52d0ebc09687f87143535ca9d7437656a6a1b19ef0ddd41755101dd8dc02df5aa4b5b",
				"14742190468a2ac12fd6394be0ba2fee6c0052e329db628e24b84f11de1a34af3715c75b1a53b2a34065564bdbcc89091aba900419d8cdb9f8d9029a2ae96600",
				"2f9a75af31c99244b0c951627020802633b20f7a3126fbd339f357f8bc04bc529b0de0d740dc1cd3e269025aede02c3c7fd2b280b9e0e8cf6c06276ed5626f1a"
		};
		
		assertTrue("Tokening 1", PassEncryptor.TokenizeHash(hashCodes[0],6).length == 6 && hashCodes[0].substring(0,15).equals(PassEncryptor.TokenizeHash(hashCodes[0],6)[0]));
		assertTrue("Tokening 2", PassEncryptor.TokenizeHash(hashCodes[1],4).length == 4 && hashCodes[1].substring(16,31).equals(PassEncryptor.TokenizeHash(hashCodes[1],4)[1]));
		assertTrue("Tokening 3", PassEncryptor.TokenizeHash(hashCodes[2],7).length == 7 && hashCodes[2].substring(0,15).equals(PassEncryptor.TokenizeHash(hashCodes[2],7)[0]));
		assertTrue("Tokening 4", PassEncryptor.TokenizeHash(hashCodes[3],8).length == 8 && hashCodes[3].substring(32,47).equals(PassEncryptor.TokenizeHash(hashCodes[3],8)[2]));
		assertTrue("Tokening 5", PassEncryptor.TokenizeHash(hashCodes[4],5).length == 5 && hashCodes[4].substring(0,15).equals(PassEncryptor.TokenizeHash(hashCodes[4],5)[0]));
	}

	@Test
	/*
	 * IF the word list is changed it is likely that the following tests will 
	 * fail
	 */
	public void testGenPass() {
		
		
	}

}

package de.tuberlin.snet.prog2.ue02.debugging;

import java.util.Arrays;
import java.util.List;

/**
 * Class that checks the security of passwords.
 * 
 * @author PROG2-team
 *
 */
public class PasswordSecurityCheck {
	public BlacklistChecker blacklistChecker = new BlacklistChecker();	
	private static final int MININUM_PASSWORD_LENGTH = 7;
	
	
	public static void main(String[] args) {
		List<String> passwordExamples = Arrays.asList("1234", "b0b", "gartner20",
				"Gartner30", "B?masdhlAS", "Hmn1SB.HaU2d?" );
				
		PasswordSecurityCheck passwordChecker = new PasswordSecurityCheck();
		for (String password : passwordExamples) {
			try {
				passwordChecker.checkPassword(password);
				System.out.println("The password " + password + " is secure.");
			} catch (WeakPasswordException e) {
				System.out.println("The password " + password
						+ " is insecure for the following reason: " + e.getMessage());
			}
		}
	}	
	

	/**
	 * Checks the security of the given password,
	 * throws an exception if it doesn't hold the
	 * security criteria.
	 * @param password password to check
	 * @throws Exception if password is not safe
	 */
	private void checkPassword(String password) throws WeakPasswordException {

		String matchingBlacklist = blacklistChecker.checkOnBlacklists(password);
		if (matchingBlacklist != null
				|| password.length() < MININUM_PASSWORD_LENGTH
				|| !hasUpperCase(password) 
				|| !hasSpecials(password)) {

			throw new WeakPasswordException();
		}
	}

	/**
	 * @param password
	 * @return true if the given password includes special characters
	 */
	private boolean hasSpecials(String password) {
		boolean found = false;
		for (char c : password.toCharArray()) {
			if (!Character.isLetterOrDigit(c)) {
				found = true;
				break;
			}
		}
		return found;
	}

	/**
	 * @param password
	 * @return true if the given password includes upper case letters
	 */
	private boolean hasUpperCase(String password) {
		boolean found = false;
		for (char c : password.toCharArray()) {
			if (Character.isUpperCase(c)) {
				found = true;
				break;
			}
		}
		return found;
	}
}

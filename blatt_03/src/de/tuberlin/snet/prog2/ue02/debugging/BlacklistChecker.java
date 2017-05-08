package de.tuberlin.snet.prog2.ue02.debugging;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Checks if a password is on the blacklist.
 * @author PROG2-team
 *
 */
public class BlacklistChecker {

	/**
	 * Lists of usual bad passwords.
	 */
	private TreeMap<String, List<String>> badPasswords;

	/**
	 * Creates a class including many bad passwords.
	 */
	public BlacklistChecker() {
		createData();
	}
	
	/**
	 * Fills the bad passwords lists.
	 */
	private void createData() {
		badPasswords = new TreeMap<String, List<String>>();
		String str = "null  123456p porsche firebird prince rosebud 2 password guitar butter beach jaguar 3 12345678 chelsea united amateur great 4 1234 black turtle 7777777 cool 5 pussy diamond steelers muffin cooper 6 12345 nascar tiffany redsox 1313 7 dragon jackson zxcvbn star scorpio 8 qwerty cameron tomcat testing mountain 9 696969 654321 golf shannon madison 10 mustang computer bond007 murphy 987654 11 letmein amanda bear frank brazil 12 baseball wizard tiger hannah lauren 13 master xxxxxxxx doctor dave japan 14 michael money gateway eagle1 naked 15 football phoenix gators 11111 squirt 16 shadow mickey angel mother stars 17 monkey bailey junior nathan apple 18 abc123 knight thx1138 raiders alexis 19 pass iceman porno steve aaaa 20 fuckme tigers badboy forever bonnie 21 6969 purple debbie angela peaches 22 jordan andrea spider viper jasmine 23 harley horny melissa ou812 kevin 24 ranger dakota booger jake matt 25 iwantu aaaaaa 1212 lovers qwertyui 26 jennifer player flyers suckit danielle 27 hunter sunshine fish gregory beaver 28 fuck morgan porn buddy 4321 29 2000 starwars matrix whatever 4128 30 test boomer teens young runner 31 batman cowboys scooby nicholas swimming 32 trustno1 edward jason lucky dolphin 33 thomas charles walter helpme gordon 34 tigger girls cumshot jackie casper 35 robert booboo boston monica stupid 36 access coffee braves midnight shit 37 love xxxxxx yankee college saturn 38 buster bulldog lover baby gemini 39 1234567 ncc1701 barney cunt apples 40 soccer rabbit victor brian august 41 hockey peanut tucker mark 3333 42 killer john princess startrek canada 43 george johnny mercedes sierra blazer 44 sexy gandalf 5150 leather cumming 45 andrew spanky doggie 232323 hunting 46 charlie winter zzzzzz 4444 kitty 47 superman brandy gunner beavis rainbow 48 asshole compaq horney bigcock 112233 49 fuckyou carlos bubba happy arthur 50 dallas tennis 2112 sophie cream 51 jessica james fred ladies calvin 52 panties mike johnson naughty shaved 53 pepper brandon xxxxx giants surfer 54 1111 fender tits booty samson 55 austin anthony member blonde kelly 56 william blowme boobs fucked paul 57 daniel ferrari donald golden mine 58 golfer cookie bigdaddy 0 king 59 summer chicken bronco fire racing 60 heather maverick penis sandra 5555 61 hammer chicago voyager pookie eagle 62 yankees joseph rangers packers hentai 63 joshua diablo birdie einstein newyork 64 maggie sexsex trouble dolphins little 65 biteme hardcore white 0 redwings 66 enter 666666 topgun chevy smith 67 ashley willie bigtits winston sticky 68 thunder welcome bitches warrior cocacola 69 cowboy chris green sammy animal 70 silver panther super slut broncos 71 richard yamaha qazwsx 8675309 private 72 fucker justin magic zxcvbnm skippy 73 orange banana lakers nipples marvin 74 merlin driver rachel power blondes 75 michelle marine slayer victoria enjoy 76 corvette angels scott asdfgh girl 77 bigdog fishing 2222 vagina apollo 78 cheese david asdf toyota parker 79 matthew maddog video travis qwert 80 121212 hooters london hotdog time 81 patrick wilson 7777 paris sydney 82 martin butthead marlboro rock women 83 freedom dennis srinivas xxxx voodoo 84 ginger fucking internet extreme magnum 85 blowjob captain action redskins juice 86 nicole bigdick carter erotic abgrtyu 87 sparky chester jasper dirty 777777 88 yellow smokey monster ford dreams 89 camaro xavier teresa freddy maxwell 90 secret steven jeremy arsenal music 91 dick viking 11111111 access14 rush2112 92 falcon snoopy bill wolf russia 93 taylor blue crystal nipple scorpion 94 111111 eagles peter iloveyou rebecca 95 131313 winner pussies alex tester 96 123123 samantha cock florida mistress 97 bitch house beer eric phantom 98 hello miller rocket legend billy 99 scooter flower theman movie 6666 100 please jack oliver success albert";
		String[] arr = str.split(" ");
		ArrayList<String> bpwds = new ArrayList<String>();
		for (String s : arr) {
			bpwds.add(s);
		}
		badPasswords.put("#599 worst passwords ever", bpwds);

		str = "00000 0000000 00000000 0987654321 11111 111111 1111111 11111111 123123 12321 123321 12345 123456 1234567 12345678 123456789 1234567890 1234abcd 1234qwer 123abc 123asd 123qwe 1q2w3e 22222 222222 2222222 22222222 33333 333333 3333333 33333333 44444 444444 4444444 44444444 54321 55555 555555 5555555 55555555 654321 66666 666666 6666666 66666666 7654321 77777 777777 7777777 77777777 87654321 88888 888888 8888888 88888888 987654321 99999 999999 9999999 99999999 a1b2c3 aaaaa abc123 academia access account Admin admin admin1 admin12 admin123 adminadmin administrator anything asddsa asdfgh asdsa asdzxc backup boss123 business campus changeme cluster codename codeword coffee computer controller cookie customer database default desktop domain example exchange explorer files foobar foofoo forever freedom games home123 ihavenopass Internet internet intranet killer letitbe letmein Login login lotus love123 manager market money monitor mypass mypassword mypc123 nimda nobody nopass nopassword nothing office oracle owner pass1 pass12 pass123 passwd Password password password1 password12 password123 private public pw123 q1w2e3 qazwsx qazwsxedc qqqqq qwe123 qweasd qweasdzxc qweewq qwerty qwewq root123 rootroot sample secret secure security server shadow share student super superuser supervisor system temp123 temporary temptemp test123 testtest unknown windows work123 xxxxx zxccxz zxcvb zxcvbn zxcxz zzzzz";
		arr = str.split(" ");
		bpwds = new ArrayList<String>();
		for (String s : arr) {
			bpwds.add(s);
		}
		badPasswords.put("#Conficker-A Worm passwords", bpwds);
	}

	/**
	 * @return The name of the blacklist the password was found on.
	 */
	public String checkOnBlacklists(String password) {

		//badPasswords = null; why?!?!?!
		
		for (String blacklistName : badPasswords.keySet()) {
			if (badPasswords.get(blacklistName).contains(password.toLowerCase())) {
				return blacklistName;
			}
		}

		return null;
	}
}

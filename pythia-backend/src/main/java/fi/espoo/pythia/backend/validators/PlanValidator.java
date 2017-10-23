package fi.espoo.pythia.backend.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.web.multipart.MultipartFile;

import fi.espoo.pythia.backend.transfer.PlanValue;

public class PlanValidator {

	// ^ (only from beginning), 4 * [0-9]
	public String mainNoRegex = "^\\d{4}";
	// anywhere , _ , 1 OR 2 * 0 , 1 OR 2 * [1-9], . 
	String subNoRegexZero = "\\_(0{1,2})[1-9]{1,2}\\.";
	// anywhere , _ , 3 OR 4 * [0-9], .
	public String subNoRegexNonZero = "\\_\\d{3,4}\\.";
	// ^ (only from beginning), 4 * [0-9] , r OR R , _
	public String referenceRegex = "^\\d{4,5}(r|R)\\_";

	/**
	 * 
	 * @param mainNo
	 * @param name
	 * @return
	 */
	public boolean isReferenceFile(short mainNo, MultipartFile mfile) {
		String name = mfile.getName();
		short extractedMainNo = extractMainNoFromFileName(name);
		if (extractedMainNo == mainNo) {
			// CHECK R
			return regexTestPatternMatcher(name, referenceRegex);

		}
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * 
	 * @param mfile
	 * @param planV
	 * @return
	 */
	public boolean isMainNoAndSubNo(MultipartFile mfile, PlanValue planV) {
		
		String name = mfile.getName();
		
		short planMainNo = planV.getMainNo();
		short planSubNo = planV.getSubNo();
		
		
		short mFileMainNo = extractMainNoFromFileName(name);
		short mFileSubNo = extractSubNoFromFileName(name);
		
		if(planMainNo == mFileMainNo && planSubNo == mFileSubNo){
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param mainNo
	 * @return
	 */
	public short extractSubNoFromFileName(String name) {

		short subNo = -1;

		// file = FileConverter.multipartFileToFile(mfile);

		validateSubNo(name);

		if (validateSubNo(name)) {
			int[] subNoIndex = getSubNo(name);
			System.out.println("[0]:" + subNoIndex[0]);
			System.out.println("[1]:" + subNoIndex[1]);
			System.out.println("name.charAt(mainNoIndex[0]):" + name.charAt(subNoIndex[0]));
			System.out.println("name.charAt(mainNoIndex[1]):" + name.charAt(subNoIndex[1]));
			String ssubNo = name.substring(subNoIndex[0], subNoIndex[1]);
			subNo = Short.parseShort(ssubNo);
		}

		return subNo;

	}

	/**
	 * 
	 * @param mfile
	 * @return
	 */
	public short extractMainNoFromFileName(String name) {

		// validate that first 4 charaters are digits
		//

		short mainNo = -1;
		validateMainNo(name);
		if (validateMainNo(name)) {
			int[] mainNoIndex = getMainNo(name);
			System.out.println("[0]:" + mainNoIndex[0]);
			System.out.println("[1]:" + mainNoIndex[1]);
			System.out.println("name.charAt(mainNoIndex[0]):" + name.charAt(mainNoIndex[0]));
			System.out.println("name.charAt(mainNoIndex[1]):" + name.charAt(mainNoIndex[1]));
			String smainNo = name.substring(mainNoIndex[0], mainNoIndex[1]);
			System.out.println("substring:" + smainNo);
			mainNo = Short.parseShort(smainNo);
		}

		return mainNo;
	}

	/**
	 * find a subno that starts with "_" ends with "." between 1-2 zeros and 1-2
	 * numbers _001. _012.
	 * 
	 * OR
	 * 
	 * find a subno that starts with "_" ends with "." between 3-4 numbers _123.
	 * 
	 * @param name
	 * @return
	 */
	public boolean validateSubNo(String name) {

		boolean match = regexTestPatternMatcher(name, subNoRegexZero);
		if (match == false) {

			match = regexTestPatternMatcher(name, subNoRegexNonZero);
		}
		return match;
	}

	/**
	 * 
	 * @param text
	 * @return
	 */
	public int[] getSubNo(String text) {

		String s = subNoRegexZero;
		boolean match = regexTestPatternMatcher(text, s);
		if (match == false) {
			s = subNoRegexNonZero;
		}
		int[] returnTab = regexFirstMatch(text, s);
		// start is at "_" move it 1 forward
		int i = returnTab[0];
		returnTab[0] = i + 1;
		// end is at "p" move it 1 backwards
		i = returnTab[1];
		returnTab[1] = i - 1;

		return returnTab;
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public boolean validateMainNo(String name) {
		boolean match = regexTestPatternMatcher(name, mainNoRegex);
		return match;
	}

	/**
	 * 
	 * @param text
	 * @return
	 */
	public int[] getMainNo(String text) {

		// String mainNoRegex = "^\\d{4}";
		return regexFirstMatch(text, mainNoRegex);
	}

	/**
	 * 
	 * @param text
	 * @param regexPattern
	 * @return
	 */
	public int[] regexFirstMatch(String text, String regexPattern) {
		Pattern pattern = Pattern.compile(regexPattern);
		Matcher matcher = pattern.matcher(text);
		int[] indexTable = new int[2];

		while (matcher.find()) {
			indexTable[0] = matcher.start();
			indexTable[1] = matcher.end();
		}

		return indexTable;
	}

	/**
	 * 
	 * @param text
	 * @param regexPattern
	 * @return
	 */
	public boolean regexTestPatternMatcher(String text, String regexPattern) {

		Pattern pattern = Pattern.compile(regexPattern);
		// in case you would like to ignore case sensitivity,
		// you could use this statement:
		// Pattern pattern = Pattern.compile("\\s+", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(text);
		// check all occurance
		while (matcher.find()) {
			System.out.println("Start index: " + text + ":" + matcher.start());
			System.out.println(" End index: " + text + ":" + matcher.end() + " ");
			System.out.println(matcher.group());

		}

		if (!pattern.matcher(text).find()) {
			System.out.println("no match");
			return false;

		} else {
			return true;
		}

	}


}

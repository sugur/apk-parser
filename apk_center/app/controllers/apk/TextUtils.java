package controllers.apk;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtils {

	static final Pattern INT_PATTERN = Pattern.compile("[^\\d]*([\\d-]+).*");

	public static int extractInt(String text) {
		Integer[] rets = extractInts(text);
		if (rets != null && rets.length > 0)
			return rets[0];
		return -1;
	}

	public static Integer[] extractInts(String text) {
		try {
			Pattern INT_PATTERN1 = Pattern.compile("[^\\d]*([\\d-]+)[^\\d]*");
			Matcher m = INT_PATTERN1.matcher(text);
			List<Integer> allInts = new ArrayList<Integer>();
			while (m.find()) {
				allInts.add(Integer.valueOf(m.group(1)));
			}
			return allInts.toArray(new Integer[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean containsChinese(String str) {
		if (str == null || str.length() == 0)
			return false;

		// String strToCheck = new String(str.getBytes(), "UTF-16BE");
		// char word;
		char[] chars = str.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (isChinese(chars[i]))
				return true;
		}
		return false;
	}

	public static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}
}

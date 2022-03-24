package com.oasis.admin.geography.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*import com.cu.ajent.business.common.IPhone;
import com.cu.ajent.exceptions.AjentException;
import com.cu.ajent.exceptions.AjentRuntimeException;
import com.cu.ajent.exceptions.NullValueException;
import com.cu.ajent.exceptions.ValidationException;
import com.cu.ajent.format.AjBooleanFormat;
import com.cu.ajent.format.AjCommissionPercentFormat;
import com.cu.ajent.format.AjCurrencyFormat;
import com.cu.ajent.format.AjDateFormat;
import com.cu.ajent.format.AjDateFormatNoTZConversion;
import com.cu.ajent.format.AjDateTimeFormat;
import com.cu.ajent.format.AjDateTimeMediumFormat;
import com.cu.ajent.format.AjDateTimeSortableFormat;
import com.cu.ajent.format.AjDateTimeSortableWithMillisecondFormat;
import com.cu.ajent.format.AjDecimalFormat;
import com.cu.ajent.format.AjDoubleFormat;
import com.cu.ajent.format.AjFormat;
import com.cu.ajent.format.AjIDFormat;
import com.cu.ajent.format.AjIntegerFormat;
import com.cu.ajent.format.AjPercentFormat;
import com.cu.ajent.format.AjPhoneFormat;
import com.cu.ajent.format.AjPostalCodeFormat;
import com.cu.ajent.format.AjTimeFormat;
import com.cu.ajent.format.AjTimeMediumFormat;
import com.cu.ajent.format.AjXMLDateFormat;
import com.cu.ajent.format.AjXMLDateTimeFormat;
import com.cu.ajent.format.FormatterFactory;
import com.cu.ajent.i18n.ErrorMessages;
import com.cu.ajent.logger.CommonLogger;
import com.cu.ajent.transfer.to.core.StateSummaryTO;
import com.cu.ajent.transfer.to.data.DataAddressTO;
import com.cu.ajent.types.DataType;
import com.cu.ajent.types.GovtIdType;
import com.cu.ajent.types.PartyType;
import com.cu.ajent.util.BigDecimalHelper;
import com.cu.ajent.util.BooleanHelper;
import com.cu.ajent.util.DateTimeHelper;
import com.cu.ajent.util.IntegerHelper;
import com.cu.ajent.util.NumberToWord;
import com.cu.ajent.util.ObjectHelper;
import com.cu.ajent.util.StringHelper;*/

public class StringHelper {

	private static final String ATTR_ENCODING = "ENCODING=";

	private static final String CORE_POJO_PREFIX = "AjCore";

	private static final String PROXY_POJO_PREFIX = "AjProxy";

	private static final String DATA_POJO_PREFIX = "AjData";

	private static final String PARAM_PREFIX = "${";

	private static final String PARAM_SUFFIX = "}";

	/**
	 * Static map used to convert hi ascii characters (127-255) to a low ascii
	 * equivalent. This map is used when exporting items so that the transforms do
	 * not get corrupted.
	 */
	protected static Map<Integer, Character> hiAsciiMap = new HashMap<Integer, Character>();
	static {
		// hiAsciiMap.put(127, ' ');
		// hiAsciiMap.put(128, ' ');
		// hiAsciiMap.put(129, ' ');
		hiAsciiMap.put(130, ','); // Convert ‚ to ,
		hiAsciiMap.put(131, 'f'); // Convert ƒ to f
		hiAsciiMap.put(132, ','); // Convert „ to ,
		// hiAsciiMap.put(133, ' '); // Convert … to ' '
		// hiAsciiMap.put(134, ' '); // Convert † to ' '
		// hiAsciiMap.put(135, ' '); // Convert ‡ to ' '
		hiAsciiMap.put(136, '^'); // Convert ˆ to ^
		// hiAsciiMap.put(137, ' '); // Convert ‰ to ' '
		hiAsciiMap.put(138, 'S'); // Convert Š to S
		hiAsciiMap.put(139, '<'); // Convert ‹ to <
		// hiAsciiMap.put(140, ' '); // Convert Œ to ' '
		// hiAsciiMap.put(141, ' '); // Convert to ' '
		hiAsciiMap.put(142, 'Z'); // Convert Ž to Z
		// hiAsciiMap.put(143, ' '); // Convert to ' '
		// hiAsciiMap.put(144, ' '); // Convert to ' '
		hiAsciiMap.put(145, '\''); // Convert ‘ to '
		hiAsciiMap.put(146, '\''); // Convert ˆ to ^
		hiAsciiMap.put(147, '"'); // Convert “ to "
		hiAsciiMap.put(148, '"'); // Convert ” to "
		hiAsciiMap.put(149, '*'); // Convert • to *
		hiAsciiMap.put(150, '-'); // Convert – to -
		hiAsciiMap.put(151, '-'); // Convert — to -
		// hiAsciiMap.put(152, ' '); // Convert to ' '
		// hiAsciiMap.put(153, ' '); // Convert ™ to ' '
		hiAsciiMap.put(154, 's'); // Convert š to s
		hiAsciiMap.put(155, '>'); // Convert › to >
		// hiAsciiMap.put(156, ' '); // Convert œ to ' '
		// hiAsciiMap.put(157, ' '); // Convert to ' '
		hiAsciiMap.put(158, 'z'); // Convert ž to z
		hiAsciiMap.put(159, 'Y'); // Convert Ÿ to Y
		// hiAsciiMap.put(160, ' '); // Convert to ' '
		hiAsciiMap.put(161, 'i'); // Convert ¡ to i
		// hiAsciiMap.put(162, ' '); // Convert ¢ to ' '
		// hiAsciiMap.put(163, ' '); // Convert £ to ' '
		// hiAsciiMap.put(164, ' '); // Convert ¤ to ' '
		// hiAsciiMap.put(165, ' '); // Convert ¥ to ' '
		hiAsciiMap.put(166, '|'); // Convert ¦ to |
		// hiAsciiMap.put(167, ' '); // Convert § to ' '
		// hiAsciiMap.put(168, ' '); // Convert ¨ to ' '
		// hiAsciiMap.put(169, ' '); // Convert © to ' '
		// hiAsciiMap.put(170, ' '); // Convert ª to ' '
		// hiAsciiMap.put(171, ' '); // Convert « to ' '
		// hiAsciiMap.put(172, ' '); // Convert ¬ to ' '
		// hiAsciiMap.put(173, ' '); // Convert ­ to ' '
		// hiAsciiMap.put(174, ' '); // Convert ® to ' '
		hiAsciiMap.put(175, '-'); // Convert ¯ to -
		// hiAsciiMap.put(176, ' '); // Convert ° to ' '
		// hiAsciiMap.put(177, ' '); // Convert ± to ' '
		// hiAsciiMap.put(178, ' '); // Convert ² to ' '
		// hiAsciiMap.put(179, ' '); // Convert ³ to ' '
		hiAsciiMap.put(180, '\''); // Convert ´ to '
		hiAsciiMap.put(181, 'u'); // Convert µ to u
		// hiAsciiMap.put(182, ' '); // Convert ¶ to ' '
		// hiAsciiMap.put(183, ' '); // Convert · to ' '
		// hiAsciiMap.put(184, ' '); // Convert ¸ to ' '
		// hiAsciiMap.put(185, ' '); // Convert ¹ to -
		// hiAsciiMap.put(186, ' '); // Convert º to ' '
		// hiAsciiMap.put(187, ' '); // Convert » to ' '
		// hiAsciiMap.put(188, ' '); // Convert ¼ to ' '
		// hiAsciiMap.put(189, ' '); // Convert ½ to ' '
		hiAsciiMap.put(190, '\''); // Convert ´ to '
		hiAsciiMap.put(191, 'u'); // Convert µ to u
		// hiAsciiMap.put(192, ' '); // Convert ¶ to ' '
		// hiAsciiMap.put(193, ' '); // Convert · to ' '
		// hiAsciiMap.put(194, ' '); // Convert ¸ to ' '
		// hiAsciiMap.put(195, ' '); // Convert ¹ to -
		// hiAsciiMap.put(196, ' '); // Convert º to ' '
		// hiAsciiMap.put(197, ' '); // Convert » to ' '
		// hiAsciiMap.put(198, ' '); // Convert ¼ to ' '
		// hiAsciiMap.put(199, ' '); // Convert ½ to ' '
		// hiAsciiMap.put(190, ' '); // Convert ¾ to ' '
		// hiAsciiMap.put(191, ' '); // Convert ¿ to ' '
		hiAsciiMap.put(192, 'A'); // Convert À to A
		hiAsciiMap.put(193, 'A'); // Convert Á to A
		hiAsciiMap.put(194, 'A'); // Convert Â to A
		hiAsciiMap.put(195, 'A'); // Convert Ã to A
		hiAsciiMap.put(196, 'A'); // Convert Ã to A
		hiAsciiMap.put(197, 'A'); // Convert Ã to A
		// hiAsciiMap.put(198, ' '); // Convert Æ to ' '
		hiAsciiMap.put(199, 'C'); // Convert Ç to C
		hiAsciiMap.put(200, 'E'); // Convert È to E
		hiAsciiMap.put(201, 'E'); // Convert É to E
		hiAsciiMap.put(202, 'E'); // Convert Ê to E
		hiAsciiMap.put(203, 'E'); // Convert Ë to E
		hiAsciiMap.put(204, 'I'); // Convert Ì to I
		hiAsciiMap.put(205, 'I'); // Convert Í to I
		hiAsciiMap.put(206, 'I'); // Convert Î to I
		hiAsciiMap.put(207, 'I'); // Convert Ï to I
		hiAsciiMap.put(208, 'D'); // Convert Ð to D
		hiAsciiMap.put(209, 'N'); // Convert Ñ to N
		hiAsciiMap.put(210, 'O'); // Convert Ò to O
		hiAsciiMap.put(211, 'O'); // Convert Ó to O
		hiAsciiMap.put(212, 'O'); // Convert Ô to O
		hiAsciiMap.put(213, 'O'); // Convert Õ to O
		hiAsciiMap.put(214, 'O'); // Convert Ö to O
		// hiAsciiMap.put(215, ' '); // Convert × to ' '
		hiAsciiMap.put(216, '0'); // Convert Ø to 0
		hiAsciiMap.put(217, 'U'); // Convert Ù to U
		hiAsciiMap.put(218, 'U'); // Convert Ú to U
		hiAsciiMap.put(219, 'U'); // Convert Û to U
		hiAsciiMap.put(220, 'U'); // Convert Ü to U
		hiAsciiMap.put(221, 'Y'); // Convert Ý to Y
		// hiAsciiMap.put(222, ' '); // Convert Þ to ' '
		// hiAsciiMap.put(223, ' '); // Convert ß to O
		hiAsciiMap.put(224, 'a'); // Convert à to a
		hiAsciiMap.put(225, 'a'); // Convert á to a
		hiAsciiMap.put(226, 'a'); // Convert â to a
		hiAsciiMap.put(227, 'a'); // Convert ã to a
		hiAsciiMap.put(228, 'a'); // Convert ä to a
		hiAsciiMap.put(229, 'U'); // Convert å to a
		// hiAsciiMap.put(230, ' '); // Convert æ to ' '
		hiAsciiMap.put(231, 'c'); // Convert ç to c
		hiAsciiMap.put(232, 'e'); // Convert è to e
		hiAsciiMap.put(233, 'e'); // Convert é to e
		hiAsciiMap.put(234, 'e'); // Convert ê to e
		hiAsciiMap.put(235, 'e'); // Convert ë to e
		hiAsciiMap.put(236, 'i'); // Convert ì to i
		hiAsciiMap.put(237, 'i'); // Convert í to i
		hiAsciiMap.put(238, 'i'); // Convert î to i
		hiAsciiMap.put(239, 'i'); // Convert ï to i
		// hiAsciiMap.put(240, ' '); // Convert ð to ' '
		hiAsciiMap.put(241, 'n'); // Convert ñ to n
		hiAsciiMap.put(242, 'o'); // Convert ò to o
		hiAsciiMap.put(243, 'o'); // Convert ó to o
		hiAsciiMap.put(244, 'o'); // Convert ô to o
		hiAsciiMap.put(245, 'o'); // Convert õ to o
		hiAsciiMap.put(246, 'o'); // Convert ö to o
		hiAsciiMap.put(247, '/'); // Convert ÷ to /
		hiAsciiMap.put(248, '0'); // Convert ø to 0
		hiAsciiMap.put(249, 'u'); // Convert ù to u
		hiAsciiMap.put(250, 'u'); // Convert ú to u
		hiAsciiMap.put(251, 'u'); // Convert û to u
		hiAsciiMap.put(252, 'u'); // Convert ü to u
		hiAsciiMap.put(253, 'y'); // Convert ý to y
		// hiAsciiMap.put(254, ' '); // Convert þ to ' '
		hiAsciiMap.put(255, 'y'); // Convert ÿ to y
	}

	/**
	 * This is a private constructor to prevent this utility class from being
	 * instantiated.
	 */
	private StringHelper() {
	}

	/**
	 * Concatenate two objects as strings, separator used only if both strings are
	 * not blank.
	 * 
	 * @param sb          string buffer to append to
	 * @param obj1        object 1
	 * @param separator   separator
	 * @param obj2        object 2
	 * @param allowSpaces spaces in obj1 or obj2 are allowed (not considered blank)
	 * @return string buffer
	 */
	public static StringBuffer concat(StringBuffer sb, Object obj1, String separator, Object obj2,
			boolean allowSpaces) {
		boolean isBlank1 = isBlank(obj1) && (!allowSpaces || asString(obj1).length() <= 0);
		boolean isBlank2 = isBlank(obj2) && (!allowSpaces || asString(obj2).length() <= 0);
		if (!isBlank1 && !isBlank2) {
			sb.append(asString(obj1));
			sb.append(asString(separator));
			sb.append(asString(obj2));
		} else if (!isBlank1) {
			sb.append(asString(obj1));
		} else if (!isBlank2) {
			sb.append(asString(obj2));
		}
		return sb;
	}

	/**
	 * Concatenate two objects as strings, separator used only if both strings are
	 * not blank.
	 * 
	 * @param sb          string builder to append to
	 * @param obj1        object 1
	 * @param separator   separator
	 * @param obj2        object 2
	 * @param allowSpaces spaces in obj1 or obj2 are allowed (not considered blank)
	 * @return string builder
	 */
	public static StringBuilder concat(StringBuilder sb, Object obj1, String separator, Object obj2,
			boolean allowSpaces) {
		boolean isBlank1 = isBlank(obj1) && (!allowSpaces || asString(obj1).length() <= 0);
		boolean isBlank2 = isBlank(obj2) && (!allowSpaces || asString(obj2).length() <= 0);
		if (!isBlank1 && !isBlank2) {
			sb.append(asString(obj1));
			sb.append(asString(separator));
			sb.append(asString(obj2));
		} else if (!isBlank1) {
			sb.append(asString(obj1));
		} else if (!isBlank2) {
			sb.append(asString(obj2));
		}
		return sb;
	}

	/**
	 * Concatenate two objects as strings, separator used only if both strings are
	 * not blank.
	 * 
	 * @param sb        string buffer to append to
	 * @param obj1      object 1
	 * @param separator separator
	 * @param obj2      object 2
	 * @return string buffer
	 */
	public static StringBuffer concat(StringBuffer sb, Object obj1, String separator, Object obj2) {
		return concat(sb, obj1, separator, obj2, false);
	}

	/**
	 * Concatenate two objects as strings, separator used only if both strings are
	 * not blank.
	 * 
	 * @param sb        string builder to append to
	 * @param obj1      object 1
	 * @param separator separator
	 * @param obj2      object 2
	 * @return string builder
	 */
	public static StringBuilder concat(StringBuilder sb, Object obj1, String separator, Object obj2) {
		return concat(sb, obj1, separator, obj2, false);
	}

	/**
	 * Concatenate two objects as strings, separator used only if both strings are
	 * not blank.
	 * 
	 * @param obj1        object 1
	 * @param separator   separator
	 * @param obj2        object 2
	 * @param allowSpaces spaces in obj1 or obj2 are allowed (not considered blank)
	 * @return conatenated string
	 */
	public static String concat(Object obj1, String separator, Object obj2, boolean allowSpaces) {
		return concat(new StringBuilder(), obj1, separator, obj2, allowSpaces).toString();
	}

	/**
	 * Concatenate two objects as strings, separator used only if both strings are
	 * not blank.
	 * 
	 * @param obj1      object 1
	 * @param separator separator
	 * @param obj2      object 2
	 * @return conatenated string
	 */
	public static String concat(Object obj1, String separator, Object obj2) {
		return concat(obj1, separator, obj2, false);
	}

	/**
	 * Concatenate two objects as strings.
	 * 
	 * @param sb          string buffer to append to
	 * @param obj1        object 1
	 * @param obj2        object 2
	 * @param allowSpaces spaces in obj1 or obj2 are allowed (not considered blank)
	 * @return string buffer
	 */
	public static StringBuffer concat(StringBuffer sb, Object obj1, Object obj2, boolean allowSpaces) {
		return concat(sb, obj1, null, obj2, allowSpaces);
	}

	/**
	 * Concatenate two objects as strings.
	 * 
	 * @param sb          string builder to append to
	 * @param obj1        object 1
	 * @param obj2        object 2
	 * @param allowSpaces spaces in obj1 or obj2 are allowed (not considered blank)
	 * @return string builder
	 */
	public static StringBuilder concat(StringBuilder sb, Object obj1, Object obj2, boolean allowSpaces) {
		return concat(sb, obj1, null, obj2, allowSpaces);
	}

	/**
	 * Concatenate two objects as strings.
	 * 
	 * @param sb   string buffer to append to
	 * @param obj1 object 1
	 * @param obj2 object 2
	 * @return string buffer
	 */
	public static StringBuffer concat(StringBuffer sb, Object obj1, Object obj2) {
		return concat(sb, obj1, obj2, false);
	}

	/**
	 * Concatenate two objects as strings.
	 * 
	 * @param sb   string builder to append to
	 * @param obj1 object 1
	 * @param obj2 object 2
	 * @return string builder
	 */
	public static StringBuilder concat(StringBuilder sb, Object obj1, Object obj2) {
		return concat(sb, obj1, obj2, false);
	}

	/**
	 * Concatenate two objects as strings.
	 * 
	 * @param obj1        object 1
	 * @param obj2        object 2
	 * @param allowSpaces spaces in obj1 or obj2 are allowed (not considered blank)
	 * @return conatenated string
	 */
	public static String concat(Object obj1, Object obj2, boolean allowSpaces) {
		return concat(new StringBuilder(), obj1, obj2, allowSpaces).toString();
	}

	/**
	 * Concatenate two objects as strings.
	 * 
	 * @param obj1 object 1
	 * @param obj2 object 2
	 * @return conatenated string
	 */
	public static String concat(Object obj1, Object obj2) {
		return concat(obj1, obj2, false);
	}

	/**
	 * Capitalizes first character in a string. For example, "whatever" becomes
	 * "Whatever".
	 * 
	 * @param obj object as string to be capitalized
	 * @return capitalized string
	 */
	public static String capitalizeFirstChar(Object obj) {
		String s = asString(obj);
		if (s.length() > 0) {
			String firstChar = s.substring(0, 1).toUpperCase();
			s = firstChar + (s.length() > 1 ? s.substring(1) : "");
		}
		return s;
	}

	/**
	 * Make sure there is a space before every upper case word or acronym (except
	 * the first one) in a string.
	 * 
	 * @param obj object as string to be spaced out
	 * @return spaced out string
	 */
	public static String spaceCapitalizedWords(Object obj) {
		StringBuilder sb = new StringBuilder(asString(obj));
		int len = sb.length();
		for (int i = len - 1; i > 0; i--) {
			char prevCh = sb.charAt(i - 1);
			char currCh = sb.charAt(i);
			char nextCh = i < len - 1 ? sb.charAt(i + 1) : '\0';

			// treat all non-alpha chars as if they were upper case
			boolean prevLow = Character.isLowerCase(prevCh) && Character.isLetter(prevCh);
			boolean currUp = Character.isUpperCase(currCh) || !Character.isLetter(currCh);
			boolean nextLow = Character.isLowerCase(nextCh) && Character.isLetter(nextCh);

			if (currUp && (prevLow || nextLow) && prevCh != ' ' && currCh != ' ') {
				sb.insert(i, ' ');
			}
		}
		return sb.toString();
	}

	/**
	 * This method translates a provided string to a proper cased string with the
	 * first letter upper case, and all other letters lower case.
	 * 
	 * @param obj object as string to be proper cased
	 * @return The proper-cased value.
	 */
	public static String properCase(Object obj) {
		StringBuilder sb = new StringBuilder(asString(obj));
		sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		for (int i = 1; i < sb.length(); i++) {
			sb.setCharAt(i, Character.toLowerCase(sb.charAt(i)));
		}
		return sb.toString();
	}

	/**
	 * /** This method translates a provided string to a string with the first
	 * character of each word capitalized and all other letters in lower case. This
	 * will will also convert any white space to a single space between words.
	 * 
	 * @param obj object as string to be proper cased
	 * @return The proper-cased value.
	 */
	public static String titleCase(Object obj) {
		String value = asString(obj);
		StringBuilder sb = new StringBuilder();
		String[] s = asString(value).split("\\s");
		for (int i = 0; i < s.length; i++) {
			if (s[i].length() > 0) {
				if (sb.length() > 0) {
					sb.append(' ');
				}
				String pc = properCase(s[i]);
				sb.append(pc);
			}
		}
		return sb.toString();
	}

	/**
	 * This method is used to perform a <em>toString</em> on an object reflectively
	 * field by field.
	 * 
	 * @param obj The object to be printed
	 * @return The stringified representation of the object
	 */
	/*
	 * public static String objToString(final Object obj) {
	 * 
	 * // First, check to see if obj is null. // If it is, return an empty string.
	 * if (obj == null) { return ""; }
	 * 
	 * StringBuilder result = new StringBuilder(obj.getClass().getName());
	 * 
	 * Map fields = ObjectHelper.getAllFields(obj.getClass()); Set entries =
	 * fields.entrySet(); for (Iterator i = entries.iterator(); i.hasNext();) {
	 * Map.Entry entry = (Map.Entry) i.next(); String fieldName = (String)
	 * entry.getKey(); Field field = (Field) entry.getValue();
	 * 
	 * if (!Modifier.isStatic(field.getModifiers())) { result.append("\n");
	 * result.append(fieldName); result.append('=');
	 * 
	 * try { Object value = ObjectHelper.getFieldValue(obj, field); if (value ==
	 * null) { result.append("<null>"); } else if
	 * (field.getType().getName().equals("java.lang.String")) { result.append("\"");
	 * result.append(asString(value)); result.append("\""); } else {
	 * result.append(asString(value)); } } catch (Exception e) {
	 * result.append("<inaccessible>"); } } }
	 * 
	 * return result.toString(); }
	 */

	/**
	 * Get the non-qualified name (minus the package) for the specified class name.
	 * 
	 * @param className class name
	 * @return non-qualified class name
	 */
	public static String getClassName(String className) {
		if (className != null) {
			int i = className.lastIndexOf('.');
			return i != -1 ? className.substring(i + 1) : className;
		}
		return null;
	}

	/**
	 * Get the non-qualified name (minus the package) for the specified class.
	 * 
	 * @param clazz class
	 * @return non-qualified class name
	 */
	public static String getClassName(Class clazz) {
		return clazz != null ? getClassName(clazz.getName()) : null;
	}

	/**
	 * Get the non-qualified name (minus the package) for the specified object's
	 * class.
	 * 
	 * @param obj object
	 * @return non-qualified class name
	 */
	public static String getClassName(Object obj) {
		return obj != null ? getClassName(obj.getClass()) : null;
	}

	/**
	 * Get user displayable name for the specified class name.
	 * 
	 * @param className class name
	 * @return user class name
	 */
	public static String getUserClassName(String className) {
		if (!isBlank(className)) {
			if (className.startsWith(CORE_POJO_PREFIX)) {
				className = className.substring(CORE_POJO_PREFIX.length());
			} else if (className.startsWith(PROXY_POJO_PREFIX)) {
				className = className.substring(PROXY_POJO_PREFIX.length());
			} else if (className.startsWith(DATA_POJO_PREFIX)) {
				className = className.substring(DATA_POJO_PREFIX.length());
			}
		}
		return spaceCapitalizedWords(className);
	}

	/**
	 * Get user displayable name for the specified class.
	 * 
	 * @param clazz class
	 * @return user class name
	 */
	public static String getUserClassName(Class clazz) {
		return getUserClassName(getClassName(clazz));
	}

	/**
	 * Converts collections to CSV type string, maps to comma separated name value
	 * pairs, null to empty string, otherwise just returns the toString() value.
	 * This method guarantees a string will always be returned (null is never
	 * returned, even if the toString() method on the object returns null).
	 * 
	 * @param object the object to convert
	 * @return the converted string
	 */
	public static String asString(Object object) {
		return asString(object, 0);
	}

	/**
	 * Converts collections to CSV type string, maps to comma separated name value
	 * pairs, null to empty string, otherwise just returns the toString() value.
	 * This method guarantees a string will always be returned (null is never
	 * returned, even if the toString() method on the object returns null). This
	 * method additionally may truncate length of result to provided maxLength. If
	 * maxLength = 0 no truncation will be performed.
	 * 
	 * @param object    the object to convert
	 * @param maxLength length to truncate result to, 0 = no truncation
	 * 
	 * @return the converted string
	 */
	public static String asString(Object object, int maxLength) {
		return asString(object, maxLength, false);
	}

	/**
	 * Converts collections to CSV type string, maps to comma separated name value
	 * pairs, null to empty string, otherwise just returns the toString() value.
	 * This method guarantees a string will always be returned (null is never
	 * returned, even if the toString() method on the object returns null). This
	 * method additionally may truncate length of result to provided maxLength. If
	 * maxLength = 0 no truncation will be performed.
	 * 
	 * @param object    the object to convert
	 * @param normalize - true/false indicates if new line characters has to be
	 *                  removed from object's string representation
	 * @return
	 */
	public static String asString(Object object, boolean normalize) {
		return asString(object, 0, normalize);
	}

	/**
	 * Converts collections to CSV type string, maps to comma separated name value
	 * pairs, null to empty string, otherwise just returns the toString() value.
	 * This method guarantees a string will always be returned (null is never
	 * returned, even if the toString() method on the object returns null). This
	 * method additionally may truncate length of result to provided maxLength. If
	 * maxLength = 0 no truncation will be performed.
	 * 
	 * @param object    the object to convert
	 * @param maxLength length to truncate result to, 0 = no truncation
	 * @param normalize - true/false indicates if new line characters has to be
	 *                  removed from object's string representation
	 * 
	 * @return the converted string
	 */
	public static String asString(Object object, int maxLength, boolean normalize) {

		String result;
		if (object instanceof String) {
			// if it's already a string, just return it
			result = (String) object;
			if (normalize) {
				result = result.replaceAll("[\n\r[\r\n]\u0085\u2028\u2029]", " ");
			}
		} else if (object instanceof Collection) {
			// return CSV string for collections
			result = asString((Collection) object);
		} else if (object instanceof Map) {
			// return CSV string for maps
			result = asString((Map) object);
		} else if (object instanceof Object[]) {
			// return CSV string for arrays
			result = asString((Object[]) object);
		} else if (object == null) {
			// return empty string for nulls
			result = "";
		} else {
			// asString() is called recursively in case toString() returns null
			result = asString(object.toString(), maxLength, normalize);
		}

		if (maxLength > 0 && result.length() > maxLength) {
			result = result.substring(0, maxLength - 1);
		}

		return result;
	}

	/**
	 * Converts collection to delimited string.
	 * 
	 * @param collection    the collection to convert
	 * @param delimiter     delimiter char
	 * @param lastDelimiter delimiter char between last two elements
	 * @return the converted string
	 */
	public static String asString(Collection collection, String delimiter, String lastDelimiter) {
		StringBuilder sb = new StringBuilder();
		if (collection != null) {
			for (Iterator i = collection.iterator(); i.hasNext();) {
				Object object = i.next();
				if (sb.length() > 0) {
					if (i.hasNext()) {
						sb.append(delimiter);
					} else {
						sb.append(lastDelimiter);
					}
				}
				sb.append(asString(object));
			}
		}
		return sb.toString();
	}

	/**
	 * Converts collection to delimited string.
	 * 
	 * @param collection the collection to convert
	 * @param delimiter  delimiter char
	 * @return the converted string
	 */
	public static String asString(Collection collection, String delimiter) {
		return asString(collection, delimiter, delimiter);
	}

	/**
	 * Converts collection to delimited string.
	 * 
	 * @param collection the collection to convert
	 * @param delimiter  delimiter char
	 * @param prefix
	 * @param postfix
	 * @return the converted string
	 */
	public static String asString(Collection collection, String delimiter, String prefix, String postfix) {
		String data = asString(collection, delimiter);
		String result = "";

		if (!StringHelper.isBlank(data)) {
			if (!StringHelper.isBlank(prefix)) {
				result += prefix;
			}

			result += data;

			if (!StringHelper.isBlank(postfix)) {
				result += postfix;
			}
		} else {
			result = data;
		}

		return result;
	}

	/**
	 * Converts collection to CSV type string.
	 * 
	 * @param collection the collection to convert
	 * @return the converted string
	 */
	public static String asString(Collection collection) {
		return asString(collection, ", "); // TODO get delimiter for locale
	}

	/**
	 * Converts array to CSV type string.
	 * 
	 * @param collection the array to convert
	 * @return the converted string
	 */
	public static String asString(Object[] array) {
		return asString(Arrays.asList(array));
	}

	/**
	 * Converts collection to delimited string.
	 * 
	 * @param collection the collection to convert
	 * @param delimiter  delimiter char
	 * @return the converted string
	 */
	public static String asOrString(Collection collection) {
		return asString(collection, ", ", " or "); // TODO get delimiters for locale
	}

	/**
	 * Converts collection to delimited string.
	 * 
	 * @param collection the collection to convert
	 * @param delimiter  delimiter char
	 * @return the converted string
	 */
	public static String asAndString(Collection collection) {
		return asString(collection, ", ", " and "); // TODO get delimiters for locale
	}

	/**
	 * Converts collection to delimited string in sorted order
	 * 
	 * @param collection the collection to convert
	 * @param delimiter  delimiter char
	 * @return the converted string
	 */
	public static String asSortedString(Collection collection, String delimiter) {
		if (collection != null && collection.size() > 0) {
			List objList = new ArrayList(collection);
			Collections.sort(objList, new Comparator() {

				public int compare(Object s1, Object s2) {
					return StringHelper.compare(s1, s2);
				}
			});
			return asString(objList, delimiter);
		}
		return "";
	}

	/**
	 * Converts collection to CSV type string in sorted order.
	 * 
	 * @param collection the collection to convert
	 * @return the converted string
	 */
	public static String asSortedString(Collection collection) {
		return asSortedString(collection, ", "); // TODO get delimiter for locale
	}

	/**
	 * Converts map to delimited string, with Key=Value for each map item.
	 * 
	 * @param map       the map to convert
	 * @param delimiter delimiter char
	 * @return the converted string
	 */
	public static String asString(Map map, String delimiter) {
		StringBuilder sb = new StringBuilder();
		if (map != null) {
			for (Iterator i = map.entrySet().iterator(); i.hasNext();) {
				Map.Entry entry = (Map.Entry) i.next();
				if (sb.length() > 0) {
					sb.append(delimiter);
				}
				sb.append(asString(entry.getKey()));
				sb.append('=');
				sb.append(asString(entry.getValue()));
			}
		}
		return sb.toString();
	}

	/**
	 * Converts map to CSV type string with key=value.
	 * 
	 * @param map the map to convert
	 * @return the converted string
	 */
	public static String asString(Map map) {
		return asString(map, ", "); // TODO get delimiter for locale
	}

	/**
	 * Converts collection to CSV type string, null to empty string, otherwise just
	 * returns the toString() value for the property on the object.
	 * 
	 * @param object the object to convert
	 * @param prop   property on the object to convert to string
	 * @return the converted string
	 */
	/*
	 * public static String asString(Object object, String prop) { try { return
	 * asString(ObjectHelper.getAccessorValues(object, prop)); } catch
	 * (IllegalAccessException e) { return ""; } }
	 */

	public static String asString(String delim, Object... objs) {
		String str = "";

		if (objs != null) {
			for (Object o : objs) {
				String tmp = StringHelper.asString(o);

				if (!StringHelper.isBlank(tmp)) {
					str = str + tmp + StringHelper.asString(delim);
				}
			}
		}

		return str;
	}

	/**
	 * Converts value to an XML string.
	 * 
	 * @param sb    string buffer to append converted string to
	 * @param value value to convert special characters to built in entities
	 * @return string buffer with special characters converted
	 */
	public static StringBuffer asXMLString(StringBuffer sb, Object value) {
		String s = asString(value);
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			switch (ch) {
			case '<':
				sb.append("&lt;");
				break;
			case '>':
				sb.append("&gt;");
				break;
			case '&':
				sb.append("&amp;");
				break;
			case '"':
				sb.append("&quot;");
				break;
			case '\'':
				sb.append("&apos;");
				break;
			default:
				if (ch > 255) {
					ch = ' ';
					// PR47641 - Translate characters so that they don't corrupt xalan transform
				} else if (ch > 126) {
					Character mapVal = hiAsciiMap.get(new Integer(ch));
					if (mapVal == null) {
						ch = ' ';
					} else {
						ch = mapVal;
					}
				}
				sb.append(ch);
			}
		}
		return sb;
	}

	/**
	 * Converts value to an XML string.
	 * 
	 * @param sb    string builder to append converted string to
	 * @param value value to convert special characters to built in entities
	 * @return string builder with special characters converted
	 */
	public static StringBuilder asXMLString(StringBuilder sb, Object value) {
		String s = asString(value);
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			switch (ch) {
			case '<':
				sb.append("&lt;");
				break;
			case '>':
				sb.append("&gt;");
				break;
			case '&':
				sb.append("&amp;");
				break;
			case '"':
				sb.append("&quot;");
				break;
			case '\'':
				sb.append("&apos;");
				break;
			default:
				if (ch > 255) {
					ch = ' ';
					// PR47641 - Translate characters so that they don't corrupt xalan transform
				} else if (ch > 126) {
					Character mapVal = hiAsciiMap.get(new Integer(ch));
					if (mapVal == null) {
						ch = ' ';
					} else {
						ch = mapVal;
					}
				}
				sb.append(ch);
			}
		}
		return sb;
	}

	/**
	 * Converts value to an XML string.
	 * 
	 * @param value value to convert special characters to built in entities
	 * @return string with special characters converted
	 */
	public static String asXMLString(Object value) {
		return asXMLString(new StringBuilder(), value).toString();
	}

	/**
	 * Converts value to a regular expression string.
	 * 
	 * @param sb    string buffer to append converted string to
	 * @param value value to convert special characters to regex escapes
	 * @return string buffer with special characters converted
	 */
	public static StringBuffer asRegexString(StringBuffer sb, Object value) {
		String s = asString(value);
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (!Character.isLetterOrDigit(ch)) {
				sb.append('\\');
			}
			sb.append(ch);
		}
		return sb;
	}

	/**
	 * Converts value to a regular expression string.
	 * 
	 * @param sb    string builder to append converted string to
	 * @param value value to convert special characters to regex escapes
	 * @return string builder with special characters converted
	 */
	public static StringBuilder asRegexString(StringBuilder sb, Object value) {
		String s = asString(value);
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (!Character.isLetterOrDigit(ch)) {
				sb.append('\\');
			}
			sb.append(ch);
		}
		return sb;
	}

	/**
	 * Converts value to a regular expression string.
	 * 
	 * @param value value to convert special characters to regex escapes
	 * @return string with special characters converted
	 */
	public static String asRegexString(Object value) {
		return asRegexString(new StringBuilder(), value).toString();
	}

	/**
	 * Converts value to a UI string. Special characters are converted to UI
	 * escapes.
	 * 
	 * @param sb    string buffer to append converted string to
	 * @param value value to convert special characters to UI escapes
	 * @return string buffer with special characters converted
	 */
	public static StringBuffer asUIString(StringBuffer sb, Object value) {
		String s = asString(value);
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			switch (ch) {
			case '&':
				sb.append("&&");
				break;
			default:
				sb.append(ch);
			}
		}
		return sb;
	}

	/**
	 * Converts value to a UI string. Special characters are converted to UI
	 * escapes.
	 * 
	 * @param sb    string builder to append converted string to
	 * @param value value to convert special characters to UI escapes
	 * @return string builder with special characters converted
	 */
	public static StringBuilder asUIString(StringBuilder sb, Object value) {
		String s = asString(value);
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			switch (ch) {
			case '&':
				sb.append("&&");
				break;
			default:
				sb.append(ch);
			}
		}
		return sb;
	}

	/**
	 * Converts value to a UI string. Special characters are converted to UI
	 * escapes.
	 * 
	 * @param value value to convert special characters to UI escapes
	 * @return string with special characters converted
	 */
	public static String asUIString(Object value) {
		return asUIString(new StringBuilder(), value).toString();
	}

	/**
	 * Converts object as a blank string to null value, otherwise just returns the
	 * object as a string.
	 * 
	 * @param object    the object as string to convert
	 * @param nullValue string to return for null value
	 * @return the converted string
	 */
	public static String asNull(Object object, String nullValue) {
		return isBlank(object) ? nullValue : asString(object);
	}

	/**
	 * Converts object as a blank string to null, otherwise just returns the object
	 * as a string.
	 * 
	 * @param object the object as string to convert
	 * @return the converted string
	 */
	public static String asNull(Object object) {
		return asNull(object, null);
	}

	/**
	 * Determines if object as a string is all digits.
	 * 
	 * @param object object to check if all digits
	 * @return true/false if input object is all digits
	 */
	public static boolean isDigits(Object object) {
		String s = asString(object);
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (!Character.isDigit(ch)) {
				return false;
			}
		}
		return s.length() > 0;
	}

	/**
	 * Determines if object as a string is "blank" (null, empty string, or all white
	 * space).
	 * 
	 * @param object object to check as a string
	 * @return true if object as a string is blank, false otherwise
	 */
	public static boolean isBlank(Object object) {
		if (object instanceof StringBuilder) {
			/*
			 * optimization if object is a StringBuilder since it can be very large and we
			 * don't want to convert the whole thing to a String just to check if it is
			 * blank because most of the time it will either be empty or start with a
			 * non-white space character
			 */
			StringBuilder sb = (StringBuilder) object;
			for (int i = 0; i < sb.length(); i++) {
				if (!Character.isWhitespace(sb.charAt(i))) {
					return false;
				}
			}
			return true;
		} else if (object instanceof StringBuffer) {
			/*
			 * optimization if object is a StringBuffer since it can be very large and we
			 * don't want to convert the whole thing to a String just to check if it is
			 * blank because most of the time it will either be empty or start with a
			 * non-white space character
			 */
			StringBuffer sb = (StringBuffer) object;
			for (int i = 0; i < sb.length(); i++) {
				if (!Character.isWhitespace(sb.charAt(i))) {
					return false;
				}
			}
			return true;
		}
		return asString(object).trim().equals("");
	}

	/**
	 * Determines if objects as strings are "equal" (null and empty string are
	 * equal).
	 * 
	 * @param obj1 first object to check
	 * @param obj2 second object to check
	 * @return true if objects as strings are equal, false otherwise
	 */
	public static boolean isEqual(Object obj1, Object obj2) {
		return asString(obj1).equals(asString(obj2));
	}

	/**
	 * Determines if objects as strings are "equal" (null and empty string are
	 * equal).
	 * 
	 * @param obj1 first object to check
	 * @param obj2 second object to check
	 * @return true if objects as strings are equal, false otherwise
	 */
	public static boolean isEqualIgnoreCase(Object obj1, Object obj2) {
		return asString(obj1).equalsIgnoreCase(asString(obj2));
	}

	/**
	 * Determines if objects as strings are "similar" (different case and unequal
	 * white space are always similar, null and empty string are similar).
	 * 
	 * @param obj1 first object to check
	 * @param obj2 second object to check
	 * @return true if objects as strings are equal, false otherwise
	 */
	public static boolean isSimilar(Object obj1, Object obj2) {
		return isEqualIgnoreCase(removeRedundantWhiteSpace(obj1), removeRedundantWhiteSpace(obj2));
	}

	/**
	 * Compares two objects. It will convert two objects to strings with the
	 * asString and then compare them. This considers case when comparing. A empty
	 * string and null string will compare as equal. An empty/null value will be
	 * before a value.
	 * 
	 * @param obj1 first object to compare
	 * @param obj2 second object to compare
	 * @return -1 if obj1 before obj2, 0 if obj1 = obj2, 1 if obj1 after obj2
	 */
	public static int compare(Object obj1, Object obj2) {
		return asString(obj1).compareTo(asString(obj2));
	}

	/**
	 * Compares two objects. It will convert two objects to strings with the
	 * asString and then compare them. This ignores case when comparing. A empty
	 * string and null string will compare as equal. An empty/null value will be
	 * before a value.
	 * 
	 * @param obj1 first object to compare
	 * @param obj2 second object to compare
	 * @return -1 if obj1 before obj2, 0 if obj1 = obj2, 1 if obj1 after obj2
	 */
	public static int compareIgnoreCase(Object obj1, Object obj2) {
		return asString(obj1).compareToIgnoreCase(asString(obj2));
	}

	/**
	 * Formats object. Determines formatter to use. Collections and null objects are
	 * supported.
	 * 
	 * @param value object to format
	 * @return formatted string
	 */
	/*
	 * public static String format(Object value) { return format(value,
	 * FormatterFactory.getFormatter(value)); }
	 */
	/**
	 * Formats object. Determines formatter to use. Collections and null objects are
	 * supported.
	 * 
	 * @param value object to format
	 * @return formatted string
	 * @throws AjentException if unable to format value
	 */

	/*
	 * public static String formatValue(Object value) { return formatValue(value,
	 * FormatterFactory.getFormatter(value)); }
	 */
	/**
	 * Formats object for XML. Determines formatter to use. Collections and null
	 * objects are supported.
	 * 
	 * @param value object to format
	 * @return formatted string
	 * @throws AjentException if unable to format value
	 */
	/*
	 * public static String formatXML(Object value) { return formatValue(value,
	 * FormatterFactory.getXMLFormatter(value)); }
	 */

	/**
	 * Formats object for XML with supplied formatter. Collections and null objects
	 * are supported.
	 * 
	 * @param value     object to format
	 * @param formatter formatter to use
	 * @return formatted string
	 * @throws AjentException if unable to format value
	 */
	/*
	 * public static String formatXML(Object value, AjFormat formatter) throws
	 * AjentException { return formatter != null ? formatValue(value, formatter) :
	 * formatXML(value); }
	 */

	/**
	 * Formats object for SQL. Determines formatter to use. Collections and null
	 * objects are supported.
	 * 
	 * @param value object to format
	 * @return formatted string
	 * @throws AjentException if unable to format value
	 */
	/*
	 * public static String formatSQL(Object value) throws AjentException { return
	 * formatValue(value, FormatterFactory.getSQLFormatter(value)); }
	 */

	/**
	 * Formats object for SQL with supplied formatter. Collections and null objects
	 * are supported.
	 * 
	 * @param value     object to format
	 * @param formatter formatter to use
	 * @return formatted string
	 * @throws AjentException if unable to format value
	 */
	/*
	 * public static String formatSQL(Object value, AjFormat formatter) throws
	 * AjentException { return formatter != null ? formatValue(value, formatter) :
	 * formatSQL(value); }
	 */
	/**
	 * Formats object with supplied formatter. Collections and null objects are
	 * supported.
	 * 
	 * @param value     object to format
	 * @param formatter formatter to use
	 * @return formatted string
	 */

	/*
	 * public static String format(Object value, AjFormat formatter) { try { return
	 * formatValue(value, formatter); } catch (AjentException ae) { return
	 * formatException(ae); } }
	 */
	/**
	 * Formats object for specified data type. Collections and null objects are
	 * supported.
	 * 
	 * @param value    object to format
	 * @param dataType data type to use
	 * @return formatted string
	 */
	/*
	 * public static String format(Object value, DataType dataType) { return
	 * format(value, FormatterFactory.getFormatter(dataType)); }
	 */

	/**
	 * Formats object with supplied formatter. Collections and null objects are
	 * supported.
	 * 
	 * @param value     object to format
	 * @param formatter formatter to use
	 * @return formatted string
	 * @throws AjentException if unable to format value
	 */
	/*
	 * public static String formatValue(Object value, AjFormat formatter) throws
	 * AjentException { // note: formatters can handle null values, the format of a
	 * null value could be different for different // formatters, for example for a
	 * given formatter a null could be the string "NULL" instead of the empty
	 * string. if (formatter != null) { return formatter.format(value); } return
	 * asString(value); }
	 */

	/**
	 * Formats property on an object with supplied formatter. Collections and null
	 * objects are supported.
	 * 
	 * @param value     object
	 * @param prop      property on the object to format
	 * @param formatter formatter to use
	 * @return formatted string
	 */
	/*
	 * public static String format(Object value, String prop, AjFormat formatter) {
	 * try { return format(ObjectHelper.getAccessorValues(value, prop), formatter);
	 * } catch (IllegalAccessException e) { return ""; } }
	 */

	/**
	 * Formats property on an object with supplied formatter. Collections and null
	 * objects are supported.
	 * 
	 * @param value     object
	 * @param prop      property on the object to format
	 * @param formatter formatter to use
	 * @return formatted string
	 * @throws AjentException if unable to format value for property
	 */
	/*
	 * public static String formatProperty(Object value, String prop, AjFormat
	 * formatter) throws AjentException { try { return
	 * formatValue(ObjectHelper.getAccessorValues(value, prop), formatter); } catch
	 * (IllegalAccessException e) { return ""; } }
	 */
	/**
	 * Formats Boolean.
	 * 
	 * @param value boolean to format
	 * @return formatted string or empty string if value is null
	 */
	/*
	 * public static String formatBoolean(Boolean value) { return format(value, new
	 * AjBooleanFormat()); }
	 */

	/**
	 * Formats Boolean.
	 * 
	 * @param value         boolean to format
	 * @param formatPattern format pattern
	 * @return formatted string or empty string if value is null
	 */
	/*
	 * public static String formatBoolean(Boolean value, String formatPattern) {
	 * AjBooleanFormat formatter = new AjBooleanFormat();
	 * formatter.setFormatPattern(formatPattern); return format(value, formatter); }
	 */

	/**
	 * Formats Boolean.
	 * 
	 * @param value         boolean to format
	 * @param formatPattern format pattern
	 * @return formatted string or empty string if value is null
	 */
	public static String formatBoolean(boolean value, String formatPattern) {
		return formatBoolean(new Boolean(value), formatPattern);
	}

	/**
	 * Formats Boolean.
	 * 
	 * @param value boolean to format
	 * @return formatted string
	 */
	public static String formatBoolean(boolean value) {
		return formatBoolean(new Boolean(value));
	}

	/**
	 * Formats ID.
	 * 
	 * @param value ID to format
	 * @return formatted string or empty string if value is null
	 */
	/*
	 * public static String formatID(Integer value) { return format(value, new
	 * AjIDFormat()); }
	 */
	/**
	 * Formats Integer.
	 * 
	 * @param value integer to format
	 * @return formatted string or empty string if value is null
	 */
	/*
	 * public static String formatInteger(Integer value) { return format(value, new
	 * AjIntegerFormat()); }
	 */

	/**
	 * Formats Integer.
	 * 
	 * @param value integer to format
	 * @return formatted string
	 */
	public static String formatInteger(int value) {
		return formatInteger(new Integer(value));
	}

	/**
	 * Formats Double.
	 * 
	 * @param value integer to format
	 * @return formatted string or empty string if value is null
	 */
	/*
	 * public static String formatDouble(Double value) { return format(value, new
	 * AjDoubleFormat()); }
	 */

	/**
	 * Formats Double.
	 * 
	 * @param value integer to format
	 * @return formatted string
	 */
	public static String formatDouble(double value) {
		return formatDouble(new Double(value));
	}

	/**
	 * Formats Decimal.
	 * 
	 * @param value decimal to format
	 * @return formatted string or empty string if value is null
	 */
	/*
	 * public static String formatDecimal(BigDecimal value) { return format(value,
	 * new AjDecimalFormat()); }
	 */

	/**
	 * Formats money value.
	 * 
	 * @param value value to format
	 * @return formatted string or empty string if value is null
	 */
	/*
	 * public static String formatMoney(BigDecimal value) { return format(value, new
	 * AjCurrencyFormat()); }
	 */

	/**
	 * Formats money value. in double
	 * 
	 * @param value value to format
	 * @return formatted string or empty string if value is null
	 */
	/*
	 * public static String formatMoneyInWords(Double value) { return value != null
	 * ? NumberToWord.convert(value) : ""; }
	 */
	/**
	 * Formats money value in integer
	 * 
	 * @param value value to format
	 * @return formatted string or empty string if value is null
	 */

	/*
	 * public static String formatMoneyInWords(Integer value) { return value != null
	 * ? NumberToWord.convert(value) : ""; }
	 */
	/**
	 * This function takes BigDecimal value and depending on if the value is double
	 * call the convert function of double and
	 * 
	 * @param value value to format
	 * @return formatted string or empty string if value is null
	 */
	/*
	 * public static String formatMoneyInWords(BigDecimal value) { if (value !=
	 * null) { if (value.toString().contains(".")) { // this means amount is xxxx.xx
	 * Double dValue = value.doubleValue(); return formatMoneyInWords(dValue); }
	 * else { // this means amount is xxxx Integer nValue = value.intValue(); return
	 * formatMoneyInWords(nValue); } } return ""; }
	 */

	/**
	 * Formats percent value.
	 * 
	 * @param value value to format
	 * @return formatted string or empty string if value is null
	 */
	/*
	 * public static String formatPercent(BigDecimal value) { return format(value,
	 * new AjPercentFormat()); }
	 */
	/**
	 * Formats percent value which is actual percent, like 20%.
	 * 
	 * @param value value to format
	 * @return formatted string or empty string if value is null
	 */
	/*
	 * public static String formatPercent100(BigDecimal value) { return
	 * format(BigDecimalHelper.rate(value), new AjPercentFormat()); }
	 */

	/**
	 * Formats percent value for commission.
	 * 
	 * @param value value to format
	 * @return formatted string or empty string if value is null
	 */
	/*
	 * public static String formatCommissionPercent(BigDecimal value) { return
	 * format(value, new AjCommissionPercentFormat()); }
	 */

	/**
	 * Formats commission percent value which is actual percent, like 20%.
	 * 
	 * @param value value to format
	 * @return formatted string or empty string if value is null
	 */
	/*
	 * public static String formatCommissionPercent100(BigDecimal value) { return
	 * format(BigDecimalHelper.rate(value), new AjCommissionPercentFormat()); }
	 */

	/**
	 * Formats Date. This will convert the server time zone date to the client time
	 * zone date for display.
	 * 
	 * @param value date to format
	 * @return formatted string or empty string if value is null
	 */
	/*
	 * public static String formatDate(Date value) { return format(value, new
	 * AjDateFormat()); }
	 */
	/**
	 * Formats Date according to the passed in format pattern.
	 * 
	 * @param value   - value date to format
	 * @param pattern - date pattern to convert
	 * @return formatted string or empty string if value is null.
	 */
	/*
	 * public static String formatDate(Date value, String pattern) { AjDateFormat
	 * dateFormat = new AjDateFormat(); dateFormat.setFormatPattern(pattern); return
	 * format(value, dateFormat); }
	 */

	/**
	 * Formats Date according to the short format pattern.
	 * 
	 * @param value - value date to format
	 * @return formatted string or empty string if value is null.
	 */
	/*
	 * public static String formatDateShort(Date value) { return formatDate(value,
	 * ((SimpleDateFormat)
	 * DateFormat.getDateInstance(DateFormat.SHORT)).toPattern()); }
	 */
	/**
	 * Formats XML Date.
	 * 
	 * @param value date to format
	 * @return formatted string or empty string if value is null
	 */
	/*
	 * public static String formatXMLDate(Date value) { return format(value, new
	 * AjXMLDateFormat()); }
	 */

	/**
	 * Formats the date without converting it from the server time zone to the
	 * client time zone.
	 */
	/*
	 * public static String formatDateNoTZConversion(Date value) { return
	 * format(value, new AjDateFormatNoTZConversion()); }
	 */

	/**
	 * Formats Time.
	 * 
	 * @param value time to format
	 * @return formatted string or empty string if value is null
	 */
	/*
	 * public static String formatTime(Date value) { return format(value, new
	 * AjTimeFormat()); }
	 */

	/**
	 * Formats Time according to the passed in format pattern.
	 * 
	 * @param value   - value time to format
	 * @param pattern - time pattern to convert
	 * @return formatted string or empty string if value is null.
	 */
	/*
	 * public static String formatTime(Date value, String pattern) { AjTimeFormat
	 * timeFormat = new AjTimeFormat(); timeFormat.setFormatPattern(pattern); return
	 * format(value, timeFormat); }
	 */

	/**
	 * Formats Time.
	 * 
	 * @param value time to format
	 * @return formatted string or empty string if value is null
	 */
	/*
	 * public static String formatTimeMedium(Date value) { return format(value, new
	 * AjTimeMediumFormat()); }
	 */

	/**
	 * Formats Elapsed Time.
	 * 
	 * @param value elapsed time amount to format
	 * @return formatted string
	 */
	public static String formatElapsedTime(Long elapsedTime) {
		return formatElapsedTime(elapsedTime, Boolean.TRUE);
	}

	/**
	 * Formats Elapsed Time.
	 * 
	 * @param value elapsed time amount to format
	 * @return formatted string
	 */
	public static String formatElapsedTime(long elapsedTime) {
		return formatElapsedTime(elapsedTime, true);
	}

	/**
	 * Formats Elapsed Time.
	 * 
	 * @param value     elapsed time amount to format
	 * @param showHours show hours
	 * @return formatted string
	 */
	public static String formatElapsedTime(Long elapsedTime, Boolean showHours) {
		if (elapsedTime != null) {
			return formatElapsedTime(elapsedTime.longValue(), BooleanHelper.isTrue(showHours));
		} else {
			return "";
		}
	}

	/**
	 * Formats Elapsed Time.
	 * 
	 * @param value     elapsed time amount to format
	 * @param showHours show hours
	 * @return formatted string
	 */
	/*
	 * public static String formatElapsedTime(long elapsedTime, boolean showHours) {
	 * int hours = DateTimeHelper.getHours(elapsedTime); int minutes =
	 * DateTimeHelper.getMinutes(elapsedTime); int seconds =
	 * DateTimeHelper.getSeconds(elapsedTime);
	 * 
	 * String hrsStr = String.valueOf(100 + hours).substring(1); String minStr =
	 * String.valueOf(100 + minutes).substring(1); String secStr =
	 * String.valueOf(100 + seconds).substring(1);
	 * 
	 * String text; if (hours == 0) { if (minutes == 0) { String formatStr =
	 * showHours ? "00:00:%s" : "00:%s"; text = String.format(formatStr, secStr); }
	 * else { String formatStr = showHours ? "00:%s:%s" : "%s:%s"; text =
	 * String.format(formatStr, minStr, secStr); } } else { text =
	 * String.format("%s:%s:%s", hrsStr, minStr, secStr); }
	 * 
	 * return text; }
	 */

	/**
	 * Formats Date and Time.
	 * 
	 * @param value date/time to format
	 * @return formatted string or empty string if value is null
	 */
	/*
	 * public static String formatDateTime(Date value) { return format(value, new
	 * AjDateTimeFormat()); }
	 */

	/**
	 * Formats Date and Time.
	 * 
	 * @param value             date/time to format
	 * @param convertToServerTZ boolean specifying if time should be the server time
	 *                          even when displayed on the client
	 * @return formatted string or empty string if value is null
	 */
	/*
	 * public static String formatDateTime(Date value, boolean convertToServerTZ) {
	 * return format(value, new AjDateTimeFormat(convertToServerTZ)); }
	 */
	/**
	 * Formats XML Date and Time.
	 * 
	 * @param value date/time to format
	 * @return formatted string or empty string if value is null
	 */

	/*
	 * public static String formatXMLDateTime(Date value) { return format(value, new
	 * AjXMLDateTimeFormat()); }
	 */
	/**
	 * Formats Date and Time with seconds.
	 * 
	 * @param value date/time to format
	 * @return formatted string or empty string if value is null
	 */
	/*
	 * public static String formatDateTimeMedium(Date value) { return format(value,
	 * new AjDateTimeMediumFormat()); }
	 */

	/**
	 * Formats Date and Time. Time is only included if present.
	 * 
	 * @param value date/time to format
	 * @return formatted string or empty string if value is null
	 */
	/*
	 * public static String formatDateTimeOptional(Date value) { if
	 * (DateTimeHelper.equalDates(value, DateTimeHelper.getDateOnly(value))) {
	 * return formatDate(value); } else { return formatDateTime(value); } }
	 */

	/**
	 * Returns a date/time in a sortable format (yyyyMMddHHmmss).
	 * 
	 * @param value a date value
	 * @return string converted into yyyyMMddHHmmss format.
	 */
	/*
	 * public static String formatDateTimeSortable(Date value) { return
	 * format(value, new AjDateTimeSortableFormat()); }
	 */

	/**
	 * Returns a date/time in a sortable format with milliseconds
	 * (yyyyMMddHHmmssSSSS).
	 * 
	 * @param value a date value
	 * @return string converted into yyyyMMddHHmmssSSSS format.
	 */
	/*
	 * public static String formatDateTimeSortableWithMillisecond(Date value) {
	 * return format(value, new AjDateTimeSortableWithMillisecondFormat()); }
	 */

	/**
	 * Formats phone number.
	 * 
	 * @param value phone to format
	 * @return formatted string or empty string if value is null
	 */
	/*
	 * public static String formatPhone(String value) { return format(value, new
	 * AjPhoneFormat()); }
	 */

	/**
	 * Formats phone number.
	 * 
	 * @param value phone to format
	 * @return formatted string or empty string if value is null
	 */
	/*
	 * public static String formatPhone(IPhone value) { return format(value, new
	 * AjPhoneFormat()); }
	 */

	/**
	 * Formats phone number.
	 * 
	 * @param areaCode 3 digit area code
	 * @param number   7 digit phone number
	 * @return formatted string or empty string if value is null
	 */
	/*
	 * public static String formatPhone(String areaCode, String number) { return
	 * (new AjPhoneFormat()).formatPhone(areaCode, number); }
	 */
	/**
	 * Formats phone number.
	 * 
	 * @param areaCode  3 digit area code
	 * @param number    7 digit phone number
	 * @param extension phone extension
	 * @return formatted string or empty string if value is null
	 */
	/*
	 * public static String formatPhone(String areaCode, String number, String
	 * extension) { return (new AjPhoneFormat()).formatPhone(areaCode, number,
	 * extension); }
	 * 
	 * public static String formatAddress(DataAddressTO address) { if (address !=
	 * null) { return formatAddress(address.getStreet1(), address.getStreet2(),
	 * address.getCity(), address.getState(), address.getPostalCode(),
	 * address.getCountry() != null ? address.getCountry().getAlpha3() : "", ", ");
	 * } else { return ""; } }
	 * 
	 * public static String formatAddressVertically(DataAddressTO address) { if
	 * (address != null) { return formatAddress(address.getStreet1(),
	 * address.getStreet2(), address.getCity(), address.getState(),
	 * address.getPostalCode(), address.getCountry() != null ?
	 * address.getCountry().getAlpha3() : ""); } else { return ""; } }
	 */

	/**
	 * Formats address.
	 * 
	 * @param street1 street1
	 * @param street2 street2
	 * @param city    city
	 * @param state   state
	 * @param zip     postal code
	 * @param country country
	 * @return formatted string
	 */
	/*
	 * public static String formatAddress(String street1, String street2, String
	 * city, String state, String zip, String country) { return
	 * formatAddress(street1, street2, city, state, zip, country, "\n"); }
	 */

	/**
	 * Formats address. TODO It needs to be internatialized.
	 * 
	 * @param street1   street1, can be null
	 * @param street2   street2, can be null
	 * @param city      city, can be null
	 * @param state     state, can be null
	 * @param zip       postal code, can be null
	 * @param country   country, can be null
	 * @param separator separator, can be null
	 * @return formatted string
	 */
	/*
	 * public static String formatAddress(String street1, String street2, String
	 * city, String state, String zip, String country, String separator) { String
	 * csz = formatCityStateZip(city, state, zip); return
	 * concat(concat(concat(street1, separator, street2), separator, csz),
	 * separator, country); }
	 */

	/**
	 * Formats address.
	 * 
	 * @param street1 street1, can be null
	 * @param street2 street2, can be null
	 * @param city    city, can be null
	 * @param state   state, can be null
	 * @param zip     postal code, can be null
	 * @param country country, can be null
	 * @return formatted string
	 */
	/*
	 * public static String formatAddress(String street1, String street2, String
	 * city, StateSummaryTO state, String zip, String country) { return
	 * formatAddress(street1, street2, city, state, zip, country, "\n"); }
	 */
	/**
	 * Formats address.
	 * 
	 * @param street1   street1, can be null
	 * @param street2   street2, can be null
	 * @param city      city, can be null
	 * @param state     state, can be null
	 * @param zip       postal code, can be null
	 * @param country   country, can be null
	 * @param separator separator, can be null
	 * @return formatted string
	 */
	/*
	 * public static String formatAddress(String street1, String street2, String
	 * city, StateSummaryTO state, String zip, String country, String separator) {
	 * return formatAddress(street1, street2, city, state != null ?
	 * state.getAbbreviation() : null, zip, country, separator); }
	 */

	/**
	 * Formats city, state zip. TODO It needs to be internatialized.
	 * 
	 * @param city  city, can be null
	 * @param state state, can be null
	 * @param zip   postal code, can be null
	 * @return formatted city, state zip
	 */
	/*
	 * public static String formatCityStateZip(String city, String state, String
	 * zip) { return concat(concat(city, ", ", state), "  ", formatPostalCode(zip));
	 * }
	 */
	/**
	 * Formats city, state zip.
	 * 
	 * @param city  city, can be null
	 * @param state state, can be null
	 * @param zip   postal code, can be null
	 * @return formatted city, state zip
	 */
	/*
	 * public static String formatCityStateZip(String city, StateSummaryTO state,
	 * String zip) { return formatCityStateZip(city, state != null ?
	 * state.getAbbreviation() : null, zip); }
	 */
	/**
	 * Formats name using the provided parameters.
	 * 
	 * @param prefix name prefix
	 * @param first  first name
	 * @param middle middle name
	 * @param last   last name
	 * @param suffix name suffix
	 * @return formated name
	 */
	public static String formatName(Object prefix, Object first, Object middle, Object last, Object suffix) {
		return concat(concat(concat(concat(prefix, " ", first), " ", middle), " ", last), " ", suffix);
	}

	/**
	 * Get the length of an object as a string (supports null for object).
	 * 
	 * @param object the object as string to get length from
	 * @return length of object as string
	 */
	public static int length(Object obj) {
		return asString(obj).length();
	}

	/**
	 * Generate a sequence of n occurances of a pattern.
	 * 
	 * @param sb      string buffer to append sequence to
	 * @param pattern pattern to repeat
	 * @param n       number of times to repeat pattern
	 * @return resulting string buffer
	 */
	public static StringBuffer sequence(StringBuffer sb, String pattern, int n) {
		for (int i = 0; i < n; i++) {
			sb.append(pattern);
		}
		return sb;
	}

	/**
	 * Generate a sequence of n occurances of a pattern.
	 * 
	 * @param sb      string builder to append sequence to
	 * @param pattern pattern to repeat
	 * @param n       number of times to repeat pattern
	 * @return resulting string builder
	 */
	public static StringBuilder sequence(StringBuilder sb, String pattern, int n) {
		for (int i = 0; i < n; i++) {
			sb.append(pattern);
		}
		return sb;
	}

	/**
	 * Generate a sequence of n occurances of a pattern.
	 * 
	 * @param pattern pattern to repeat
	 * @param n       number of times to repeat pattern
	 * @return resulting string
	 */
	public static String sequence(String pattern, int n) {
		return sequence(new StringBuilder(), pattern, n).toString();
	}

	/**
	 * Generate a sequence of n occurances of a pattern.
	 * 
	 * @param pattern pattern to repeat
	 * @param n       number of times to repeat pattern
	 * @return resulting string
	 */
	/*
	 * public static String sequence(String pattern, Integer n) { return
	 * sequence(pattern, IntegerHelper.intValue(n)); }
	 */

	/**
	 * Pad the Original string with toPad string(not a single character) at a time.
	 * so e.g if original string is 'test' and pad string is 'xxxxx' then pad
	 * 'xxxxx' till max length.
	 * 
	 * @param original string with original string to padd
	 * @param right    right justify if true, left justify otherwise
	 * @param length   max length to the string
	 * @param pad      pad string
	 * @return justified string
	 */
	/*
	 * public static String justify(String original, Boolean right, Integer
	 * maxLength, String toPad) { StringBuilder sb = new StringBuilder(); if
	 * (original != null && maxLength != null) { // get the difference between max
	 * length and actual original string length int diff =
	 * IntegerHelper.subtract(maxLength, original.length()).intValue(); // this is
	 * length of the toPad string int padLength = toPad.length();
	 * 
	 * // if original string is less than a max length, there is scope for padding.
	 * if (original.length() < maxLength) {
	 * 
	 * // for right padding, assign the orignal string first if (right) {
	 * sb.append(original); } // get the number how many characters to pad diff =
	 * maxLength - original.length();
	 * 
	 * // till the remaining characters are more than pad string while (diff >=
	 * padLength) { sb = sb.append(toPad); diff = diff - padLength; } // as soon as
	 * there are less char left to pad than pad string if (diff <= padLength) { //
	 * get substring from toPad for those many char sb.append(toPad.substring(0,
	 * diff)); }
	 * 
	 * // in case of left padding at the origial at the end if (!right) {
	 * sb.append(original); } } else { sb.append(original); } } else {
	 * sb.append(original); }
	 * 
	 * return sb.toString(); }
	 */

	/**
	 * Justify and pad or truncate to length content of string buffer.
	 * 
	 * @param sb     string buffer to justify
	 * @param right  right justify if true, left justify otherwise
	 * @param length resulting length
	 * @param pad    pad character
	 * @return justified string buffer
	 */
	/*
	 * public static StringBuffer justify(StringBuffer sb, Boolean right, Integer
	 * length, String pad) { if ((sb != null) && (length != null)) { int diff =
	 * IntegerHelper.subtract(length, sb.length()).intValue(); int padLength =
	 * pad.length();
	 * 
	 * if (BooleanHelper.isTrue(right)) { // right justification if (diff < 0) { //
	 * truncate on the left sb.replace(0, length.intValue(), sb.substring(0 -
	 * diff)); sb.setLength(length.intValue()); } else if (pad != null) { // pad on
	 * the left sb.insert(0, sequence(pad.toString(), diff)); } } else { // left
	 * justification (default) if (diff < 0) { // truncate on the right
	 * sb.setLength(length.intValue()); } else if (pad != null) { // pad on the
	 * right sequence(sb, pad.toString(), diff); } } } return sb; }
	 */
	/**
	 * Justify and pad or truncate to length content of string builder.
	 * 
	 * @param sb     string builder to justify
	 * @param right  right justify if true, left justify otherwise
	 * @param length resulting length
	 * @param pad    pad character
	 * @return justified string builder
	 */
	/*
	 * public static StringBuilder justify(StringBuilder sb, Boolean right, Integer
	 * length, String pad) { if ((sb != null) && (length != null)) { int diff =
	 * IntegerHelper.subtract(length, sb.length()).intValue(); int padLength =
	 * pad.length();
	 * 
	 * if (BooleanHelper.isTrue(right)) { // right justification if (diff < 0) { //
	 * truncate on the left sb.replace(0, length.intValue(), sb.substring(0 -
	 * diff)); sb.setLength(length.intValue()); } else if (pad != null) { // pad on
	 * the left sb.insert(0, sequence(pad.toString(), diff)); } } else { // left
	 * justification (default) if (diff < 0) { // truncate on the right
	 * sb.setLength(length.intValue()); } else if (pad != null) { // pad on the
	 * right sequence(sb, pad.toString(), diff); } } } return sb; }
	 */

	/**
	 * Justify and pad or truncate to length content of string buffer.
	 * 
	 * @param sb     string buffer to justify
	 * @param right  right justify if true, left justify otherwise
	 * @param length resulting length
	 * @param pad    pad character
	 * @return justified string buffer
	 */
	/*
	 * public static StringBuffer justify(StringBuffer sb, Boolean right, Integer
	 * length, Character pad) { if ((sb != null) && (length != null)) { int diff =
	 * IntegerHelper.subtract(length, sb.length()).intValue(); if
	 * (BooleanHelper.isTrue(right)) { // right justification if (diff < 0) { //
	 * truncate on the left sb.replace(0, length.intValue(), sb.substring(0 -
	 * diff)); sb.setLength(length.intValue()); } else if (pad != null) { // pad on
	 * the left sb.insert(0, sequence(pad.toString(), diff)); } } else { // left
	 * justification (default) if (diff < 0) { // truncate on the right
	 * sb.setLength(length.intValue()); } else if (pad != null) { // pad on the
	 * right sequence(sb, pad.toString(), diff); } } } return sb; }
	 */
	/**
	 * Justify and pad or truncate to length content of string builder.
	 * 
	 * @param sb     string builder to justify
	 * @param right  right justify if true, left justify otherwise
	 * @param length resulting length
	 * @param pad    pad character
	 * @return justified string builder
	 */
	/*
	 * public static StringBuilder justify(StringBuilder sb, Boolean right, Integer
	 * length, Character pad) { if ((sb != null) && (length != null)) { int diff =
	 * IntegerHelper.subtract(length, sb.length()).intValue(); if
	 * (BooleanHelper.isTrue(right)) { // right justification if (diff < 0) { //
	 * truncate on the left sb.replace(0, length.intValue(), sb.substring(0 -
	 * diff)); sb.setLength(length.intValue()); } else if (pad != null) { // pad on
	 * the left sb.insert(0, sequence(pad.toString(), diff)); } } else { // left
	 * justification (default) if (diff < 0) { // truncate on the right
	 * sb.setLength(length.intValue()); } else if (pad != null) { // pad on the
	 * right sequence(sb, pad.toString(), diff); } } } return sb; }
	 */

	/**
	 * Justify and pad or truncate to length content of string buffer.
	 * 
	 * @param sb     string buffer to justify
	 * @param right  right justify if true, left justify otherwise
	 * @param length resulting length
	 * @param pad    pad character
	 * @return justified string buffer
	 */
	public static StringBuffer justify(StringBuffer sb, boolean right, int length, char pad) {
		return justify(sb, new Boolean(right), new Integer(length), new Character(pad));
	}

	/**
	 * Justify and pad or truncate to length content of string builder.
	 * 
	 * @param sb     string builder to justify
	 * @param right  right justify if true, left justify otherwise
	 * @param length resulting length
	 * @param pad    pad character
	 * @return justified string builder
	 */
	public static StringBuilder justify(StringBuilder sb, boolean right, int length, char pad) {
		return justify(sb, new Boolean(right), new Integer(length), new Character(pad));
	}

	/**
	 * Justify and pad or truncate to length object as a string.
	 * 
	 * @param obj    object as a string to justify
	 * @param right  right justify if true, left justify otherwise
	 * @param length resulting length
	 * @param pad    pad character
	 * @return justified string
	 */
	public static String justify(Object obj, boolean right, int length, char pad) {
		return justify(new StringBuilder(asString(obj)), right, length, pad).toString();
	}

	/**
	 * Justify and pad or truncate to length array of objects as strings.
	 * 
	 * @param objs    array of objects as strings to justify
	 * @param rights  right justify if true, left justify otherwise
	 * @param lengths resulting lengths
	 * @param pads    pad characters
	 * @return justified string
	 */
	public static String justify(Object[] objs, boolean[] rights, int[] lengths, char[] pads) {
		StringBuilder sb = new StringBuilder();
		if (objs != null) {
			boolean right = false;
			int length = 10;
			char pad = ' ';
			for (int i = 0; i < objs.length; i++) {
				if (sb.length() > 0) {
					sb.append(' ');
				}
				if (rights != null && i < rights.length) {
					right = rights[i];
				}
				if (lengths != null && i < lengths.length) {
					length = lengths[i];
				}
				if (pads != null && i < pads.length) {
					pad = pads[i];
				}
				sb.append(justify(objs[i], right, length, pad));
			}
		}
		return sb.toString();
	}

	/**
	 * Converts value to a string with no non-numeric characters.
	 * 
	 * @param sb    string buffer to append converted string to
	 * @param value value to convert to string with no non-numerics
	 * @return string buffer with non-numeric characters removed
	 */
	public static StringBuffer removeNonNumerics(StringBuffer sb, Object value) {
		String s = asString(value);
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (Character.isDigit(ch)) {
				sb.append(ch);
			}
		}
		return sb;
	}

	/**
	 * Converts value to a string with no non-numeric characters.
	 * 
	 * @param sb    string builder to append converted string to
	 * @param value value to convert to string with no non-numerics
	 * @return string builder with non-numeric characters removed
	 */
	public static StringBuilder removeNonNumerics(StringBuilder sb, Object value) {
		String s = asString(value);
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (Character.isDigit(ch)) {
				sb.append(ch);
			}
		}
		return sb;
	}

	/**
	 * Converts value to a string with no non-numeric characters.
	 * 
	 * @param value value to convert to string with no non-numerics
	 * @return string with non-numeric characters removed
	 */
	public static String removeNonNumerics(Object value) {
		return value != null ? removeNonNumerics(new StringBuilder(), value).toString() : null;
	}

	/**
	 * Converts value to a string with no numeric characters.
	 * 
	 * @param sb    string buffer to append converted string to
	 * @param value value to convert to string with no numerics
	 * @return string buffer with numeric characters removed
	 */
	public static StringBuffer removeNumerics(StringBuffer sb, Object value) {
		String s = asString(value);
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (!Character.isDigit(ch)) {
				sb.append(ch);
			}
		}
		return sb;
	}

	/**
	 * Converts value to a string with no numeric characters.
	 * 
	 * @param sb    string builder to append converted string to
	 * @param value value to convert to string with no numerics
	 * @return string builder with numeric characters removed
	 */
	public static StringBuilder removeNumerics(StringBuilder sb, Object value) {
		String s = asString(value);
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (!Character.isDigit(ch)) {
				sb.append(ch);
			}
		}
		return sb;
	}

	/**
	 * Converts value to a string with no numeric characters.
	 * 
	 * @param value value to convert to string with no numerics
	 * @return string with numeric characters removed
	 */
	public static String removeNumerics(Object value) {
		return value != null ? removeNumerics(new StringBuilder(), value).toString() : null;
	}

	/**
	 * Converts value to a string with no white space characters (TAB, CR, LF and
	 * SPACE).
	 * 
	 * @param sb    string buffer to append converted string to
	 * @param value value to convert to string with no white space
	 * @return string buffer with white space characters removed
	 */
	public static StringBuffer removeWhiteSpace(StringBuffer sb, Object value) {
		String s = asString(value);
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (!Character.isWhitespace(ch)) {
				sb.append(ch);
			}
		}
		return sb;
	}

	/**
	 * Converts value to a string with no white space characters (TAB, CR, LF and
	 * SPACE).
	 * 
	 * @param sb    string builder to append converted string to
	 * @param value value to convert to string with no white space
	 * @return string builder with white space characters removed
	 */
	public static StringBuilder removeWhiteSpace(StringBuilder sb, Object value) {
		String s = asString(value);
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (!Character.isWhitespace(ch)) {
				sb.append(ch);
			}
		}
		return sb;
	}

	/**
	 * Converts value to a string with no white space characters (TAB, CR, LF and
	 * SPACE).
	 * 
	 * @param value value to convert to string with no white space
	 * @return string with white space characters removed
	 */
	public static String removeWhiteSpace(Object value) {
		return value != null ? removeWhiteSpace(new StringBuilder(), value).toString() : null;
	}

	/**
	 * Converts value to a string with no leading and trailing white space nor any
	 * redundant white space characters (TAB, CR, LF and SPACE). This will also
	 * convert all non-space character white space to spaces. All duplicate white
	 * spaces will be replaced with a single space character.
	 * 
	 * @param sb    string buffer to append converted string to
	 * @param value value to convert to string with no redundant white space
	 * @return string buffer with redundant white space characters removed
	 */
	public static StringBuffer removeRedundantWhiteSpace(StringBuffer sb, Object value) {
		String[] s = asString(value).split("\\s");
		for (int i = 0; i < s.length; i++) {
			if (s[i].length() > 0) {
				if (sb.length() > 0) {
					sb.append(' ');
				}
				sb.append(s[i]);
			}
		}
		return sb;
	}

	/**
	 * Converts value to a string with no leading and trailing white space nor any
	 * redundant white space characters (TAB, CR, LF and SPACE). This will also
	 * convert all non-space character white space to spaces. All duplicate white
	 * spaces will be replaced with a single space character.
	 * 
	 * @param sb    string builder to append converted string to
	 * @param value value to convert to string with no redundant white space
	 * @return string builder with redundant white space characters removed
	 */
	public static StringBuilder removeRedundantWhiteSpace(StringBuilder sb, Object value) {
		String[] s = asString(value).split("\\s");
		for (int i = 0; i < s.length; i++) {
			if (s[i].length() > 0) {
				if (sb.length() > 0) {
					sb.append(' ');
				}
				sb.append(s[i]);
			}
		}
		return sb;
	}

	/**
	 * Converts value to a string with no leading and trailing white space nor any
	 * redundant white space characters (TAB, CR, LF and SPACE). This will also
	 * convert all non-space character white space to spaces. All duplicate white
	 * spaces will be replaced with a single space character.
	 * 
	 * @param value value to convert to string with no redundant white space
	 * @return string with redundant white space characters removed
	 */
	public static String removeRedundantWhiteSpace(Object value) {
		return value != null ? removeRedundantWhiteSpace(new StringBuilder(), value).toString() : null;
	}

	/**
	 * Removes repeating words from a string. A word is considered any portion of
	 * the string that is delimited by white space. For example the string "Client
	 * Client ID" would be converted to "Client ID".
	 * 
	 * @param sb    string buffer to append converted string to
	 * @param value value to convert to string with no redundant words
	 * @return string buffer with redundant words removed
	 */
	public static StringBuffer removeRedundantWords(StringBuffer sb, Object value) {
		String[] s = asString(value).split("\\s");
		boolean first = true;
		for (int i = 0; i < s.length; i++) {
			if (s[i].length() > 0) {
				if (first) {
					sb.append(s[i]);
					first = false;
				} else {
					// Make sure this string is different than the last one
					if (!s[i].equals(s[i - 1])) {
						if (!first) {
							sb.append(' ');
						}
						sb.append(s[i]);
					}
				}
			}
		}
		return sb;
	}

	/**
	 * Removes repeating words from a string. A word is considered any portion of
	 * the string that is delimited by white space. For example the string "Client
	 * Client ID" would be converted to "Client ID".
	 * 
	 * @param sb    string builder to append converted string to
	 * @param value value to convert to string with no redundant words
	 * @return string builder with redundant words removed
	 */
	public static StringBuilder removeRedundantWords(StringBuilder sb, Object value) {
		String[] s = asString(value).split("\\s");
		boolean first = true;
		for (int i = 0; i < s.length; i++) {
			if (s[i].length() > 0) {
				if (first) {
					sb.append(s[i]);
					first = false;
				} else {
					// Make sure this string is different than the last one
					if (!s[i].equals(s[i - 1])) {
						if (!first) {
							sb.append(' ');
						}
						sb.append(s[i]);
					}
				}
			}
		}
		return sb;
	}

	/**
	 * Removes repeating words from a string. A word is considered any portion of
	 * the string that is delimited by white space. For example the string "Client
	 * Client ID" would be converted to "Client ID".
	 * 
	 * @param value string to be converted
	 * @return The new string with redundant words removed
	 */
	public static String removeRedundantWords(Object value) {
		return value != null ? removeRedundantWords(new StringBuilder(), value).toString() : null;
	}

	/**
	 * Removes all punctuation !"#$%&'()*+,-./:;<=>?@[\]^_`{|}~ and replaces it with
	 * a space. It will then remove all redundant white spaces from the resulting
	 * string.
	 * 
	 * @param s Input string to remove duplicate white spaces from.
	 * @return
	 */
	public static String removePunctuation(String s) {
		// The \\p{Punct} is a regular expression that indicates all punctuation marks.
		if (s == null) {
			return null;
		}
		String str = s.replaceAll("\\p{Punct}", " ");
		return removeRedundantWhiteSpace(str);
	}

	/**
	 * Formats exception. Provides special handling for AjentException (only message
	 * without class name is returned).
	 * 
	 * @param exception The exception to be formatted as a String
	 * @return The formatted exception as a String
	 */
	/*
	 * public static String formatException(Throwable exception) { if ((exception
	 * instanceof AjentException || exception instanceof AjentRuntimeException) &&
	 * !isBlank(exception.getMessage())) { return exception.getMessage(); } else {
	 * return asString(exception); } }
	 */
	/**
	 * This method is used to format an exception stack trace for inclusion into an
	 * output other than System.err or System.out.
	 * 
	 * @param exception The exception containing the stack trace to be formatted as
	 *                  a String
	 * @return The formatted exception stack trace as a String
	 */
	public static String formatStackTrace(Throwable exception) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		if (exception != null) {
			PrintStream trace = new PrintStream(baos);
			exception.printStackTrace(trace);
			trace.close();
		}
		return baos.toString();
	}

	/**
	 * Formats Employer Identification Number.
	 * 
	 * @param EIN - The Ein Number to format
	 * @return The formated EIN
	 */
	public static String formatEIN(String ein) {
		if (!isBlank(ein) && (ein.length() >= 9)) {
			// check if the EIN is already preformatted
			if (ein.indexOf('-') != -1) {
				return ein;
			}

			StringBuilder formattedEIN = new StringBuilder();
			formattedEIN.append(ein.substring(0, 2));
			formattedEIN.append('-');
			formattedEIN.append(ein.substring(2, 9));
			return formattedEIN.toString();
		}
		return ein;
	}

	/**
	 * Will format the taxpayer ID based on the type of ID it is. It will either
	 * come back in SSN formtting or EIN formatting. If a type is not set, it will
	 * default to SSN for individual type, or EIN for company type. If it cannot
	 * determine the type, then it will just return what is sent in.
	 * 
	 * @param taxPayerId - ID to be formatted.
	 * @param govtIdType - Type of Government ID SSN or EIN
	 * @param partyType  - Type of Party Individual or Customer will be used to
	 *                   default EIN or SSN if the GovtIdType is not filled out.
	 * @return formatted TaxpayerID
	 */
	/*
	 * public static String formatTaxPayerId(Object taxPayerId, GovtIdType
	 * govtIdType, PartyType partyType) { String formattedTaxId =
	 * asString(taxPayerId); if (govtIdType != null) { if
	 * (govtIdType.equals(GovtIdType.EIN)) { formattedTaxId =
	 * formatEIN(formattedTaxId); } else { formattedTaxId =
	 * formatSSN(formattedTaxId); } } else if (partyType != null) { if
	 * (partyType.equals(PartyType.INDIVIDUAL)) { formattedTaxId =
	 * formatSSN(formattedTaxId); } else { formattedTaxId =
	 * formatEIN(formattedTaxId); } } return formattedTaxId; }
	 */
	/**
	 * Formats Social Security Number.
	 * 
	 * @param socialSecurityNumber The Social Security Number to format
	 * @return The formatted Social Security Number
	 */
	public static String formatSSN(String socialSecurityNumber) {
		if (!isBlank(socialSecurityNumber) && (socialSecurityNumber.length() >= 9)) {
			// check if the SSN is already preformatted
			if (socialSecurityNumber.indexOf('-') != -1) {
				return socialSecurityNumber;
			}

			StringBuilder formattedSSN = new StringBuilder();
			formattedSSN.append(socialSecurityNumber.substring(0, 3));
			formattedSSN.append('-');
			formattedSSN.append(socialSecurityNumber.substring(3, 5));
			formattedSSN.append('-');
			formattedSSN.append(socialSecurityNumber.substring(5, socialSecurityNumber.length()));
			return formattedSSN.toString();
		}

		// If it does not match a known format, return how it was sent in.
		return socialSecurityNumber;
	}

	/**
	 * Get default XML encoding
	 * 
	 * @return default encoding
	 */
	public static String getDefaultXMLEncoding() {
		// TODO get default XML encoding for this platform, currently hardcoded to
		// assume UTF-8
		return "UTF-8";
	}

	/**
	 * Get an XML document's encoding
	 * 
	 * @param doc XML document
	 * @return encoding
	 */
	public static String getXMLEncoding(String doc) {
		if (!isBlank(doc)) {
			int endOfFirstTag = doc.indexOf(">");
			if (endOfFirstTag > 0 && doc.charAt(endOfFirstTag - 1) == '?') {
				String upperLine = doc.substring(0, endOfFirstTag).toUpperCase();
				int encStart = upperLine.indexOf(ATTR_ENCODING) + ATTR_ENCODING.length() + 1;
				int encEnd = -1;

				if (encStart > 0) {
					encEnd = upperLine.indexOf("\"", encStart);
					if (encEnd == -1) {
						encEnd = upperLine.indexOf("\'", encStart);
					}
				}
				if (encStart > 0 && encEnd > 0) {
					return upperLine.substring(encStart, encEnd);
				}
			}
		}
		return getDefaultXMLEncoding();
	}

	/**
	 * Get the character set for the specified character set name
	 * 
	 * @param charsetName character set name
	 * @return character set
	 */
	public static Charset getCharset(String charsetName) {
		Charset charset = null;
		try {
			charset = Charset.forName(charsetName);
		} catch (UnsupportedCharsetException e) {
			// do nothing
		} catch (IllegalCharsetNameException e) {
			// do nothing
		}
		return charset;
	}

	/**
	 * Convert the value to a quoted string using the specified quote character.
	 * 
	 * @param value object
	 * @param quote quote character
	 * @return quoted string
	 */
	public static String quote(Object value, String quote) {
		String str = asString(value);
		if (!str.startsWith(quote) || !str.endsWith(quote)) {
			str = quote + str + quote;
		}
		return str;
	}

	/**
	 * Convert the value to a quoted string using the specified quote character.
	 * 
	 * @param value object
	 * @param quote quote character
	 * @return quoted string
	 */
	public static String quote(Object value, char quote) {
		return quote(value, String.valueOf(quote));
	}

	/**
	 * Convert the value to a quoted string using the specified quote character.
	 * 
	 * @param value object
	 * @param quote quote character
	 * @return quoted string
	 */
	public static String quote(Object value, Character quote) {
		if (quote != null) {
			return quote(value, quote.charValue());
		} else {
			return asString(value);
		}
	}

	/**
	 * Convert the value to a quoted string.
	 * 
	 * @param value object
	 * @return quoted string
	 */
	public static String quote(Object value) {
		return quote(value, '"');
	}

	/**
	 * Convert the value to an unquoted string using the specified quote character.
	 * 
	 * @param value object
	 * @parem quote quote character
	 * @return unquoted string
	 */
	public static String unquote(Object value, Character quote) {
		if (quote != null) {
			return unquote(value, quote.charValue());
		} else {
			return asString(value);
		}
	}

	/**
	 * Convert the value to an unquoted string using the specified quote character.
	 * 
	 * @param value object
	 * @parem quote quote character
	 * @return unquoted string
	 */
	public static String unquote(Object value, char quote) {
		return unparen(value, quote, quote);
	}

	/**
	 * Convert the value to an unquoted string.
	 * 
	 * @param value object
	 * @return unquoted string
	 */
	public static String unquote(Object value) {
		return unquote(value, '"');
	}

	/**
	 * Convert the value to a parenthesized string using the specified parenthesis
	 * characters.
	 * 
	 * @param value      object
	 * @param openParen  open parenthesis character
	 * @param closeParen close parenthesis character
	 * @return parenthesized string
	 */
	public static String paren(Object value, String openParen, String closeParen) {
		String str = asString(value);
		if (!isBlank(str) && (!str.startsWith(openParen) || !str.endsWith(closeParen))) {
			str = openParen + str + closeParen;
		}
		return str;
	}

	/**
	 * Convert the value to a parenthesized string using the specified parenthesis
	 * characters.
	 * 
	 * @param value      object
	 * @param openParen  open parenthesis character
	 * @param closeParen close parenthesis character
	 * @return parenthesized string
	 */
	public static String paren(Object value, char openParen, char closeParen) {
		return paren(value, String.valueOf(openParen), String.valueOf(closeParen));
	}

	/**
	 * Convert the value to a parenthesized string.
	 * 
	 * @param value object
	 * @return parenthesized string
	 */
	public static String paren(Object value) {
		return paren(value, '(', ')');
	}

	/**
	 * Convert the value to an unparenthesized string using the specified
	 * parenthesis characters.
	 * 
	 * @param value      object
	 * @param openParen  open parenthesis character
	 * @param closeParen close parenthesis character
	 * @return unparenthesized string
	 */
	public static String unparen(Object value, String openParen, String closeParen) {
		String str = asString(value);
		if (str.startsWith(openParen) && str.endsWith(closeParen)) {
			str = str.substring(openParen.length(), str.length() - closeParen.length());
		}
		return str;
	}

	/**
	 * Convert the value to an unparenthesized string using the specified
	 * parenthesis characters.
	 * 
	 * @param value      object
	 * @param openParen  open parenthesis character
	 * @param closeParen close parenthesis character
	 * @return unparenthesized string
	 */
	public static String unparen(Object value, char openParen, char closeParen) {
		return unparen(value, String.valueOf(openParen), String.valueOf(closeParen));
	}

	/**
	 * Convert the value to an unparenthesized string.
	 * 
	 * @param value object
	 * @return unparenthesized string
	 */
	public static String unparen(Object value) {
		return unparen(value, '(', ')');
	}

	/**
	 * Convert the value to an unparenthesized string.
	 * 
	 * @param value object
	 * @return unparenthesized string
	 */
	public static String unparenAll(Object value) {
		String str = asString(value);
		char[][] parens = new char[][] { { '(', ')' }, { '{', '}' }, { '[', ']' }, { '<', '>' } };
		int prevLen;
		do {
			prevLen = str.length();
			for (int i = 0; i < parens.length; i++) {
				str = unparen(str, parens[i][0], parens[i][1]);
			}
		} while (prevLen != str.length());
		return str;
	}

	/**
	 * Convert the value to a parameterized string.
	 * 
	 * @param value object
	 * @return parameterized string
	 */
	public static String param(Object value) {
		return paren(value, PARAM_PREFIX, PARAM_SUFFIX);
	}

	/**
	 * Convert the value as a CSV type string separated by specified delimiter to a
	 * collection of individual strings.
	 * 
	 * @param value      object
	 * @param collection collection to insert individual strings into
	 * @param delimiter  delimiter
	 * @param trim       trim individual strings
	 * @param allowBlank allow blank individual strings into collection
	 * @return collection of strings
	 */
	public static Collection asCollection(Object value, Collection collection, String delimiter, boolean trim,
			boolean allowBlank) {
		if (collection != null) {
			String[] strings = asString(value).split(asRegexString(delimiter));
			for (int i = 0; i < strings.length; i++) {
				String str = trim ? strings[i].trim() : strings[i];
				if (allowBlank || !isBlank(str)) {
					collection.add(str);
				}
			}
		}
		return collection;
	}

	/**
	 * Convert the value as a CSV type string to a collection of individual strings.
	 * 
	 * @param value      object
	 * @param collection collection to insert individual strings into
	 * @return collection of strings
	 */
	public static Collection asCollection(Object value, Collection collection) {
		return asCollection(value, collection, ",", true, false); // TODO get delimiter for locale
	}

	/**
	 * Convert the value as a CSV type string to a collection of individual strings.
	 * 
	 * @param value object
	 * @return collection of strings
	 */
	public static Collection asCollection(Object value) {
		return asCollection(value, value != null ? new LinkedHashSet() : null);
	}

	/**
	 * Convert the collection of values to a collection of strings.
	 * 
	 * @param values collection of values
	 * @return collection of strings
	 */
	/*
	 * public static Collection<String> asStringCollection(Collection values) {
	 * Collection<String> result = null; if (values != null) { result =
	 * ObjectHelper.getEmptyCollection(values); for (Object value : values) {
	 * result.add(asString(value)); } } return result; }
	 */

	/**
	 * Convert the value as a CSV type string separated by specified delimiter to an
	 * array of individual strings.
	 * 
	 * @param value      object
	 * @param delimiter  delimiter
	 * @param trim       trim individual strings
	 * @param allowBlank allow blank individual strings
	 * @param allowDup   allow duplicate individual strings
	 * @return array of strings
	 */
	public static String[] asArray(Object value, String delimiter, boolean trim, boolean allowBlank, boolean allowDup) {
		Collection collection = value != null ? allowDup ? new ArrayList() : new LinkedHashSet() : null;
		collection = asCollection(value, collection, delimiter, trim, allowBlank);
		return collection != null ? (String[]) collection.toArray(new String[0]) : null;
	}

	/**
	 * Convert the value as a CSV type string to an array of individual strings.
	 * 
	 * @param value object
	 * @return array of strings
	 */
	public static String[] asArray(Object value) {
		return asArray(value, ",", true, false, false);
	}

	/**
	 * Convert an array of objects to an array of strings.
	 * 
	 * @param values array of objects
	 * @return array of strings
	 */
	public static String[] asArray(Object[] values) {
		String[] result = null;
		if (values != null) {
			result = new String[values.length];
			for (int i = 0; i < values.length; i++) {
				result[i] = asString(values[i]);
			}
		}
		return result;
	}

	/**
	 * Get the last n characters of a given object as a string. Useful for masking
	 * credit card and bank account numbers.
	 * 
	 * @param object the object to get last n chars of
	 * @param n      number of chars
	 * @return the string result
	 */
	public static String lastChars(Object object, int n) {
		String s = asString(object);
		int start = s.length() - n;
		return start > 0 ? s.substring(start) : s;
	}

	/**
	 * Validates that the string is the specified length. Otherwise it throws a
	 * validation exception with the appropriate message.
	 * 
	 * @param str           The string to check the length on.
	 * @param correctLength The length that the string should be.
	 * @param messageLabel  The label that should be used to for the number of
	 *                      characters in the string. For example "digits" or
	 *                      "characters".
	 * @param fieldLabel    The label for the field that is being validated.
	 * @return True if it is the correct length.
	 * @throws ValidationException If it is not the correct length
	 * @throws NullValueException  If the value is null or has a length of 0
	 */
	/*
	 * public static boolean isCorrectLength(String str, int correctLength, String
	 * messageLabel, String fieldLabel) throws ValidationException,
	 * NullValueException { boolean result = false;
	 * 
	 * if (str != null && str.length() == correctLength) { result = true; } else {
	 * Object[] args = new Object[3]; args[0] = new Integer(correctLength); args[1]
	 * = messageLabel; args[2] = fieldLabel; if (str == null || str.length() == 0) {
	 * NullValueException ae = new
	 * NullValueException(ErrorMessages.VALIDATION_WRONG_LENGTH, args);
	 * ae.setLogLevel(CommonLogger.INFO);
	 * CommonLogger.log(CommonLogger.COLLECTOR_LOGGER, StringHelper.class, ae);
	 * throw ae; } else { ValidationException ae = new
	 * ValidationException(ErrorMessages.VALIDATION_WRONG_LENGTH, args, str);
	 * ae.setLogLevel(CommonLogger.INFO);
	 * CommonLogger.log(CommonLogger.COLLECTOR_LOGGER, StringHelper.class, ae);
	 * throw ae; } } return result; }
	 */

	/**
	 * Validates that the string is the specified length. Otherwise it throws a
	 * validation exception with the appropriate message.
	 * 
	 * @param str          The string to check the length on.
	 * @param validLengths An array of the possible correct lengths.
	 * @param messageLabel The label that should be used to for the number of
	 *                     characters in the string. For example "digits" or
	 *                     "characters".
	 * @param fieldLabel   The label for the field that is being validated.
	 * @return True if it is the correct length.
	 * @throws ValidationException If it is not the correct length;
	 */
	/*
	 * public static boolean isCorrectLength(String str, int[] validLengths, String
	 * messageLabel, String fieldLabel) throws ValidationException { boolean result
	 * = false;
	 * 
	 * if (str != null && validLengths != null) { for (int i = 0; i <
	 * validLengths.length; i++) { if (str.length() == validLengths[i]) { result =
	 * true; break; } } } if (result == false) { String correctLengthLabel =
	 * formatArray(validLengths); Object[] args = new Object[3]; args[0] =
	 * correctLengthLabel; args[1] = messageLabel; args[2] = fieldLabel;
	 * ValidationException ae = new
	 * ValidationException(ErrorMessages.VALIDATION_WRONG_LENGTH, args, str);
	 * ae.setLogLevel(CommonLogger.INFO);
	 * CommonLogger.log(CommonLogger.COLLECTOR_LOGGER, StringHelper.class, ae);
	 * throw ae; } return result; }
	 */

	/**
	 * Validates that the string starts with one of the strings in the array.
	 * Otherwise it throws a validation exception with the appropriate message.
	 * 
	 * @param str          The string to validate
	 * @param startStrings An array of strings to check against.
	 * @return True if it starts with a string in the array.
	 * @throws ValidationException If it does not start with a character in the
	 *                             array.
	 */
	/*
	 * public static boolean startsWith(String str, String[] startStrings, String
	 * messageLabel) throws ValidationException { boolean result = false;
	 * 
	 * if (str != null && startStrings != null) { for (int i = 0; i <
	 * startStrings.length; i++) { if (str.startsWith(startStrings[i])) { result =
	 * true; break; } } } if (result == false) { String startStringLabel =
	 * formatArray(startStrings); ValidationException ae = new
	 * ValidationException(ErrorMessages.VALIDATION_STARTS_WITH, new Object[] {
	 * messageLabel, startStringLabel }, str); ae.setLogLevel(CommonLogger.INFO);
	 * CommonLogger.log(CommonLogger.COLLECTOR_LOGGER, StringHelper.class, ae);
	 * throw ae; }
	 * 
	 * return result; }
	 */

	/**
	 * Formats an array into a displayable string that can be used in a message box
	 * or label.
	 * 
	 * @param values The array to format.
	 * @return The string representation of the array.
	 */
	public static String formatArray(Object[] values) {
		StringBuilder result = new StringBuilder();
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				if (i != 0) {
					if (values.length > 2) {
						result.append(", ");
					} else {
						result.append(' ');
					}
					if (i == values.length - 1) {
						// TODO i18n
						result.append("or ");
					}
				}
				result.append(asString(values[i]));
			}
		}
		return result.toString();
	}

	/**
	 * Formats an array into a displayable string that can be used in a message box
	 * or label.
	 * 
	 * @param values The array to format.
	 * @return The string representation of the array.
	 */
	public static String formatArray(int[] values) {
		StringBuilder result = new StringBuilder();
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				if (i != 0) {
					if (values.length > 2) {
						result.append(", ");
					} else {
						result.append(' ');
					}
					if (i == values.length - 1) {
						// TODO i18n
						result.append("or ");
					}
				}
				result.append(values[i]);
			}
		}
		return result.toString();
	}

	/**
	 * Determines that the number is valid using the following checksum algorithm:
	 * 
	 * Starting with the second digit from the right, and proceeding to the left,
	 * double the value of every other digit.
	 * 
	 * Add the individual digits comprising each of the products obtained above to
	 * each of the unaffected digits in the original number.
	 * 
	 * Divide the sum obtained above by 10. If the result is an integer the number
	 * is valid.
	 * 
	 * For example:
	 * 
	 * Credit Card Number = 49927398716
	 * 
	 * (1x2)=2, (8x2)=16,(3x2)=6,(2x2)=4,(9x2)=18
	 * 
	 * 4+(1+8)+9+(4)+7+(6)+9+(1+6)+7+(2)+6 = 70
	 * 
	 * 70/10 = 7 The Credit Card Number is Valid
	 * 
	 * @param ccNumber The credit card number to validate.
	 * @return True if the number passes the check sum algorithm.
	 * @throws ValidationException If the number does not pass the algorithm.
	 */
	/*
	 * public static boolean passesCreditCardCheckSum(String ccNumber) throws
	 * ValidationException { boolean result = false; StringBuilder digitsToAdd = new
	 * StringBuilder();
	 * 
	 * if (ccNumber != null) { for (int i = ccNumber.length() - 1; i >= 0; i--) {
	 * char ch = ccNumber.charAt(i); if (Character.isDigit(ch)) { int mod =
	 * (ccNumber.length() - i) % 2; switch (mod) { case 0:
	 * digitsToAdd.append(Character.digit(ch, 10) * 2); break; case 1:
	 * digitsToAdd.append(ch); break; } } else { ValidationException ae = new
	 * ValidationException(ErrorMessages.VALIDATION_NON_NUMERIC, null, ccNumber);
	 * ae.setLogLevel(CommonLogger.INFO);
	 * CommonLogger.log(CommonLogger.COLLECTOR_LOGGER, StringHelper.class, ae);
	 * throw ae; } }
	 * 
	 * Add the individual digits comprising each of the products obtained above to
	 * each of the unaffected digits in the original number.
	 * 
	 * int checkSumTotal = 0; for (int i = 0; i < digitsToAdd.length(); i++) { char
	 * ch = digitsToAdd.charAt(i); checkSumTotal += Character.digit(ch, 10); }
	 * 
	 * Divide the sum obtained above by 10. If the result is an integer the number
	 * is valid.
	 * 
	 * if (checkSumTotal % 10 == 0) { result = true; } } if (result == false) {
	 * ValidationException ae = new
	 * ValidationException(ErrorMessages.VALIDATION_CREDIT_CARD_NUMBER, null,
	 * ccNumber); ae.setLogLevel(CommonLogger.INFO);
	 * CommonLogger.log(CommonLogger.COLLECTOR_LOGGER, StringHelper.class, ae);
	 * throw ae; }
	 * 
	 * return result; }
	 */

	/**
	 * Check to see if a 5 or 9 digit zip code. If so strip any non-digit or space
	 * and save. Otherwise just trim spaces and save.
	 * 
	 * @param postalCode
	 * @return normalized postal code.
	 */
	/*
	 * public static String formatPostalCode(String postalCode) { return
	 * format(postalCode, new AjPostalCodeFormat()); }
	 */

	/**
	 * Check the postal code, test for US, Mexican, or Canadian zip codes. If they
	 * are in that format then normalize, otherwise just trim spaces and save. US: 5
	 * or 9 digits Mexican: 5 digits Canidian: pattern: A9A 9A9 where A is a-z and 9
	 * is 0-9. Items should be upper cased.
	 * 
	 * If an empty value is passed in, set it to null.
	 * 
	 * @param postalCode
	 * @return normalized postal code.
	 */
	public static String removePostalCodeFormating(String postalCode) {
		if (postalCode == null) {
			return null;
		}
		// Trim leading, trailing and redundent spaces.
		String testZip = removeRedundantWhiteSpace(postalCode);
		String zipPattern5 = "\\d\\d\\d\\d\\d";
		String zipPattern9 = "\\d\\d\\d\\d\\d\\d\\d\\d\\d";
		String zipPattern5dash4 = "\\d\\d\\d\\d\\d-\\d\\d\\d\\d";
		// US & Mexico zip patterns 5(#) or 9(#) or 5(#)-4(#)
		String zipUSMexPattern = "(" + zipPattern5 + ")|(" + zipPattern9 + ")|(" + zipPattern5dash4 + ")";
		// Canadian Pattern A9A 9A9
		String zipCanPattern = "\\p{Alpha}\\d\\p{Alpha} \\d\\p{Alpha}\\d";

		if (testZip.matches(zipUSMexPattern)) {
			return removeNonNumerics(testZip);
		} else if (testZip.matches(zipCanPattern)) {
			return testZip.toUpperCase();
		} else {
			return asNull(testZip);
		}

		// String returnZip = null;
		// String nonNumeric = removeNonNumerics(postalCode);
		// if (nonNumeric.length() == 5 || nonNumeric.length() == 9) {
		// returnZip = nonNumeric;
		// } else {
		// returnZip = postalCode;
		// }
		// return asNull(returnZip);
	}

	/**
	 * Formats Elapsed Time.
	 * 
	 * @param elapsedTimeMillis elapsed time amount to format
	 * @return formatted string
	 */
	public static String formatEllapsedTime(Long elapsedTimeMillis) {
		long elapsedTimeDay = elapsedTimeMillis.longValue() / (24 * 60 * 60 * 1000);

		long elapsedTimeHour = (elapsedTimeMillis.longValue() / (60 * 60 * 1000)) % 24;

		long elapsedTimeMin = (elapsedTimeMillis.longValue() / (60 * 1000)) % 60;

		// long elapsedTimeSec = (elapsedTimeMillis.longValue() / 1000) % 60;

		return String.format("%d days %d hrs %d mins", new Long(elapsedTimeDay), new Long(elapsedTimeHour),
				new Long(elapsedTimeMin));
	}

	/**
	 * Formats elapsed time in mins and secs.
	 * 
	 * @param elapsedTimeMillis elapsed time amount to format
	 * @return formatted string
	 */
	public static String formatEllapsedTimeMS(Long elapsedTimeMillis) {
		// long elapsedTimeDay = elapsedTimeMillis.longValue() / (24 * 60 * 60 * 1000);

		// long elapsedTimeHour = (elapsedTimeMillis.longValue() / (60 * 60 * 1000)) %
		// 24;

		long elapsedTimeMin = (elapsedTimeMillis.longValue() / (60 * 1000)) % 60;

		long elapsedTimeSec = (elapsedTimeMillis.longValue() / 1000) % 60;

		return String.format("%d mins %d secs", new Long(elapsedTimeMin), new Long(elapsedTimeSec));
	}

	/**
	 * This method translates characters in the <code>fromString</code> found in the
	 * <code>sourceString</code> to the corresponding character (by position) in the
	 * <code>toChars</code> string. In other words, if the source string is
	 * <code>This is a test string</code> and the from string is <code>tih</code>
	 * and the to string is <code>xyz</code>, the resulting string is
	 * <code>Tzys ys a xest stryng</code>.
	 * 
	 * @param sourceString The source string to be translated
	 * @param fromChars    The string of characters to search for in the source
	 *                     string
	 * @param toChars      The string of characters to be used to replace any
	 *                     matching character from the from string. The fromChars
	 *                     and toChars strings should be the same length, because
	 *                     there is a position correspondence between a character
	 *                     found in the <code>fromChars</code> string to the
	 *                     character used in the <code>toChars</code> string. If the
	 *                     toChars string is shorter, then the toChars string wraps
	 *                     around on a modulo arithmetic for the position in the
	 *                     from string. If the toChars string is longer, then the
	 *                     excess is ignored. If toChars is null or zero length,
	 *                     then the characters found in the source string that match
	 *                     the from string are removed.
	 * @return The translated string, null if source was null, an empty string if
	 *         source was empty, or the original string if the fromChars string is
	 *         null or empty.
	 */
	public static String translate(String sourceString, String fromChars, String toChars) {
		if (sourceString == null || sourceString.length() == 0 || fromChars == null || fromChars.length() == 0) {
			return sourceString;
		}

		char[] from = fromChars.toCharArray();
		char[] to = new char[] {};
		if (toChars != null && toChars.length() > 0) {
			to = toChars.toCharArray();
		}

		StringBuilder sb = new StringBuilder(sourceString);
		for (int i = 0; i < sb.length(); i++) {
			char c = sb.charAt(i);
			for (int j = 0; j < from.length; j++) {
				if (from[j] == c) {
					if (to.length == 0) {
						sb.deleteCharAt(i);
						i--;
					} else {
						if (j > to.length) {
							sb.setCharAt(i, to[j % to.length]);
						} else {
							sb.setCharAt(i, to[j]);
						}
					}
				}
			}
		}

		return sb.toString();
	}

	/**
	 * Checks string and makes sure that it is not to long. If it is, it will
	 * truncate the string.
	 * 
	 * @param field   - String to check the length on.
	 * @param maxSize - The maximum size of the field.
	 * @return - String, truncated if it is longer than the max size.
	 */
	public static String sizeString(String field, int maxSize) {
		if (field == null) {
			return null;
		} else if (field.length() <= maxSize) {
			return field;
		} else { // Field is > size
			return field.substring(0, maxSize);
		}
	}

	/**
	 * Replace all occurances of char1 with char2 in a string buffer.
	 * 
	 * @param sb    string buffer
	 * @param char1 character to replace
	 * @param char2 replacement character
	 * @return string buffer
	 */
	public static StringBuffer replace(StringBuffer sb, char char1, char char2) {
		for (int i = 0; i < sb.length(); i++) {
			if (char1 == sb.charAt(i)) {
				sb.setCharAt(i, char2);
			}
		}
		return sb;
	}

	/**
	 * Replace all occurances of char1 with char2 in a string builder.
	 * 
	 * @param sb    string builder
	 * @param char1 character to replace
	 * @param char2 replacement character
	 * @return string builder
	 */
	public static StringBuilder replace(StringBuilder sb, char char1, char char2) {
		for (int i = 0; i < sb.length(); i++) {
			if (char1 == sb.charAt(i)) {
				sb.setCharAt(i, char2);
			}
		}
		return sb;
	}

	/**
	 * Determines if key is present in map of values.
	 * 
	 * @param key        map key
	 * @param ignoreCase ignore key case
	 * @param values     values
	 * @return true if key is present, false otherwise
	 */
	public static boolean containsKey(Object key, boolean ignoreCase, Map values) {
		if (values != null) {
			if (ignoreCase) {
				for (Map.Entry entry : (Set<Map.Entry>) values.entrySet()) {
					if (isEqualIgnoreCase(key, entry.getKey())) {
						return true;
					}
				}
			} else {
				return values.containsKey(key);
			}
		}
		return false;
	}

	/**
	 * Get value from map of values.
	 * 
	 * @param key        map key
	 * @param ignoreCase ignore key case
	 * @param values     values
	 * @return value
	 */
	public static Object getValue(Object key, boolean ignoreCase, Map values) {
		if (values != null) {
			if (ignoreCase) {
				for (Map.Entry entry : (Set<Map.Entry>) values.entrySet()) {
					if (isEqualIgnoreCase(key, entry.getKey())) {
						return entry.getValue();
					}
				}
			} else {
				return values.get(key);
			}
		}
		return null;
	}

	/**
	 * Gets all references to parameter type variables ("${var}" format).
	 * 
	 * @param obj object as string that contains variables
	 * @return variable names
	 */
	public static Collection<String> getVars(Object obj) {

		Collection<String> vars = new ArrayList();
		String s = asString(obj);
		int i = s.indexOf(PARAM_PREFIX);
		while (i >= 0) {
			int j = s.indexOf(PARAM_SUFFIX, i + PARAM_PREFIX.length());
			if (j < 0) {
				break;
			}

			vars.add(s.substring(i + PARAM_PREFIX.length(), j));

			i = s.indexOf(PARAM_PREFIX, j + PARAM_SUFFIX.length());
		}
		return vars;
	}

	/**
	 * Replace all references to parameter type variables ("${var}" format) with
	 * their values.
	 * 
	 * @param obj        object as string to be substituted
	 * @param ignoreCase ignore variable name case when looking up value
	 * @param values     values map, map key is variable name, map value is variable
	 *                   value
	 * @return string with variables substituted
	 */
	public static String substVars(Object obj, boolean ignoreCase, Map<String, String> values) {

		String s = asString(obj);
		int i = s.indexOf(PARAM_PREFIX);
		while (i >= 0) {
			int j = s.indexOf(PARAM_SUFFIX, i + PARAM_PREFIX.length());
			if (j < 0) {
				break;
			}

			String name = s.substring(i + PARAM_PREFIX.length(), j);
			int inc = PARAM_PREFIX.length();
			if (containsKey(name, ignoreCase, values)) {
				// perform the actual substitution
				s = s.substring(0, i) + asString(getValue(name, ignoreCase, values))
						+ s.substring(j + PARAM_SUFFIX.length());
				inc = 0;
			}

			i = s.indexOf(PARAM_PREFIX, i + inc);
		}
		return s;
	}

	/**
	 * Replace all references to delimited variables with their values.
	 * 
	 * @param obj        object as string to be substituted
	 * @param delim      delimiter character for variables in source string
	 * @param ignoreCase ignore variable name case when looking up value
	 * @param values     values map, map key is variable name, map value is variable
	 *                   value
	 * @return string with variables substituted
	 */
	public static String substVars(Object obj, char delim, boolean ignoreCase, Map<String, String> values) {

		char chars[] = substVars(obj, ignoreCase, values).toCharArray();
		StringBuilder sb = new StringBuilder();
		StringBuilder var = new StringBuilder();
		boolean inVar = false;
		boolean inEsc = false;
		for (int i = 0; i < chars.length; i++) {
			char ch = chars[i];
			if (ch == delim) {
				if (inEsc) {
					// escaped delimiter, replace with single delimiter
					sb.append(delim);
					inVar = false;
					inEsc = false;
				} else if (inVar) {
					// end of variable reference
					String name = var.toString();
					if (containsKey(name, ignoreCase, values)) {
						// perform the actual substitution
						sb.append(asString(getValue(name, ignoreCase, values)));
					} else {
						// variable not found, keep original variable reference
						sb.append(delim);
						sb.append(var);
						sb.append(delim);
					}

					inVar = false;
					inEsc = false;
				} else {
					// possible start of variable reference or escaped delimiter
					inVar = true;
					inEsc = true;
				}
				var.setLength(0);
			} else {
				if (inVar) {
					var.append(ch);
				} else {
					sb.append(ch);
				}
				inEsc = false;
			}
		}
		if (inVar) {
			// missing trailing delimiter
			sb.append(delim);
			sb.append(var);
		}
		return sb.toString();
	}

	/**
	 * Replace all references to environment variables with their values.
	 * 
	 * @param obj object as string to be substituted
	 * @return string with environment variables substituted
	 */
	public static String substEnvVars(Object obj) {

		return substVars(obj, '%', true, System.getenv());
	}

	/**
	 * Replace all references to system properties with their values.
	 * 
	 * @param obj object as string to be substituted
	 * @return string with system properties substituted
	 */
	public static String substSysPropVars(Object obj) {

		return substVars(obj, '%', false, (Map) System.getProperties());
	}

	/**
	 * Replace all references to all system and environment variables with their
	 * values.
	 * 
	 * @param obj object as string to be substituted
	 * @return string with all variables substituted
	 */
	public static String substAllSystemVars(Object obj) {

		// system properties take precedence over environment variables (see also
		// HomeHelper.getHomeURI())
		return substEnvVars(substSysPropVars(obj));
	}

	/**
	 * Replace all references to all system and environment and to parameter type
	 * variables ("${var}" format) with their values.
	 * 
	 * @param obj        object as string to be substituted
	 * @param ignoreCase ignore variable name case when looking up value
	 * @param values     values map, map key is variable name, map value is variable
	 *                   value
	 * @return string with variables substituted
	 */
	public static String substAllVars(Object obj, boolean ignoreCase, Map<String, String> values) {

		return substAllSystemVars(substVars(obj, ignoreCase, values));
	}

	/**
	 * Replace all references to all system and environment and delimited variables
	 * with their values.
	 * 
	 * @param obj        object as string to be substituted
	 * @param delim      delimiter character for variables in source string
	 * @param ignoreCase ignore variable name case when looking up value
	 * @param values     values map, map key is variable name, map value is variable
	 *                   value
	 * @return string with variables substituted
	 */
	public static String substAllVars(Object obj, char delim, boolean ignoreCase, Map<String, String> values) {

		return substAllSystemVars(substVars(obj, delim, ignoreCase, values));
	}

	/**
	 * This method implements the non-printable character scrubbing process as
	 * defined in the Data Normalization Business Policy. According to that
	 * document:
	 * <p>
	 * In the case of deterministic elements, no control characters, unprintable
	 * characters, or invalid code points are allowed at all. For non-deterministic
	 * data elements (for example, notes), the new line (\n) control character will
	 * be allowed. New line processing is somewhat special, and is documented
	 * separately.
	 * <p>
	 * In Ajility, examples of deterministic elements are firstName, lastName,
	 * street1. Examples of non-deterministic elements are notes and alerts. For the
	 * definition of deterministic elements, see the Data Normalization Business
	 * Policy.
	 * 
	 * @param text The string to remove nonPrintableCharactersFrom
	 * @return The <code>text</code> parameter with nonPrintable characters
	 *         scrubbed.
	 */
	public static String nonPrintableCharacterScrubbing(String text, boolean isDeterministic) {
		if (text == null) {
			return null;
		}

		Pattern p = null;
		if (isDeterministic) {
			p = Pattern.compile("\\p{Cntrl}");
		} else {
			p = Pattern.compile("[\\p{Cntrl}&&[^\\n\\r]]");
		}

		Matcher m = p.matcher(text);
		if (m.find()) {
			text = m.replaceAll("");
		}
		// Entry of carriage return and/or line feed control characters is replaced with
		// a single line feed character
		// for fields that allow multiple lines.
		if (!isDeterministic) {
			p = Pattern.compile("\\r\\n");
			m = p.matcher(text);
			if (m.find()) {
				text = m.replaceAll("\\\n");
			}

			p = Pattern.compile("\\r");
			m = p.matcher(text);
			if (m.find()) {
				text = m.replaceAll("\\\n");
			}

		}

		return text;
	}

	/**
	 * Converts a windows path with "\" backslash characters and converts it to java
	 * friendly "/" forward slashes. Meant to be used if you have an absolute path,
	 * not a UNC path
	 * 
	 * @param aPath absolute path like c:\program files\xxx\xx
	 * @return a String path converted to forward slashes
	 */
	public static String NormalizeWindowsPath(String aPath) {
		aPath = aPath.replace("\\", "/");
		return aPath;
	}

	/**
	 * Upper case a string (handles null)
	 * 
	 * @param obj object as string to be upper cased
	 * @return object as stirng upper cased
	 */
	public static String toUpperCase(Object object) {
		return object != null ? asString(object).toUpperCase() : null;
	}

	/**
	 * Lower case a string (handles null)
	 * 
	 * @param obj object as string to be lower cased
	 * @return object as stirng lower cased
	 */
	public static String toLowerCase(Object object) {
		return object != null ? asString(object).toLowerCase() : null;
	}

	/**
	 * Checks if the object as a String contains any of the contained objects as a
	 * strings.
	 * 
	 * @param object    the object to check for contained
	 * @param contained the set of objects to check
	 * @return
	 */
	public static boolean containsAny(Object object, Collection contained) {
		if (!isBlank(object) && contained != null) {
			String str = asString(object);
			for (Object contain : contained) {
				if (str.contains(asString(contain))) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Checks if the object as a String contains all of the contained objects as a
	 * strings.
	 * 
	 * @param object    the object to check for contained
	 * @param contained the set of objects to check
	 * @return
	 */
	public static boolean containsAll(Object object, Collection contained) {
		if (!isBlank(object) && contained != null) {
			String str = asString(object);
			for (Object contain : contained) {
				if (!str.contains(asString(contain))) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * Take in the byte size of a file and output a human friendly String format.
	 * The smallest value displayed is 1KB.
	 * 
	 * @param sizeInBytes Object which should be some form or number (Integer, Long,
	 *                    Double, ...)
	 * @return String of readable file size in KB, MG, GB, and TB
	 */
	public static String getReadableFileSize(Object sizeInBytes) {
		String fileSize = "";
		if (sizeInBytes != null) {
			Long tempSize = (((Number) sizeInBytes).longValue());
			double k = tempSize / 1024.0;
			double m = tempSize / 1048576.0;
			double g = tempSize / 1073741824.0;
			double t = tempSize / 1099511627776.0;

			DecimalFormat dec = new DecimalFormat("0");

			if (t > 1) {
				fileSize = dec.format(t).concat(" TB");
			} else if (g > 1) {
				fileSize = dec.format(g).concat(" GB");
			} else if (m > 1) {
				fileSize = dec.format(m).concat(" MB");
			} else if (k > 1) {
				fileSize = dec.format(k).concat(" KB");
			} else {
				fileSize = "1 KB";
			}
		}

		return fileSize;
	}

}

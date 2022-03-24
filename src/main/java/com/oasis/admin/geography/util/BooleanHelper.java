package com.oasis.admin.geography.util;


public final class BooleanHelper {
	 /**
     * This is a private constructor to prevent this class from being instantiated.
     */
    private BooleanHelper() {}

    /**
     * Invert boolean if not null
     * 
     * @param b Boolean to invert
     * @return inverted Boolean, null if b is null
     */
    public static Boolean invert(Boolean b) {
        if (b == null) {
            return null;
        } else if (b.booleanValue()) {
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }

    /**
     * Invert boolean if not null
     * 
     * @param b Boolean to invert
     * @return inverted Boolean, null if b is null
     */
    public static Boolean not(Boolean b) {
        return invert(b);
    }

    /**
     * Pessimistically determines of a Boolean is true (i.e. a null is not true). Note: to optimistically determine if a
     * Boolean is true use !isFalse().
     * 
     * @param b Boolean
     * @return true if b is not null and is true, false otherwise
     */
    public static boolean isTrue(Boolean b) {
        return b != null ? b.booleanValue() : false;
    }

    /**
     * Pessimistically determines of a Boolean is false (i.e. a null is not false). Note: to optimistically determine if
     * a Boolean is false use !isTrue().
     * 
     * This method is not really deprecated because it has a legitimate use (typically when a null value is assumed to
     * default to true). It is only marked as deprecated to prevent accidental use.
     * 
     * @deprecated BE CAREFULL WHEN YOU USE THIS METHOD, PERHAPS YOU WANT !isTrue() (ESPECIALLY IF YOU ARE DEALING WITH
     *             A PERSISTED DB VALUE)
     * 
     * @param b Boolean
     * @return true if b is not null and is false, false otherwise
     */
    public static boolean isFalse(Boolean b) {
        return b != null ? !b.booleanValue() : false;
    }

    /**
     * returns "Yes" or "No" based on the boolean value
     * 
     * @param b boolean
     * @return string
     */
    public static String yesNo(Boolean b) {
        return StringHelper.formatBoolean(new Boolean(isTrue(b)), "Yes/No");
    }

    /**
     * Returns "Yes" or "No" based on the boolen value for a boolean primitive.
     * 
     * @param b - boolean value to check.
     * @return String Yes or No
     */
    public static String yesNo(boolean b) {
        return yesNo(new Boolean(b));
    }

    /**
     * Performs an And operation on two booleans.
     * 
     * @param b1
     * @param b2
     * @return - true if b1 && b2 is true, false if not.
     */
    public static boolean and(Boolean b1, Boolean b2) {
        return isTrue(b1) && isTrue(b2);
    }

    /**
     * Performs an And operation on two booleans.
     * 
     * @param b1
     * @param b2
     * @return - true if b1 && b2 is true, false if not.
     */
    public static boolean and(boolean b1, Boolean b2) {
        return b1 && isTrue(b2);
    }

    /**
     * Performs an And operation on two booleans.
     * 
     * @param b1
     * @param b2
     * @return - true if b1 && b2 is true, false if not.
     */
    public static boolean and(Boolean b1, boolean b2) {
        return isTrue(b1) && b2;
    }

    /**
     * Performs an And operation on two booleans.
     * 
     * @param b1
     * @param b2
     * @return - true if b1 && b2 is true, false if not.
     */
    public static boolean and(boolean b1, boolean b2) {
        return b1 && b2;
    }

    /**
     * Performs an Or operation on two booleans.
     * 
     * @param b1
     * @param b2
     * @return - true if b1 || b2 is true, false if not.
     */
    public static boolean or(Boolean b1, Boolean b2) {
        return isTrue(b1) || isTrue(b2);
    }

    /**
     * Performs an Or operation on two booleans.
     * 
     * @param b1
     * @param b2
     * @return - true if b1 || b2 is true, false if not.
     */
    public static boolean or(boolean b1, Boolean b2) {
        return b1 || isTrue(b2);
    }

    /**
     * Performs an Or operation on two booleans.
     * 
     * @param b1
     * @param b2
     * @return - true if b1 || b2 is true, false if not.
     */
    public static boolean or(Boolean b1, boolean b2) {
        return isTrue(b1) || b2;
    }

    /**
     * Performs an Or operation on two booleans.
     * 
     * @param b1
     * @param b2
     * @return - true if b1 || b2 is true, false if not.
     */
    public static boolean or(boolean b1, boolean b2) {
        return b1 || b2;
    }

    /**
     * compares two values to see if they are equal, supports null for either value
     * 
     * @param val1 value to be compared with second value
     * @param val2 value by be compared with first value
     * @return result of equals
     */
    public static boolean eq(Boolean val1, Boolean val2) {
        return isTrue(val1) == isTrue(val2);
    }

    /**
     * compares two values to see if they are equal, supports null for first value
     * 
     * @param val1 value to be compared with second value
     * @param val2 value by be compared with first value
     * @return result of equals
     */
    public static boolean eq(Boolean val1, boolean val2) {
        return isTrue(val1) == val2;
    }

    /**
     * compares two values to see if they are equal, supports null for second value
     * 
     * @param val1 value to be compared with second value
     * @param val2 value by be compared with first value
     * @return result of equals
     */
    public static boolean eq(boolean val1, Boolean val2) {
        return val1 == isTrue(val2);
    }

    /**
     * compares two values to see if they are equal
     * 
     * @param val1 value to be compared with second value
     * @param val2 value by be compared with first value
     * @return result of equals
     */
    public static boolean eq(boolean val1, boolean val2) {
        return val1 == val2;
    }

    /**
     * Value to a Boolean.
     * 
     * @param val value
     * @return boolean value
     */
    public static Boolean asBoolean(Object val) {
        if (val instanceof Boolean) {
            return (Boolean) val;
        } else if (StringHelper.isEqualIgnoreCase(val, "T") || StringHelper.isEqualIgnoreCase(val, "Y")
            || StringHelper.isEqualIgnoreCase(val, "YES") || StringHelper.isEqual(val, "1")) {
            return Boolean.TRUE;
        } else if (StringHelper.isEqualIgnoreCase(val, "F") || StringHelper.isEqualIgnoreCase(val, "N")
            || StringHelper.isEqualIgnoreCase(val, "NO") || StringHelper.isEqual(val, "0")) {
            return Boolean.FALSE;
        } else {
            return StringHelper.isBlank(val) ? Boolean.FALSE : new Boolean(StringHelper.asString(val));
        }
    }

    /**
     * Boolean to an Integer.
     * 
     * @param val value
     * @return integer value
     */
    public static Integer asInteger(Boolean val) {
        return isTrue(val) ? new Integer(1) : new Integer(0);
    }

    /**
     * Compares two objects as booleans (supports null either value, treated as false)
     * 
     * @param val1 value to be compared with second value
     * @param val2 value by be compared with first value
     * @return result of compare
     */
    public static int compare(Object val1, Object val2) {
        return asBoolean(val1).compareTo(asBoolean(val2));
    }
}

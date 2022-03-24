package com.oasis.admin.geography.util;

import java.io.Serializable;
import java.util.Comparator;


public abstract class TypeObject implements Serializable, Comparable{
	 /**
     * "field name" for getTypeByOrd() accessor on subclasses (used for reflection)
     */
    public static final String TYPE_BY_ORD = "typeByOrd";

    /**
     * Compares types by their labels
     */
    public static class TypeLabelComparator implements Comparator {

        /**
         * default constructor
         */
        public TypeLabelComparator() {}

        /**
         * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
         */
        public int compare(Object o1, Object o2) {
            return StringHelper.compareIgnoreCase(o1, o2);
        }
    }

    /**
     * Compares types by their order
     */
    public static class TypeOrdComparator implements Comparator {

        /**
         * default constructor
         */
        public TypeOrdComparator() {}

        /**
         * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
         */
        public int compare(Object o1, Object o2) {
            return ((TypeObject) o1).compareTo(o2);
        }
    }

    /**
     * Database specific value of the type.
     */
    private int ord;

    /**
     * Name of the type
     */
    private String label;

    /**
     * Default constructor
     */
    protected TypeObject() {}

    /**
     * Constructs DataType object
     * 
     * @param id value of the type object to be stored in database
     * @param name name of the data type, ex. boolean, integer...
     */
    protected TypeObject(int id, String name) {
        ord = id;
        label = name;
    }

    /**
     * Constructor
     * 
     * @param id type id
     */
    protected TypeObject(int id) {
        this(id, null);
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TypeObject)) {
            return false;
        } else if (o == this) {
            return true;
        }
        return ord == ((TypeObject) o).ord;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        String s = getDisplayName();
        if (!StringHelper.isBlank(s)) {
            return s;
        } else if (getId() != null) {
            return StringHelper.asString(getId());
        } else {
            return super.toString();
        }
    }

    /**
     * Returns type ord
     * 
     * @return type ord
     */
    public int ord() {
        return ord;
    }

    /**
     * Returns type name
     * 
     * @return type name
     */
    public String label() {
        return label;
    }

    protected void setLabel(String label) {
        this.label = label;
    }

    /**
     * Returns ord of type Integer for DB
     * 
     * @return ID of the type
     */
    public Integer getId() {
        return new Integer(ord);
    }

    /**
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(Object o) {
        return ord - ((TypeObject) o).ord;
    }

    /**
     * @see com.cu.ajent.types.Displayable#getDisplayableId()
     */
    public Serializable getDisplayableId() {
        return getId();
    }

    /**
     * @see com.cu.ajent.types.Displayable#getDisplayName()
     */
    public String getDisplayName() {
        return label();
    }

    /**
     * Determine if two type objects are equals, supports null for either object (or null for both).
     * 
     * @param o1 type object 1
     * @param o2 type object 2
     * @return true if o1 is equal to o2, false otherwise
     */
    public static boolean isEqual(TypeObject o1, TypeObject o2) {
        if (o1 == null && o2 == null) {
            return true;
        } else if (o1 != null) {
            return o1.equals(o2);
        }
        return false;
    }
}

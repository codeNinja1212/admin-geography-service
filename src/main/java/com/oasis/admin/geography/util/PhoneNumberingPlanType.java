package com.oasis.admin.geography.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class PhoneNumberingPlanType {


    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
   	@GeneratedValue(strategy = GenerationType.AUTO)
       private int phoneNumberingPlanTypeId;
    
    /**
     * Collection of all registered types.
     */
    private static Map allTypes = new HashMap();

    // Map used to look up type by importExportValue
    private static Map importExportValueMap = new HashMap();

    public static final int AFRICA_ID = 1;

    public static final int EAST_ASIA_ID = 2;

    public static final int EUROPE_3_ID = 3;

    public static final int EUROPE_4_ID = 4;

    public static final int NORTH_AMERICA_ID = 5;

    public static final int RUSSIA_ID = 6;

    public static final int SOUTH_ASIA_MIDDLE_EAST_ID = 7;

    public static final int SOUTH_LATIN_AMERICA_ID = 8;

    public static final int SOUTH_PACIFIC_ID = 9;

    public static final PhoneNumberingPlanType AFRICA = new PhoneNumberingPlanType(AFRICA_ID, "Africa", "AFRICA");

    public static final PhoneNumberingPlanType EAST_ASIA = new PhoneNumberingPlanType(EAST_ASIA_ID, "East Asia",
        "EAST_ASIA");

    public static final PhoneNumberingPlanType EUROPE_3 = new PhoneNumberingPlanType(EUROPE_3_ID, "Europe 3",
        "EUROPE_3");

    public static final PhoneNumberingPlanType EUROPE_4 = new PhoneNumberingPlanType(EUROPE_4_ID, "Europe 4",
        "EUROPE_4");

    public static final PhoneNumberingPlanType NORTH_AMERICA = new PhoneNumberingPlanType(NORTH_AMERICA_ID,
        "North America", "NORTH_AMERICA");

    public static final PhoneNumberingPlanType RUSSIA = new PhoneNumberingPlanType(RUSSIA_ID, "Russia", "RUSSIA");

    public static final PhoneNumberingPlanType SOUTH_ASIA_MIDDLE_EAST = new PhoneNumberingPlanType(
        SOUTH_ASIA_MIDDLE_EAST_ID, "South Asia / Middle East", "SOUTH_ASIA_MIDDLE_EAST");

    public static final PhoneNumberingPlanType SOUTH_LATIN_AMERICA = new PhoneNumberingPlanType(SOUTH_LATIN_AMERICA_ID,
        "South Latin America", "SOUTH_LATIN_AMERICA");

    public static final PhoneNumberingPlanType SOUTH_PACIFIC = new PhoneNumberingPlanType(SOUTH_PACIFIC_ID,
        "South Pacific", "SOUTH_PACIFIC");

    /**
     * Default constructor is provided so that class can be instantiated via reflection. i.e. Class.newInstance();
     */
    public PhoneNumberingPlanType() {}

    /**
     * Constructs Type object
     * 
     * @param id value of the type object to be stored in database
     * @param name The name or label for the type. This will be displayed in the UI.
     * @param anImportExportValue The import/export value that is associated with this type.
     */
    protected PhoneNumberingPlanType(int id, String name, String anImportExportValue) {
        
        allTypes.put(new Integer(id), this);
        importExportValueMap.put(anImportExportValue, this);
    }

    /**
     * Returns the collection of all types. The returned collection is a copy of the actual collection where the type
     * objects are stored. Modifing it won't make an effect on the collection of registered types.
     * 
     * @return Collection of type objects
     */
    public static Collection getAllTypes() {
        return getAllTypes(false);
    }

    /**
     * Returns a collection of all available types sorted by type label.
     * 
     * @param sortedByLabel if true the returned collection of types is sorted by type label, otherwise it is sorted by
     *            order.
     * @return Collection of all available types.
     */
    public static Collection getAllTypes(Boolean sortedByLabel) {
        return getAllTypes(BooleanHelper.isTrue(sortedByLabel));
    }

    /**
     * Returns a collection of all available types sorted by type label.
     * 
     * @param sortedByLabel if true the returned collection of types is sorted by type label, otherwise it is sorted by
     *            order.
     * @return Collection of all available types.
     */
    public static Collection getAllTypes(boolean sortedByLabel) {
        ArrayList list = new ArrayList(allTypes.values());
        Comparator comparator = null;
        if (sortedByLabel) {
            comparator = new TypeObject.TypeLabelComparator();
        } else {
            comparator = new TypeObject.TypeOrdComparator();
        }
        Collections.sort(list, comparator);
        return list;
    }

    /**
     * Returns type object by ord (id)
     * 
     * @param ord type id
     * @return type object
     */
    public static PhoneNumberingPlanType getTypeByOrd(int ord) {
        return getTypeByOrd(new Integer(ord));
    }

    /**
     * Returns type object by ord (id)
     * 
     * @param ord type id
     * @return type object
     */
    public static PhoneNumberingPlanType getTypeByOrd(Integer ord) {
        return (PhoneNumberingPlanType) allTypes.get(ord);
    }

    /**
     * Returns type object by importExportValue
     * 
     * @param importExportValue import export value
     * @return type object
     */
    public static PhoneNumberingPlanType getTypeByValue(String importExportValue) {
        return (PhoneNumberingPlanType) importExportValueMap.get(importExportValue);
    }

}

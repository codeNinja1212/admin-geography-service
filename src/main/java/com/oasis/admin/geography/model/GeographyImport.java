package com.oasis.admin.geography.model;

import java.util.Date;

/*import com.cu.ajent.types.UIFImportFailReasonType;
import com.cu.ajent.types.UIFImportStatusType;
import com.cu.ajent.util.xml.UOWDocumentObject;
*/

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class GeographyImport {
	
	public static final String RUN_GEO_JOB_CLASS = "com.cu.ajent.business.timer.job.RunGeographyImportJob";

    public static final String GEOGRAPHY_IMPORT_ID = "geographyImportId";

    @Id
	@NonNull
    private Integer geographyImportId;

    public static final String PROCESS_INITIATED_BY = "processInitiatedBy";

    private String processInitiatedBy;

    public static final String PROCESS_START_DATE_TIME = "processStartDateTime";

    private Date processStartDateTime;

    public static final String PROCESS_END_DATE_TIME = "processEndDateTime";

    private Date processEndDateTime;

    public static final String DOC_NAME = "docName";

    private String docName;

    public static final String LOG_NAME = "logName";

    private String logName;

    public static final String IMPORT_STATUS_ID = "importStatusId";

    //private UIFImportStatusType importStatus;
    
    private String importStatus;

    public static final String IMPORT_STATUS_DATE_TIME = "importStatusDateTime";

    private Date importStatusDateTime;

    public static final String FAILURE_REASON_ID = "failureReasonId";

    //private UIFImportFailReasonType failureReason;
    
    private String failureReason;

    public static final String JOB_MONITOR_ID = "jobMonitorId";

    private Integer jobMonitorId;

    // begin header fields

    public static final String PRODUCT_NAME = "productName";

    private String productName;

    public static final String PRODUCT_VERSION = "productVersion";

    private String productVersion;

    public static final String METADATA_VERSION = "metadataVersion";

    private String metadataVersion;

    public static final String DESCRIPTION = "description";

    private String description;

    public static final String DATE_TIME_GENERATED = "dateTimeGenerated";

    private Date dateTimeGenerated;

    // end header fields

    // begin statistics

    public static final String NUMBER_COUNTRIES_REMOVED = "numberCountriesRemoved";

    private Integer numberCountriesRemoved;

    public static final String NUMBER_COUNTRIES_UPDATED = "numberCountriesUpdated";

    private Integer numberCountriesUpdated;

    public static final String NUMBER_COUNTRIES_ADDED = "numberCountriesAdded";

    private Integer numberCountriesAdded;

    public static final String NUMBER_STATES_REMOVED = "numberStatesRemoved";

    private Integer numberStatesRemoved;

    public static final String NUMBER_STATES_UPDATED = "numberStatesUpdated";

    private Integer numberStatesUpdated;

    public static final String NUMBER_STATES_ADDED = "numberStatesAdded";

    private Integer numberStatesAdded;

    public static final String NUMBER_COUNTIES_REMOVED = "numberCountiesRemoved";

    private Integer numberCountiesRemoved;

    public static final String NUMBER_COUNTIES_UPDATED = "numberCountiesUpdated";

    private Integer numberCountiesUpdated;

    public static final String NUMBER_COUNTIES_ADDED = "numberCountiesAdded";

    private Integer numberCountiesAdded;

    public static final String NUMBER_CITIES_REMOVED = "numberCitiesRemoved";

    private Integer numberCitiesRemoved;

    public static final String NUMBER_CITIES_UPDATED = "numberCitiesUpdated";

    private Integer numberCitiesUpdated;

    public static final String NUMBER_CITIES_ADDED = "numberCitiesAdded";

    private Integer numberCitiesAdded;

    public static final String NUMBER_POSTAL_CODES_REMOVED = "numberPostalCodesRemoved";

    private Integer numberPostalCodesRemoved;

    public static final String NUMBER_POSTAL_CODES_UPDATED = "numberPostalCodesUpdated";

    private Integer numberPostalCodesUpdated;

    public static final String NUMBER_POSTAL_CODES_ADDED = "numberPostalCodesAdded";

    private Integer numberPostalCodesAdded;

    public static final String NUMBER_AREA_CODES_REMOVED = "numberAreaCodesRemoved";

    private Integer numberAreaCodesRemoved;

    public static final String NUMBER_AREA_CODES_UPDATED = "numberAreaCodesUpdated";

    private Integer numberAreaCodesUpdated;

    public static final String NUMBER_AREA_CODES_ADDED = "numberAreaCodesAdded";

    private Integer numberAreaCodesAdded;

    public static final String NUMBER_TIME_ZONES_REMOVED = "numberTimeZonesRemoved";

    private Integer numberTimeZonesRemoved;

    public static final String NUMBER_TIME_ZONES_UPDATED = "numberTimeZonesUpdated";

    private Integer numberTimeZonesUpdated;

    public static final String NUMBER_TIME_ZONES_ADDED = "numberTimeZonesAdded";

    private Integer numberTimeZonesAdded;

    public static final String NUMBER_CURRENCIES_REMOVED = "numberCurrenciesRemoved";

    private Integer numberCurrenciesRemoved;

    public static final String NUMBER_CURRENCIES_UPDATED = "numberCurrenciesUpdated";

    private Integer numberCurrenciesUpdated;

    public static final String NUMBER_CURRENCIES_ADDED = "numberCurrenciesAdded";

    private Integer numberCurrenciesAdded;

    public static final String NUMBER_LANGUAGES_REMOVED = "numberLanguagesRemoved";

    private Integer numberLanguagesRemoved;

    public static final String NUMBER_LANGUAGES_UPDATED = "numberLanguagesUpdated";

    private Integer numberLanguagesUpdated;

    public static final String NUMBER_LANGUAGES_ADDED = "numberLanguagesAdded";

    private Integer numberLanguagesAdded;

    // end statistics

    /** transient field */
    //private UOWDocumentObject uow;

    /** transient field */
    private Integer logProgressCount;

}

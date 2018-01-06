package com.hatchers.ruralcaravane.ConstructionTeamDetails.Databases;

/**
 * Created by Nikam on 30/12/2017.
 */
public class ConstructionTable {

    public static final String CONSTRUCTION_TEAM_TABLE = "Construction_Team_Table";

    public static final String TECHNICIAN_ID = "technician_id",TECHNICIAN_NAME="technician_name",
            TECHNICIAN_ADDRESS="technician_address",TECHNICIAN_MOBILENO="technician_mobileno",
            TECHNICIAN_AGE="technician_age", TECHNICIAN_GENDER="technician_gender",
            UPLOAD_STATUS="upload_status",TECHNICIAN_UNIQUE_ID="technician_unique_id",
            CUSTOMER_ID="customer_id",KITCHEN_ID="kitchen_id",HALF_COMPLETION_IMAGE="half_completion_image",
            COMPLETION_IMAGE="completion_image",DATETIME="date_time";

    public static final String CREATE_CONSTRUCTION_TEAM_TABLE="CREATE TABLE " + CONSTRUCTION_TEAM_TABLE+ "("+TECHNICIAN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+TECHNICIAN_NAME+" TEXT,"+TECHNICIAN_ADDRESS+" TEXT,"+TECHNICIAN_AGE+" INTEGER,"+TECHNICIAN_GENDER+" TEXT,"+TECHNICIAN_MOBILENO+" INTEGER, "+UPLOAD_STATUS+" TEXT, "+HALF_COMPLETION_IMAGE+" TEXT, "+COMPLETION_IMAGE+" TEXT, "+TECHNICIAN_UNIQUE_ID+" INTEGER, "+CUSTOMER_ID+" INTEGER, "+KITCHEN_ID+" INTEGER, "+DATETIME+" TEXT)";

    private  String technicianIdValue,technicianNameValue,
            technicianAddressValue,technicianMobileNoValue,
            technicianAgeValue,technicianGenderValue,
            uploadStatusValue,halfCompletionImageValue,completionImageValue,
            customerIdValue,kitchenIdValue,dateTimeValue,technicianUniqueIdValue;

    public ConstructionTable() {
    }

    public ConstructionTable(String technicianIdValue, String technicianNameValue, String technicianAddressValue, String technicianMobileNoValue, String technicianAgeValue, String technicianGenderValue,String uploadStatusValue,String halfCompletionImageValue,String completionImageValue,String customerIdValue,String kitchenIdValue,String dateTimeValue,String technicianUniqueIdValue) {
        this.technicianIdValue = technicianIdValue;
        this.technicianNameValue = technicianNameValue;
        this.technicianAddressValue = technicianAddressValue;
        this.technicianMobileNoValue = technicianMobileNoValue;
        this.technicianAgeValue = technicianAgeValue;
        this.technicianGenderValue = technicianGenderValue;
        this.uploadStatusValue=uploadStatusValue;
        this.halfCompletionImageValue=halfCompletionImageValue;
        this.completionImageValue=completionImageValue;
        this.customerIdValue=customerIdValue;
        this.kitchenIdValue=kitchenIdValue;
        this.dateTimeValue=dateTimeValue;
        this.technicianUniqueIdValue=technicianUniqueIdValue;
    }


    public String getTechnicianIdValue() {
        return technicianIdValue;
    }

    public void setTechnicianIdValue(String technicianIdValue) {
        this.technicianIdValue = technicianIdValue;
    }

    public String getTechnicianNameValue() {
        return technicianNameValue;
    }

    public void setTechnicianNameValue(String technicianNameValue) {
        this.technicianNameValue = technicianNameValue;
    }

    public String getTechnicianAddressValue() {
        return technicianAddressValue;
    }

    public void setTechnicianAddressValue(String technicianAddressValue) {
        this.technicianAddressValue = technicianAddressValue;
    }

    public String getTechnicianMobileNoValue() {
        return technicianMobileNoValue;
    }

    public void setTechnicianMobileNoValue(String technicianMobileNoValue) {
        this.technicianMobileNoValue = technicianMobileNoValue;
    }

    public String getTechnicianAgeValue() {
        return technicianAgeValue;
    }

    public void setTechnicianAgeValue(String technicianAgeValue) {
        this.technicianAgeValue = technicianAgeValue;
    }

    public String getTechnicianGenderValue() {
        return technicianGenderValue;
    }

    public void setTechnicianGenderValue(String technicianGenderValue) {
        this.technicianGenderValue = technicianGenderValue;
    }

    public String getUploadStatusValue() {
        return uploadStatusValue;
    }

    public void setUploadStatusValue(String uploadStatusValue) {
        this.uploadStatusValue = uploadStatusValue;
    }

    public String getHalfCompletionImageValue() {
        return halfCompletionImageValue;
    }

    public void setHalfCompletionImageValue(String halfCompletionImageValue) {
        this.halfCompletionImageValue = halfCompletionImageValue;
    }

    public String getCompletionImageValue() {
        return completionImageValue;
    }

    public void setCompletionImageValue(String completionImageValue) {
        this.completionImageValue = completionImageValue;
    }

    public String getCustomerIdValue() {
        return customerIdValue;
    }

    public void setCustomerIdValue(String customerIdValue) {
        this.customerIdValue = customerIdValue;
    }

    public String getKitchenIdValue() {
        return kitchenIdValue;
    }

    public void setKitchenIdValue(String kitchenIdValue) {
        this.kitchenIdValue = kitchenIdValue;
    }

    public String getDateTimeValue() {
        return dateTimeValue;
    }

    public void setDateTimeValue(String dateTimeValue) {
        this.dateTimeValue = dateTimeValue;
    }

    public String getTechnicianUniqueIdValue() {
        return technicianUniqueIdValue;
    }

    public void setTechnicianUniqueIdValue(String technicianUniqueIdValue) {
        this.technicianUniqueIdValue = technicianUniqueIdValue;
    }
}

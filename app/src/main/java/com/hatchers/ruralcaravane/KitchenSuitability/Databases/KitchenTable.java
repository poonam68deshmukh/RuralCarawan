package com.hatchers.ruralcaravane.KitchenSuitability.Databases;


public class KitchenTable {


    public static final String KITCHEN_TABLE="KitchenTable";

    public static final String KITCHEN_ID="kitchen_id",KITCHEN_UNIQUE_ID="unique_id",
                               HOUSE_TYPE="house_type",ROOF_TYPE="roof_type",
                               KITCHEN_HEIGHT="kitchen_height",UPLOAD_STATUS="upload_status",
                               PLACE_IMAGE="place_image",LATITUDE="latitude",LONGITUDE="longitude",
                               GEO_ADDRESS="geo_address",UPLOAD_DATE="upload_date",
                               COST_OF_CHULLHA="cost_of_chullha",CUSTOMER_ID = "customer_id",CUSTOMER_NAME="customer_name";

    public static final String CREATE_KITCHEN_TABLE="CREATE TABLE " + KITCHEN_TABLE + "("+KITCHEN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+HOUSE_TYPE+" TEXT,"+ROOF_TYPE+" TEXT,"+KITCHEN_HEIGHT+" INTEGER, "+UPLOAD_STATUS+" TEXT, "+CUSTOMER_ID+" INTEGER, "+CUSTOMER_NAME+" TEXT, "+PLACE_IMAGE+" TEXT, "+KITCHEN_UNIQUE_ID+" INTEGER, "+LATITUDE+" FLOAT, "+LONGITUDE+" FLOAT, "+GEO_ADDRESS+" TEXT, "+UPLOAD_DATE+" TEXT, "+COST_OF_CHULLHA+" FLOAT)";

    private String kitchen_idValue,house_typeValue,roof_typeValue,kitchen_heightValue,upload_statusValue,customer_idValue,customer_nameValue,placeImageValue,kitchenUniqueIdValue,latitudeValue,longitudeValue,uploadDateValue,geoAddressValue,costOfChullhaValue;

    public KitchenTable() {
    }

    public KitchenTable(String kitchen_idValue, String house_typeValue, String roof_typeValue, String kitchen_heightValue, String upload_statusValue,String customer_idValue,String customer_nameValue,String placeImageValue,String geoAddressValue,String costOfChullhaValue,String kitchenUniqueIdValue,String latitudeValue,String longitudeValue,String uploadDateValue) {
        this.kitchen_idValue = kitchen_idValue;
        this.house_typeValue = house_typeValue;
        this.roof_typeValue = roof_typeValue;
        this.kitchen_heightValue = kitchen_heightValue;
        this.upload_statusValue=upload_statusValue;
        this.customer_idValue=customer_idValue;
        this.customer_nameValue=customer_nameValue;
        this.placeImageValue=placeImageValue;
        this.geoAddressValue=geoAddressValue;
        this.kitchenUniqueIdValue=kitchenUniqueIdValue;
        this.latitudeValue=latitudeValue;
        this.longitudeValue=longitudeValue;
        this.uploadDateValue=uploadDateValue;
        this.costOfChullhaValue=costOfChullhaValue;

    }

    public String getKitchen_idValue() {
        return kitchen_idValue;
    }

    public void setKitchen_idValue(String kitchen_idValue) {
        this.kitchen_idValue = kitchen_idValue;
    }

    public String getHouse_typeValue() {
        return house_typeValue;
    }

    public void setHouse_typeValue(String house_typeValue) {
        this.house_typeValue = house_typeValue;
    }

    public String getRoof_typeValue() {
        return roof_typeValue;
    }

    public void setRoof_typeValue(String roof_typeValue) {
        this.roof_typeValue = roof_typeValue;
    }

    public String getKitchen_heightValue() {
        return kitchen_heightValue;
    }

    public void setKitchen_heightValue(String kitchen_heightValue) {
        this.kitchen_heightValue = kitchen_heightValue;
    }

    public String getUpload_statusValue() {
        return upload_statusValue;
    }

    public void setUpload_statusValue(String upload_statusValue) {
        this.upload_statusValue = upload_statusValue;
    }

    public String getCustomer_idValue() {
        return customer_idValue;
    }

    public void setCustomer_idValue(String customer_idValue) {
        this.customer_idValue = customer_idValue;
    }

    public String getCustomer_nameValue() {
        return customer_nameValue;
    }

    public void setCustomer_nameValue(String customer_nameValue) {
        this.customer_nameValue = customer_nameValue;
    }

    public String getPlaceImageValue() {
        return placeImageValue;
    }

    public void setPlaceImageValue(String placeImageValue) {
        this.placeImageValue = placeImageValue;
    }

    public String getKitchenUniqueIdValue() {
        return kitchenUniqueIdValue;
    }

    public void setKitchenUniqueIdValue(String uniqueIdValue) {
        this.kitchenUniqueIdValue = uniqueIdValue;
    }

    public String getLatitudeValue() {
        return latitudeValue;
    }

    public void setLatitudeValue(String latitudeValue) {
        this.latitudeValue = latitudeValue;
    }

    public String getLongitudeValue() {
        return longitudeValue;
    }

    public void setLongitudeValue(String longitudeValue) {
        this.longitudeValue = longitudeValue;
    }

    public String getUploadDateValue() {
        return uploadDateValue;
    }

    public void setUploadDateValue(String uploadDateValue) {
        this.uploadDateValue = uploadDateValue;
    }

    public String getGeoAddressValue() {
        return geoAddressValue;
    }

    public void setGeoAddressValue(String geoAddressValue) {
        this.geoAddressValue = geoAddressValue;
    }

    public String getCostOfChullhaValue() {
        return costOfChullhaValue;
    }

    public void setCostOfChullhaValue(String costOfChullhaValue) {
        this.costOfChullhaValue = costOfChullhaValue;
    }
}


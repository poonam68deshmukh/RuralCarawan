package com.hatchers.ruralcaravane.payment_details.database;


public class PaymentTable {

    public static final String PAYMENT_TABLE="PaymentTable";

    public static final String PAYMENT_ID="payment_id",PAYMENT_AMOUNT="payment_amount",
                               ADVANCE_AMOUNT="advance_amount",REMAINING_AMOUNT="reamaining_amount",
                               UPLOAD_STATUS="upload_status",CHULLHA_IMAGE="chullha_image",
                               CUSTOMER_ID="customer_id",KITCHEN_ID="kitchen_id",COST_OF_CHULLHA="cost_of_chullha";

    public static final String CREATE_PAYMENT_TABLE="CREATE TABLE " + PAYMENT_TABLE +
            "("+PAYMENT_ID+" TEXT PRIMARY KEY ,"+PAYMENT_AMOUNT+" TEXT,"
            +ADVANCE_AMOUNT+" TEXT,"+REMAINING_AMOUNT+" TEXT, "+UPLOAD_STATUS+" TEXT, "
            +CHULLHA_IMAGE+" TEXT, "+CUSTOMER_ID+" TEXT)";

    private String payment_idValue,payment_amountValue,advance_amountValue,reamaining_amountValue,upload_statusValue,imagePathValue,customerIdValue,customerName;

    public PaymentTable() {
    }

    public PaymentTable(String payment_idValue, String payment_amountValue, String advance_amountValue, String reamaining_amountValue, String upload_statusValue,String imagePathValue,String customerIdValue,String customerName) {
        this.payment_idValue = payment_idValue;
        this.payment_amountValue = payment_amountValue;
        this.advance_amountValue = advance_amountValue;
        this.reamaining_amountValue = reamaining_amountValue;
        this.upload_statusValue = upload_statusValue;
        this.imagePathValue=imagePathValue;
        this.customerIdValue=customerIdValue;
        this.customerName=customerName;
    }

    public String getPayment_idValue() {
        return payment_idValue;
    }

    public void setPayment_idValue(String payment_idValue) {
        this.payment_idValue = payment_idValue;
    }

    public String getPayment_amountValue() {
        return payment_amountValue;
    }

    public void setPayment_amountValue(String payment_amountValue) {
        this.payment_amountValue = payment_amountValue;
    }

    public String getAdvance_amountValue() {
        return advance_amountValue;
    }

    public void setAdvance_amountValue(String advance_amountValue) {
        this.advance_amountValue = advance_amountValue;
    }

    public String getReamaining_amountValue() {
        return reamaining_amountValue;
    }

    public void setReamaining_amountValue(String reamaining_amountValue) {
        this.reamaining_amountValue = reamaining_amountValue;
    }

    public String getUpload_statusValue() {
        return upload_statusValue;
    }

    public void setUpload_statusValue(String upload_statusValue) {
        this.upload_statusValue = upload_statusValue;
    }

    public String getImagePathValue() {
        return imagePathValue;
    }

    public void setImagePathValue(String imagePathValue) {
        this.imagePathValue = imagePathValue;
    }

    public String getCustomerIdValue() {
        return customerIdValue;
    }

    public void setCustomerIdValue(String customerIdValue) {
        this.customerIdValue = customerIdValue;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}

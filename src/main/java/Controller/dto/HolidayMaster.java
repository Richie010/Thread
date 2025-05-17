package Controller.dto;

import java.util.Date;

public class HolidayMaster {


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getHolidayDate() {
        return holidayDate;
    }

    public void setHolidayDate(Date holidayDate) {
        this.holidayDate = holidayDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    private Date holidayDate;
    private String description;
    private String countryName;
    private String paymethod;
    private int countryId;
    private int paymethodId;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getPaymethod() {
        return paymethod;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public int getPaymethodId() {
        return paymethodId;
    }

    public void setPaymethodId(int paymethodId) {
        this.paymethodId = paymethodId;
    }

    public HolidayMaster(int id, Date holidayDate, String description, String countryName, String paymethod, int countryId, int paymethodId) {
        this.id = id;
        this.holidayDate = holidayDate;
        this.description = description;
        this.countryName = countryName;
        this.paymethod = paymethod;
        this.countryId = countryId;
        this.paymethodId = paymethodId;
    }

    public void setPaymethod(String paymethod) {
        this.paymethod = paymethod;
    }



    public HolidayMaster(){

    }
}

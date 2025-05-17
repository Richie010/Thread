package Controller.dto;

public class Paymethod {
    @Override
    public String toString() {
        return "Paymethod{" +
                "id=" + id +
                ", countryId=" + countryId +
                ", paymethodCode='" + paymethodCode + '\'' +
                ", paymethodDesc='" + paymethodDesc + '\'' +
                '}';
    }

    public Paymethod(){

    }
    public Paymethod(int id, int countryId, String paymethodCode, String paymethodDesc) {
        this.id = id;
        this.countryId = countryId;
        this.paymethodCode = paymethodCode;
        this.paymethodDesc = paymethodDesc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getPaymethodCode() {
        return paymethodCode;
    }

    public void setPaymethodCode(String paymethodCode) {
        this.paymethodCode = paymethodCode;
    }

    public String getPaymethodDesc() {
        return paymethodDesc;
    }

    public void setPaymethodDesc(String paymethodDesc) {
        this.paymethodDesc = paymethodDesc;
    }

    private int id;
    private int countryId;
    private String paymethodCode;
    private String paymethodDesc;
}

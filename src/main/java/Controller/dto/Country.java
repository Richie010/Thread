package Controller.dto;

public class Country {
    private int id;
    private String CountryCode;
    private String CountryName;

    public Country(int id) {
        this.id = id;
    }

    public Country(){

    }

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
    }

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", CountryCode='" + CountryCode + '\'' +
                ", CountryName='" + CountryName + '\'' +
                ", Status='" + Status + '\'' +
                '}';
    }

    private String Status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

package Controller.repo;

import Controller.dto.Country;
import Controller.dto.HolidayMaster;
import Controller.dto.Paymethod;
import org.apache.poi.ss.formula.functions.Count;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HolidayRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;




    public List<HolidayMaster> getAllHolidays(){
        String sql="SELECT CON.COUNTRY_NAME, HOL.HOLIDAY_DATE, HOL.DESCRIPTION FROM holidaymaster HOL INNER JOIN CountryMaster CON ON CON.id = HOL.country_id";
        List<HolidayMaster>  hol= jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(HolidayMaster.class));
       return hol;

    }
    public void addHoliday(HolidayMaster holiday) {
        String sql = "INSERT INTO holidaymaster (holiday_date, description, country_id, pay_method_id) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, holiday.getHolidayDate(), holiday.getDescription(), holiday.getCountryId(), holiday.getPaymethodId());

    }

    public List<Country> findAllcountry(){
        String sql="SELECT  ID, COUNTRY_CODE,COUNTRY_NAME FROM CountryMaster";
        List<Country>  hol= jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Country.class));
        return hol;
    }

    public List<Paymethod> findAllPaymethod(){
        String sql="SELECT  country_id, paymethod_code FROM PaymentMaster";
        List<Paymethod>  hol= jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Paymethod.class));
        return hol;
    }
    public int deleteHolidayBy(int id) {
        String sql="DELETE FROM holiday_master WHERE id=?";
        return jdbcTemplate.update(sql,id);
    }


    public List<Country> getAllCountries() {
        String sql = "SELECT id, country_name, country_code FROM CountryMaster";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Country.class));
    }
    public String getCountryCodeById(Long id) {
        String sql = "SELECT country_code FROM CountryMaster WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, String.class);
        } catch (EmptyResultDataAccessException e) {
            return "";
        }
    }
    public List<Paymethod> getPayMethodsByCountry(Long countryId) {
        String sql = "SELECT id, paymethod_code FROM PaymentMaster WHERE country_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Paymethod.class), countryId);
    }
}

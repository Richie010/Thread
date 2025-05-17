package Controller.repo;

import Controller.dto.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CountryRepository {

    @Autowired
    public JdbcTemplate jdbcTemplate;

    public List<Country> findAll() {
        List<Country> countries;
        countries=jdbcTemplate.query("SELECT * FROM countrymaster", (rs, rowNum) -> mapRowToCountry(rs));
        return countries;
    }
    private Country mapRowToCountry(ResultSet rs) throws SQLException {
        Country c = new Country();
        c.setId(rs.getInt("id"));
        c.setCountryCode(rs.getString("country_code"));
        c.setCountryName(rs.getString("country_name"));
        c.setStatus(rs.getString("status"));
        return c;
    }

}

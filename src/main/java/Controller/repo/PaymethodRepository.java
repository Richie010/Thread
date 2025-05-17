package Controller.repo;

import Controller.dto.Paymethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PaymethodRepository {

    @Autowired
    private JdbcTemplate jdbctemplate;


    public List<Object> getPaymethodsByCountryId(int countryId) {
        String sql = "SELECT *FROM paymentmaster WHERE country_id=?";
        return jdbctemplate.query(sql, new Object[]{countryId}, (rs, rowNum) -> mapRowToCountry(rs));
    }

    private Object mapRowToCountry(ResultSet rs) throws SQLException {
        Paymethod p = new Paymethod();
        p.setId(rs.getInt("id"));
        p.setCountryId(rs.getInt("country_id"));
        p.setPaymethodCode(rs.getString("payment_method"));
        p.setPaymethodDesc(rs.getString("payment_desc"));
        return p;
    }
}

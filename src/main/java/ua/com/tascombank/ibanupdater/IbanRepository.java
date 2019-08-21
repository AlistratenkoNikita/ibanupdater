package ua.com.tascombank.ibanupdater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IbanRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<IbanStore> findInPayments() {
        return findAll("SELECT ID, R_BANK_MFO,R_ACC FROM PAYADMIN.PAYMENTS WHERE RECIPIENT_IBAN IS NOT NULL " +
                "AND SUBSTR(RECIPIENT_IBAN,5,6)!=R_BANK_MFO");
    }

    public List<IbanStore> findInTemplates() {
        return findAll("SELECT ID,MFO,RECIPIENT_ACCOUNT FROM PAYADMIN.PAYMENT_TEMPLATES " +
                "WHERE RECIPIENT_ACCOUNT_IBAN IS NOT NULL AND SUBSTR(RECIPIENT_ACCOUNT_IBAN,5,6)!=MFO");
    }

    private List<IbanStore> findAll(String query) {
        return jdbcTemplate.query(
                query, (rs, rowNum) -> new IbanStore().setId(rs.getString(1))
                        .setMfo(rs.getString(2))
                        .setAcc(rs.getString(3))
        );
    }
}

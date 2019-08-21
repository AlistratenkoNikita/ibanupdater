package ua.com.tascombank.ibanupdater;

import org.apache.commons.validator.routines.checkdigit.CheckDigitException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.tascombank.tas24b.core.iban.IBANUtils;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

@Service
public class IbanUpdateService {

    @Autowired
    private IbanRepository repo;

    private static final String UPDATE_PAYMENTS_STRING = "UPDATE PAYADMIN.PAYMENTS SET RECIPIENT_IBAN='?1' WHERE ID=?2";
    private static final String UPDATE_TEMPLATES_STRING = "UPDATE PAYADMIN.PAYMENT_TEMPLATES SET RECIPIENT_ACCOUNT_IBAN='?1' WHERE ID=?2";

    public void generateUpdatesForPayments() {
        generateUpdates(repo.findInPayments(), "payments_updates.txt", UPDATE_PAYMENTS_STRING);
    }

    public void generateUpdatesForTemplates() {
        generateUpdates(repo.findInTemplates(), "templates_updates.txt", UPDATE_TEMPLATES_STRING);
    }

    private void generateUpdates(List<IbanStore> accounts, String filename, String updateString) {
        try (PrintWriter out = new PrintWriter(filename)) {
            for (IbanStore ibanStore : accounts) {
                out.println(updateString
                        .replace("?1", IBANUtils.generateIBAN(ibanStore.getAcc(), ibanStore.getMfo(), "UA"))
                        .replace("?2", ibanStore.getId())
                );
            }
        } catch (FileNotFoundException | CheckDigitException e) {
            e.printStackTrace();
        }
    }
}

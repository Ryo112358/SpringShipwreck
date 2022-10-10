package account.validation;

import account.contracts.PayrollCM;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.regex.Pattern;

public class ContractValidator {

    private static final Pattern pattern = Pattern.compile(".*password.*", Pattern.CASE_INSENSITIVE);

    public static boolean validatePassword(UserCredential credential) {
        if(pattern.matcher(credential.getPassword()).matches())
            throw new BreachedPasswordException();

        return true;
    }

    public static boolean validate(PayrollCM payrollCM) {
        final int month = Integer.parseInt(payrollCM.getPeriod().split("-")[0]);
        if(month > 12 || month < 1)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid month");

        return true;
    }
}

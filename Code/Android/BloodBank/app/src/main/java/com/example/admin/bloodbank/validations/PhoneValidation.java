package com.example.admin.bloodbank.validations;

import com.example.admin.bloodbank.activities.NumberPhoneCustom;
import com.mobsandgeeks.saripaar.AnnotationRule;
import java.lang.annotation.Annotation;

/**
 * Created by Admin on 09/02/2017.
 */

public class PhoneValidation extends AnnotationRule<NumberPhoneCustom.PhoneValidator, String> {
    /**
     * Constructor. It is mandatory that all subclasses MUST have a constructor with the same
     * signature.
     *
     * @param phoneValidator The rule {@link Annotation} instance to which
     *                       this rule is paired.
     */
    protected PhoneValidation(NumberPhoneCustom.PhoneValidator phoneValidator) {
        super(phoneValidator);
    }

    @Override
    public boolean isValid(String phone) {
        boolean isValid = false;
        String[] array = {"01", "09"};
        for (String beginning : array) {
            if (phone.trim().startsWith(beginning)) {
                isValid = true;
                break;
            }
        }
        return isValid;
    }
}

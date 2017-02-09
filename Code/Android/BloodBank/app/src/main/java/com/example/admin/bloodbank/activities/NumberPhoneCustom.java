package com.example.admin.bloodbank.activities;

import com.example.admin.bloodbank.validations.PhoneValidation;
import com.mobsandgeeks.saripaar.annotation.ValidateUsing;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Admin on 09/02/2017.
 */
public @interface NumberPhoneCustom {
    String message();
    @ValidateUsing(PhoneValidation.class)
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)

    public @interface PhoneValidator {
        int min()           default 10;
        int max()           default 11;
        public int messageResId()   default -1;                     // Mandatory attribute
        public String message()     default "Số điện thoại phải đúng định dạng";   // Mandatory attribute
        public int sequence()       default -1;                     // Mandatory attribute
    }
}

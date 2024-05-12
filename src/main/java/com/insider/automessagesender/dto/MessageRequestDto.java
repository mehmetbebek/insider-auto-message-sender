package com.insider.automessagesender.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.insider.automessagesender.validator.PhoneNumberConstraint;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageRequestDto {

    @Size(max = 140, message = "Message content must be less than 140 characters")
    @NotEmpty(message = "Message content must be provided")
    private String content;

    @PhoneNumberConstraint
    private String recipientPhoneNumber;

    public MessageRequestDto(String recipientPhoneNumber) {
        this.recipientPhoneNumber = recipientPhoneNumber;
    }

    @JsonIgnore
    public String formatPhoneNumber() throws NumberParseException {
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        Phonenumber.PhoneNumber number = phoneUtil.parse(recipientPhoneNumber, "TR");
        return phoneUtil.format(number, PhoneNumberUtil.PhoneNumberFormat.E164);
    }
}

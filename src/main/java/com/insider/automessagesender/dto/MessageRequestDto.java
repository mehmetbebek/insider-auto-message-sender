package com.insider.automessagesender.dto;

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
}

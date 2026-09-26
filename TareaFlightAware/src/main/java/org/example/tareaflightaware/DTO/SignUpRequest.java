package org.example.tareaflightaware.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {
    @NotBlank @Email private String email;
    @NotBlank @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).{8,}$") private String password;
    @NotBlank @Pattern(regexp = "^(?=.*[A-Z]).+$") private String firstName;
    @NotBlank @Pattern(regexp = "^(?=.*[A-Z]).+$") private String lastName;
}

package com.bookmyshow.payloads;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

//    private String id;

//    @NotEmpty
//    @Size(min=4,message = "User FirstName must be minimum of 4 Charactors !! ")
    private  String fname;

//    @NotEmpty
//    @Size(min=4,message = "User LastName must be minimum of 4 Charactors !! ")
    private  String lname;

//    @Past
//    @NotNull
    private LocalDate dob;


//    @Email(message = " Email Address Is Not Valid !!! ")
    private String  email;


//    @NotEmpty
//    @Size(min=3,  max = 10,message = " must be minimum of 4 Charactors !!")
//    @Pattern(regexp = "^[a-zA-Z0-9_]{3,15}$", message = "Invalid password format")
    private String password;


}

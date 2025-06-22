package com.productrial.business.dtos;

import com.productrial.enums.InventoryStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Data
public class AuthenticationDTO {
    private String username;
    private String firstName;
    private String email;
    private String password;
}

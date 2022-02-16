package uz.pdp.warehouseapp.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Response {
    private String message;
    private boolean success;
}

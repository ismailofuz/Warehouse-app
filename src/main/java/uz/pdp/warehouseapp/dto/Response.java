package uz.pdp.warehouseapp.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Response {
    private String message;
    private boolean success;
}

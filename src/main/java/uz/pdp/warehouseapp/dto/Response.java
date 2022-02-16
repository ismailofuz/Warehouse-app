package uz.pdp.warehouseapp.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Response {
    private String message;
    private boolean success;
}

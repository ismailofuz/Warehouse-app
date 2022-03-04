package uz.pdp.warehouseapp.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
@Getter
public class Response {
    private String message;
    private boolean success;
}

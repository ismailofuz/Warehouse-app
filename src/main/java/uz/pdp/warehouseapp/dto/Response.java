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
    private Object object;
    public Response (String message, boolean success) {
        this.message = message;
        this.success = success;
    }





}

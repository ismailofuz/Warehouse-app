package uz.pdp.warehouseapp.dto;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MeasurementDto {
    private String name;
    private boolean active;
}

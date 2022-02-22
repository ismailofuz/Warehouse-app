package uz.pdp.warehouseapp.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class WarehouseDto {
    private String name;
    private boolean active;
    private List<Integer> userList;

}

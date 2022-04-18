package backend.rest.warehouse;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WarehouseJsonHandler {
    @JsonProperty("name")
    public String name;

    @JsonProperty("quantity")
    public Integer quantity;

    @JsonProperty("amount")
    public Double amount;
}

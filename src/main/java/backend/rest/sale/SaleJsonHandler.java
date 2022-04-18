package backend.rest.sale;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class SaleJsonHandler {
    @JsonProperty("amount")
    public Double amount;

    @JsonProperty("quantity")
    public Integer quantity;

    @JsonProperty("sale_date")
    public LocalDateTime sale_date;

    @JsonProperty("warehouse_id")
    public Integer warehouse_id;
}

package backend.rest.charge;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class ChargeJsonHandler {
    @JsonProperty("amount")
    public Double amount;

    @JsonProperty("charge_date")
    public LocalDateTime charge_date;

    @JsonProperty("expense_item_id")
    public Integer expense_item_id;
}

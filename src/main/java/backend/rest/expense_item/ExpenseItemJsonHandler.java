package backend.rest.expense_item;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExpenseItemJsonHandler {
    @JsonProperty("name")
    public String name;
}

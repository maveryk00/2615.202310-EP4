package xyz.maveryk.EP4.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VisitCreateResponse {

    @JsonProperty("code")
    public Integer code;

    @JsonProperty("message")
    public String message;

}

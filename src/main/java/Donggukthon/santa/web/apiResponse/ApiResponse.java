package Donggukthon.santa.web.apiResponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"code", "status", "message", "data"})
public class ApiResponse<T> {

    private final int code;
    @JsonProperty("status")
    private final String status;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;


    public ApiResponse(SuccessStatus status, T result) {
        this.code = status.getCode();
        this.status = status.getStatus();
        this.message = status.getMessage();
        this.data = result;
    }
    public ApiResponse(ErrorStatus status) {
        this.code = status.getCode();
        this.status = status.getStatus();
        this.message = status.getMessage();
    }


    public ApiResponse(SuccessStatus status) {
        this.code = status.getCode();
        this.status = status.getStatus();
        this.message = status.getMessage();
    }

}

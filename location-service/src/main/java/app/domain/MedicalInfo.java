package app.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Embeddable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Embeddable
public class MedicalInfo {

    private final String vin;
    private long bfr;

    private long fmi;

    @SuppressWarnings("unused")
    private MedicalInfo() {
        this.vin = "";
    }
}

package app.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import java.util.Date;
import java.util.Random;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Entity
@Table(name = "RUNNING_INFORMATION")
public class RunningInformation {
    public enum HealWarningLevel {
        LOW, NORMAL, HIGH;
    }

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private final UserInfo userInfo;

    private String runningId;

    private double longitude;
    private double latitude;

    private double runningDistance;
    private double totalRunningTime;

    private int heartRate;
    private HealWarningLevel healWarningLevel;

    private Date timestamp;

    public RunningInformation() {
        this.userInfo = null;
    }

    public RunningInformation(String username, String address) {
        this.userInfo = new UserInfo(username, address);
    }

    @JsonCreator
    public RunningInformation(
            @JsonProperty("runningId") String runningId,
            @JsonProperty("latitude") String latitude,
            @JsonProperty("longitude") String longitude,
            @JsonProperty("runningDistance") String runningDistance,
            @JsonProperty("totalRunningTime") String totalRunningTime,
            @JsonProperty("heartRate") String heartRate,
            @JsonProperty("timestamp") String timestamp,
            @JsonProperty("userInfo") UserInfo userInfo
    ) {
        this.runningId = runningId;
        this.latitude = Double.parseDouble(latitude);
        this.longitude = Double.parseDouble(longitude);
        this.runningDistance = Double.parseDouble(runningDistance);
        this.totalRunningTime = Double.parseDouble(totalRunningTime);
        this.timestamp = new Date();
        this.userInfo = userInfo;
        this.heartRate = getRandomHeartRate(60, 200);

        if (this.heartRate > 120) {
            this.healWarningLevel = HealWarningLevel.HIGH;
        } else if (this.heartRate > 75) {
            this.healWarningLevel = HealWarningLevel.NORMAL;
        } else if (this.heartRate >= 60) {
            this.healWarningLevel = HealWarningLevel.LOW;
        } else {
            // Why? Because other SDEs will not know this if block is related to the this.heartRate = getRandomHeartRate(60, 200);
            // option 1: DANGER
            // option 2: Intentionally left blank
            // option 3: Exception
            // option 4: Print warning
        }
    }

    public RunningInformation(final UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getUsername() {
        return this.userInfo == null ? null : this.userInfo.getUsername();
    }

    public String getAddress() {
        return this.userInfo == null ? null : this.userInfo.getAddress();
    }

    private int getRandomHeartRate(int min, int max) {
        Random rn = new Random();
        return min + rn.nextInt(max - min + 1);
    }
}

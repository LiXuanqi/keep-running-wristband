package app.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.NONE)
public class PositionInfo {

    private String runningId;
    private Point position;
    private RunnerStatus runnerStatus = RunnerStatus.NONE;

    private Leg leg;

    private Double distanceFromStart;

    private Double speed;
}

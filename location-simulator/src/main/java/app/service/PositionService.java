package app.service;

import app.domain.CurrentPosition;

public interface PositionService {

    void processPositionInfo(long id,
                             CurrentPosition currentPosition,
                             boolean exportPositionsToKml,
                             boolean sendPositionsToDistributionService);
}

package app.service;

import app.domain.DirectionInput;
import app.domain.Point;
import app.domain.SimulatorFixture;
import app.domain.SupplyLocation;

import java.util.List;

public interface PathService {

    List<DirectionInput> loadDirectionInput();

    SimulatorFixture loadSimulatorFixture();

    List<Point> getCoordinatesFromGoogle(DirectionInput directionInput);

    String getCoordinatesFromGoogleAsPolyline(DirectionInput directionInput);

    List<SupplyLocation> getSupplyLocations();
}

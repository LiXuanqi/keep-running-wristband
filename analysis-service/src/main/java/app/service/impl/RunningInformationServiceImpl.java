package app.service.impl;

import app.domain.RunningInformation;
import app.domain.RunningInformationRepository;
import app.service.RunningInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class RunningInformationServiceImpl implements RunningInformationService {

    private RunningInformationRepository runningInformationRepository;

    @Autowired
    public RunningInformationServiceImpl(RunningInformationRepository runningInformationRepository) {
        this.runningInformationRepository = runningInformationRepository;
    }

    @Override
    public List<RunningInformation> saveRunningInformation(List<RunningInformation> runningInformationList) {
        return null;
    }

    @Override
    public Page<RunningInformation> findByHeartRate(int heartRate, Pageable pageable) {
        return null;
    }

    @Override
    public Page<RunningInformation> findByHeartRateGreaterThan(int heartRate, Pageable pageable) {
        return null;
    }

    @Override
    public Page<RunningInformation> findAllRunningInformationByHealthLevel(Pageable pageable) {
        return null;
    }

    @Override
    public void deleteAll() {

    }
}

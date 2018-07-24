package app.rest;

import app.domain.RunningInformation;
import app.service.RunningInformationService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RunningInformationQueryController {
    private final String kDefaultPage = "0";
    private final String kDefaultImagePerPage = "30";

    @Autowired
    private RunningInformationService runningInformationService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestBody List<RunningInformation> runningInformationList) {
        runningInformationService.saveRunningInformation(runningInformationList);
    }

    @RequestMapping(value = "/purge", method = RequestMethod.DELETE)
    public void purge() {
        runningInformationService.deleteAll();
    }

    @RequestMapping(value = "/heartRate/{heartRate}", method = RequestMethod.GET)
    public Page<RunningInformation> findByHeartRate(
            @PathVariable Integer heartRate,
            @RequestParam(name = "page", required = false, defaultValue = kDefaultPage) Integer page,
            @RequestParam(name = "size", required = false, defaultValue = kDefaultImagePerPage) Integer size
    ) {
        return runningInformationService.findByHeartRate(heartRate, new PageRequest(page, size));
    }

    @RequestMapping(value = "/heartRateGreaterThan/{heartRate}", method = RequestMethod.GET)
    public ResponseEntity<List<JSONObject>> findByHeartRateGreaterThan(
            @PathVariable Integer heartRate,
            @RequestParam(name = "page", required = false, defaultValue = kDefaultPage) Integer page,
            @RequestParam(name = "size", required = false, defaultValue = kDefaultImagePerPage) Integer size
    ) {
        Page<RunningInformation> rawResults = runningInformationService.findByHeartRateGreaterThan(heartRate, new PageRequest(page, size));
        List<RunningInformation> content = rawResults.getContent();

        // Transform RunningInformation to customized JSON objects.
        List<JSONObject> results = new ArrayList<JSONObject>();
        // it's not a good practice, if the key changed, you should change it everywhere. it's better to used a final String kFieldRunningId.
        for (RunningInformation item : content) {
            JSONObject info = new JSONObject();
            info.put("runningId", item.getRunningId());
            info.put("totalRunningTime", item.getTotalRunningTime());
            info.put("heartRate", item.getHeartRate());
            info.put("userId", item.getId());
            info.put("userName", item.getUserInfo().getUsername());
            info.put("userAddress", item.getUserInfo().getAddress());
            info.put("healthWarningLevel", item.getHealWarningLevel()); // TODO: should order.
            results.add(info);
        }
        return new ResponseEntity<List<JSONObject>>(results, HttpStatus.OK);

    }
}

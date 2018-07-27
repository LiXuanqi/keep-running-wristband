package app.rest;


import app.domain.Location;
import app.domain.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RunningBulkUploadController {


    private LocationRepository repository;

    @Autowired
    public RunningBulkUploadController(LocationRepository repository) {
        this.repository = repository;

    }

    @RequestMapping(value = "/running", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestBody List<Location> locations) throws Exception {
        repository.saveAll(locations);
    }

    @RequestMapping(value = "/purge", method = RequestMethod.POST)
    public void purge() {
        repository.deleteAll();
    }

}

package treboder.optaplanner.examples.timetable.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import treboder.optaplanner.examples.timetable.TimetableApplication;

@RestController
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(TimetableApplication.class);

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        logger.info("Hello World");
        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
    }




}

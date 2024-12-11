package oop.cwk.ramudi.TicketingSystem.post.controller;


import oop.cwk.ramudi.TicketingSystem.init.table.LogTable;
import oop.cwk.ramudi.TicketingSystem.init.table.LogTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// @CrossOrigin(origins = "*")
@RequestMapping("/api/logs")
public class LogController {

    @Autowired
    private LogTableRepository logTableRepository;

    /**
     * Posting the log to the log table in tha database
     *
     * @param logMessage
     * @return
     */
    @PostMapping
    public ResponseEntity<String> postLog(@RequestBody String logMessage) {
        LogTable log = new LogTable();
        log.setLogMessage(logMessage);
        logTableRepository.save(log);
        return ResponseEntity.ok("Log saved successfully!");
    }

    /**
     * Get request for the logs
     *
     * @return
     */
    @GetMapping
    public List<LogTable> getAllLogs() {
        return logTableRepository.findAll();
    }


}

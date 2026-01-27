package com.sdlcpro.txdemo.presenter;

import com.sdlcpro.txdemo.core.service.impl.BatchJobService;
import org.springframework.batch.core.JobExecution;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/batch")
public class BatchJobController {

    private final BatchJobService batchJobService;

    public BatchJobController(BatchJobService batchJobService) {
        this.batchJobService = batchJobService;
    }

    @PostMapping("/start")
    public ResponseEntity<String> startBatch() {
        try {
            JobExecution execution = batchJobService.startJob();
            return ResponseEntity.ok(
                    "Batch started. JobExecutionId = " + execution.getId()
            );
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Batch failed: " + e.getMessage());
        }
    }
}

package com.worldline.perfectnumber.controller;

import com.worldline.perfectnumber.service.AuditLogService;
import com.worldline.perfectnumber.service.PerfectNumberService;
import com.worldline.perfectnumber.valueobject.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Perfect Number")
@RestController
@RequestMapping("/api/perfect-number")
public class PerfectNumberController {

    private final PerfectNumberService perfectNumberService;
    private final AuditLogService auditLogService;

    @Autowired
    public PerfectNumberController(PerfectNumberService perfectNumberService,
                                   AuditLogService auditLogService){
        this.perfectNumberService = perfectNumberService;
        this.auditLogService = auditLogService;
    }

    @Operation(summary = "Find All Perfect Numbers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns perfect numbers in range",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Integer.class)))}),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})
    })
    @GetMapping
    public ResponseEntity<List<Integer>> findPerfectNumbers(@RequestParam @Parameter(name = "input", description = "Array of numbers") int[] input,
                                                            @RequestParam @Parameter(name = "start", description = "Start index (inclusive)") int start,
                                                            @RequestParam @Parameter(name = "end", description = "End index (exclusive)") int end,
                                                            HttpServletRequest request){

        List<Integer> perfectNumbers = perfectNumberService.findPerfectNumbers(input, start, end);
        auditLogService.logRequest(request.getRemoteAddr(), perfectNumbers);

        return ResponseEntity.ok(perfectNumbers);
    }
}

package com.axel.maxnumberfinder.controller;

import com.axel.maxnumberfinder.service.ExcelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ExcelController {
    private final ExcelService excelService;

    public ExcelController(ExcelService excelService) {
        this.excelService = excelService;
    }

    @GetMapping("/max-number")
    public int getMaxNumber(@RequestParam("filePath") String filePath,
                            @RequestParam("n") int n) throws Exception {
        return excelService.findMax(filePath, n);
    }
}

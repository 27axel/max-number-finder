package com.axel.maxnumberfinder.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.util.PriorityQueue;

@Service
public class ExcelService {
    public int findMax(String filePath, int n) throws Exception {
        try (FileInputStream file = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(file)) {

            if (workbook.getNumberOfSheets() == 0) {
                throw new IllegalArgumentException("Файл не содержит листов.");
            }

            Sheet sheet = workbook.getSheetAt(0);

            PriorityQueue<Integer> minHeap = new PriorityQueue<>();

            for (Row row : sheet) {
                for (Cell cell : row) {
                    if (cell.getCellType() == CellType.NUMERIC) {
                        int num = (int) cell.getNumericCellValue();

                        if (minHeap.size() < n) {
                            minHeap.add(num);
                        } else if (minHeap.peek() != null && num > minHeap.peek()) {
                            minHeap.poll();
                            minHeap.add(num);
                        }
                    }
                }
            }

            if (minHeap.size() < n) {
                throw new IllegalArgumentException("В файле недостаточно чисел для нахождения " + n + "-го максимального числа.");
            }

            return minHeap.peek() != null ? minHeap.peek() : 0;
        }
    }
}

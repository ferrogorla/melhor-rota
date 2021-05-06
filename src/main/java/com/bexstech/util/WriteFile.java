package com.bexstech.util;

import java.io.FileWriter;
import java.io.IOException;

import com.bexstech.dto.RouteRequestDTO;

public class WriteFile {

    public static void writeCSV(String filePath, RouteRequestDTO routeRequestDTO) throws IOException {
        FileWriter csvWriter = new FileWriter(filePath, true);

        csvWriter.append("\n");
        csvWriter.append( routeRequestDTO.getFrom());
        csvWriter.append(",");
        csvWriter.append( routeRequestDTO.getTo());
        csvWriter.append(",");
        csvWriter.append( routeRequestDTO.getPrice().toString());

        csvWriter.flush();
        csvWriter.close();
    }

}

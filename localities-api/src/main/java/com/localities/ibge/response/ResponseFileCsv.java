package com.localities.ibge.response;

import com.localities.ibge.model.ResponseLocality;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.Set;

@Component
public class ResponseFileCsv implements ResponseFile {
    private final Logger log = LoggerFactory.getLogger(ResponseFileCsv.class);

    private static final String FILE_DELIMITER = ",";

    @Override
    public void buildResponse(Set<ResponseLocality> responseLocalities, HttpServletResponse httpResponse) {
        log.info("Writing .csv file...");
        final String fileText = getFileText(responseLocalities);

        try {
            httpResponse.setHeader("Content-Type", "text/csv; charset=UTF-8");
            httpResponse.setHeader("Content-Disposition", "attachment; filename=municipios.csv");
            httpResponse.setHeader("Content-Length", String.valueOf(fileText.length()));
            httpResponse.getWriter().write(fileText);
        } catch (final Exception exception) {
            log.error(exception.getMessage());
        }
    }

    private static String getFileHeaders() {
        return String.join(FILE_DELIMITER, ResponseLocality.getFileFields());
    }

    private String getFileText(Set<ResponseLocality> responseLocalities) {
        final String lineSeparator = System.lineSeparator();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getFileHeaders());
        for (final ResponseLocality responseLocality : responseLocalities) {
            stringBuilder.append(lineSeparator);
            stringBuilder.append(getValues(responseLocality));
        }
        return stringBuilder.toString();
    }

    private String getValues(ResponseLocality responseLocality) {
        return String.join(FILE_DELIMITER,
                String.valueOf(responseLocality.getStateId()),
                responseLocality.getStateAbbreviation(),
                responseLocality.getRegionName(),
                responseLocality.getCityName(),
                responseLocality.getMesoregionName(),
                responseLocality.getFormattedName()
        );
    }
}

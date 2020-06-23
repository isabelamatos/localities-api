package com.localities.ibge.response;

import com.localities.ibge.model.ResponseLocality;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.Set;

@Component
public class ResponseFileJson implements ResponseFile {
    private final Logger log = LoggerFactory.getLogger(ResponseFileJson.class);

    @Override
    public void buildResponse(Set<ResponseLocality> responseLocalities, HttpServletResponse httpResponse) {
        try {
            log.info("Writing .json file...");
            final String fileText = new ObjectMapper().writeValueAsString(responseLocalities);

            httpResponse.setHeader("Content-Type", "application/json; charset=UTF-8");
            httpResponse.setHeader("Content-Disposition", "attachment; filename=municipios.json");
            httpResponse.setHeader("Content-Length", String.valueOf(fileText.length()));
            httpResponse.getWriter().write(fileText);
        } catch (Exception exception) {
            log.error(exception.getMessage());
        }
    }
}
package com.localities.ibge.service;

import com.localities.ibge.model.*;
import com.localities.ibge.response.ResponseFile;
import com.localities.ibge.response.ResponseFileFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class Service {
    private final Logger log = LoggerFactory.getLogger(Service.class);

    @Autowired
    private IbgeApiService ibgeApiService;

    public void getAllLocalities(String fileType, HttpServletResponse httpResponse) {
        log.info("Searching cities...");
        final Set<IbgeCity> ibgeCities = ibgeApiService.getIbgeCities();

        final ResponseFile responseFile = ResponseFileFactory.getResponseFile(fileType);
        final Set<ResponseLocality> responseLocalities = ibgeCities.stream().map(ibgeCity -> {
                final IbgeMesoregion ibgeMesoregion = ibgeCity.getIbgeMicroregion().getIbgeMesoregion();
                final IbgeState ibgeState = ibgeMesoregion.getIbgeState();
                final IbgeRegion ibgeRegion = ibgeState.getIbgeRegion();

                return new ResponseLocality(ibgeState.getId(), ibgeState.getAbbreviation(), ibgeRegion.getName(), ibgeCity.getName(), ibgeMesoregion.getName());
            }).collect(Collectors.toSet());

        responseFile.buildResponse(responseLocalities, httpResponse);
    }

    public Set<ResponseCity> getCitiesId(String cityName) {
        log.info("Searching cities...");
        final Set<IbgeCity> ibgeCities = ibgeApiService.getIbgeCitiesByName(cityName);
        return ibgeCities.stream().map(ibgeCity -> new ResponseCity(ibgeCity.getId())).collect(Collectors.toSet());
    }
}

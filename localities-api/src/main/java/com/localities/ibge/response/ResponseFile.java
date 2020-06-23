package com.localities.ibge.response;

import com.localities.ibge.model.ResponseLocality;

import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public interface ResponseFile {
    void buildResponse(Set<ResponseLocality> responseLocalities, HttpServletResponse httpResponse);
}

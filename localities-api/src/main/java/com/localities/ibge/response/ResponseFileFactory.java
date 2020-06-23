package com.localities.ibge.response;

public class ResponseFileFactory {
    public static ResponseFile getResponseFile(String fileType) {
        switch (fileType) {
            case "csv":
                return new ResponseFileCsv();

            case "json":
                return new ResponseFileJson();
        }
        return null;
    }
}

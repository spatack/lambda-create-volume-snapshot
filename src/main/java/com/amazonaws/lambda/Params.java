package com.amazonaws.lambda;

/**
 * description
 *
 * @author jason lin 2019/02/21 10:20
 */
public class Params {
    String accessKey;
    String secretKey;
    String volumeId;
    String description;
    String region;

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getVolumeId() {
        return volumeId;
    }

    public void setVolumeId(String volumeId) {
        this.volumeId = volumeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Params{" +
                "accessKey='" + accessKey + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", volumeId='" + volumeId + '\'' +
                ", description='" + description + '\'' +
                ", region='" + region + '\'' +
                '}';
    }
}

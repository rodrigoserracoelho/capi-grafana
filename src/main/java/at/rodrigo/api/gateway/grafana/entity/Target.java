package at.rodrigo.api.gateway.grafana.entity;

import lombok.Data;

@Data
public class Target {
    private String expr;
    private String interval;
    private String legendFormat;
    private String refId;
}
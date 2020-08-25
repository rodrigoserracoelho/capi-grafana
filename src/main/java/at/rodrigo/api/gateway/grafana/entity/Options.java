package at.rodrigo.api.gateway.grafana.entity;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Options {
    private String colorMode;
    private String graphMode;
    private String justifyMode;
    private String orientation;
}

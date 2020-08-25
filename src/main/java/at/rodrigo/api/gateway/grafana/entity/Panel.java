package at.rodrigo.api.gateway.grafana.entity;

import lombok.Data;

import java.util.List;

@Data
public class Panel {
    private String datasource;
    private FieldConfig fieldConfig;
    private GridPos gridPos;
    private Integer id;
    private Options options;
    private String pluginVersion;
    private List<Target> targets;
    private String title;
    private String type;
}

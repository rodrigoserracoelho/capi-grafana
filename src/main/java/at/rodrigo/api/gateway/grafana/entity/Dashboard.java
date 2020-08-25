package at.rodrigo.api.gateway.grafana.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Dashboard {
    private Integer id;
    private String uid;
    private String title;
    private List<String> tags = new ArrayList<>();
    private String timezone;
    private Integer schemaVersion;
    private Integer version;
    private String refresh;
    private List<Panel> panels = new ArrayList<>();
    private Time time;
    private Integer folderId;
    private boolean overwrite;
}
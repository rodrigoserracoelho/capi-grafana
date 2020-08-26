package at.rodrigo.api.gateway.grafana.http;

public class GrafanaConstants {
    public static final String BROWSER = "browser";
    public static final String DEFAULT_TIME_FROM = "now-5m";
    public static final String DEFAULT_TIME_TO = "now";
    public static final int DEFAULT_DECIMAL_VALUE = 0;
    public static final int SCHEMA_VERSION = 16;
    public static final int DASHBOARD_VERSION = 0;
    public static final int FOLDER_ID = 0;
    public static final String PLUGIN_VERSION = "7.0.0";
    public static final String PANEL_TYPE = "graph";
    public static final String REFRESH = "5s";

    public static final int DEFAULT_GRIS_POS_H = 9;
    public static final int DEFAULT_GRIS_POS_W = 12;
    public static final int DEFAULT_GRIS_POS_X = 0;
    public static final int DEFAULT_GRIS_POS_Y = 0;

    public static final String COLOR_MODE = "value";
    public static final String GRAPH_MODE = "area";
    public static final String JUSTIFY_MODE = "auto";
    public static final String ORIENTATION = "auto";

    public static final String DEFAULT_REF_ID = "A";

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final String APPLICATION_TYPE_JSON = "application/json";
    public static final String GRAFANA_API_CALL_ERROR = "Error creating Grafana Dashboard. Error: {}";
}

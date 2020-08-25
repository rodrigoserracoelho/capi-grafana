package at.rodrigo.api.gateway.grafana.http;

import at.rodrigo.api.gateway.grafana.entity.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class GrafanaDashboardBuilder {

    private String grafanaEndpoint;
    private boolean basicAuthorization;
    private String grafanaUser;
    private String grafanaPassword;
    private String grafanaToken;

    public GrafanaDashboardBuilder(String grafanaEndpoint, boolean basicAuthorization, String grafanaUser, String grafanaPassword, String grafanaToken) {
        this.grafanaEndpoint = grafanaEndpoint;
        this.basicAuthorization = basicAuthorization;
        this.grafanaUser = grafanaUser;
        this.grafanaPassword = grafanaPassword;
        this.grafanaToken = grafanaToken;
    }

    public void buildDashboardObject(String title,
                               List<String> tags,
                               String refresh,
                               String datasource,
                               Integer panelId,
                               String expression,
                               String panelTitle) {

        Dashboard dashboard = new Dashboard();
        dashboard.setTitle(title);
        dashboard.setTags(tags);
        dashboard.setTimezone(GrafanaConstants.BROWSER);
        dashboard.setSchemaVersion(GrafanaConstants.SCHEMA_VERSION);
        dashboard.setVersion(GrafanaConstants.DASHBOARD_VERSION);
        dashboard.setRefresh(refresh);

        Time time = new Time();
        time.setFrom(GrafanaConstants.DEFAULT_TIME_FROM);
        time.setTo(GrafanaConstants.DEFAULT_TIME_TO);

        dashboard.setTime(time);
        dashboard.setFolderId(GrafanaConstants.FOLDER_ID);
        dashboard.setOverwrite(false);

        Panel panel = new Panel();
        panel.setDatasource(datasource);

        Defaults defaults = new Defaults();
        defaults.setDecimals(GrafanaConstants.DEFAULT_DECIMAL_VALUE);

        FieldConfig fieldConfig = new FieldConfig();
        fieldConfig.setDefaults(defaults);

        panel.setFieldConfig(fieldConfig);

        GridPos gridPos = new GridPos();
        gridPos.setH(GrafanaConstants.DEFAULT_GRIS_POS_H);
        gridPos.setW(GrafanaConstants.DEFAULT_GRIS_POS_W);
        gridPos.setX(GrafanaConstants.DEFAULT_GRIS_POS_X);
        gridPos.setY(GrafanaConstants.DEFAULT_GRIS_POS_Y);

        panel.setGridPos(gridPos);
        panel.setId(panelId);

        Options options = new Options();
        options.setColorMode(GrafanaConstants.COLOR_MODE);
        options.setGraphMode(GrafanaConstants.GRAPH_MODE);
        options.setJustifyMode(GrafanaConstants.JUSTIFY_MODE);
        options.setOrientation(GrafanaConstants.ORIENTATION);

        panel.setOptions(options);
        panel.setPluginVersion(GrafanaConstants.PLUGIN_VERSION);

        Target target = new Target();
        target.setExpr(expression);
        target.setInterval("");
        target.setLegendFormat("");
        target.setRefId(GrafanaConstants.DEFAULT_REF_ID);

        List<Target> targets = new ArrayList<>();
        targets.add(target);

        panel.setTargets(targets);
        panel.setTitle(panelTitle);
        panel.setType(GrafanaConstants.PANEL_TYPE);

        List<Panel> panels = new ArrayList<>();
        panels.add(panel);

        dashboard.setPanels(panels);

        GrafanaDashboard grafanaDashboard = new GrafanaDashboard();
        grafanaDashboard.setDashboard(dashboard);

        try {
            Response response = createDashboard(grafanaDashboard);
            if(!response.isSuccessful()) {
                log.info(GrafanaConstants.GRAFANA_API_CALL_ERROR, response.body().string());
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    private Response createDashboard(GrafanaDashboard grafanaDashboard) throws IOException {
        OkHttpClient client = new OkHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();
        RequestBody requestBody = RequestBody.create(objectMapper.writeValueAsString(grafanaDashboard), MediaType.parse(GrafanaConstants.APPLICATION_TYPE_JSON));

        Request.Builder requestBuilder = new Request.Builder().url(grafanaEndpoint);
        if(basicAuthorization && grafanaUser != null && grafanaPassword != null) {
            requestBuilder.addHeader(GrafanaConstants.AUTHORIZATION_HEADER, Credentials.basic(grafanaUser, grafanaPassword));
        } else if(grafanaToken != null) {
            requestBuilder.addHeader(GrafanaConstants.AUTHORIZATION_HEADER, GrafanaConstants.BEARER + grafanaToken);
        }

        requestBuilder.post(requestBody);
        Request request = requestBuilder.build();

        Call call = client.newCall(request);
        return call.execute();
    }
}

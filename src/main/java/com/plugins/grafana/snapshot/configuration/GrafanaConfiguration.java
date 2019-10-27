package com.plugins.grafana.snapshot.configuration;

import hudson.model.AbstractDescribableImpl;
import org.apache.commons.lang.StringUtils;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;

import java.io.Serializable;

import static hudson.Util.fixEmptyAndTrim;

public class GrafanaConfiguration extends AbstractDescribableImpl<GrafanaConfiguration>
        implements Serializable {


    private String grafanaUrl;
    private String grafanaToken;

    public String getGrafanaUrl() {
        return grafanaUrl;
    }

    public String getGrafanaToken() {
        return grafanaToken;
    }

    @DataBoundConstructor
    public GrafanaConfiguration() {
        // no args constructor
    }

    @Deprecated
    public GrafanaConfiguration(String grafanaUrl, String grafanaToken) {
        setGrafanaUrl(grafanaUrl);
        setGrafanaToken(grafanaToken);
    }

    public GrafanaConfiguration(GrafanaConfiguration toCopy) {
        this.grafanaUrl = toCopy.getGrafanaUrl();
        this.grafanaToken = toCopy.getGrafanaToken();
    }

    public GrafanaConfiguration mergeWithParent(GrafanaConfiguration parent) {
        if (parent == null) {
            return this;
        }
        GrafanaConfiguration result = new GrafanaConfiguration(this);
        if (StringUtils.isBlank(result.getGrafanaUrl())) {
            result.setGrafanaUrl(parent.getGrafanaUrl());
        }
        if (StringUtils.isBlank(result.getGrafanaToken())) {
            result.setGrafanaToken(parent.getGrafanaToken());
        }
        return result;
    }

    @DataBoundSetter
    public void setGrafanaUrl(String grafanaUrl) {
        this.grafanaUrl = normalizeUrl(fixEmptyAndTrim(grafanaUrl));
    }

    @DataBoundSetter
    public void setGrafanaToken(String grafanaToken) {
        this.grafanaToken = fixEmptyAndTrim(grafanaToken);
    }



    private String normalizeUrl(String url) {
        if (url == null) {
            return null;
        }

        if (url.endsWith("/")) {
            return url.substring(0, url.length() - 1);
        }

        return url;
    }
}

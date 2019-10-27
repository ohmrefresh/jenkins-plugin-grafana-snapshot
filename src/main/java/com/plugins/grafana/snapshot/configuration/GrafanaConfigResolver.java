package com.plugins.grafana.snapshot.configuration;

import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.ExtensionPoint;
import hudson.model.Item;

public abstract class GrafanaConfigResolver implements ExtensionPoint {

    @NonNull
    public abstract GrafanaConfiguration forJob(@NonNull Item job);
}



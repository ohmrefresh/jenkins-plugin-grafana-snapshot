package com.plugins.grafana.snapshot.configuration;


import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.Extension;
import hudson.model.Item;
import jenkins.model.GlobalConfiguration;
import net.sf.json.JSONObject;
import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundSetter;
import org.kohsuke.stapler.StaplerRequest;

@Extension
@Symbol("grafanaSnapshotConfig")
public class GlobalVaultConfiguration extends GlobalConfiguration {

    private GrafanaConfiguration configuration;

    @NonNull
    public static GlobalVaultConfiguration get() {
        GlobalVaultConfiguration instance = GlobalConfiguration.all()
                .get(GlobalVaultConfiguration.class);
        if (instance == null) {
            throw new IllegalStateException();
        }
        return instance;
    }

    public GlobalVaultConfiguration() {
        load();
    }

    public GrafanaConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public boolean configure(StaplerRequest req, JSONObject json) throws FormException {
        req.bindJSON(this, json);
        return true;
    }

    @DataBoundSetter
    public void setConfiguration(GrafanaConfiguration configuration) {
        this.configuration = configuration;
        save();
    }

    @Extension(ordinal = 0)
    public static class ForJob extends GrafanaConfigResolver {

        @NonNull
        @Override
        public GrafanaConfiguration forJob(@NonNull Item job) {
            return GlobalVaultConfiguration.get().getConfiguration();
        }
    }

    protected Object readResolve() {

        return this;
    }

}

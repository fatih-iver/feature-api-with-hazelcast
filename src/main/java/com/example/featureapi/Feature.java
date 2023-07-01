package com.example.featureapi;

import org.togglz.core.context.FeatureContext;
import org.togglz.core.user.FeatureUser;
import org.togglz.core.user.SimpleFeatureUser;
import org.togglz.core.user.thread.ThreadLocalUserProvider;

public enum Feature implements org.togglz.core.Feature {

    NEW_WEBSITE;

    public boolean isActive() {
        return FeatureContext.getFeatureManager().isActive(this);
    }

    public boolean isActive(String user) {
        FeatureUser featureUser = new SimpleFeatureUser(user);
        ThreadLocalUserProvider.bind(featureUser);
        boolean isActive = isActive();
        ThreadLocalUserProvider.release();
        return isActive;
    }
}

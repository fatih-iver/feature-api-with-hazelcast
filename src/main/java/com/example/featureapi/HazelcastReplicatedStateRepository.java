package com.example.featureapi;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.replicatedmap.ReplicatedMap;
import org.togglz.core.Feature;
import org.togglz.core.repository.FeatureState;
import org.togglz.core.repository.StateRepository;

public class HazelcastReplicatedStateRepository implements StateRepository {

    private final StateRepository delegate;
    private final ReplicatedMap<String, FeatureState> replicatedMap;

    public HazelcastReplicatedStateRepository(StateRepository delegate, HazelcastInstance hazelcastInstance) {
        this.delegate = delegate;
        this.replicatedMap = hazelcastInstance.getReplicatedMap("togglz");
    }

    @Override
    public FeatureState getFeatureState(Feature feature) {
        String featureName = feature.name();
        FeatureState featureState = replicatedMap.get(featureName);

        if (featureState != null) {
            System.out.println("Feature will be served from Hazelcast: " + featureName);
            return featureState;
        }

        featureState = delegate.getFeatureState(feature);

        if (featureState != null) {
            System.out.println("Feature will be cached in Hazelcast: " + featureName);
            replicatedMap.put(featureName, featureState.copy());
        }

        return featureState;
    }

    @Override
    public void setFeatureState(FeatureState featureState) {
        delegate.setFeatureState(featureState);
        String featureName = featureState.getFeature().name();
        System.out.println("Feature will be cached in Hazelcast: " + featureName);
        replicatedMap.put(featureName, featureState.copy());
    }
}

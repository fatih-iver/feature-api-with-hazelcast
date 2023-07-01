package com.example.featureapi;

import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.togglz.core.manager.EnumBasedFeatureProvider;
import org.togglz.core.repository.StateRepository;
import org.togglz.core.repository.jdbc.JDBCStateRepository;
import org.togglz.core.spi.FeatureProvider;
import org.togglz.core.user.UserProvider;
import org.togglz.core.user.thread.ThreadLocalUserProvider;

import javax.sql.DataSource;

@Configuration
public class TogglzConfiguration {

    @Bean
    FeatureProvider featureProvider() {
        return new EnumBasedFeatureProvider(Feature.class);
    }

    @Bean
    public StateRepository stateRepository(DataSource dataSource, HazelcastInstance hazelcastInstance) {
        return new HazelcastReplicatedStateRepository(new JDBCStateRepository(dataSource), hazelcastInstance);
    }

    @Bean
    UserProvider userProvider() {
        return new ThreadLocalUserProvider();
    }

}

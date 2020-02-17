package edu.uta.cse.serveme.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @author housirvip
 */
@Slf4j
@Configuration
public class FireBaseConfig {

    @Value("${firebase.database}")
    private String database;

    @Bean
    public FirebaseOptions firebaseOptions() throws IOException {
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.getApplicationDefault())
                .setDatabaseUrl(database)
                .build();

        FirebaseApp.initializeApp();

        return options;
    }
}

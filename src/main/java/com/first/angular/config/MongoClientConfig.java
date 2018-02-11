package com.first.angular.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;

@Configuration
public class MongoClientConfig {

    @Autowired
    private MongoConfig config;

    /**
     * 必须设置 primary ,否则无法设置多源数据.
     */
    @Primary
    @Bean // used for develop profile
    @Profile(value = "dev")
    public MongoDbFactory dbFactory() throws Exception {
        return new SimpleMongoDbFactory(
                new MongoClientURI("mongodb://" + config.getUsername() + ":" + config.getPassword() + "@"
                        + config.getFirstHost() + ":" + config.getPort() + "/" + config.getDatabase()));
    }

    @Primary
    @Bean
    @Profile(value = "dev")
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(dbFactory());
    }

    /**
     * test , prod profile.
     * 
     * @return MongoDB工厂
     * @throws Exception
     *             初始化异常
     */
    @Primary
    @Bean
    @Profile(value = { "test", "prod" })
    public MongoDbFactory mongo() throws Exception {

        List<ServerAddress> addresses = new ArrayList<ServerAddress>();
        ServerAddress addr = new ServerAddress(config.getFirstHost(), config.getPort());
        ServerAddress addr2 = new ServerAddress(config.getSecondHost(), config.getPort());
        addresses.add(addr);
        addresses.add(addr2);
        MongoClientOptions options = new MongoClientOptions.Builder().connectTimeout(config.getTimeout())
                .readPreference(ReadPreference.primaryPreferred()).build();
        MongoCredential mongoCredential = MongoCredential.createCredential(config.getUsername(), config.getDatabase(),
                config.getPassword().toCharArray());
        List<MongoCredential> credentialsList = new ArrayList<MongoCredential>();
        credentialsList.add(mongoCredential);
        MongoClient client = new MongoClient(addresses, credentialsList, options);

        return new SimpleMongoDbFactory(client, config.getDatabase());
    }

    @Primary
    @Bean
    @Profile(value = { "test", "prod" })
    public MongoTemplate getmongoTemplate() throws Exception {
        return new MongoTemplate(mongo());
    }
}

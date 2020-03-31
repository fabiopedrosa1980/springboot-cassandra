package br.com.pedrosa.springbootcassandra;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableCassandraRepositories(basePackages = "br.com.pedrosa")
public class AppConfig extends AbstractCassandraConfiguration {

    protected String keyspaceName = "mykeyspace";

    @Override
    protected String getKeyspaceName() {
        return this.keyspaceName;
    }

    @Override
    protected List getKeyspaceCreations() {
        return Collections.singletonList(CreateKeyspaceSpecification
                .createKeyspace(keyspaceName).ifNotExists()
                .with(KeyspaceOption.DURABLE_WRITES, true)
                .withSimpleReplication());
    }

    @Override
    protected List getStartupScripts() {
        return Collections.singletonList("CREATE KEYSPACE IF NOT EXISTS "
                + keyspaceName + " WITH replication = {"
                + " 'class': 'SimpleStrategy', "
                + " 'replication_factor': '3' " + "};");

    }
}

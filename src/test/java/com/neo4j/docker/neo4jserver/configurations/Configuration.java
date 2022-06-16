package com.neo4j.docker.neo4jserver.configurations;


import com.neo4j.docker.utils.Neo4jVersion;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumMap;
import java.util.Map;

enum Setting{
    MEMORY_HEAP_MAXSIZE,
    MEMORY_PAGECACHE_SIZE,
    MEMORY_HEAP_INITIALSIZE,
    DIRECTORIES_LOGS,
    DIRECTORIES_DATA,
    DIRECTORIES_METRICS,
    CLUSTER_DISCOVERY_ADDRESS,
    CLUSTER_TRANSACTION_ADDRESS,
    CLUSTER_RAFT_ADDRESS,
    JVM_ADDITIONAL,
    SECURITY_PROCEDURES_UNRESTRICTED,
    TXLOG_RETENTION_POLICY,
    DEFAULT_LISTEN_ADDRESS,
    LOGS_GC_ROTATION_KEEPNUMBER

    }

class Configuration
{
    private static Map<Setting,Configuration> CONFIGURATIONS_5X = new EnumMap<>( Setting.class ) {{
        put( Setting.MEMORY_HEAP_MAXSIZE, new Configuration( "server.memory.heap.max_size"));
        put( Setting.MEMORY_PAGECACHE_SIZE, new Configuration("server.memory.pagecache.size"));
        put( Setting.MEMORY_HEAP_INITIALSIZE, new Configuration("server.memory.heap.initial_size"));
        put( Setting.DIRECTORIES_LOGS, new Configuration("server.directories.logs"));
        put( Setting.DIRECTORIES_DATA, new Configuration("server.directories.data"));
        put( Setting.DIRECTORIES_METRICS, new Configuration("server.directories.metrics"));
        put( Setting.CLUSTER_DISCOVERY_ADDRESS, new Configuration("server.discovery.advertised_address"));
        put( Setting.CLUSTER_TRANSACTION_ADDRESS, new Configuration("server.cluster.advertised_address"));
        put( Setting.CLUSTER_RAFT_ADDRESS, new Configuration("server.cluster.raft.advertised_address"));
        put( Setting.JVM_ADDITIONAL, new Configuration("server.jvm.additional"));
        put( Setting.SECURITY_PROCEDURES_UNRESTRICTED, new Configuration("dbms.security.procedures.unrestricted"));
        put( Setting.TXLOG_RETENTION_POLICY, new Configuration("db.tx_log.rotation.retention_policy"));
        put( Setting.DEFAULT_LISTEN_ADDRESS, new Configuration("server.default_listen_address"));
        put( Setting.LOGS_GC_ROTATION_KEEPNUMBER, new Configuration( "server.logs.gc.rotation.keep_number"));
    }};

    private static Map<Setting,Configuration> CONFIGURATIONS_4X = new EnumMap<>( Setting.class ) {{
        put( Setting.MEMORY_HEAP_MAXSIZE, new Configuration("dbms.memory.heap.max_size"));
        put( Setting.MEMORY_PAGECACHE_SIZE, new Configuration("dbms.memory.pagecache.size"));
        put( Setting.MEMORY_HEAP_INITIALSIZE, new Configuration("dbms.memory.heap.initial_size"));
        put( Setting.DIRECTORIES_LOGS, new Configuration("dbms.directories.logs"));
        put( Setting.DIRECTORIES_DATA, new Configuration("dbms.directories.data"));
        put( Setting.DIRECTORIES_METRICS, new Configuration("dbms.directories.metrics"));
        put( Setting.CLUSTER_DISCOVERY_ADDRESS, new Configuration("causal_clustering.discovery_advertised_address"));
        put( Setting.CLUSTER_TRANSACTION_ADDRESS, new Configuration("causal_clustering.transaction_advertised_address"));
        put( Setting.CLUSTER_RAFT_ADDRESS, new Configuration("causal_clustering.raft_advertised_address"));
        put( Setting.JVM_ADDITIONAL, new Configuration("dbms.jvm.additional"));
        put( Setting.SECURITY_PROCEDURES_UNRESTRICTED, new Configuration("dbms.security.procedures.unrestricted"));
        put( Setting.TXLOG_RETENTION_POLICY, new Configuration("dbms.tx_log.rotation.retention_policy"));
        put( Setting.DEFAULT_LISTEN_ADDRESS, new Configuration("dbms.default_listen_address"));
        put( Setting.LOGS_GC_ROTATION_KEEPNUMBER, new Configuration( "dbms.logs.gc.rotation.keep_number"));
    }};

    public static Map<Setting,Configuration> getConfigurationNameMap( Neo4jVersion version )
    {
        if(version.isOlderThan( Neo4jVersion.NEO4J_VERSION_500 ))
        {
            return CONFIGURATIONS_4X;
        }
        else return CONFIGURATIONS_5X;
    }

    public static Path getConfigurationResourcesFolder( Neo4jVersion version )
    {
        if(version.isOlderThan( Neo4jVersion.NEO4J_VERSION_500 ))
        {
            return Paths.get( "src", "test", "resources", "confs", "before50");
        }
        else return Paths.get("src", "test", "resources", "confs");
    }
    
    public String name;
    public String envName;

    private Configuration( String name )
    {
        this.name = name;
        this.envName = "NEO4J_" + name.replace( '_', '-' )
                                      .replace( '.', '_')
                                      .replace( "-", "__" );
    }
}

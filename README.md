# Tiamat

Module also known as the backend for "Holdeplassregisteret"

# Build
 ```mvn clean install```

# Local run
 ```mvn spring-boot:run```

# Run with external properties file and PostgreSQL
To run with PostgreSQL you ned an external application.properties.
Below is an example of application.properties:
```
spring.jpa.database=POSTGRESQL
spring.datasource.platform=postgres
spring.jpa.show-sql=false
spring.jpa.hibernate.ddl-auto=create
spring.database.driverClassName=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://localhost:5435/tiamat
spring.datasource.username=tiamat
spring.datasource.password=<PASSWORD>
spring.datasource.initializationFailFast=false
spring.profiles.active=default
spring.jpa.properties.hibernate.dialect=org.hibernate.spatial.dialect.postgis.PostgisDialect

peliasReverseLookupEndpoint=https://beta.rutebanken.org/apiman-gateway/rutebanken/geocoder/1.0/reverse
logging.level.no.rutebanken.tiamat.pelias=TRACE

```

To start Tiamat with this configuration, specify **spring.config.location**:

```mvn spring-boot:run -Dspring.config.location=/path/to/application.properties```


## HikariCP
Tiamat is using HikariCP. Most properties shoul be be possible to be specified in in application.properties, like `spring.datasource.initializationFailFast=false`. More information here. https://github.com/brettwooldridge/HikariCP/wiki/Configuration
See also http://stackoverflow.com/a/26514779

# Postgres
There is a PostgreSQL docker container in vagrant. It can be provisioned by using the tag **rb**:

```ONLY_TAGS=rb PLAY=build vagrant provision```
```ONLY_TAGS=rb PLAY=run vagrant provision```

# Run with in-memory GeoDB and bootstrap generation of data from GTFS stops.txt
```mvn spring-boot:run -Dspring.profiles.active=geodb,bootstrap -Dspring.config.location=src/test/resources/application.properties```

# Run with in-memory GeoDB without bootstrapped data from GTFS stops.txt
```mvn spring-boot:run -Dspring.config.location=src/test/resources/application.properties```
(default profiles are set in internal application.properties)

# Run with external config **and** bootstrap data from GTFS:
Can be used with an empty PostgreSQL.
```mvn spring-boot:run -Dspring.profiles.active=bootstrap -Dspring.config.location=/path/to/application.properties```


# Run Keycloak
Bot Tiamat and Abzu are set up to be used with Keycloak. Currently, Keycloak is not running in vagrant so we have to run it standalone. *Currently disabled, see NRP-16*

* Download Keycloak version 1.7.0.CR1 (or newer)
* Change the port in standalone/configuration/standalone.xml** to 18080 : ```{jboss.http.port:18080}```
* ```git pull``` devsetup.
* run:```bin/standalone.sh -Dkeycloak.migration.action=import -Dkeycloak.migration.provider=dir -Dkeycloak.migration.dir=/path/to/git/devsetup/vagrant/provisioning/roles/keycloak/files/ -Dkeycloak.migration.strategy=OVERWRITE_EXISTING```

# Docker image
 ```mvn -Pf8-build```

# Run the docker image in, eh, docker
choose **one** of:

* ```mvn docker:start```
* ```docker run -it rutebanken/tiamat:0.0.1-SNAPSHOT```

For more docker plugin goals, see: http://ro14nd.de/docker-maven-plugin/goals.html


# Export *ALL* data from Tiamat
*Not that you need to run this with enough memory available, or else you might run into **java.lang.OutOfMemoryError: GC overhead limit exceeded**. Exactly how much memory should be tested.*

```
curl -H"Accept: application/xml" -H"Content-type: application/xml" -XGET http://nhr.rutebanken.org/jersey/site_frame > netex_site_frame_stop_places.xml
```

# Import data into Tiamat

Clean existing data in postgresql (streamline if frequently used):
```
TRUNCATE stop_place CASCADE;
TRUNCATE quay CASCADE;
TRUNCATE topographic_place CASCADE;
```

```
curl --max-time 6000 -H"Accept: application/xml" -H"Content-type: application/xml" -XPOST -d@netex_site_frame_stop_places.xml http://nhr.rutebanken.org/jersey/site_frame
```


*Note that the import above is somewhat fragile. It is developed during the proof of concept. For instance, it does allow you to call the import multiple times. It also might happen that you loose the connection, but the import continues to run in Tiamat. Please monitor the logs of Tiamat while using the import.*


# See also
https://rutebanken.atlassian.net/wiki/display/REIS/Holdeplassregister

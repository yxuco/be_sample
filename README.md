# BE CICD Sample

### Install TIBCO BusinessEvents jars into local Maven repository

The Java utilities in this sample uses 3 jars from the TIBCO BusinesEvents library.

 - $BE_HOME/lib/cep-common.jar
 - $BE_HOME/lib/cep-kernel.jar
 - $BE_HOME/lib/be-functions.jar

They are not available in Maven Central, and so you need to install them into your local Maven repository using the following commands:

    mvn install:install-file -Dfile=$BE_HOME/lib/cep-common.jar -DgroupId=com.tibco.be \
    -DartifactId=cep-common -Dversion=5.4.1 -Dpackaging=jar
    mvn install:install-file -Dfile=$BE_HOME/lib/cep-kernel.jar -DgroupId=com.tibco.be \
    -DartifactId=cep-kernel -Dversion=5.4.1 -Dpackaging=jar
    mvn install:install-file -Dfile=$BE_HOME/lib/be-functions.jar -DgroupId=com.tibco.be \
    -DartifactId=be-functions -Dversion=5.4.1 -Dpackaging=jar

## Build the sample

After you clone this project to, e.g., $workspace/be, you can build all components of this sample from the project root

    cd $workspace/be
    mvn clean install

This will build all components and execute unit tests of the following 5 components:

 - BEAssert: Java implementation of BE catalog functions for Hamcrest matcher functions;
 - BEStats: Java implementation of BE catalog functions for collecting performance stats and storing stats in a time-series database, i.e., InfluxDB;
 - BETestClient: Java helper functions for wrapping and executing BE unit tests as Java jUnit tests;
 - BETestLib: BE projlib designed to provide an HTTP service for processing jUnit test requests; This sample demonstrates the approach of implementing BE unit tests inside the BE project, and driving the tests using a jUnit wrapper;  It also demonstrates the use of Maven to build BE projlibs;
 - SimpleHTTP: BE sample to demonstrate the approach of implementing BE unit tests in Java, and executing jUnit tests using API-mode of BE engines.

## The author

Yueming is a Sr. Architect working at [TIBCO](http://www.tibco.com/) Architecture Service Group.

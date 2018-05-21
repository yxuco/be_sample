# BEAssert
This Java utility supports the testing of [TIBCO BusinessEvents](https://docs.tibco.com/products/tibco-businessevents-5-4-1) (BE) applications using [JUnit](http://junit.org/).  It implements BE catalog functions for assertion.  It wraps most core assert functions from [JUnit](https://github.com/junit-team/junit) and [Hamcrest](https://github.com/hamcrest/JavaHamcrest), and implements some extensions for BE applications.  This utility is used to demonstrate a BE test framework in the project [BETestLib](https://github.com/yxuco/be_sample/tree/master/BETestLib).

## Dependencies

Following jars from TIBCO BusinesEvents installation are used by this utility.

 - $BE_HOME/lib/cep-common.jar
 - $BE_HOME/lib/cep-kernel.jar
 - $BE_HOME/lib/be-functions.jar
 
They are not available in Maven Central, so, you need to install them into your local Maven repository using the following command:

    mvn install:install-file -Dfile=$BE_HOME/lib/cep-common.jar -DgroupId=com.tibco.be \
    -DartifactId=cep-common -Dversion=5.4.1 -Dpackaging=jar
    mvn install:install-file -Dfile=$BE_HOME/lib/cep-kernel.jar -DgroupId=com.tibco.be \
    -DartifactId=cep-kernel -Dversion=5.4.1 -Dpackaging=jar
    mvn install:install-file -Dfile=$BE_HOME/lib/be-functions.jar -DgroupId=com.tibco.be \
    -DartifactId=be-functions -Dversion=5.4.1 -Dpackaging=jar

## Build the utility

In your workspace,

    cd BEAsert
    mvn clean install

The Maven build should be successful.  This step downloads dependency packages from [Maven Central](http://search.maven.org/), executes unit tests, and builds `beassert-1.0.0.jar` which is required to run the [BETestLib](https://github.com/yxuco/be_sample/tree/master/BETestLib) project.

The jar file is build in `$WORKSPACE/BEAssert/target/beassert-1.0.0.jar`, and it is also installed in your local Maven repository `~/.m2/repository/com/tibco/psg/beassert/1.0.0/beassert-1.0.0.jar`.

## Development using Eclipse
 
You may also edit and build it using either a standalone Eclipse, or the TIBCO BusinessEvents Studio.

 - Launch the TIBCO BusinessEvents Studio, for example.
 - Pulldown **File** menu and select **Import...**
 - In the **Import** dialog, select **Existing Maven Projects**, then click **Next >** button.
 - In the **Import Maven Projects** dialog, browse for **Root Directory**, select and open the `BEAssert` folder under your workspace.
 - Confirm that `$workspace/BEAssert` is populated as the **Root Directory**, then click the **Finish** button.
 - In **Package Explorer**, highlight the root folder of the imported project `BEAssert`, and pulldown **Window** menu to open the Java Perspective.
 - In **Package Explorer**, open the `src/test/java` folder, select the file `MatchAssertTest.java`, right-click it and select the popup menu **Run As** -> **JUnit Test**.
 - You should see the famous Green/Red bar in the JUnit test panel.
 - Right-click root folder `BEAssert` and select the popup menu **Run As -> Maven install**, this will package and install the jar in your local Maven repository.

## The author

Yueming is a Sr. Architect working at [TIBCO](http://www.tibco.com/) Architecture Service Group.

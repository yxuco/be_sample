# beassert
This Java utility supports the testing of [TIBCO BusinessEvents](https://docs.tibco.com/products/tibco-businessevents-5-2-0) (BE) applications using [JUnit](http://junit.org/).  It implements BE catalog functions for assertion.  It wraps most core assert functions from [JUnit](https://github.com/junit-team/junit) and [Hamcrest](https://github.com/hamcrest/JavaHamcrest), and implements some extensions for BE applications.  This utility is used to demonstrate a BE test framework by the demo project [DataTypeDemo](https://github.com/yxuco/DataTypeDemo).

## Dependencies
This utility is used by the following 2 projects to demonstrate BE testing, so check them out to understand the usage of the assertion functions in this utility.

 - [DataTypeDemo](https://github.com/yxuco/DataTypeDemo)
 - [beunit](https://github.com/yxuco/beunit)
 
#### Maven

This is a Maven project, and so if Maven has not been installed on your system, you'll need to install Maven and Git as described in the [beunit](https://github.com/yxuco/beunit) project.
    
#### Clone this project from GitHub

In the root folder of your workspace, clone the project using command

    git clone https://github.com/yxuco/beassert.git

It should download the source code to the folder `beassert` in your workspace. 

#### Install TIBCO BusinessEvents jars into local Maven repository

2 jars from TIBCO BusinesEvents installation are used by this utility.

 - $BE_HOME/lib/cep-common.jar
 - $BE_HOME/lib/cep-kernel.jar
 
They are not available in Maven Central, so, you need to install them into your local Maven repository using the following command:

    mvn install:install-file -Dfile=$BE_HOME/lib/cep-common.jar -DgroupId=com.tibco.be \
    -DartifactId=cep-common -Dversion=5.4.1 -Dpackaging=jar
    mvn install:install-file -Dfile=$BE_HOME/lib/cep-kernel.jar -DgroupId=com.tibco.be \
    -DartifactId=cep-kernel -Dversion=5.4.1 -Dpackaging=jar
    mvn install:install-file -Dfile=$BE_HOME/lib/be-functions.jar -DgroupId=com.tibco.be \
    -DartifactId=be-functions -Dversion=5.4.1 -Dpackaging=jar

## Build the utility

In your workspace,

    cd beassert
    mvn clean install

The Maven build should be successful.  This step downloads dependency packages from [Maven Central](http://search.maven.org/), executes unit tests, and builds `beassert-1.0.jar` which is required to run the [DataTypeDemo](https://github.com/yxuco/DataTypeDemo) project.

The jar file is build in `$WORKSPACE/beassert/target/beassert-1.0.jar`, and it is also installed in your local Maven repository `~/.m2/repository/com/tibco/psg/beassert/1.0/beassert-1.0.jar`.

## Development using Eclipse
 
You may also edit and build it using either a standalone Eclipse, or the TIBCO BusinessEvents Studio.

 - Launch the TIBCO BusinessEvents Studio, for example.
 - Pulldown **File** menu and select **Import...**
 - In the **Import** dialog, select **Existing Maven Projects**, then click **Next >** button.
 - In the **Import Maven Projects** dialog, browse for **Root Directory**, select and open the `beassert` folder under your workspace.
 - Confirm that `your-workspace/beassert` is populated as the **Root Directory**, then click the **Finish** button.
 - In **Package Explorer**, highlight the root folder of the imported project `beassert`, and pulldown **Window** menu to open the Java Perspective.
 - In **Package Explorer**, open the `src/test/java` folder, select the file `MatchAssertTest.java`, right-click it and select the popup menu **Run As** -> **JUnit Test**.
 - You should see the famous Green/Red bar in the JUnit test panel.
 - Right-click root folder `beassert` and select the popup menu **Run As -> Maven install**, this will package and install the jar in your local Maven repository.

## The author

Yueming is a Sr. Architect working at [TIBCO](http://www.tibco.com/) Architecture Service Group.

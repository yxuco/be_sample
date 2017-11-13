This is a sample project of [TIBCO BusinessEvents](https://docs.tibco.com/products/tibco-businessevents-5-2-0).  It illustrates the use of various data types in TIBCO BusinessEvents (BE) applications.  The main purpose of this project is, however, to demonstrate a framework for testing BE applications.

## Unit test framework for TIBCO BusinessEvents

To support Test-Driven development (TDD) and Continuous Delivery (CD), we need a test framework to automate the unit tests for individual rules and rule-functions in BE applications.  This project illustrates a light-weight framework for developers to write unit tests within the same BE application project, and expose all tests as a uniform REST API.  The Java project [beunit](https://github.com/yxuco/beunit) illustrates how to use [JUnit](http://junit.org/) to run tests and collect BE test results via the REST API of this test framework.  With the help of [Maven](https://maven.apache.org/) and [Jenkins](https://jenkins-ci.org/), we can include the unit test as a step in the CD process.

## Prerequisites

It is tested with TIBCO BusinessEvents 5.2.0, but the framework may work with earlier version of BE as well.  If you are a licensed TIBCO customer, you can download and install the following BE packages from [TIBCO eDelivery](https://edelivery.tibco.com):
 - [TIBCO BusinessEvents Standard Edition](https://edelivery.tibco.com/storefront/eval/tibco-businessevents-standard-edition/prod10052.html)
 - [TIBCO BusinessEvents Data Modeling](https://edelivery.tibco.com/storefront/eval/tibco-businessevents-data-modeling/prod10354.html)
 - [TIBCO BusinessEvents Decision Manager](https://edelivery.tibco.com/storefront/eval/tibco-businessevents-decision-manager/prod10355.html)
 - [TIBCO BusinessEvents Stream Processing](https://edelivery.tibco.com/storefront/eval/tibco-businessevents-event-stream-processing/prod10353.html)
 
## Install and configure

#### Clone the project from GitHub

If Git has not been installed, please check the notes in [beunit](https://github.com/yxuco/beunit) to install and configure Git and Maven.

In the root folder of your workspace, clone the project using the command

    git clone https://github.com/yxuco/BETestLib.git

It should download the source code to the `BETestLib` folder under your workspace.

#### BE catalog functions for Assert

To write readable tests with assertion, we wrapped and extended the [JUnit](http://junit.org/) and [Hamcrest](http://hamcrest.org/JavaHamcrest/) core assertion APIs as BusinessEvents catalog functions.  These functions are implemented by the project [beassert](https://github.com/yxuco/beassert).  So, before continue, you need to download and build [beassert](https://github.com/yxuco/beassert) according to the instructions there.

#### Import project into BusinessEvents Studio

Launch the TIBCO BusinessEvents Studio, import and configure the BE project as follows.

 - Pulldown **File** menu and select **Import...**
 - In the **Import** dialog, select **Existing TIBCO BusinessEvents Studio Project**, then click **Next >** button.
 - In the **Existing TIBCO BusinessEvents Project Import Wizard** dialog, browse for **Existing project root directory**, select and open the `BETestLib` folder under your workspace.
 - Confirm that `your-workspace/BETestLib` is populated as the **Existing project root directory**, uncheck `Copy project into workspace`, then click the **Finish** button.
 - If the **BusinessEvents Studio Development** perspective is not open, in **Package Explorer** or **Studio Explorer**, highlight the root of the imported project `BETestLib`, and pulldown **Window** menu to open the **BusinessEvents Studio Development Perspective**.
 - Right-click the project root `BETestLib` and select popup menu **Properties**.  Highlight **Build Path** and in **Java Libraries** panel, add a Third Party library `beassert-1.0.jar`, which is built by the project [beassert](https://github.com/yxuco/beassert).
 - In **Studio Explorer**, highlight the project root folder `BETestLib`, pulldown **Project** menu and select **Clean...**.
 - In **Studio Explorer**, right-click the project root folder and select the popup menu **Build Enterprise Archive...**.
 - In **Building Enterprise Archive...** dialog, select a **File Location** for the EAR file, e.g., `/tmp/BETestLib.ear`, then click the **OK** button.
 - If you see a dialog "`The Enterprise Archive file was built correctly`", you are ready to start the BE engine. (Note that if the build failed because you rebuilt the `beassert-1.0.jar`, you may have to restart the BusinessEvents Studio, and execute the **Clean...** step again.)
 - To start the BE engine in BusinessEvents Studio, in the "run configuration", add 3 jars to the classpath: `junit-4.12.jar`, `hamcrest-core-1.3.jar`, and `beassert-1.0.jar`.  All 3 jars can be found in the Maven local repository, which is by default, ~/.m2/repository.
 - To start the BE engine from command-line, use the following command from where you generated the `BETestLib.ear` file.  **Note:** edit the `be-engine.tra` to add the 3 jar files to the classpath.

```
${BE_HOME}/bin/be-engine --propFile ${BE_HOME}/bin/be-engine.tra -u default \
-c ${WORKSPACE}/BETestLib/Deployments/BETestLib.cdd BETestLib.ear
```

where `BE_HOME` is the root folder of the BE product installation, e.g., `/usr/local/tibco/be/5.2`, and `WORKSPACE` is the workspace folder where you cloned the `BETestLib` project.

#### Execute JUnit tests in BusinessEvents Studio

While the BE engine is running, you can launch the JUnit tests in the BusinessEvents Studio as described in the [beunit](https://github.com/yxuco/beunit) project.  Change to Java Perspective when you run the JUnit tests.  Congratulations if you see a Green bar in the JUnit test panel, indicating success of all test cases.  

Please email or open issues if there is any problem with this project.  To get started on using this test framework, you may check out the [tutorial](https://github.com/yxuco/betest_tutorial).

**Note:** On my Mac, BusinessEvents Studio sometimes hangs during startup due to Eclipse cached state.  If you experience the same, you may try to start it from command-line using the following flags:

    cd $BE_HOME/studio/eclipse/studio.app/Contents/MacOS
    ./studio -clean -clearPersistedState

## The author

Yueming is a Sr. Architect working at [TIBCO](http://www.tibco.com/) Architecture Service Group.
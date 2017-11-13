# beunit
This is a Java utility that uses [JUnit](http://junit.org/) to test [TIBCO BusinessEvents](https://docs.tibco.com/products/tibco-businessevents-5-2-0) (BE) applications.  It contains JUnit tests for demonstrating a BE test framework illustrated by the BE project [DataTypeDemo](https://github.com/yxuco/DataTypeDemo).  The DataTypeDemo application exposes BE test cases as a REST API, while this project demonstrates how to use JUnit test runner to invoke the REST API, execute BE tests and display results in the TIBCO BusinessEvents Studio or a standalone [Eclipse](https://eclipse.org/).  Since JUnit is integrated with almost all tools for Continuous Integration (CI), these tests can be executed by, e.g., [Maven](https://maven.apache.org/) or [Jenkins](https://jenkins-ci.org/).

## Dependencies

#### Maven
This is a Maven project, and so if maven has not been installed on your system, you'll need to follow the instructions at [Maven Download](https://maven.apache.org/download.cgi) to download and install Maven.

On my Mac, I unzipped the Maven binary distribution in `/usr/local/apache-maven-3.2.1`, and then created a symbolic link

    ln -s /usr/local/apache-maven-3.2.1 /usr/local/maven

I then added the following Maven environment variables in `~/.bash_profile`

    export JAVA_HOME=/usr/local/java
    export M2_HOME=/usr/local/maven
    export MAVEN_OPTS="-Xms256m -Xmx512m"
    export PATH=$M2_HOME/bin:$PATH

So if you use a different version of Maven, you can simply update the symbolic link.  Note that my `JAVA_HOME` is a symbolic link to `/Library/Java/JavaVirtualMachines/jdk1.7.0_51.jdk/Contents/Home`, which would likely be different from your environment.
After it is done, you may verify the correct Maven environment using the command

    mvn --version

It should print out something similar to

    Apache Maven 3.2.1 (ea8b2b07643dbb1b84b6d16e1f08391b666bc1e9; 2014-02-14T10:37:52-07:00)
    Maven home: /usr/local/maven
    Java version: 1.7.0_51, vendor: Oracle Corporation
    Java home: /Library/Java/JavaVirtualMachines/jdk1.7.0_51.jdk/Contents/Home/jre
    Default locale: en_US, platform encoding: UTF-8
    OS name: "mac os x", version: "10.10.3", arch: "x86_64", family: "mac"
    
#### Clone this project from GitHub
Check if Git is installed on your system using the command

    git --version

It should print out the version number of the Git installation, e.g.,

    git version 2.3.5

If necessary, you can download and install Git by following steps at [Installing Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git).

In the root folder of your workspace, clone the project using the command

    git clone https://github.com/yxuco/beunit.git

It should download the source code to the folder `beunit` in your workspace. Use the following command to verify your development environment

    cd beunit
    mvn clean compile

The Maven build should be successful, and this step also downloads dependency packages from the [Maven Central](http://search.maven.org/).

Before we can test and package this utility, we need to get the BE project [DataTypeDemo](https://github.com/yxuco/DataTypeDemo) running.

#### DataTypeDemo
[DataTypeDemo](https://github.com/yxuco/DataTypeDemo) is a BE application that we use to demonstrate the BE test framework.  All tests would fail if the DataTypeDemo process is not running.  So, follow the steps described in [DataTypeDemo](https://github.com/yxuco/DataTypeDemo) to configure and start the BE process.

After the `DataTypeDemo` process is started, you can test and package this utility as follows:

    cd beunit
    mvn clean package

## Execute JUnit tests in Eclipse
 
You may execute JUnit tests in either a standalone Eclipse, or the TIBCO BusinessEvents Studio.

 - Launch the TIBCO BusinessEvents Studio, for example.
 - Pulldown **File** menu and select **Import...**
 - In the **Import** dialog, select **Existing Maven Projects**, then click **Next >** button.
 - In the **Import Maven Projects** dialog, browse for **Root Directory**, select and open the `beunit` folder under your workspace.
 - Confirm that `your-workspace/beunit` is populated as the **Root Directory**, then click the **Finish** button.
 - In **Package Explorer**, highlight the root folder of the imported project `beunit`, and pulldown **Window** menu to open the Java Perspective.
 - In **Package Explorer**, open the `src/test/java` folder, select the file `DataTypeDemoTestSuite.java`, right-click it and select the popup menu **Run As** -> **JUnit Test**.
 - You should see the famous Green/Red bar in the JUnit test panel.  All tests would fail if the `DataTypeDemo` process is not running.

## The author

Yueming is a Sr. Architect working at [TIBCO](http://www.tibco.com/) Architecture Service Group.

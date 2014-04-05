app-direct-challenge
====================

AppDirect Integration Challenge

### Running locally

From the command line with Maven:

    $ cd app-direct-challenge
    $ mvn package
    $ java -jar target/dependency/webapp-runner.jar --port 8080 target/*.war

or

In your preferred IDE such as SpringSource Tool Suite (STS) or IDEA:

* Import app-direct-challenge as a Maven Project
* Drag-n-drop the project onto the "SpringSource tc Server Developer Edition" or another Servlet 2.5 or > Server to run, such as Tomcat.

Access the deployed web application at: http://localhost:8080/app-direct-challenge/

To get the code:
-------------------
Clone the repository:

    $ git clone git://github.com/jeanfleblanc/app-direct-challenge.git

If this is your first time using Github, review http://help.github.com to learn the basics.

Other info:
-------------------
   
### Building and Deploying

**TODO**

```xml

<!-- repository -->
<repositories>
  <repository>
    <id>scribe-java-mvn-repo</id>
    <url>https://raw.github.com/fernandezpablo85/scribe-java/mvn-repo/</url>
    <snapshots>
      <enabled>true</enabled>
      <updatePolicy>always</updatePolicy>
    </snapshots>
  </repository>
</repositories>

<!-- dependency -->
<dependency>
  <groupId>org.scribe</groupId>
  <artifactId>scribe</artifactId>
  <version>1.3.6</version>
</dependency>
```

## Getting started in less than 2 minutes

Check the [Getting Started](http://wiki.github.com/fernandezpablo85/scribe-java/getting-started) page and start rocking! Please Read the [FAQ](http://wiki.github.com/fernandezpablo85/scribe-java/faq) before creating an issue :)

## Questions?

Feel free to drop me an email, but there's already a [StackOverflow](http://stackoverflow.com) tag for [scribe](http://stackoverflow.com/questions/tagged/scribe) you should use. I'm subscribed to it so I'll pick the question immediately.
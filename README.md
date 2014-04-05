app-direct-challenge
====================

AppDirect Integration Challenge

### Running locally

1. Run mvn install to create WAR
2. Deploy in any JEE 5 Compatible Application Server

To get the code:
-------------------
Clone the repository:

    $ git clone git://github.com/SpringSource/spring-mvc-showcase.git

If this is your first time using Github, review http://help.github.com to learn the basics.

To run the application:
-------------------	
From the command line with Maven:

    $ cd spring-mvc-showcase
    $ mvn tomcat7:run

or

In your preferred IDE such as SpringSource Tool Suite (STS) or IDEA:

* Import spring-mvc-showcase as a Maven Project
* Drag-n-drop the project onto the "SpringSource tc Server Developer Edition" or another Servlet 2.5 or > Server to run, such as Tomcat.

Access the deployed web application at: http://localhost:8080/spring-mvc-showcase/

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
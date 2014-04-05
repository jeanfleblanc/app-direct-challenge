app-direct-challenge
====================

AppDirect Integration Challenge

To get the code:
-------------------
Clone the repository:

    $ git clone git://github.com/jeanfleblanc/app-direct-challenge.git

If this is your first time using Github, review [http://help.github.com](http://help.github.com) to learn the basics.

Running locally:
-------------------

To create a war and deploy the application from the command line with Maven:

    $ cd app-direct-challenge
    $ mvn package
    $ java -jar target/dependency/webapp-runner.jar --port 8080 target/*.war

or

In your preferred IDE such as SpringSource Tool Suite (STS) or IDEA:

* Import app-direct-challenge as a Maven Project
* Drag-n-drop the project onto the "SpringSource tc Server Developer Edition" or another Servlet 2.5 or > Server to run, such as Tomcat.

Access the deployed web application at: http://localhost:8080/app-direct-challenge/

Other info:
-------------------
   
You can access a running instance of this application at [http://ad-leblanc.herokuapp.com](http://ad-leblanc.herokuapp.com)    

Questions?
-------------------

Feel free to drop me an email!
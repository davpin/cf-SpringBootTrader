# cf-SpringBootTrader
Microservice version of the Spring Trader app using spring boot

![Spring Trader](/docs/springtrader2.png)

#Introduction
This repository holds a collection of micro services that work together to present a trading application surfaced though a web UI, but more interfaces can be created that re-utilise the microservices.

It was created to support workshops and demonstrations of building and using `microservices` architectures and running these in **Cloud Foundry** (although it is possible to run these on other runtimes).

The workshops follow a series of exercises, or labs, and you can find links to the guides for these exercises [below](#workshops).

##Table of Contents

1. [Architecture](#architecture)
2. [Deploying the application](#deployment)
3. [Workshops](#workshops)
4. [Demos](#demos)
5. [Roadmap](#roadmap)
6. [Contributing to the project](#contributing)


#Architecture
The system is composed of 4 microservices. The relationship between the microservices is illustrated below.

![architecture](/docs/microservices_relationship.png)

##1. Quote Microservice
This service is a spring boot application responsible for providing up to date company and ticker/quote information. It does this by providing a REST api with 2 calls:
* ``/quote/{symbol}``
Returns an up to date quote for the given symbol.
* ``/company/{search}``
Returns a list of companies that have the search parameter in their names or symbols.

This application has no dependencies apart from an external service - [markitondemand](http://dev.markitondemand.com/) - to retrieve the real time data.

##2. Account Microservice
This service is a spring boot application responsible for creating and managing user accounts.

It stores the accounts in a RDBMS store and uses a spring JPA respository to accomplish this. It provides several REST api calls for other services to consume its services.

##3. Portfolio Microservice
This service is a spring boot application responsible for managing portfolios - these are collections of holdings, which in turn are collection of orders on a particular share.

This service accepts orders (both BUY and SELL) and stores these in a RDBMS store - *it does not have to be the same RDBMS as the Account service, but it can be!* It provides REST api calls for other services to consume its services.

This service is dependent on the Account service above to ensure the logged in user has enough funds to buy stock as well as keeping the account funds up to date. It is also dependent on the Quote service to retrieve up to date quote information and calculate the current value of portfolios.

##4. Web Microservice
This service is a spring boot application providing the web interface.

The web interface is built using bootstrap and Thymeleaf and uses a Spring controller to delegate calls to the relevant services:
* Account service
* Quote service
* Portfolio service

#Deployment

To deploy the microservices please follow the guides of the [workshop below](#workshops).

Each guide includes instructions on how to deploy and run to:
  - Pivotal Cloud Foundry with [Spring Cloud Services for PCF](https://network.pivotal.io/products/p-spring-cloud-services) installed.
  - Cloud Foundry without Spring Cloud Services.
  - [Pivotal Web Services](http://run.pivotal.io)
  - local machine.

#Workshops:

The following guides describe how to setup the environment and deploy the microservices to **Cloud Foundry**.

At Pivotal we love education, not just educating ourselves, but also educating others. As such, these guides follow the *"teaching you how to fish"* principle - Rather than giving you line by line/command by command instructions, we provide guidelines and links to documentation where you can read and learn more.

1. [Setting up the environment] [setup]
2. [Creating a discovery service] [registry]
3. [Creating a circuit breaker dashboard] [circuitbreaker]
4. [creating the configuration service][configserver]
5. [Pushing the Quote service] [pushquote]
6. [Pushing all the services] [pushall]
7. [Scaling the services] [scale]
8. Auto Scaling - TODO: use autoscaler service!
9. [Blue/Green deployments] [bluegreen]
10. Monitor workshop - TODO: show how to monitor microservices using springbootadmin, ELK tile and Zipkin.


[setup]: docs/lab_setup.md
[registry]: docs/lab_registryserver.md
[circuitbreaker]: docs/lab_circuitbreaker.md
[configserver]: docs/lab_configserver.md
[pushquote]: docs/lab_pushquote.md
[pushall]: docs/lab_pushall.md
[scale]: docs/lab_scale.md
[bluegreen]: docs/lab_bluegreen.md

#Demos

###1. Deploying to **Pivotal Cloud Foundry**.
TODO: document a walk through of this demo. This is a walkthough of the workshop above.
- Deploying microservices to Cloud Foundry.
- Creating and binding services to applications.

###2. Service discovery.
TODO: document a walk through of this demo.

###3. Scalability
TODO: document a walk through of this demo.
- provide a load generator to mimic users.
- scale instances.

###4. Configuration Management.
TODO: document a walk through of this demo.

###5. Traceability across all services.
TODO: document a walk through of this demo using Zipkin and Kibana.

###6. Continuous Integration/Continuous Delivery.
TODO: document a walk through of this demo.

###7. Operations Demo
TODO: document how to operate/monitor several microservices, using Spring Boot Admin and ELK tile


#Features

- **Discovery service:**
  All microservices register with the [Discovery Service](http://cloud.spring.io/spring-cloud-netflix/spring-cloud-netflix.html) and discover other microservices through it.
- **Correlation/Traceability:**
  Traceability of requests through all the microservices. This is done using [Spring-cloud-sleuth](http://cloud.spring.io/spring-cloud-sleuth/).
- **Config Server:**
  The microservices obtain the configuration from a [Configuration Service](http://cloud.spring.io/spring-cloud-config/) backed by a git repository. This means that configuration is now auditables and version controlled, as well as providing the ability to refresh configuration during runtime.

#Roadmap

The roadmap for this project is constantly evolving. Please feel free to reach out with ideas.
- **Better APIs:**
  Better APIs with documentation that conform to some standard and logic.
- **Security:**
  Secure microservices with OAUTH2.
- **Monitoring/Operations:**
  Show how to monitor a distributed system comprising of multiple microservices.
  This will comprise of:
   - Spring Boot Admin
   - ELK tile
   - Zipkin
- **Stock analysis system:**
   A microservice, or set of microservices, to analyse stock and provide recommendations. Also, to show polyglot persistence as well as more sophisticated data workloads.
- **Mobile UI:**
  Mobile interface to expose the services on an iOS device and/or Android device natively, making use of the [Pivotal Cloud Foundry Mobile Services](http://docs.pivotal.io/mobile/index.html).

#Contributing
Everyone is encouraged to help improved this project.

Here are some ways you can contribute:

- by using alpha, beta, and prerelease versions
- by reporting bugs
- by suggesting new features
- by writing or editing documentation
- by writing code (no patch is too small: fix typos, add comments, clean up inconsistent whitespace)
- by refactoring code
- by closing [issues](https://github.com/dpinto-pivotal/cf-SpringBootTrader/issues)

##Submitting an Issue

We use the [GitHub issue tracker](https://github.com/dpinto-pivotal/cf-SpringBootTrader/issues) to track bugs and features. Before submitting a bug report or feature request, check to make sure it hasn't already been submitted. When submitting a bug report, please include any relevant information. Ideally, a bug report should include a pull request with failing specs, and maybe even a fix!

##Submitting a Pull Request

1. Fork the project.
2. Create a topic branch.
3. Implement your feature or bug fix.
4. Commit and push your changes.
5. Submit a pull request.

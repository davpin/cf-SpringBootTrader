# Creating the discovery service.

All our microservices will connect to a Service Registry. we will use the Discovery service provided by the [Spring Cloud Services for PCF](https://network.pivotal.io/products/p-spring-cloud-services) by Pivotal Cloud Foundry.

Underneath the covers, this discovery service is implemented using the [Spring Cloud Netflix - Eureka](http://cloud.spring.io/spring-cloud-netflix/).

As such, all we have to do to implement a discovery service is to create a service instance.
### Exercise

1. Log in to the Apps Manager through your browser. The URL will be: `https://console.<your_cloud_foundry_url>/`

Go the *Marketplace* and choose a *Service Registry for Pivotal Cloud Foundry*.

When prompted for the name of the service, insert **"discovery-service"** and bind it to the space you are using to deploy your applications.

> You can pick any name of the service, however, the service is already specified in the manifest files, so it is easier to re-use that name. If you do modify the name, ensure you modify it in the manifest files as well.

## Alternative to Registry service
The registry service allows us to have a dynamic registration and discovery of services. However, it is not the only way for microservices to connect to each other.

An alternative is to use [Spring Cloud Connectors](http://cloud.spring.io/spring-cloud-connectors/) with [user-provided or managed services](http://docs.pivotal.io/pivotalcf/devguide/services/).

This repository has a [branch](https://github.com/dpinto-pivotal/cf-SpringBootTrader/tree/v0.1-CUPS-based) with a version of the application that utilises user-provided services to discover each other.

It is also possible to deploy your own Eureka server to the platform.

# Summary

The registry server has a UI that can be accessed on the link provided in the console UI. Find the discovery service you created and click on **Manage**.

You can now move on to [Creating the Circuit Breaker Dashboard](lab_circuitbreaker.md)

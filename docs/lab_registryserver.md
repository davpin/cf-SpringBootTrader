# Pushing the registry server.

All our microservices will connect to a Service Registry. This is currently implemented using the [Spring Cloud Netflix - Eureka](http://cloud.spring.io/spring-cloud-netflix/).

Whilst we will be pushing our own registry service, in the future this may be [provided](https://network.pivotal.io/products/p-spring-cloud-services) by the platform.

In **Cloud Foundry** vocabulary, deploying an application is referred to as *pushing* the application since we are uploading the application artifact to the cloud.

More information on the application deployment process can be found [here](http://docs.pivotal.io/pivotalcf/devguide/deploy-apps/deploy-app.html)


## Application manifest files.
Application manifests tell `cf push` what to do with applications. This includes everything from how many instances to create and how much memory to allocate to what services applications should use.

A manifest can help you automate deployment, especially of multiple applications at once.

### Modifying the manifest files.
Since each application requires a route to be bounded to the application, and potentially there may be many instances of these services deployed, we want to ensure we have unique routes bounded to the services we deploy.

This requires editing the manifest-\*.yml files in the base directory of the project.

### Exercise

1. Modify the manifest file manifest-registry.yml so it has a unique route. As a clue, read [this](http://docs.pivotal.io/pivotalcf/devguide/deploy-apps/manifest.html#host).
  > It may be a good idea to prefix or suffix the host variable with your initials to ensure uniqueness of routes. You can also use the [`random-route`](http://docs.pivotal.io/pivotalcf/devguide/deploy-apps/manifest.html#random-route) option in the manifest.

  > Note that the registry server has two routes.

  > Comment out the line in the registry manifest that defines the `EUREKA_CLIENT_SERVICEURL_DEFAULTZONE` since we will only be using one standalone registry server. Although you can deploy multiple eureka servers that are peers of each other.

2. Once you have modified the manifest files, *push* the **registry service** to the cloud by [specifying the specific manifest file](http://docs.pivotal.io/pivotalcf/devguide/deploy-apps/manifest.html#find-manifest) to the `cf push` command.
  > HINT: use the -f option to the push command.

## Alternative to Registry service
The registry service allows us to have a dynamic registration and discovery of services. However, it is not the only way for microservices to connect to each other.

An alternative is to use [Spring Cloud Connectors](http://cloud.spring.io/spring-cloud-connectors/) with [user-provided or managed services](http://docs.pivotal.io/pivotalcf/devguide/services/).

This repository has a [branch](https://github.com/dpinto-pivotal/cf-SpringBootTrader/tree/v0.1-CUPS-based) with a version of the application that utilises user-provided services to discover each other.

# Summary

The registry server has a UI that can be accessed on the link(s) provided at the end of the `cf push` command. Copy it to a browser and ensure you can access the registry server.

Congratulations! You have now pushed your first application to *Cloud Foundry*!


You can now move on to [creating the user-provided service](lab_userprovided.md)

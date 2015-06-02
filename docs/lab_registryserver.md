# Pushing the registry server.

All our microservices will connect a the Service Registry. This is currently implemented using the [Spring Cloud Netflix - Eureka](http://cloud.spring.io/spring-cloud-netflix/).

In **Cloud Foundry** vocabulary, deploying an application is referred to as *pushing* the application since we are uploading the application artifact to the cloud.

More information on the application deployment process can be found [here](http://docs.pivotal.io/pivotalcf/devguide/deploy-apps/deploy-app.html)


## Application manifest files.
Application manifests tell `cf push` what to do with applications. This includes everything from how many instances to create and how much memory to allocate to what services applications should use.

A manifest can help you automate deployment, especially of multiple applications at once.

### Modifying the manifest files.
Since each application requires a route to be bounded to the application, and potentially there may be many instances of these services deployed, we want to ensure we have unique routes bounded to the services we deploy.

This requires editing the manifest-\*.yml files in the base directory of the project.

### Exercise

1. Modify the manifest files so each service has a unique route. As a clue, read [this](http://docs.pivotal.io/pivotalcf/devguide/deploy-apps/manifest.html#host).

> It may be a good idea to prefix or suffix the host variable with your initials to ensure uniqueness of routes.

2. Once you have modified the manifest files, *push* the **registry service** to the cloud by [specifying the specific manifest file](http://docs.pivotal.io/pivotalcf/devguide/deploy-apps/manifest.html#find-manifest) to the `cf push` command.

# Summary

The registry server has a UI that can be accessed on the link provided at the end of the `cf push` command. Copy it to a browser and ensure you can access the registry server.

Congratulations! You have now pushed your first application to *Cloud Foundry*!


You can now move on to [creating the user-provided service](lab_userprovided.md)

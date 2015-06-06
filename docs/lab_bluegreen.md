# Zero downtime deployments.

One of the benefits of a microservice architecture is the ability to upgrade each service individually. However, for continuous operations, we would like no downtime during the upgrade as well as the ability to test the new version and be able to rollback in case the new version doesn't *cut the mustard*. This can be accomplished with the platform.

In this lab, you will be mimicking upgrading the quote service.

### Exercise

1. Create a new manifest file to deploy the quote service. Give the application a different name and a different route.

2. Push the application.
  > What happens in the registry service UI after you pushed the application?

3. Use the CLI or UI to delete the old version of the quote service whilst making requests on the UI of the application.


# Blue/green deployments.

In the [Pushing the registry server](lab_registryserver.md) lab we discussed an alternative approach for microservices to bind to each other. In this approach, we cannot rely on the registry service to give us the zero downtime deployments. Even in the registry service approach, we may want to upgrade the registry server itself in which case we can employ the Blue/Green deployment technique.

In order to minimise downtime as new versions of the software is created and released, **Cloud Foundry** provides a technique to deploy these new versions/releases without incurring downtime. This technique is called [Blue/Green deployment](http://docs.pivotal.io/pivotalcf/devguide/deploy-apps/blue-green.html)

In fact, the technique is very powerful, as it allows multiple versions of the application to be deployed and either serving requests or not serving requests (or even serving requests but not on the production route.)

The procedure is very simple:

* push the new version of the application with a new name and a new route.
* test the new version under the new route.
* if successful, bind the old route or production route to the new version.
* unbind the route from the old version.
* the old version can remain running until it is certain a rollback will not be necessary and then removed.

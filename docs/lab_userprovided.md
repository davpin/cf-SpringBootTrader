# Creating a user-provided service
In the previous lab, you deployed a single microservice that has no dependencies. However, it is very rare that a microservice has no dependencies - *no microservice is an island!*

**Cloud Foundry** provides a marketplace where administrators can enable certain services to be available to developers/operators. These services are called [*Managed services*](http://docs.pivotal.io/pivotalcf/devguide/services/#managed-services), in contrast to [*User-provided services*](http://docs.pivotal.io/pivotalcf/devguide/services/#user-provided-services). We will be using a *Managed Service* in a later lab.

In order for our microservices to be able to connect to the Registry service, we will have to create a [*User-provided service*](http://docs.pivotal.io/pivotalcf/devguide/services/user-provided.html). This tells our microservices where to find the registry service.

### Exercise
1. Create a *user provided service* using the CLI.

  Name this service **eureka-service** and specify the URI of your instance of the registry service. For example:

  `cf cups eureka-service -p '{"tag":"eureka","uri":"<my-eureka-service-URI>"}'`

  The URI of your eureka service is the URI where your registry server is deployed. This was displayed at the end of `cf push` command in the [previous lab](lab_registryserver.md).
  > The URI will be similar to `eureka-dpinto.cfapps.io`.

  > Do **not** specify the protocol!!! ie. "http://"

2. Multiple spaces.

    If you are deploying the services to multiple [spaces](http://docs.pivotal.io/pivotalcf/concepts/roles.html#spaces), then you must create the user-provided service in each space.


# Summary
You have now created a *user-provided service*. This will allow other applications to know where your registry service is located so they can register themselves as well as discover where other services are located.

Move on to [pushing the quote service lab](lab_pushquote.md).

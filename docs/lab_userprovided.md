# Creating a user-provided service

**Cloud Foundry** provides a marketplace where administrators can enable certain services to be available to developers/operators. These services are called [*Managed services*](http://docs.pivotal.io/pivotalcf/devguide/services/#managed-services), in contrast to [*User-provided services*](http://docs.pivotal.io/pivotalcf/devguide/services/#user-provided-services). We will be using a *Managed Service* later.

In order for our microservices to be able to connect to the Registry server, we will have to create a [*User-provided service*](http://docs.pivotal.io/pivotalcf/devguide/services/user-provided.html).

### Exercise
Create a *user provided service* using the CLI.

Name this service **eureka-service** and specify the URI of your instance of the registry server. For example:

`cf cups eureka-service -p '{"tag":"eureka","uri":"<my-eureka-server-URI>"}'`

The URI of your eureka server is the URI where your registry server is be deployed. This was displayed at the end of `cf push` command in the [previous lab](lab_registryserver.md).
> The URI will be similar to `eureka-server-dpinto.cfapps.io`.
> Do not specify the protocol, ie. "http://"

# Summary
You have now created a *user-provided service*. This will allow other applications to know where your registry service is located.

Move on to [pushing the quote service lab](lab_pushquote.md).

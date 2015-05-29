# Scaling the services.
Within **Cloud Foundry** the concept of scalability can mean different things:
- scaling the amount of resources available to the platform to run applications instances - as well as other platform components - [Scaling CF](http://docs.pivotal.io/pivotalcf/concepts/high-availability.html).
- increasing memory of each application instance ([vertical scaling](http://docs.pivotal.io/pivotalcf/devguide/deploy-apps/cf-scale.html#vertical)).
- horizontally scaling the application ([horizontal scaling](http://docs.pivotal.io/pivotalcf/devguide/deploy-apps/cf-scale.html#horizontal)).

One of the benefits of a micro service architecture is that each service can be scaled independently. This means that if the quote service is under stress (CPU,memory), it can be scaled up independently from the other services. The platform will automatically load balance the requests across the available instances.

Scale the Quote service to two instances using either the UI or the CLI.

How far can you scale the quote service? what is the limitation?

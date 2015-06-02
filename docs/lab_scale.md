# Scaling the services.
Within **Cloud Foundry** the concept of scalability can mean different things:
- scaling the amount of resources available to the platform to run applications instances - as well as other platform components - [Scaling CF](http://docs.pivotal.io/pivotalcf/concepts/high-availability.html).
- increasing memory of each application instance ([vertical scaling](http://docs.pivotal.io/pivotalcf/devguide/deploy-apps/cf-scale.html#vertical)).
- horizontally scaling the application ([horizontal scaling](http://docs.pivotal.io/pivotalcf/devguide/deploy-apps/cf-scale.html#horizontal)).

One of the benefits of a micro service architecture is that each service can be scaled independently. This means that if the quote service is under stress (CPU,memory), it can be scaled up independently from the other services. The platform will automatically load balance the requests across the available instances.

### Exercise
1. Scale the Quote service to two instances using either the UI or the CLI.

2. Monitor the logs of the quote service application, either via the [CLI](http://docs.pivotal.io/pivotalcf/devguide/deploy-apps/streaming-logs.html#view) or the UI.

3. Use the REST api of the quote service to retrieve quotes, for example:

`curl http://<ROUTE_TO_QUOTE_SERVICE>/quote/EMC`

or put the URL in your browser window.

How do the logs show which instance of the application is actually servicing the request?

How far can you scale the quote service? what is the limitation?

# Summary

What happened in the registry server when you scale up?

Move on to the [next lab](lab_bluegreen.md)

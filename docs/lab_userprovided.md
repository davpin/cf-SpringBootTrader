# Creating a user-provided service

In order for our microservices to be able to connect to the Registry server, we will have to create a [*User-provided service*](http://docs.pivotal.io/pivotalcf/devguide/services/user-provided.html).

To create this, we will be using the CLI.

Name this service **eureka-service** and specify the URL of your instance of the quote service. For example:

`cf cups eureka-service -p '{"tag":"eureka","uri":"<my-eureka-server-URL>"}'`

The URL of your eureka server is the URL where your registryServer will be deployed. You can deduce this by looking at the manifest file for the registryServer and combining the *name* with the domain of your cloud. For example: `eureka-server-dpinto.cfapps.io`

> Do not specify the protocol, ie. "http://"

Once you have successfully created the user-provided service, trying pushing the registryServer again.

If the registryServer is deployed successfully, you can access its UI in the URL given at the end of the push command.

Move on to [pushing the quote service lab](lab_pushquote.md)

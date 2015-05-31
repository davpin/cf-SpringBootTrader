# Zero downtime deployments - Blue/Green deployments.

In order to minimise downtime as new versions of the software is created and released, **Cloud Foundry** provides a technique to deploy these new versions/releases without incurring downtime. This technique is called [Blue/Green deployment](http://docs.pivotal.io/pivotalcf/devguide/deploy-apps/blue-green.html)

In fact, the technique is very powerful, as it allows multiple versions of the application to be deployed and either serving requests or not serving requests (or even serving requests but not on the production route.)

In this exercise, we will push another version of the quote service whilst the current one is still running. Then we will bind the route of the original service to it. Finally, we will unbind the route from the original service.

1. Modify the manifest file for the quote service so that it has a different name and route from the previous one you deployed, ie. add a "-1" at the end.

2. Push the application.

3. Use the CLI to map the route from the original quote service to the new one.

4. Use the CLI to unmap the route from the original service.

Feel free to use the application UI or curl whilst performing the above steps to ensure there was no downtime in the quote service.

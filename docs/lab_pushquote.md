# Pushing the Quote service

In **Cloud Foundry** vocabulary, deploying an application is referred to as *pushing* the application since we are uploading the application artifact to the cloud.

More information on the application deployment process can be found [here](http://docs.pivotal.io/pivotalcf/devguide/deploy-apps/deploy-app.html)

The `cf push` command is used for this. In fact, running this command with no parameters, the CLI will look for a file *manifest.yml* in the current directory. Alternatively, you can specify an application name as the parameter and the CLI will upload all files it finds in the current directory.

### Application manifest files.
Application manifests tell `cf push` what to do with applications. This includes everything from how many instances to create and how much memory to allocate to what services applications should use.

A manifest can help you automate deployment, especially of multiple applications at once.

## Modifying the manifest files.
Since each application requires a route to be bounded to the application, and potentially there may be many instances of these services deployed, we want to ensure we have unique routes bounded to the services we deploy.

Luckily, Pivotal Cloud Foundry allows us to assign a [`random-route`](http://docs.pivotal.io/pivotalcf/devguide/deploy-apps/manifest.html#random-route) to applications. The manifest files in the project include this option.

### Exercise

1. *Push* the **quote service** to the cloud by [specifying the specific manifest file](http://docs.pivotal.io/pivotalcf/devguide/deploy-apps/manifest.html#find-manifest) to the `cf push` command.

  > HINT: user the -f option to the push command.


# Summary

Ensure you have a working quote service application by sending HTTP requests to it, for example:

`curl http://<ROUTE_TO_QUOTE_SERVICE>/quote/EMC`

> You can also put the above URL in your browser.

Ensure the service registers itself with the registry server by looking at the discovery service dashboard.

Congratulations! you have now deployed an application to the cloud that registers itself with the registry service and handles HTTP requests.

Now you can go to [next lab](lab_pushall.md)

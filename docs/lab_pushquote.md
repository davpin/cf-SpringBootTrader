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

  > Manifest files are generated as part of the build. They are placed in `springboottrades-quotes/build`

2. When the script has finished, set the `CF_TARGET` environment variable in both applications to the API endpoint of your Elastic Runtime instance (as in `https://api.example.com`), then restage the applications so that the changes will take effect.

  ```
  $ cf set-env quotes CF_TARGET https://api.cloudfoundry.com
  Setting env variable 'CF_TARGET' to 'https://api.cloudfoundry.com' for app quotes in org myorg / space outer as user...
  OK
  TIP: Use 'cf restage' to ensure your env variable changes take effect
  $ cf restage quotes
  ```
  > You only need to do this once per application.

##Deploying without Spring Cloud Services

  If Spring Cloud Services are not available, you should have pushed an instance of the [discovery service](https://github.com/dpinto-pivotal/cf-SpringBootTrader-extras) to the cloud. Now, you'll have to create a [*User-provided service*](http://docs.pivotal.io/pivotalcf/devguide/services/user-provided.html) and bind it to the quote service.

  ### Exercise
  1. Create a *user provided service* using the CLI.

    Name this service **circuit-breaker-dashboard** and specify the URI of your instance of the registry service. For example:

    `cf cups discovery-service -p '{"tag":"eureka","uri":"<my-eureka-URI>"}'`

    The URI of your discovery service is the URI where your dashboard service is deployed. This was displayed at the end of `cf push` command when deploying the discovery service.
    > The URI will be similar to `eureka-dpinto.cfapps.io`.

    > Do **not** specify the protocol!!! ie. "http://"

    2. push the application

##Running it locally
  To run the quote service locally, you can use the gradle wrapper script as such:

  ```
  gradlew :springboottrades-quotes:bootRun
  ```
  The service should start up and bind to the discovery service running locally.

# Summary

Ensure you have a working quote service application by sending HTTP requests to it, for example:

`curl http://<ROUTE_TO_QUOTE_SERVICE>/quote/EMC`

> You can also put the above URL in your browser.

Ensure the service registers itself with the registry server by looking at the discovery service dashboard.

Congratulations! you have now deployed an application to the cloud that registers itself with the registry service and handles HTTP requests.

Now you can go to [next lab](lab_pushall.md)

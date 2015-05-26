# Lab 2 - Pushing the Quote service

In **Cloud Foundry** vocabulary, deploying an application is referred to as *pushing* the application since we are uploading the application artifact to the cloud.

More information on the application deployment process can be found [here](http://docs.pivotal.io/pivotalcf/devguide/deploy-apps/deploy-app.html)

The `cf push` command is used for this. In fact, running this command with no parameters, the CLI will look for a file *manifest.yml* in the current directory. Alternatively, you can specify an application name as the parameter and the CLI will upload all files it finds in the current directory.

### Application manifest files.
Application manifests tell `cf push` what to do with applications. This includes everything from how many instances to create and how much memory to allocate to what services applications should use.

A manifest can help you automate deployment, especially of multiple applications at once.

## 1. Modifying the manifest files.
Since each application requires a route to be bounded to the application, and potentially there may be many instances of these services deployed, we want to ensure we have unique routes bounded to the services we deploy.

This requires editing the manifest-\*.yml files in the base directory of the project.

Modify the manifest files do each service has a unique route. As a clue, read [this](http://docs.pivotal.io/pivotalcf/devguide/deploy-apps/manifest.html#host).

> It may be a good idea to prefix or suffix the host variable with your initials to ensure uniqueness of routes.

Once you have modified the manifest files, *push* the quote service to the cloud by [specifying the specific manifest file](http://docs.pivotal.io/pivotalcf/devguide/deploy-apps/manifest.html#find-manifest) to the `cf push` command.


# Summary

Ensure you have a working quote service application by sending HTTP requests to it, for example:

`curl http://<ROUTE_TO_QUOTE_SERVICE>/quote/EMC`

You have now deployed your first application to *Cloud Foundry*. Can you describe the steps the platform went through between your `cf push` command and having the application accessible?

Now you can go to [Lab 3][lab3]
[lab3]: docs/lab3.md

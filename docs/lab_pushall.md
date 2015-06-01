# Pushing all the services.

In the previous lab, you deployed a single microservice that has no dependencies. However, it is very rare that a microservice has no dependencies - *no microservice is an island!*

In fact, even the quote service has a dependency on an external service to retrieve real-time data.

In this exercise, we will be deploying all the applications in the project to the cloud and create all required services.

### 1. Quote service
The quote service has no dependencies - apart from the external service which is hardcoded in the application - *Don't do this at home!*

### 2. Accounts service
The accounts service has a dependency on a RDBMS database.

**Cloud Foundry** provides a marketplace where administrators can enable certain services to be available to developers/operators. These services are called [*Managed services*](http://docs.pivotal.io/pivotalcf/devguide/services/#managed-services), in contrast to [*User-provided services*](http://docs.pivotal.io/pivotalcf/devguide/services/#user-provided-services) as we shall see later.

There are a couple of ways to create a service in **Cloud Foundry**. For this service we will explore using the UI to create the service.

Log in to the Apps Manager through your browser. The URL will be something like: https://console.&lt;your_cloud_foundry_url&gt;/;

Go the *Marketplace* and choose a MySQL service. In Pivotal Web Services this will be the "ClearDB MySQL Database". Depending on your cloud provider, you may have multiple plans to choose from. For this exercise, the smallest *free* plan will suffice.

When prompted for the name of the service, insert **"traderDB"** and bind it to the space you are using to deploy your applications.

> You can pick any name of the service, however, the service is already specified in the manifest files, so it is easier to re-use that name. If you do modify the name, ensure you modify it in the manifest files as well.

## 3. Portfolio service

The portfolio service has a dependency on 3 services:

- A RDBMS.
- The quote service.
- The Account service.

For the RDBMS, we will be re-using the service created for the *Accounts service*. This is for simplicity of these exercises, and it is possible and probably favorable to create a new DB service specific to the portfolio service.

For the portfolio service also connects to the quote and account service. Since we are using a registry service, we do not need to provide any more information for this to happen, apart from letting it know where the registry service is. This is done vi the *User-provided service* you created before.

## 4. Web service
The Web service is the UI front-end and also acts as an API aggregator. As such, it uses all the other microservices in the project, i.e. The quote, account and portfolio services.

Similarly to above, we will be using the registry service to retrieve information about these microservices.


## 5. Push all the applications.

Now that we have all the required services created, let's push all the applications in one go. The **Cloud Foundry** manifest file allows us to [define multiple applications in a single file](http://docs.pivotal.io/pivotalcf/devguide/deploy-apps/manifest.html#multi-apps)

Ensure you have modified the *manifest-all.yml* file to have unique routes, and those routes will match the URLs specified in the user-provided services you created.

Use the CLI to push all applications using the *manifest-all.yml* manifest file.

Once completed, go to the URL of the Web service in your browser.

# Summary
Congratulations! You have now deployed a set of microservices to the cloud that interact with each other.

Feel free to familiarise yourself with the UI of the application. You can access the application in a browser on the URL provided at the end of the push command.

Now you can go to [next lab](lab_scale.md)

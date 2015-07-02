# Pushing all the services.

In this exercise, we will be deploying all the applications in the project to the cloud and create all required services.

### 1. Quote service
We have already deployed the quote service in the [previous lab](lab_pushquote.md), so nothing to do here.

### 2. Accounts service
The accounts service has a dependency on a RDBMS database.

**Cloud Foundry** provides a marketplace where administrators can enable certain services to be available to developers/operators. These services are called [*Managed services*](http://docs.pivotal.io/pivotalcf/devguide/services/#managed-services), in contrast to [*User-provided services*](http://docs.pivotal.io/pivotalcf/devguide/services/#user-provided-services) as we have seen [before](lab_userprovided.md).

There are a couple of ways to create a service in **Cloud Foundry**. For this service we will explore using the UI to create the service, but you can also create it using the CLI.

### Exercise

1. Log in to the Apps Manager through your browser. The URL will be: `https://console.<your_cloud_foundry_url>/`

Go the *Marketplace* and choose a MySQL service. In Pivotal Web Services this will be the "ClearDB MySQL Database". Depending on your cloud provider, you may have multiple plans to choose from. For this exercise, the smallest *free* plan will suffice.

When prompted for the name of the service, insert **"traderdb"** and bind it to the space you are using to deploy your applications.

> You can pick any name of the service, however, the service is already specified in the manifest files, so it is easier to re-use that name. If you do modify the name, ensure you modify it in the manifest files as well.

## 3. Portfolio service

The portfolio service has a dependency on 3 services:

- A RDBMS.
- The quote service.
- The Account service.

For the RDBMS, we will be re-using the service created for the *Accounts service*. This is for simplicity of these exercises, and it is possible and probably favorable to create a new DB service specific to the portfolio service.

The portfolio service also connects to the quote and account service. We are using a registry service to automatically and dynamically discover the other services. As such we only need the *User-provided service* you created [before](lab_userprovided.md).

## 4. Web service
The Web service is the UI front-end and also acts as an API aggregator. As such, it uses all the other microservices in the project, i.e. The quote, account and portfolio services.

Similarly to above, we will be using the registry service to retrieve information about these microservices.


## 5. Push all the applications.

Now that we have all the required services created, let's push all the applications in one go. The **Cloud Foundry** manifest file allows us to [define multiple applications in a single file](http://docs.pivotal.io/pivotalcf/devguide/deploy-apps/manifest.html#multi-apps)

### Exercise
1. Create a manifest file that will push the accounts, portfolio and web microservices in one go.

> Make sure to use unique routes for all the apps. i.e. ``<app-name>-<initials>``

2. Use the CLI to push all applications using the manifest file created.

Once completed, go to the URL of the Web service in your browser.

# Summary
Congratulations! You have now deployed a set of microservices to the cloud that interact with each other.

Feel free to familiarise yourself with the UI of the application. You can access the application in a browser on the URL provided at the end of the push command and see something similar to the image below.

![Spring Trader](/docs/springtrader.png)

Now you can go to [next lab](lab_scale.md)

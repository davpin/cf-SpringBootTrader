# Pushing all the services.

In the previous lab, you deployed a single microservice that has no dependencies. However, it is very rare that a microservice has no dependencies - *no microservice is an island!*

In fact, even the quote service has a dependency on an external service to retrieve real-time data.

In this exercise, we will be deploying all the applications in the project to the cloud and creating all required services.

### 1. Quote service
The quote service has no dependencies - apart from the external service which is hardcoded in the application - *Don't do this at home!*

### 2. Accounts service
The accounts service has a dependency on a RDBMS database.

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

For the portfolio service to retrieve updated pricing information, it requests the data from quote service. To achieve this, you will have to create a [*User-provided service*](http://docs.pivotal.io/pivotalcf/devguide/services/user-provided.html).

To create this, we will be using the CLI.

Name this service **quoteService** and specify the URL of your instance of the quote service. For example:

`cf cups quoteService -p '{"tag":"quotes","uri":"http://quotes.<my-domain>"}'`

The portfolio service also needs to interact with the Account service to instruct it to increase or decrease the balance of the funds in the account. Thus, create another User-provided service, naming it *accountService* and specifying the URL of the accounts service.


## 4. Web service
The Web service is the UI front-end and also acts as an API aggregator. As such, it uses all the other microservices in the project, i.e. The quote, account and portfolio services.

We have already created *user-provided services* for the quote and account microservices, thus only the portfolio one is required.

As above, create a *User-provided service* naming it *portfolioService* and specifying the URL of your portfolio service.



## 5. Push all the applications.

Now that we have all the required services created, let's push all the applications in one go. The **Cloud Foundry** manifest file allows us to [define multiple applications in a single file](http://docs.pivotal.io/pivotalcf/devguide/deploy-apps/manifest.html#multi-apps)

Ensure you have modified the *manifest-all.yml* file to have unique routes, and those routes will match the URLs specified in the user-provided services you created.

Use the CLI to push all applications using the *manifest-all.yml* manifest file.

Once completed, go to the URL of the Web service in your browser.

# Summary
Congratulations! You have now deployed a set of microservices to the cloud that interact with each other.

Feel free to familiarise yourself with the UI of the application.

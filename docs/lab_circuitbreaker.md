# Creating the Circuit Breaker Dashboard.

Cloud-native architectures are typically composed of multiple layers of distributed services. End-user requests may comprise multiple calls to these services, and if a lower-level service fails, the failure can cascade up to the end user and spread to other dependent services. Heavy traffic to a failing service can also make it difficult to repair. Using Circuit Breaker Dashboard, you can monitor a service for failure, prevent failures from cascading, and supply dependent services until the failing service is operable again.

The code to create a circuit breaker involved providing a fallback method. For example:

```java
  @HystrixCommand(fallbackMethod = "getCompaniesFallback")
	public List<CompanyInfo> getCompanies(String name) {
		logger.debug("Fetching companies with name or symbol matching: " + name);
		CompanyInfo[] infos = restTemplate.getForObject("http://quotes/company/{name}", CompanyInfo[].class, name);
		return Arrays.asList(infos);
	}

	private List<CompanyInfo> getCompaniesFallback(String name) {
		List<CompanyInfo> infos = new ArrayList<>();
		return infos;
	}
```
It is useful to know when the circuits are open as it may suggest a problem with the dependent services. [Spring Cloud Services for Pivotal Cloud Foundry](https://network.pivotal.io/products/p-spring-cloud-services) provides a centralised dashboard to collect all the statistics from circuit breakers and visualize them in one place.

Underneath the covers, this circuit breaker pattern is implemented using the [Spring Cloud Netflix - Hystrix](http://cloud.spring.io/spring-cloud-netflix/).

In order to make use of the dashboard, we need to create an instance of the service to bind it to our applications.
### Exercise

1. Log in to the Apps Manager through your browser. The URL will be: `https://console.<your_cloud_foundry_url>/`

Go the *Marketplace* and choose a *Circuit Breaker for Pivotal Cloud Foundry standard*.

When prompted for the name of the service, insert **"circuit-breaker-dashboard"** and bind it to the space you are using to deploy your applications.

> You can pick any name of the service, however, the service is already specified in the manifest files, so it is easier to re-use that name. If you do modify the name, ensure you modify it in the manifest files as well.

##Deploying without Spring Cloud Services
If the cloud does not provide us with the services, then we can deploy the services ourselves. Bare in mind that our deployment of the Circuit Breaker Dashboard Service will not be highly available or load balanced.

Follow the guidelines to deploy the Discover service [here](https://github.com/dpinto-pivotal/cf-SpringBootTrader-extras) - TODO!

Currently, this service is not available in the [extras](https://github.com/dpinto-pivotal/cf-SpringBootTrader-extras) project but will be soon. However, the service is not required to run the application. Just ensure you remove the service name from the manifest files.

In order for our microservices to be able to connect to the Circuit breaker dashboard service, we will have to create a [*User-provided service*](http://docs.pivotal.io/pivotalcf/devguide/services/user-provided.html). This tells our microservices where to find the service.

### Exercise
1. Create a *user provided service* using the CLI.

  Name this service **circuit-breaker-dashboard** and specify the URI of your instance of the registry service. For example:

  `cf cups circuit-breaker-dashboard -p '{"tag":"eureka","uri":"<my-circuit-breaker-dashboard-URI>"}'`

  The URI of your discovery service is the URI where your dashboard service is deployed. This was displayed at the end of `cf push` command when deploying the discovery service.
  > The URI will be similar to `eureka-dpinto.cfapps.io`.

  > Do **not** specify the protocol!!! ie. "http://"

##Running it locally
If you want to run all the services locally, you'll need to start the circuit breaker dashboard service.

Follow the guidelines to run the Discover service locally  [here](https://github.com/dpinto-pivotal/cf-SpringBootTrader-extras).

# Summary

The circuit breaker dashboard can be accessed on the link provided in the console UI. Find the circuit breaker service you created and click on **Manage**.

You can now move on to [creating the configuration service](lab_configserver.md)

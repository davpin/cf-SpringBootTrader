#Setting up the environment.
In this exercise, we will be setting up the environment and all the tools required.

It assumes you are running Linux or MacOS, although it is possible to configure on Windows too.

If you are in a Pivotal workshop and don't want to install and configure these tools in your laptop, ask for the VM which has all required tools already installed. However, you'll still have to provide credentials/accounts for git and Pivotal Web Services.

## 1. git install

You'll need the git CLI in order to clone and use the code in this repository.

Follow the instructions [here](https://help.github.com/articles/set-up-git/#platform-mac) to install and configure the git CLI on your host.

## 2. Cloud Foundry account creation

You'll need an account on a **Cloud Foundry** instance. Pivotal provides a public instance of **Cloud Foundry** with a 60 day free trial period.

Create an account on Pivotal Web Services [here](http://run.pivotal.io).

## 3. Cloud Foundry CLI

The **Cloud Foundry** command line interface is an easy way to interact with instances of **Cloud Foundry**.

You can obtain the CLI for multiple OS [here](https://github.com/cloudfoundry/cli)

## 4. Orgs and spaces

In order to deploy applications to **Cloud Foundry**, you'll need to setup an organisation and a space. Whilst creating the account on Pivotal Web Services, these should have been setup automatically. If you are using another instance of **CF**, then create at least one organisation and one space - this is the minimum required for the labs.

You can learn more about orgs and spaces within **Cloud Foundry** at http://docs.pivotal.io/pivotalcf/concepts/roles.html

## 5. Cloning the repository

Clone the GIT repository to your local machine. On the command line issue the following command:

```git clone https://github.com/dpinto-pivotal/cf-SpringBootTrader.git```

This command will copy the code in the repository to your local machine, creating a directory named `cf-SpringBootTrader`. All actions will now be done inside this directory.

Once you have cloned the repository, it is important to build it to create the application artifacts.

The projects use [gradle](http://gradle.org) as the build tool with gradle wrapper. Thus, all it is required to build all the microservices is:

```
./gradlew build
```

You can also build individual services by naming them, for example:
```
./gradlew :springboottrades-quotes:build
```


## 6. Logging in to Cloud Foundry

Login to your instance of **Cloud Foundry**. Instructions on how to do this can be found at http://docs.pivotal.io/pivotalcf/devguide/installcf/whats-new-v6.html#login

# Summary

At the end of this lab, you should have an environment setup to enable you to deploy applications to **Cloud Foundry**. You will also have the code required for the rest of the labs.

In order to check that all is setup correctly, you should have something similar to the following:

```
Penguin:cf-SpringBootTrader dpinto$ cf target

API endpoint:   https://CF-URI (API version: 2.23.0)
User:           dpinto
Org:            dpinto-org
Space:          development
```

Now you can go to [next lab](lab_registryserver.md)

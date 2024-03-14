# Table of Contents

1. [Overview](#overview)
2. [Application Structure](#application-structure)
3. [Prerequisites](#prerequisites)
4. [Building](#building)
5. [Docker Compose](#docker-compose)

## Overview

This is a code test project for a Senior Software Engineer role at Teamviewer.

## Application Structure

The application is divided into several pieces listed below. Within the Java source, there are several packages listed
below.

* com.teamviewer.controllers
    * Contains the Rest endpoints
* com.teamviewer.models
    * Contains all the POJO's to represent the database tables and their mappings
* com.teamviewer.repositories
    * Contains the interfaces for interacting with the database
* com.teamviewer.service
    * Contains the services for managing all the data.
    * These services handle object creation, updating, deletion, and querying.
    * These services also contain any business logic required when performing any fo these actions.
    * Ideally, these could be used anywhere else that any of these actions would need to be performed.
* com.teamviewer.swagger
    * Contains the Swagger configuration for scraping the Rest endpoints to build the Swagger UI

## Prerequisites

Note: It is **important** to note that this project will connect to a Postgres database and populate with test
data when using Docker compose.

## Building

This application is configured to build with Gradle. Building thie application prior to Dockerizing is 
requried in order to deploy to a container.

To build the application, you must first set the properties for the database in the file: /src/test/java/resources/application.properties
to connect to your Postgres database. With the correct settings, you can run the following command from a command window to build the project. Building 
the application will also execute the unit tests.

    gradlew build

## Docker Compose

This application is configured to be deployed with docker compose, which is a tool for defining and running
multi-container applications. It is the key to unlocking a streamlined and efficient development and deployment
experience.

The process of dockerizing this application is extremely simplified with docker compose. The compose.yaml file will
create all the necessary containers (both the database and Spring boot app) and configure them automatically. Use the
following command to Dockerize with docker compose:

      docker compose up

Once completed, you should have a container running with the following services inside:

      teamviewer: Spring boot app that contains the Swagger UI

      teamviewer_database: Postgres database

When the container is running, you should be able to view the Swagger UI at this
url:

[Swagger UI](http://localhost:8080/swagger-ui.html)


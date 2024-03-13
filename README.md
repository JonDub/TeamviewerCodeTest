# Table of Contents

1. [Overview](#overview)
2. [Application Structure](#application-structure)
3. [Prerequisites](#prerequisites)
4. [Building](#building)
5. [Dockerizing](#dockerizing)
    * [Manual Dockerization](#manual-dockerize)
    * [Docker Compose](#docker-compose)

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

This application is configured to build with Gradle. To build the application, simply run the following command from
a command window. Building the application will also run the unit tests.

    ./gradlew build

## Dockerizing

There are two methods to Dockerize this application. The sections below describe how to perform each. The preferred
method is to use Docker Compose.

### Manual Dockerize

The process of building and deploying to Docker is a two-step process. The first step is building the Docker image.
This step prepares the code to be deployed to a Docker container. The second step is deploying and running previously
build image. This step actually deploys the application to a usable state.

This application is configured to build and deploy a Docker image with a simple script. Use the command below to
perform these actions with Docker.

The first step is to build the Docker image from the source code. Use the following command to perform this action.

    docker build --tag=teamviewer:latest .

Once complete, there should be a new Docker image available called "teamviewer". Now, the second step will launch the
image in a Docker container. Use the following command to startup the image.

    docker run -p 8080:8080 teamviewer

Once complete, you should be able to navigate to the following url to view the Swagger docs for the deployed
application.

    http://localhost:8080/swagger-ui/index.html

### Docker Compose

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

[Swagger UI](#http://localhost:8080/swagger-ui.html)


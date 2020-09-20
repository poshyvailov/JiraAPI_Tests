FROM circleci/openjdk:8u222-stretch-browsers
MAINTAINER Oleksandr Poshyvailov
USER root
RUN sudo rm -rf /var/lib/apt/lists/* && sudo apt update && sudo apt-get install maven
CMD mvn test


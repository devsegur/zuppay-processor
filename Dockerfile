FROM gradle:6.5.0-jdk11 as build
COPY . app/
RUN cd app; gradle clean build;
ENTRYPOINT app/core
CMD  gradle bootRun
EXPOSE 8080

FROM maven:3.9.7-amazoncorretto-21-debian-bookworm
ADD ./start.sh ./start.sh
RUN mkdir application
ADD ./ ./application
WORKDIR ./application
RUN ls && pwd
RUN mvn clean package -DskipTests
CMD ./start.sh
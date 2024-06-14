FROM maven:3.9.7-amazoncorretto-21-debian-bookworm
ADD ./start.sh ./start.sh
ADD ./* ./app
WORKDIR ./app

CMD ./start.sh
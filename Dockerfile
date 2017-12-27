FROM openjdk:8

MAINTAINER Ederson de Lima<edersondelima@gmail.com>

RUN apt-get update -qq
RUN apt-get install  -y --no-install-recommends curl vim build-essential \
                        autoconf automake autotools-dev dh-make debhelper

RUN mkdir -p /app/frajola
WORKDIR /app/frajola

ADD . /app/frajola

ENTRYPOINT ["sh", "docker/build.sh"]

EXPOSE 3000

VOLUME /data

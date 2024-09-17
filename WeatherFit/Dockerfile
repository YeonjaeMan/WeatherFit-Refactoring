FROM tomcat:9.0.74-jdk17
LABEL authors="jeong-yeonjae"

ADD ./webapp /usr/local/tomcat/webapps/webapp

CMD ["catalina.sh", "run"]

ENTRYPOINT ["top", "-b"]
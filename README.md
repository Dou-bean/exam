# exam

## mall program debug process:

### ES container issue
1. When the ES container was created, it exited immediately (by checking with `docker ps -a`). 
2. Then I used `docker log -f [the id of this container]` to find the problem: "Not Enough Space". 
3. I used `find / -name jvm.options` to locate the configuration file.
4. Then modified the `-Xms4g` and `-XMx4g` parameters to `-Xms256m` and `-XMx256m`.
5. At last, I restarted the container and it worked!

### mall containers issue
1. I followed procedures in http://www.macrozheng.com/#/deploy/mall_deploy_docker. I modified `pom.xml` to enable docker-maven-plugin and used `mvn package` but failed. 
2. I reverted the modification in step1 and run `mvn package`, and created the following Dockerfile to create the mall-admin image under `mall/mall-admin`:
```
FROM java:8
ADD ./target/mall-admin-1.0-SNAPSHOT.jar /mall-admin.jar
RUN bash -c 'touch /mall-admin.jar'
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/mall-admin.jar"]
```

3. Other two images are created likewise. 

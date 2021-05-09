# exam

mall program debug process:

1. When the ES container was created, it exited immediately (by checking with `docker ps -a`). 
2. Then I used `docker log -f [the id of this container]` to find the problem: "Not Enough Space". 
3. I used `find / -name jvm.options` to locate the configuration file.
4. Then modified the `-Xms4g` and `-XMx4g` parameters to `-Xms256m` and `-XMx256m`.
5. At last, I restarted the container and it worked!

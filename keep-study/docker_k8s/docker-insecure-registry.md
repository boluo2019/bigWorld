
第一次从远程拉取镜像,报错:
docker pull 172.16.1.99/postcommit/stellardb:developer
Error response from daemon: Get https://172.16.1.99/v2/: x509: certificate signed by unknown authority

需要执行如下操作:
1. vim /lib/systemd/system/docker.service 修改下面一行
`
z`
2. 重启docker服务
systemctl daemon-reload
systemctl restart docker
docker info 看Insecure Registries属性的值中包含刚刚刚增加的ip



参考资料：

* [一篇对k8s的总结博客](https://blog.csdn.net/huakai_sun/article/details/82378856)


- kubectl cluster-info
- kubectl get nodes

- 创建一个deployment来运行container: kubectl create deployment first-deployment --image=katacoda/docker-http-server
- 查看deployment的运行状态: kubectl get pods
- 暴露端口: kubectl expose deployment first-deployment --port=80 --type=NodePort  
- 找到对外暴露的端口号,然后,发送一个http请求: export PORT=$(kubectl get svc first-deployment -o go-template='{{range.spec.ports}}{{if .nodePort}}{{.nodePort}}{{"\n"}}{{end}}{{end}}')
  echo "Accessing host01:$PORT"
  curl host01:$PORT

- 通过yaml文件来部署Container:
下面是kubernetes-dashboard.yaml的内容:
  apiVersion: v1
  kind: Service
  metadata:
    labels:
      app: kubernetes-dashboard
    name: kubernetes-dashboard-katacoda
    namespace: kube-system
  spec:
    ports:
    - port: 80
      protocol: TCP
      targetPort: 9090
      nodePort: 30000
    selector:
      app: kubernetes-dashboard
    type: NodePort
- kubectl apply -f /opt/kubernetes-dashboard.yaml
- 查看所有的deployment object: kubectl get deployment
- 查看某个特定的deployment: kubectl describe deployment deployment-name

- 下面是service.yaml文件的内容: 该service选择了所有label为webapp1的应用??干了设么?
apiVersion: v1
  kind: Service
  metadata:
    name: webapp1-svc
    labels:
      app: webapp1
  spec:
    type: NodePort
    ports:
    - port: 80
      nodePort: 30080
    selector:
      app: webapp1
- 创建一个service: kubectl create -f service.yaml
- 查看状态: kubectl get svc 
- 查看配置: kubectl describe svc webapp1-svc
- 修改配置: kubectl edit svc webapp-svc

- 更新deployment的配置: kubectl apply -f deployment.yaml

- 通过kubectl来润一个container：kubectl run http --image=katacoda/docker-http-server:latest --replicas=1
- 查看deployment的状态：kubectl get deployments
- 创建一个service来暴露pod的某些端口，下面是将container中80端口与host的8000端口绑定到host的外部IP上：
kubectl expose deployment http --external-ip="172.17.0.37" --port=8000 --target-port=80
- 可以通过看到pod的端口暴露信息：kubectl get svc
- 通过外部ip+host port来访问pod内部提供的服务：curl http://172.17.0.37:8000

- run和expose同时执行：
kubectl run httpexposed --image=katacoda/docker-http-server:latest --replicas=1 --port=80 --hostport=8001
- 这种情况下通过kubectl get svc不能看到端口暴露信息。
- 但是可以通过这种方式来确认：docker ps | grep httpexposed
- 修改副本数：kubectl scale --replicas=3 deployment http
- 查看endipoints信息：kubectl describe svc http
- 访问服务：curl http://172.17.0.52:8000
       
                 



[在线试用平台](https://www.katacoda.com/courses/kubernetes)
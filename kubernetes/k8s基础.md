
kubernetes基本概念：

- Kubernetes API Objects
- Kubernetes Control Plane
- PLEG <=> Pod Lifecycle Event Generator

- Kubernetes Cluster: 计算、存储和网络资源的集合。
- Kubernetes Master：负责调度pod应用在哪个node上运行。
  - 运行在一个Node上的3个进程的集合。
  - kube-apiserver
  - kube-controller-manager
  - kube-scheduler
- Kubernetes Nodes：负责调度pod应用在哪个node上运行。
  - master管理，用户很少会直接和node交互。
  - 运行在一个Node上的2个进程的集合。
  - kubelet：负责与master通信
  - kube-proxy：a network proxy which reflects Kubernetes networking services on each node.
- Kubernetes Node：负责运行运行容器应用，监控并汇报容器状态。
  - 根据master的要求管理容器的生命周期。
  - 运行在Linux物理或虚拟机上。

---
Kubernetes abstractions represented by Kubernetes API

Kubernetes Object：
- Pod：kubernetes的最小工作单元。
  - 每个pod包含一个或多个容器。
  - 出现的原因
    - 便于管理
    - 资源共享
  - 使用方式
    - one container-per-pod，常见模型。
    - 一个pod运行多个容器，这些容器联系非常紧密。
- Service：
- Volume：
- Namespace：
    - kubectl get namespace

---
Kubernetes higher-level abstractions rely on Controllers

- Controller：管理pod，其定义了pod的部署方式。
  - 一般情况kubernetes不会直接创建pod。
  - 5种
    - Deployment：常用。自动创建RecplicaSet。
      - kubectl get/edit/delete/describe deployment deployment-id
    - ReplicaSet：pod多副本管理。
      - kubectl get/edit/delete/describe rs replicaset-id
    - DaemonSet：
    - StatefulSet：
      - kubectl get/edit/delete/describe sts statefulset-id
    - Job: 结束就删除pod的应用。其他都是长期运行。

- Service
		- Headless
		- 
- Namespace
- 




参考资料：
1. [Kubernetes学习之路系列](https://www.cnblogs.com/linuxk/p/9272567.html)

2. [helm用户指南](https://whmzsu.github.io/helm-doc-zh-cn/)

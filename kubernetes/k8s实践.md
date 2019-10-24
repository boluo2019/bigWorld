
k8s实践

---
安装虚拟机virtualbox

下载
https://www.virtualbox.org/
https://download.virtualbox.org/virtualbox/6.0.14/virtualbox-6.0_6.0.14-133895~Ubuntu~xenial_amd64.deb
安装
sudo dpkg -i virtualbox-6.0_6.0.14-133895_Ubuntu_xenial_amd64.deb

---

安装minikube

curl -Lo minikube https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64 && chmod +x minikube

sudo mkdir -p /usr/local/bin/
sudo install minikube /usr/local/bin/

---

minikube start


---
遇到的问题：
minikube start是报错：是因为Linux内核版本太低。
🔥  Creating virtualbox VM (CPUs=2, Memory=2000MB, Disk=20000MB) ...
🔄  Retriable failure: create: precreate: We support Virtualbox starting with version 5. Your VirtualBox install is "WARNING: The vboxdrv kernel module is not loaded. Either there is no module\n         available for the current kernel (4.15.0-65-generic) or it failed to\n         load. Please recompile the kernel module and install it by\n\n           sudo /sbin/vboxconfig\n\n         You will not be able to start VMs until this problem is fixed.\n6.0.14r133895". Please upgrade at https://www.virtualbox.org

升级 Linux内核
对于64位操作系统：
cd /tmp/
wget -c https://kernel.ubuntu.com/~kernel-ppa/mainline/v5.1/linux-headers-5.1.0-050100_5.1.0-050100.201905052130_all.deb
wget -c https://kernel.ubuntu.com/~kernel-ppa/mainline/v5.1/linux-headers-5.1.0-050100-generic_5.1.0-050100.201905052130_amd64.deb
wget -c https://kernel.ubuntu.com/~kernel-ppa/mainline/v5.1/linux-image-unsigned-5.1.0-050100-generic_5.1.0-050100.201905052130_amd64.deb
wget -c https://kernel.ubuntu.com/~kernel-ppa/mainline/v5.1/linux-modules-5.1.0-050100-generic_5.1.0-050100.201905052130_amd64.deb
sudo dpkg -i *.deb
重启



单机环境下试用kubernetes
查看Linux内核信息：
cat /boot/config-***-generic
kubernetes 条件需求
1. 你必须拥有一台安装有Docker的机器。

2. 你的内核必须支持 memory and swap accounting 。确认你的linux内核开启了如下配置:
CONFIG_RESOURCE_COUNTERS=y
CONFIG_MEMCG=y
CONFIG_MEMCG_SWAP=y
CONFIG_MEMCG_SWAP_ENABLED=y
CONFIG_MEMCG_KMEM=y

Note： $cat /boot/config-***-generic

3. 以命令行参数方式,在内核启动时开启 memory and swap accounting 选项:
GRUB_CMDLINE_LINUX="cgroup_enable=memory swapaccount=1"

$vim /etc/default/grub

# 修改 GRUB_CMDLINE_LINUX="" ==> GRUB_CMDLINE_LINUX="cgroup_enable=memory"

# 保存后, 更新grub.cfg

update-grub

reboot

Note :$cat /proc/cmdline

  BOOT_IMAGE=/boot/vmlinuz-3.18.4-aufs root=/dev/sda5 ro cgroup_enable=memory swapaccount=1



参考资料：
[1] https://www.virtualbox.org/wiki/Downloads
[2] 升级linux内核： https://www.linuxidc.com/Linux/2019-05/158569.htm
[3] [安装kubernetes的条件](https://www.cnblogs.com/zhangeamon/p/5197655.html)
[4] [适合入门的kubernetes文档](https://www.kubernetes.org.cn/doc-5)
[5] [install kubernetes的文档，不过不是很好用](https://matthewpalmer.net/kubernetes-app-developer/articles/install-kubernetes-ubuntu-tutorial.html)

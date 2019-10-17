
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



参考资料：
[1] https://www.virtualbox.org/wiki/Downloads
[2] 升级linux内核： https://www.linuxidc.com/Linux/2019-05/158569.htm


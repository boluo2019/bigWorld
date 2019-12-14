## java中的锁

---

### 不同类型的锁
每种锁的实现方式，使用场景，优点，缺点。
* java主流锁
    * 1 线程要不要锁住同步资源？
        * 锁住： 悲观锁。
            * 获取数据时先加锁。synchronized和Lock(ReentrantLock)。
            * 适合写操作多的场景。
        * 不锁住： 乐观锁。
            * 获取数据不加锁。更新数据时数据是否已被修改。CAS(Compare And Swarp)算法。原子类中的递增操作。
            * 适合读操作多的场景。 
        * java和数据库都有。
    * 2 锁住同步资源失败，线程要不要阻塞？
        * 阻塞
        * 不阻塞
            *自旋锁。通过while循环自旋避免系统调用，-XX:PreBlockSpin，默认10.
            *适应性自旋锁。自旋时间不固定。
    * 3 多个线程竞争同步资源的流程细节有没有区别？？？
        * 1 无锁： 不锁住资源，多个线程中只有一个能修改资源成功，其他线程会重试。
            * CAS
        * 2 偏向锁： 同一个线程执行同步资源时会自动获取资源。
        * 3 轻量级锁： 多个线程竞争同步资源时，没有获取资源的线程自旋等待锁释放。
        * 4 重量级锁： 多个线程竞争同步资源时，没有获取资源的线程阻塞等待唤醒。
    * 4 多线程竞争锁时要不要排队？
        * 排队： 公平锁
        * 先尝试插队，插队失败再排队： 非公平锁
    * 5 一个线程中的多个流程能不能获取同一把锁？
        * 能： 可重入锁
        * 不能： 非可重入锁
    * 6 多个线程能不能共享一把锁？
        * 能： 共享锁
        * 不能： 排他锁
---

* CAS问题
    * ABA问题：解决办法1A2B3A。
    * 循环时间长开销大
    * 只能保证一个共享变量的原子操作
* 自旋锁 VS 适应性自旋锁
    * 
    * 
    * 
* 
    * 
    * 
    * 
* 
    * 
    * 
    * 
    
    
    
    
---
Hotspot的目录结构
```
├─agent                            Serviceability Agent的客户端实现
├─make                             用来build出HotSpot的各种配置文件
├─src                              HotSpot VM的源代码
│  ├─cpu                            CPU相关代码（汇编器、模板解释器、ad文件、部分runtime函数在这里实现）
│  ├─os                             操作系相关代码
│  ├─os_cpu                         操作系统+CPU的组合相关的代码
│  └─share                          平台无关的共通代码
│      ├─tools                        工具
│      │  ├─hsdis                      反汇编插件
│      │  ├─IdealGraphVisualizer       将server编译器的中间代码可视化的工具
│      │  ├─launcher                   启动程序“java”
│      │  ├─LogCompilation             将-XX:+LogCompilation输出的日志（hotspot.log）整理成更容易阅读的格式的工具
│      │  └─ProjectCreator             生成Visual Studio的project文件的工具
│      └─vm                           HotSpot VM的核心代码
│          ├─adlc                       平台描述文件（上面的cpu或os_cpu里的*.ad文件）的编译器
│          ├─asm                        汇编器接口
│          ├─c1                         client编译器（又称“C1”）
│          ├─ci                         动态编译器的公共服务/从动态编译器到VM的接口
│          ├─classfile                  类文件的处理（包括类加载和系统符号表等）
│          ├─code                       动态生成的代码的管理
│          ├─compiler                   从VM调用动态编译器的接口
│          ├─gc_implementation          GC的实现
│          │  ├─concurrentMarkSweep      Concurrent Mark Sweep GC的实现
│          │  ├─g1                       Garbage-First GC的实现（不使用老的分代式GC框架）
│          │  ├─parallelScavenge         ParallelScavenge GC的实现（server VM默认，不使用老的分代式GC框架）
│          │  ├─parNew                   ParNew GC的实现
│          │  └─shared                   GC的共通实现
│          ├─gc_interface               GC的接口
│          ├─interpreter                解释器，包括“模板解释器”（官方版在用）和“C++解释器”（官方版不在用）
│          ├─libadt                     一些抽象数据结构
│          ├─memory                     内存管理相关（老的分代式GC框架也在这里）
│          ├─oops                       HotSpot VM的对象系统的实现
│          ├─opto                       server编译器（又称“C2”或“Opto”）
│          ├─prims                      HotSpot VM的对外接口，包括部分标准库的native部分和JVMTI实现
│          ├─runtime                    运行时支持库（包括线程管理、编译器调度、锁、反射等）
│          ├─services                   主要是用来支持JMX之类的管理功能的接口
│          ├─shark                      基于LLVM的JIT编译器（官方版里没有使用）
│          └─utilities                  一些基本的工具类
└─test                             单元测试    
```    

---
参考资料：
* [美团技术团队：java中的琐事](https://mp.weixin.qq.com/s?__biz=MjM5NjQ5MTI5OA==&mid=2651749434&idx=3&sn=5ffa63ad47fe166f2f1a9f604ed10091&chksm=bd12a5778a652c61509d9e718ab086ff27ad8768586ea9b38c3dcf9e017a8e49bcae3df9bcc8&scene=21#wechat_redirect)
* [Doug Lea并发编程文章全部译文](http://ifeve.com/doug-lea/)
* [open jdk 源码下载地址](http://hg.openjdk.java.net/)
* [open jdk8 源码下载地址](https://download.java.net/openjdk/jdk8)
* []()
* []()
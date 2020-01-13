图神经网络（Graph Neural Networks）

- GCN
- transductive learning: 训练时用到了验证/测试集的信息。
- inductive learding: 没与上面相反，没用到。实际中的大部分情况。
- GraphSAGE：inductive框架。
  - 优点：利用已知节点的信息为未知节点生成Embedding。
  - Graph SAmple and aggreGatE
    - SAmple指如何对邻居个数进行采样。
    - aggreGatE指拿到邻居的embedding之后如何汇聚这些embedding以更新自己的embedding信息。
  - 学习过程：
    - 对邻居采样 HQ：为什么要采样？
    - 采样后的邻居embedding传到节点上来，并使用一个聚合函数聚合这些邻居信息以更新节点的embedding。 HQ：只更新一次吗？相当于一轮迭代计算?
    - 根据更新后的embedding预测节点的标签。 
  - xx

 - GNN在反欺诈中的应用
    - 涉及到的一些概念：GeniePath图学习方法、node2vec无监督图学习方法、GBDT非图学习方法

参考资料：

* [清华有一个实验室总结的GNN必读论文](https://github.com/thunlp/GNNPapers)

* [知乎上一个不错的专栏系列文档](https://zhuanlan.zhihu.com/p/77317842)

* [知乎上有一个专栏已经有人翻译了这篇文章](https://linkeddataorchestration.com/2019/09/10/graph-algorithms-neural-networks-and-graph-databases-the-year-of-the-graph-newsletter-september-2019/)

* [cikm](http://www.cikm2019.net/session.html)

* [从淘宝的反运费险欺诈来讲gnn在反欺诈中的应用的一片不错的文章](https://juejin.im/post/5b7d1677e51d4538c411d25d)

* [上面反欺诈实例的作者在github上放了论文和ppt](https://github.com/chenlianMT/Who-Stole-the-Postage-)

* [图神经网络综述](https://juejin.im/post/5c3404edf265da61561f6e1c)

[] []()
[] []()
[] []()


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

参考资料：

[1] [清华有一个实验室总结的GNN必读论文](https://github.com/thunlp/GNNPapers)

[2] [知乎上一个不错的专栏系列文档](https://zhuanlan.zhihu.com/p/77317842)

[3] [知乎上有一个专栏已经有人翻译了这篇文章](https://linkeddataorchestration.com/2019/09/10/graph-algorithms-neural-networks-and-graph-databases-the-year-of-the-graph-newsletter-september-2019/)

[4] [cikm](http://www.cikm2019.net/session.html)

[] []()
[] []()
[] []()
[] []()
[] []()
[] []()


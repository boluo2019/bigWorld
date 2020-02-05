
## 各类存储引擎之间千丝万缕的关系

- bigtable
    - chubby 来自Google内部的分布式锁
        - 对应开源的zookeeper
    - gfs 文件系统
        - 对应开源的hdfs
- leveldb
    - 单机版的bigtable的开源实现
    - LSM

- rocksdb
    - leveldb 由Facebook实现的分布式版本 
    
- dgraph
    - 分布式图数据库
    - bagder
    
- bagder

- grpc
    - 支持多种

- protocol buffers (protobuf)
    - 数据传输格式
- 

- [Why we choose Badger over RocksDB in Dgraph](https://blog.dgraph.io/post/badger-over-rocksdb-in-dgraph/)
- [Why we built CockroachDB on top of RocksDB](https://www.cockroachlabs.com/blog/cockroachdb-on-rocksd/)
- [Introducing Badger: A fast key-value store written purely in Go](https://blog.dgraph.io/post/badger/)
- [grpc-java-doc](https://grpc.io/docs/quickstart/java/)
- []()
- []()



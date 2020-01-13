import java.util.ArrayList;
import java.util.List;

public class GraphTest {

    static class Vertex {
        final String vid;
        final float value;

        Vertex(String vid, float value) {
            this.vid = vid;
            this.value = value;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            List<Vertex> list = new ArrayList<>();
            for (int i = 0; i < 1000_000; i++) {
                list.add(new Vertex("v" + i, i * 0.1f));
            }
            Thread.sleep(10_000);
        }
    }
//      运行上述程序，jps查看GraphTest对应的pid
//jstat 的使用 https://www.cnblogs.com/yjd_hycf_space/p/7755633.html
//
//类加载统计： jstat -class pid
//      加载class的数量  所占用空间大小  未加载数量  未加载占用空间     时间
//      Loaded          Bytes       Unloaded    Bytes         Time
//      632             1267.9        0         0.0           0.14
//
//编译统计: jstat -compiler pid
//      Compiled Failed Invalid   Time   FailedType FailedMethod
//      82      0       0     0.15          0
//
//垃圾回收统计： jstat -gc pid ms
//      幸存区0大小 幸存区1大小 幸存区0使用大小 幸存区0使用大小  Eden大小 Eden使用大小 老年代大小 老年代使用大小 方法区大小 方法区使用大小 压缩类空间大小 压缩类空间使用大小 年轻代垃圾回收次数 xx消耗时间 老年代垃圾回收次数  xxx时间 垃圾回收小号总时间
//       S0C        S1C         S0U         S1U           EC       EU        OC         OU       MC         MU          CCSC        CCSU            YGC           YGCT       FGC            FGCT     GCT
//      7680.0    7680.0        0.0         0.0         48128.0   6742.4   126976.0     0.0     4480.0     774.5         384.0      75.9             0            0.000       0             0.000    0.000
//堆内存统计： jstat -gccapacity pid
//      NGCMN    NGCMX     NGC     S0C   S1C       EC      OGCMN      OGCMX       OGC         OC       MCMN     MCMX      MC     CCSMN    CCSMX     CCSC    YGC    FGC
//      63488.0 1011712.0 756736.0 94208.0 114176.0 528384.0   126976.0  2023424.0   225280.0   225280.0      0.0 1056768.0   4864.0      0.0 1048576.0    512.0      8     3
//新生代垃圾回收统计:  jstat -gcoldcapacity pid
//      S0C    S1C    S0U    S1U   TT MTT  DSS      EC       EU     YGC     YGCT
//  110592.0 113152.0 12049.3    0.0  4  15 113152.0 781824.0 549129.4     14    0.945
//新生代内存统计:  jstat -gnewcapacity pid
//    NGCMN      NGCMX       NGC      S0CMX     S0C     S1CMX     S1C       ECMX        EC      YGC   FGC
//   63488.0  1011712.0  1011712.0 336896.0 110592.0 336896.0 113152.0  1010688.0   781824.0    14     3
//老年代垃圾回收统计:  jstat -gcold pid
//   MC       MU      CCSC     CCSU       OC          OU       YGC    FGC    FGCT     GCT
//  4864.0   3784.2    512.0    423.0    225280.0     86856.8     13     3    1.257    2.175
//老年代内存统计:  jstat -gcoldcapacity pid
//       OGCMN       OGCMX        OGC         OC       YGC   FGC    FGCT     GCT
//   126976.0   2023424.0    225280.0    225280.0    12     3    1.257    2.119
//元数据空间统计: jstat -gcmetacapacity pid
//       MCMN       MCMX        MC       CCSMN      CCSMX       CCSC     YGC   FGC    FGCT     GCT
//       0.0  1056768.0     4864.0        0.0  1048576.0      512.0    11     3    1.257    2.099
//总结垃圾回收统计：jstat -gcutil pid
//        S0     S1     E      O      M     CCS    YGC     YGCT    FGC    FGCT     GCT
//      80.12   0.00  79.81  38.56  77.80  82.62     10    0.784     3    1.257    2.042
//jvm编译方法统计： jstat -printcompilation pid
//      Compiled  Size  Type Method
//      104     84    1 java/util/ArrayList grow
}

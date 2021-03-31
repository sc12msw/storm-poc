package uk.tojourn

import org.apache.storm.Config
import org.apache.storm.LocalCluster
import org.apache.storm.topology.TopologyBuilder 

fun main(){
    val builder = TopologyBuilder().apply {
        setSpout("word-reader", WordReaderSpout())
        setBolt("word-counter", WordCountBolt(), 1).shuffleGrouping("word-reader")
    }
    val conf = Config()
    conf.setDebug(true)
    val localCluster = LocalCluster()
    localCluster.submitTopology("wordcounter-topology", conf, builder.createTopology())
    try {
        Thread.sleep(10000)
        localCluster.close()
    } catch (error : InterruptedException ) {
        error.printStackTrace()
        localCluster.close()
    }
}

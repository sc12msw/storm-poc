package uk.tojourn

import org.apache.storm.Config
//import org.apache.storm.LocalCluster
import org.apache.storm.topology.ConfigurableTopology
import org.apache.storm.topology.TopologyBuilder
import java.lang.Exception

class WordCountTopology : ConfigurableTopology() {
companion object {
    @JvmStatic
    fun main(args: Array<String>) {
        start(WordCountTopology(), args)
    }
}
    override fun run(args: Array<out String>?): Int {
        val builder = TopologyBuilder()
        builder.setSpout("word-reader", WordReaderSpout())
        builder.setBolt("word-counter", WordCountBolt(), 1).shuffleGrouping("word-reader")
        val conf = Config()
        conf.setDebug(true)
        return submit("word-count", conf, builder)
    }

}


// For local no stack
//    val conf = Config()
//    val localCluster = LocalCluster()
//    localCluster.submitTopology("wordcounter-topology", conf, builder.createTopology())
//    try {
//        Thread.sleep(10000)
//        localCluster.close()
//    } catch (error : InterruptedException ) {
//        // TODO Auto-generated catch block
//        error.printStackTrace()
//        localCluster.close()
//    }


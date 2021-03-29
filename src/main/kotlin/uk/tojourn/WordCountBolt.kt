package uk.tojourn

import org.apache.storm.task.OutputCollector
import org.apache.storm.task.TopologyContext
import org.apache.storm.topology.IRichBolt
import org.apache.storm.topology.OutputFieldsDeclarer
import org.apache.storm.tuple.Tuple
import mu.KotlinLogging
private val logger = KotlinLogging.logger{}
class WordCountBolt : IRichBolt {

    private lateinit var counters: MutableMap<String, Int>
    private var id: Int = 0
    private lateinit var name: String
    private lateinit var fileName: String

    override fun prepare(conf: MutableMap<String, Any>?, context: TopologyContext, collector: OutputCollector) {
        counters = mutableMapOf()
        name = context.thisComponentId
        id = context.thisTaskId
    }

    override fun execute(input: Tuple) {
        val word: String = input.getStringByField("word")
        if (!counters.containsKey(word)) {
            counters[word] = 1
        } else {
            counters[word] = counters[word]!! + 1
        }
    }

    override fun cleanup() {
        logger.info{"Final word count:::::"}
        for ((key, value) in counters) {
            logger.info{"$key-$value"}
        }
    }

    override fun declareOutputFields(p0: OutputFieldsDeclarer?) {
    }

    override fun getComponentConfiguration(): MutableMap<String, Any>? {
     return null
    }
}
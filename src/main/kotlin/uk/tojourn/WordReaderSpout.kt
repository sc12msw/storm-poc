package uk.tojourn

import org.apache.storm.spout.SpoutOutputCollector
import org.apache.storm.task.TopologyContext
import org.apache.storm.topology.IRichSpout
import org.apache.storm.topology.OutputFieldsDeclarer
import org.apache.storm.tuple.Fields
import org.apache.storm.tuple.Values
import mu.KotlinLogging
private val logger = KotlinLogging.logger{}
class WordReaderSpout() : IRichSpout {

    private val serialVersionUID: Long = 441966625018520917
    private val sentences = arrayOf(
        "Hello World",
        "Apache Storm",
        "Big Data",
        "Big Data",
        "Machine Learning",
        "Hello World",
        "World",
        "Hello",
        "Storm is gravy as a baby"
    )
    private lateinit var collector: SpoutOutputCollector;
    private var isCompleted = false
    override fun open(p0: MutableMap<String, Any>?, context: TopologyContext, collector: SpoutOutputCollector) {
        this.collector = collector
    }


    override fun close() {

    }

    override fun activate() {

    }

    override fun deactivate() {

    }

    override fun nextTuple() {
        if (!isCompleted) {
            sentences.map { sentence ->
                sentence.splitToSequence(" ").forEach { word ->
                    logger.info("Found $word")
                    collector.emit(Values(word));
                }
                isCompleted = true
            }
        } else {
            this.close();
        }
    }

    override fun ack(p0: Any?) {

    }

    override fun fail(p0: Any?) {

    }

    override fun declareOutputFields(declarer: OutputFieldsDeclarer) {
        declarer.declare(Fields("word"));
    }

    override fun getComponentConfiguration(): MutableMap<String, Any>? {
        return null
    }

}
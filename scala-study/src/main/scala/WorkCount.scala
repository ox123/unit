import org.apache.spark.rdd.RDD

var file: String = "/root/wikiOfSpark.txt"
var lineRDD: RDD[String] = spark.sparkContext.textFile(file)

var wordRDD: RDD[String] = lineRDD.flatMap(line => line.split(" "))
var cleanRDD: RDD[String] = wordRDD.filter(word => !word.equals(""))
var kvRDD: RDD[(String, Int)] = cleanRDD.map(word => (word, 1))
var wordCounts: RDD[(String, Int)] = kvRDD.reduceByKey((x, y) => x + y)
wordCounts.map { case (k, v) => (v, k) }.sortByKey(false).take(10)
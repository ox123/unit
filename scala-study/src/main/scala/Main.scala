object Test {

  trait Iterator[A] {
    def hasNext: Boolean

    def next(): A
  }

  class IntIterator(to: Int) extends Iterator[Int] {
    private var current = 0

    override def hasNext: Boolean = current < to

    override def next(): Int = {
      if (hasNext) {
        val t = current
        current += 1
        t
      } else 0
    }
  }

  def matchTest(x: Any): Any = x match {
    case 1 => "one"
    case "two" => 2
    case y: Int => "scala.Int"
    case _ => "many"
  }


  def main(args: Array[String]) {
    println(matchTest(1))
    println(matchTest("y"))
    val ita = Iterator(20, 40, 2, 50, 69, 90)
    while (ita.hasNext) {
      println(ita.next())
    }
    //    println(ita.max)
    val buf = new StringBuilder;
    var myList1 = Array.range(10, 20, 2)
    for (x <- myList1) {
      println(x)
    }

    var str: String = "test"

    def log(message: String, level: String = "INFO") = println(s"$level: $message")

    log(message = "this is a message")

    print(str)
    print("\nHello World!\n" + addInt(1, 3))
    print("\n")
    println({
      "this is a test";
      var x: Int = 34;
      var y: Int = 21;
      x + y;
    })

    def name: String = System.getProperty("user.name")

    println(name)

    val list: List[Any] = List(
      "aaa",
      123,
      () => "this is a test"
    )
    list.foreach(element => println(element))
  }

  def addInt(a: Int, b: Int): Int = {
    var sum: Int = 0
    return a + b
  }

  abstract class A {
    val message: String
  }

  class B extends A {
    val message = "I'm an instance of class B"
  }

  trait C extends A {
    def loudMessage = message.toUpperCase()
  }

  /**
   * extends ... with
   */
  class D extends B with C

}
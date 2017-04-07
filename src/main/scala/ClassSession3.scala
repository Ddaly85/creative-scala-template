import doodle.core._
import doodle.core.Image._
import doodle.syntax._
import doodle.jvm.Java2DCanvas._
import doodle.backend.StandardInterpreter._


//Got through all of chapter 4 and 5

/*
Chapter 6 - Got through all of the assignments, seemed relatively straightforward
 */

object boxExample {

  def boxes(color: Color): Image = {
    val box =
    Image.rectangle(40,40).
      lineWidth(5.0).
      lineColor(color.spin(30.degrees)).
      fillColor(color)
    box beside box beside box beside box
  }

  def main(args: Array[String]) = {
    boxes(Color.blue).draw
  }
}

object mathExamples {

  def squareOfValue(valueX: Int): Int = {
    valueX * valueX
  }

  def halveExample(valueX: Double): Double = {
    valueX / 2.0
  }

  def main(args: Array[String]) = {
    println(squareOfValue(5))
    println(halveExample(3))
  }
}

object orderingExamples {

  def orderingTest(x: Int, y: Int, z: Int): Int = {
    println("Hello")
    x + y - z
  }

  def main(args: Array[String]) = {
    println(orderingTest(4,6,3))
  }
}

/*
Chapter 7
 */
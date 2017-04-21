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

//Examples of code that aren't part of the exercises
object randomExamples {

  val aBox = Image.rectangle(20,20).fillColor(Color.royalBlue)

  def boxes(count: Int): Image =
    count match {
      case 0 => Image.empty
      case n => aBox beside boxes(n-1)
    }

  def main(args: Array[String]) = {
    boxes(3).draw
  }
}

object stackingBoxes {
  //see above example, same solution
}

//Exercises (guess the result)
/*
1. Will equal 2 since it will match the string abcd
2. will return "one" since it will match the first instance of int 1
3. will return 2 since it will match the n (and 2 is an int)
4. will return a 1 since it will match with a (variable rather than string)

No match
Get an error (fail at compile or at runtime, depending, in my case I have it set to be on runtime
 */

object crossExercise {

  val circleImage = Image.circle(20)

  def crossFromCircles(count: Int): Image =
    count match {
      case 0 => circleImage
      case n => circleImage beside (circleImage above crossFromCircles(n-1) above circleImage) beside circleImage
    }

  def main(args: Array[String]) = {
    crossFromCircles(3).draw
  }
}

object chessBoard {

  def chessBlock(clr: Color) = Image.rectangle(10,10).fillColor(clr)

  val chessUnit = (chessBlock(Color.red) beside chessBlock(Color.black)) above (chessBlock(Color.black) beside chessBlock(Color.red))

  def chessCombined(count: Int): Image = {
    val chessImg =
      (chessUnit beside chessUnit) above (chessUnit beside chessUnit)
    count match {
      case 0 => chessUnit
      case n =>
        val finalImg = chessCombined(n - 1)
        (finalImg beside finalImg) above (finalImg beside finalImg)
    }
  }

  def main(args: Array[String]) = {
    chessCombined(4).draw
  }
}

object sierpinskiTriangle {

  val triangleImg = Image.triangle(10,10).lineColor(Color.violet)

  val triangleUnit = triangleImg above (triangleImg beside triangleImg)

  def triangleCombined(count: Int): Image = {
    count match {
      case 0 => triangleUnit
      case n =>
        val finalImg = triangleCombined(n - 1)
        finalImg above (finalImg beside finalImg)
    }
  }

  def main(args: Array[String]) = {
    triangleCombined(4).draw
  }
}

//Exercises on silly structural recursion examples
/*
yes it does, as example for 3 it is 1 + 1 + 1 + 0 (from 3 to 0)
No this wouldn't work, as you would end up multiplying by 0 so all answers would be 0
 */

object spinColors {

  val box = Image.rectangle(20,20)

  def boxWithColor(count: Int, clr: Color): Image = {
    count match {
      case 0 => box.fillColor(clr)
      case n =>  box.fillColor(clr) beside boxWithColor(n-1,clr.spin(10.degrees))
    }
  }

  def main(args: Array[String]) = {
    boxWithColor(4,Color.blue).draw
  }
}

object concentricCirlces {

  val sz = 20

  def circlePattern(count: Int): Image = {
    count match {
      case 0 => Image.Empty
      case n => Image.circle(sz + n*5) on circlePattern(n-1)
    }
  }

  def main(args: Array[String]) = {
    circlePattern(10).draw
  }
}

object concentricCirlcesWithColor {

  val sz = 20

  def circlePattern(count: Int, color: Color): Image = {
    count match {
      case 0 => Image.Empty
      case n => Image.circle(sz + n*5).lineColor(color) on circlePattern(n-1,color.spin(10.degrees))
    }
  }

  def main(args: Array[String]) = {
    circlePattern(10,Color.blue).draw
  }
}

object boxingClever {


  def boxes(count: Int): Image = {
    val aBox = Image.rectangle(20, 20).fillColor(Color.royalBlue)
    def recLoop(count: Int): Image =
    count match {
      case 0 => Image.empty
      case n => aBox beside recLoop(n-1)
    }
    recLoop(count)
  }

  def main(args: Array[String]) = {
    boxes(10).draw
  }
}
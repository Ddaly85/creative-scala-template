import doodle.core._
import doodle.core.Image._
import doodle.syntax._
import doodle.jvm.Java2DCanvas._
import doodle.backend.StandardInterpreter._


//Got through all of chapter 4 and 5

/*
Top-Level
1) Yes must name objects
2) Yes can name vals within objects
 */

/*
Exercises
1) 3
2) 3
3) 3, shadowing so uses val within the object it is in
4) 3, fine since doesn't create an infinite loop
5) Doesn't compile, b not defined where answer is called
6) Doesn't compile, infinite loop
 */

object ArcheryExample {
  val archeryTarget =
    (circle(25) fillColor Color.red) on
      (circle(50) fillColor Color.white) on
      (circle(75) fillColor Color.red)
  val archeryStand =
    rectangle(10, 40) fillColor Color.white lineWidth (10) lineColor (Color.black) above
      rectangle(100,40) fillColor(Color.brown)
  val grassyField =
    rectangle(200, 30) fillColor(Color.green)
  val fullPicture = archeryTarget above (archeryStand above (grassyField))

  def main(args: Array[String]) = {
    fullPicture.draw
  }
}

//Decide to test this with just passing through a house width
object streetExample {
  val houseWidth = 100

  val houseRoof =
    triangle(houseWidth, houseWidth*0.75) fillColor Color.brown.spin(30.degrees)
  val homeStructure =
    rectangle(houseWidth,houseWidth / 2) fillColor(Color.red) lineColor(Color.red) above (
    rectangle(houseWidth/10, houseWidth*0.6) fillColor (Color.black) on
      rectangle(houseWidth, houseWidth*0.6) fillColor (Color.red) lineColor(Color.red))

  val homeTotal = houseRoof above homeStructure

  val treeOnStreet =
    circle(houseWidth * 0.6) fillColor (Color.green) above
      rectangle(houseWidth/10, houseWidth * 0.6) fillColor (Color.brown)

  val streetSegment =
    (rectangle(houseWidth*0.6,houseWidth/10) fillColor(Color.yellow) beside
    rectangle(houseWidth*0.6,houseWidth/10) fillColor(Color.black)) above
      rectangle(houseWidth*1.2,houseWidth/4) fillColor(Color.black) lineColor(Color.black)
  val street =
    streetSegment beside streetSegment beside streetSegment

  val oneHouse = (homeTotal beside treeOnStreet) above street

  val finalPicture = oneHouse beside oneHouse beside oneHouse

  def main(args: Array[String]) = {
    finalPicture.draw
  }
}

object printlnExample {
  val randomText = "Hello world"

  def main(args: Array[String]) = {
    println(randomText)
  }
}

object methodParameterOrder {

  def main(args: Array[String]) = {
    val test = rectangle({println("a"); 30},{println("b"); 40})
  }
}

/*
Evaluated from top tp bottom and method parameters from left to right
 */

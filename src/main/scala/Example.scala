import doodle.core._
import doodle.core.Image._
import doodle.syntax._
import doodle.jvm.Java2DCanvas._
import doodle.backend.StandardInterpreter._

// To use this example, open the SBT console and type:
//
// Example.image.draw
object Example {

  val aBox = Image.rectangle(20,20).fillColor(Color.royalBlue)
  val smallTriangle = (triangle(10, 10) fillColor Color.red.alpha(0.5.normalized))

  def lineOfTriangles(count: Int): Image = {
    val smallTriangle = (triangle(10, 10) fillColor Color.red.alpha(0.5.normalized))
    def loop(count:Int): Image =
      count match {
        case 0 => smallTriangle
        case n => val unit = loop(n-1)
          (unit beside unit)
      }
    loop(count)
  }

  val manyShapes = (lineOfTriangles(2)) on
    (rectangle(500,50) fillColor Color.blue.alpha(0.5.normalized)) above
    ((circle(100) fillColor Color.red.spin(15.degrees)) beside
      (circle(50) fillColor Color.red.spin(30.degrees)) beside
      (circle(100) fillColor Color.red.spin(15.degrees))) above
    (rectangle(500,50) fillColor Color.blue)
  def main(args:Array[String])={
    manyShapes.draw
    //lineOfTriangles(20).draw
  }
}


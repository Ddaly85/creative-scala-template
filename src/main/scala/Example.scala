import doodle.core._
import doodle.core.Image._
import doodle.syntax._
import doodle.jvm.Java2DCanvas._
import doodle.backend.StandardInterpreter._

// To use this example, open the SBT console and type:
//
// Example.image.draw
object Example {
  val image = circle(10) on circle(20) on circle(30)
  val manyShapes = (rectangle(500,50) fillColor Color.blue) above
    ((circle(100) fillColor Color.red.spin(15.degrees)) beside
    (circle(50) fillColor Color.red.spin(30.degrees)) beside
    (circle(100) fillColor Color.red.spin(15.degrees))) above
    (rectangle(500,50) fillColor Color.blue)
  def main(args:Array[String])={
    manyShapes.draw
  }
}


import doodle.core._
import doodle.core.Image._
import doodle.syntax._
import doodle.jvm.Java2DCanvas._
import doodle.backend.StandardInterpreter._

/*
Chapter 8
 */



//Coding of some examples from chapter 8
object dotExample {

 val dot = Image.circle(5).lineWidth(3).lineColor(Color.crimson)

  val squareDots =
    dot.at(0,0).
      on(dot.at(0,100)).
      on(dot.at(100,100)).
      on(dot.at(100,0))

  def main(args: Array[String]) = {
    squareDots.draw
  }
}


object geometryExample {

  def parametricCircle(angle: Angle): Point = Point.polar(200,angle)

  def sample(start: Angle, samples: Int): Image = {
    val step = Angle.one / samples
    val dot = triangle(10,10)
    def loop(count: Int): Image = {
      val angle = step * count
      count match {
        case 0 => Image.empty
        case n => dot.at(parametricCircle(angle).toVec) on loop (n-1)
      }
    }
    loop(samples)
  }

  def main(args: Array[String]) = {
    sample(45.degrees,20).draw

  }
}

object flowerExample {

  def rose(angle: Angle): Point = Point.polar((angle * 7).cos * 200, angle)

  def sample(start: Angle, samples: Int): Image = {
    val step = Angle.one / samples
    val dot = triangle(10,10)
    def loop(count: Int): Image = {
      val angle = step * count
      count match {
        case 0 => Image.empty
        case n => dot.at(rose(angle).toVec) on loop (n-1)
      }
    }
    loop(samples)
  }

  def main(args: Array[String]) = {
    sample(45.degrees,5).draw

  }
}


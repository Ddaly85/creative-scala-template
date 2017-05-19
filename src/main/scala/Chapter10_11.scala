import doodle.backend.StandardInterpreter._
import doodle.core.Image._
import doodle.core._
import doodle.jvm.Java2DCanvas._
import doodle.syntax._
import doodle.random._
import doodle.turtle._
import doodle.turtle.Instruction._


/**
 * Ch 10 and 11
 * Unfortunately I can't be in class today because of some last minute meetings, sorry about that
 */

object turtleExample {

  val instructions = List(forward(10),turn(90.degrees),
                          forward(10),turn(90.degrees))

  def main(args: Array[String]) = {
    Turtle.draw(instructions).draw
  }
}

object polygonExample {

  def polygon(sides: Int, sideLength: Double): Image = {
    val rotate = Angle.one / sides
    def inst(count: Int): List[Instruction] = {
      count match {
        case 0 => Nil
        case n => turn(rotate) :: forward(sideLength) :: inst(n-1)
      }
    }
    Turtle.draw(inst(sides))
  }

  def main(args: Array[String]) = {
    polygon(10,50).draw
  }
}

/*
I think there is an error in the solution, when I looked at the solution it is showing:
def squareSpiral(steps: int, distance, Double, angle: Angle, increment: Double): Image = {
  def iter(n: Int, distance: Double): List[Instruction] = {
    n match {
      case 0 => Nil
      case n => forward(distance) :: turn(angle) :: iter(steps-1, distance + increment)
     }
    }

   Turtle.draw(iter(steps,distance))
  }
 But you cannot use steps-1 in your case/match because it will lead to an infinite loop,
 you need to use "n" if not you are not actually ever going to hit the 0 case (Nil)

 Also, isn't it a bit strange to use distance in both the spiralSquare def and in your iter def?
 Seems to make things confusing (what is the best practice around this?)
 */
object spiralSquareExample {

  def spiralSquare(Iterations: Int, sideLength: Double, angleRot: Angle, numChg: Double): Image = {
      def loop(count: Int, sLength: Double): List[Instruction] = {
        count match {
         case 0 => Nil
         case n => forward(sLength) :: turn(angleRot) :: loop(count-1,sLength + numChg)
       }
      }
    Turtle.draw(loop(Iterations,sideLength))
  }

  def main(args: Array[String]) = {
    spiralSquare(30,20,80.degrees,0.5).draw
  }
}

object flatMapExample {

  def mapList[A](x: List[A]): List[A] = x.flatMap(a => List(a,a))

  def main(args: Array[String]) = {
    println(mapList(List(1,2,3)))
  }
}

object flatMapNothingExample {

  def mapList[A](x: List[A]): List[A] = x.flatMap(a => List())

  def main(args: Array[String]) = {
    println(mapList(List("hi","hello")))
  }
}

//Having trouble with this one

object lSystemExample {

  //using the solution in the back of the book for rewrite as I had trouble coming up with this myself
  def rewrite(instructions: List[Instruction], rule: Instruction => List[Instruction]): List[Instruction] =
  instructions.flatMap{ i =>
    i match {
      case Branch(i) =>
        List(branch(rewrite(i,rule):_*))
      case other =>
        rule(other)
    }}

  def iterate(steps: Int, seed: List[Instruction], rule: Instruction => List[Instruction]): List[Instruction] = {
    steps match {
      case 0 => seed
      case n => iterate(n-1, rewrite(seed, rule),rule)
    }
  }

   val stepSize = 10

  def rule(i: Instruction): List[Instruction] =
    i match {
      case Forward(_) => List(Forward(stepSize),Forward(stepSize))
      case NoOp => List(branch(turn(45.degrees),forward(stepSize),noop),
                        branch(turn(-45.degrees),forward(stepSize),noop))
      case other => List(other)
    }


  def main(args: Array[String]) = {
    Turtle.draw(iterate(10,List(forward(1),turn(90.degrees)),rule)).draw
  }
}

//Didn't get through this one, will have to do this and ch 11 at another time
object flatPolygon {

  def polygon(sides: Int, sideLength: Double): Image = {
    val rotate = Angle.one / sides
    def inst(count: Int): List[Instruction] = {
      count match {
        case 0 => Nil
        case n => turn(rotate) :: forward(sideLength) :: inst(n-1)
      }
    }
    Turtle.draw(inst(sides))
  }

  def main(args: Array[String]) = {
    polygon(10,50).draw
  }
}

package leetcode.design


interface Robot {
    // returns true if next cell is open and robot moves into the cell.
    // returns false if next cell is obstacle and robot stays on the current cell.
    fun move(): Boolean

    // Robot will stay on the same cell after calling turnLeft/turnRight.
    // Each turn will be 90 degrees.
    fun turnLeft()
    fun turnRight()

    // Clean the current cell.
    fun clean()
}

class Hard_489_RobotRoomCleaner {

    val m = 200
    val n = 400
    val map = Array(200) {Array(400){-1}}
    val openedCells = Array(200){0}
    var i = 100
    var j = 200
    var minI = i
    var maxI = i
    var minJ = j
    var maxJ = j

    var cDirection = Direction.UP

    var cleanedCells = 0

//    val missingTurns =

    enum class Direction(val i: Int, val j: Int) {
        UP(-1, 0),
        RIGHT(0, 1),
        DOWN(1, 0),
        LEFT(0, -1)
    }

    fun cleanRoom(robot: Robot) {

    }

    fun turnLeft(robot: Robot) {
        robot.turnLeft()
        cDirection = when (cDirection) {
            Direction.UP ->  Direction.LEFT
            Direction.DOWN -> Direction.RIGHT
            Direction.LEFT -> Direction.DOWN
            Direction.RIGHT -> Direction.UP
        }
    }

    fun cleanAndMove() {

    }
}


internal class Solution {
    // going clockwise : 0: 'up', 1: 'right', 2: 'down', 3: 'left'
    var directions = arrayOf(intArrayOf(-1, 0), intArrayOf(0, 1), intArrayOf(1, 0), intArrayOf(0, -1))
    var visited = mutableSetOf<Pair<Int, Int>>()
    var robot: Robot? = null
    fun goBack() {
        robot!!.turnRight()
        robot!!.turnRight()
        robot!!.move()
        robot!!.turnRight()
        robot!!.turnRight()
    }

    fun backtrack(row: Int, col: Int, d: Int) {
        visited.add(Pair(row, col))
        robot!!.clean()
        // going clockwise : 0: 'up', 1: 'right', 2: 'down', 3: 'left'
        for (i in 0..3) {
            val newD = (d + i) % 4
            val newRow = row + directions[newD][0]
            val newCol = col + directions[newD][1]
            if (!visited.contains(Pair(newRow, newCol)) && robot!!.move()) {
                backtrack(newRow, newCol, newD)
                goBack()
            }
            // turn the robot following chosen direction : clockwise
            robot!!.turnRight()
        }
    }

    fun cleanRoom(robot: Robot?) {
        this.robot = robot
        backtrack(0, 0, 0)
    }
}
import data.Day8Node

class Day8HauntedWasteland {

    private val START_NODE_ID = "AAA"
    private val DESTINATION_NODE_ID = "ZZZ"
    private val LEFT = 'L'
    private val RIGHT = 'R'
    private val A_CHARACTER = 'A'
    private val Z_CHARACTER = 'Z'

    fun countStepsToReachDestination(network: List<String>): Int {
        val instructions = network[0]
        val nodes = getNodes(network)
        val startNode = nodes.find { it.id == START_NODE_ID }!!
        val destinationNode = nodes.find { it.id == DESTINATION_NODE_ID }!!
        var currentNode = startNode
        var currentInstructionIndex = 0
        var path = ""
        while (currentNode != destinationNode) {
            if (currentInstructionIndex > instructions.lastIndex) currentInstructionIndex = 0
            val currentInstruction = instructions[currentInstructionIndex]
            if (currentInstruction == LEFT) {
                path += LEFT
                currentNode = currentNode.left!!
            } else {
                path += RIGHT
                currentNode = currentNode.right!!
            }
            ++currentInstructionIndex
        }
        return path.length
    }

    fun countStepsToReachDestinationAsGhost(network: List<String>): Long {
        val instructions = network[0]
        val nodes = getNodes(network)
        val startNodes = nodes.filter { it.id.endsWith(A_CHARACTER) }

        val shortestPathLengths = startNodes.map { findPathLength(instructions, it) }

        return findLCMOfListOfNumbers(shortestPathLengths)
    }

    private fun findPathLength(instructions: String, startNode: Day8Node): Long {
        var pathLength = 0L
        var currentInstructionIndex = 0
        var currentNode = startNode
        while (!currentNode.id.endsWith(Z_CHARACTER)) {
            if (currentInstructionIndex > instructions.lastIndex) currentInstructionIndex = 0
            val currentInstruction = instructions[currentInstructionIndex]
            currentNode = if (currentInstruction == LEFT) currentNode.left!! else currentNode.right!!
            ++pathLength
            ++currentInstructionIndex
        }
        return pathLength
    }

    private fun getNodes(network: List<String>): List<Day8Node> {
        val nodes = network.drop(2).map { Day8Node.fromNodeDefinition(it) }
        nodes.forEach { it.assignDestinations(nodes) }
        return nodes
    }

    private fun findLCM(a: Long, b: Long): Long {
        val larger = if (a > b) a else b
        val maxLcm = a * b
        var lcm = larger
        while (lcm <= maxLcm) {
            if (lcm % a == 0L && lcm % b == 0L) {
                return lcm
            }
            lcm += larger
        }
        return maxLcm
    }

    private fun findLCMOfListOfNumbers(numbers: List<Long>): Long {
        var result = numbers[0]
        for (i in 1..<numbers.size) {
            result = findLCM(result, numbers[i])
        }
        return result
    }
}
import data.Day8Node

class Day8HauntedWasteland {

    private val START_NODE_ID = "AAA"
    private val DESTINATION_NODE_ID = "ZZZ"
    private val LEFT = 'L'
    private val RIGHT = 'R'

    fun countStepsToReachDestination(network: List<String>): Int {
        val instructions = network[0]
        val nodes = network.drop(2).map { Day8Node.fromNodeDefinition(it) }
        nodes.forEach { it.assignDestinations(nodes) }
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
}
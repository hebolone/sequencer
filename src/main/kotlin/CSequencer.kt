class CSequencer(i : List<Int>) {
    private var mLevel = 0
    private val mSeq = i
    //  *** DATA TYPES ***
    class ListWithLevel : ArrayList<Int> {
        private val mLevel : Int
        //  *** CONSTRUCTORS ***
        constructor(iLevel : Int) {
            mLevel = iLevel
        }
        constructor(iLevel : Int, iList : List<Int>) : this(iLevel) {
            this.addAll(iList)
        }
        //  *** METHODS ***
        override fun toString() : String {
            var retValue = "[$mLevel] - "
            this.forEach { retValue += "$it, " }
            return retValue
        }
        //  *** PROPERTIES ***
        val isFinal : Boolean
            get() = this.all { it == this[0] }
    }
    //  *** METHODS ***
    fun go(iIsDebug : Boolean = false) {
        val listOfSequences = ArrayList<ListWithLevel>()
        var currentSequence = ListWithLevel(mLevel, mSeq)
        listOfSequences.add(currentSequence)
        val maxTries = 100
        if(! currentSequence.isFinal) {
            do {
                mLevel++
                currentSequence = generateSubSeq(currentSequence)
                listOfSequences.add(currentSequence)
            } while (!currentSequence.isFinal && mLevel < maxTries)
        }

        //  Step back
        //  only if last sequence has size > 1
        when(listOfSequences.last().count()) {
            1, maxTries -> printSolution(false, listOfSequences, iIsDebug)
            else -> {
                var termToAdd = 0
                listOfSequences.asReversed().forEach {
                    termToAdd += it.last()
                    it.add(termToAdd)
                }
                printSolution(true, listOfSequences, iIsDebug)
            }
        }
    }
    private fun printSolution(iSolutionFound : Boolean, iList : ArrayList<ListWithLevel>, isDebug : Boolean) {
        println(if(iSolutionFound) "Solution found: ${iList.first().last()}" else "Solution not found.")
        if(isDebug) iList.forEach { println(it) }
    }
    private fun generateSubSeq(iList : ListWithLevel) : ListWithLevel {
        val ret = ListWithLevel(mLevel)
        var firstElement = true
        iList.forEachIndexed { index, it -> if(firstElement) firstElement = false else ret.add(it - iList[index - 1]) }
        return ret
    }
}
//----------------------------------------------------------------------------------------------------------------------

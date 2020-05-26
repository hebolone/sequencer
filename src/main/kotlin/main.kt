import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.prompt

class Cli : CliktCommand() {
    private val sequence : String by option("-s", "--sequence", help="Sequence to be processed (i.e. 1,2,3,4,5)").prompt("Sequence of integers separated by commas")
    private val debug by option("-d", "--debug", help="Debug mode").flag()

    override fun run() {
        try {
            CSequencer((sequence.split(",", " ").toTypedArray().map { it.toInt() })).apply { go(debug) }
        }
        catch(exc : Exception){
            print("Invalid string")
        }
    }
}

fun main(args: Array<String>) = Cli().main(args)

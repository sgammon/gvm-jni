package com.example

import io.micronaut.configuration.picocli.PicocliRunner
import picocli.CommandLine.Command
import picocli.CommandLine.Option

@Command(name = "demo", description = ["..."],
        mixinStandardHelpOptions = true)
class DemoCommand : Runnable {

    @Option(names = ["-v", "--verbose"], description = ["..."])
    private var verbose : Boolean = false

    override fun run() {
        // business logic here
        if (verbose) {
            println("Hi!")
        }
        System.loadLibrary("nativeimpl")
        val isolateThread: Long = SportCarNative.createIsolate()
        SportCarNative.init(isolateThread)
    }

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            PicocliRunner.run(DemoCommand::class.java, *args)
        }
    }
}

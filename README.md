# MarsRobotExample
Code exercise to process robot movement instructions and determine final positions

No UI yet for this test,
Components can be tested in AndroidStudio.
Right click on app/src/test and select Run tests.
The tests can also be run via gradle: gradle testDebug and the results will be found in the following location:
MarsRobotExample/app/build/reports/tests/testDebugUnitTest/index.html

The core class is RobotPositionCommandProcessor.

We start with a list of command lines, use line 1 to define the planet bounds and drop this from the list.
We then call a tail recursive function that consumes the list 3 lines at a time until all the commands are processed.
During the recursion we build a list of results that we .joinToString() to produce the expected output.
```
fun processRobotCommandString(inputString: String): String {
val lines = inputString.lines()
val bounds = getBoundsInteractor(lines.first())

        return processRobotCommand(bounds = bounds, commands = lines.drop(1), results = emptyList())
            .joinToString(separator = "\n")
    }
```

Robot command processor class:
Again using tail recursion to process each command, followed by a bounds check function to detect loss of robot. 
Loss of robot prevents further movements.

[RobotCommandProcessor.kt](https://github.com/bennettandy/MarsRobotExample/blob/main/app/src/main/java/uk/co/avsoftware/martianrobots/domain/RobotCommandProcessor.kt)

![test_screenshot](https://github.com/bennettandy/MarsRobotExample/assets/1751538/b841c7cd-5187-45a6-be38-f1aad94bc807)

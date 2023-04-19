# Display Entities

## Overview

An example for custom blocks using Display ENtities

### Description

Thsi is a demo from another user, here: https://github.com/dannegm/BlockEntities and their implimentation so go give them some love, i could not form it for some reason so i redid it in gradle and followed their structure :)

The master branch is for the latest version of minecraft.


# [Support](https://discord.gg/volmit)

# Building

Building BlockEntities is not as Straightforward
as [Iris](https://www.spigotmc.org/resources/iris-world-gen-custom-biome-colors.84586/), though you will need to setup a
few things if your system has never been used for java development.

<details>

<summary> Build Steps </summary>

### So this is fairly similar to Iris, but a bit modified.

### IDE Builds (for development & Compilation)

You NEED TO BE USING Intelij To build this project, or anything that can support the
plugin [Manifold](https://plugins.jetbrains.com/plugin/10057-manifold)

## Preface: if you need help compiling ask for support in the [discord](https://discord.gg/volmit), we give help regardless if you want to donate to us on spigot or compile it here :) we just want to be sure that you are able to use and enjoy the software regardless of circumstance.

1. Install [Java JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
2. Set the JDK installation path to `JAVA_HOME` as an environment variable.
    * Windows
        1. Start > Type `env` and press Enter
        2. Advanced > Environment Variables
        3. Under System Variables, click `New...`
        4. Variable Name: `JAVA_HOME`
        5. Variable Value: `C:\Program Files\Java\jdk-17.0.1` (verify this exists after installing java don't just copy
           the example text)
    * MacOS
        1. Run `/usr/libexec/java_home -V` and look for Java 17
        2. Run `sudo nano ~/.zshenv`
        3. Add `export JAVA_HOME=$(/usr/libexec/java_home)` as a new line
        4. Use `CTRL + X`, then Press `Y`, Then `ENTER`
        5. Quit & Reopen Terminal and verify with `echo $JAVA_HOME`. It should print a directory

3. Setup Gradle

<details>
<summary> Gradle Setup </summary>

* Run `gradlew setup` any time you get dependency issues with craftbukkit
* Configure ITJ Gradle to use JDK 17 (in settings, search for gradle)
* Resync the project & run your newly created task (under the development folder in gradle tasks!)

</details>

4. INSTALL [MANIFOLD](https://plugins.jetbrains.com/plugin/10057-manifold)
5. If this is your first time building BlockEntities for MC 1.19+ run `gradlew setup` inside the root BlockEntities project folder.
   Otherwise, skip this step. Grab a coffee, this may take up to 5 minutes depending on your cpu & internet connection.
6. Once the project has setup, run `gradlew blockentities`
7. The BlockEntities jar will be placed in `BlockEntities/build/BlockEntities-XXX-XXX.jar` Enjoy! Consider supporting us by buying it on spigot!

</details>


## Credits

Helping out in any way you can is appreciated, and you will be listed here for your contributions :)
<details>
<summary> Language </summary>

* [NextdoorPsycho](https://github.com/NextdoorPsycho): English Translation
* [Nowhere (Armin231)](https://github.com/Armin231): German Translation

</details>
<details>
<summary> Code </summary>

* [Vatuu](https://github.com/Vatuu)
* [Cyberpwn](https://github.com/cyberpwnn)
* [NextdoorPsycho](https://github.com/NextdoorPsycho)

</details>

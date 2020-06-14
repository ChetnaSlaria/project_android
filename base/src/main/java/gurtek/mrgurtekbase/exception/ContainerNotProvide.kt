package gurtek.mrgurtekbase.exception

/**
 * * Created by Gurtek Singh on 1/30/2018.
 * Sachtech Solution
 * gurtek@protonmail.ch
 */
class ContainerNotProvide : IllegalAccessException() {

    override val message: String?
        get() = "Container id must not set before use fragment inflate"
}
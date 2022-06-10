import org.gradle.api.artifacts.dsl.DependencyHandler
import dependancies.*

fun DependencyHandler.libraries() {

    androidX()
    DaggerHilt()
    glide()
    gson()
    materialDesign()
    NavGraph()
    okHttp()
    retrofit()
    vmLifeCycle()
    googleFirebase()
    testing()
    androidPaging()
}
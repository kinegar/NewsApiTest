package dependancies

import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.testing(){
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.3")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0")
}
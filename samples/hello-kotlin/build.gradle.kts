buildscript {

    extra["kotlinVersion"] = "1.1.0-dev-1159"
    extra["repo"] = "https://repo.gradle.org/gradle/repo"

    repositories {
        maven { setUrl(extra["repo"]) }
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${extra["kotlinVersion"]}")
    }
}

apply {
    plugin("kotlin")
    plugin<ApplicationPlugin>()
}

configure<ApplicationPluginConvention> {
    mainClassName = "samples.HelloWorldKt"
}

repositories {
    maven { setUrl(extra["repo"]) }
}

dependencies {
    compile("org.jetbrains.kotlin:kotlin-stdlib:${extra["kotlinVersion"]}")
}

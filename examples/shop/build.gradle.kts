plugins {
    id("feather-server-api.java-conventions")
}

repositories {
    maven {
        name = "spigot-snapshots"
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }
    maven {
        name = "JitPack"
        url = uri("https://jitpack.io")
        content {
            includeGroup("com.github.MilkBowl")
        }
    }
}

val shopHtml: Configuration by configurations.creating {
    isCanBeConsumed = false
    isCanBeResolved = true
}

dependencies {
    compileOnly("org.bukkit:bukkit:1.8.8-R0.1-SNAPSHOT")
    compileOnly("com.github.MilkBowl:VaultAPI:1.7")

    compileOnly(project(":api"))

    shopHtml(project(":examples:shop-html", "shopHtml"))
}

tasks.withType<ProcessResources> {
    from(shopHtml)
    filesMatching("plugin.yml") {
        expand("version" to project.version)
    }
}

tasks.withType<Jar> {
    archiveBaseName.set("feather-server-api-example-shop")
    archiveClassifier.set("bukkit")
}

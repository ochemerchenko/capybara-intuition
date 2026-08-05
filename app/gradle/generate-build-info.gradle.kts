import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

tasks.register("generateBuildInfo") {
    doLast {
        val versionName = project.extra["appVersionName"] as String
        val versionCode = project.extra["appVersionCode"] as Int

        val buildTime = LocalDateTime.now()
            .format(
                DateTimeFormatter.ofPattern(
                    "yyyy-MM-dd HH:mm:ss"
                )
            )

        val buildInfo = """
# Build information

Version: $versionName

Version code: $versionCode

Build time: $buildTime
""".trimIndent()

        val output = file(
            "src/main/assets/onboarding/build_info.md"
        )

        output.parentFile.mkdirs()
        output.writeText(buildInfo)
    }
}

tasks.named("preBuild") {
    dependsOn("generateBuildInfo")
}
package ai.yaaif.jetbrains

import com.intellij.notification.NotificationType
import com.intellij.openapi.ide.CopyPasteManager
import java.awt.datatransfer.StringSelection
import java.net.JarURLConnection
import java.nio.file.Files
import java.nio.file.Paths

val YAAIF_WORKFLOWS = listOf(
  "yaaif-auth",
  "yaaif-doctor",
  "yaaif-plan-usecase",
  "yaaif-scenario",
  "yaaif-create-skill",
  "yaaif-create-mcp",
  "yaaif-create-ambient",
  "yaaif-create-file-workflow",
  "yaaif-platform-tools",
  "yaaif-platform-updates",
  "yaaif-wiki",
  "yaaif-docs",
  "yaaif-kg-enhance",
  "yaaif-kg-skill-plan",
  "yaaif-ops-support",
  "yaaif-requirements",
  "yaaif-governance",
)

private fun sortedSkillPaths(paths: List<String>): List<String> = paths.sortedWith(
  compareBy({ it != "SKILL.md" }, { it }),
)

fun loadWorkflowInstructions(skill: String): String {
  val root = object {}.javaClass.classLoader.getResource("skills/$skill")
    ?: error("Missing bundled YAAIF workflow: $skill")
  val paths = when (root.protocol) {
    "file" -> Files.walk(Paths.get(root.toURI())).use { stream ->
      stream.filter { Files.isRegularFile(it) }
        .map { Paths.get(root.toURI()).relativize(it).toString().replace('\\', '/') }
        .toList()
    }
    "jar" -> {
      val connection = root.openConnection() as JarURLConnection
      connection.jarFile.use { jar ->
        jar.entries().toList()
          .filter { !it.isDirectory && it.name.startsWith("skills/$skill/") }
          .map { it.name.removePrefix("skills/$skill/") }
      }
    }
    else -> error("Unsupported YAAIF workflow resource protocol: ${root.protocol}")
  }
  return sortedSkillPaths(paths).joinToString("\n\n") { path ->
    object {}.javaClass.classLoader.getResourceAsStream("skills/$skill/$path")
      ?.bufferedReader()?.use { it.readText() }
      ?: error("Missing bundled YAAIF workflow resource: $skill/$path")
  }
}

fun openYaaifWorkflow(skill: String) {
  val instructions = loadWorkflowInstructions(skill)
  val prompt = "Use the bundled YAAIF $skill workflow below. Ensure the YAAIF session and tenant first, use the matching yaaif_* MCP tools, and obtain explicit confirmation before reviewed apply operations.\n\n$instructions"
  CopyPasteManager.getInstance().setContents(StringSelection(prompt))
  showHandoffNotification("$skill instructions copied. Open AI Assistant and paste them to start the YAAIF workflow.", NotificationType.INFORMATION)
}

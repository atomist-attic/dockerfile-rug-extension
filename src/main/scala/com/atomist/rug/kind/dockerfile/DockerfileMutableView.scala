package com.atomist.rug.kind.dockerfile

import com.atomist.rug.kind.core.{LazyFileArtifactBackedMutableView, ProjectMutableView}
import com.atomist.rug.spi.{ExportFunction, ExportFunctionParameterDescription}
import com.atomist.source.FileArtifact

object DockerfileMutableView {

  val useful: String = "not really useful"

}

class DockerfileMutableView(
                             originalBackingObject: FileArtifact,
                             parent: ProjectMutableView,
                             dockerfile: Dockerfile
                           )
  extends LazyFileArtifactBackedMutableView(originalBackingObject, parent) {

  import DockerfileMutableView._

  override def nodeName = "Dockerfile"

  // Typically you will parse the contents of the original backing object into
  // some useful form
  private val originalContent = originalBackingObject.content
  private var _currentContent = originalContent

  override protected def currentContent: String = _currentContent

  @ExportFunction(readOnly = true, description = "Returns something useful")
  def somethingUseful: String = useful

  @ExportFunction(readOnly = true, description = "Returns something useful")
  def contents: String = currentContent

  @ExportFunction(readOnly = false, description = "Set contents of Dockerfile file to `content`")
  def setContents(
                   @ExportFunctionParameterDescription(name = "content", description = "New contents for file") content: String
                 ): Unit = _currentContent = content

}
